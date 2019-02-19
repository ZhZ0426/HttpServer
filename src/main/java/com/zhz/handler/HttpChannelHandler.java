package com.zhz.handler;

import com.zhz.http.ControllerManager;
import com.zhz.tool.InterceptorCollection;
import com.zhz.tool.UrlTools;

import java.util.Map;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.QueryStringDecoder;

public class HttpChannelHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String responseStr = null;
        if (msg instanceof HttpRequest) {

            // 请求，解码器将请求转换成HttpRequest对象
            FullHttpRequest request = (FullHttpRequest) msg;
            // 获取请求参数
            if (InterceptorCollection.interceptor(request)) {
                QueryStringDecoder queryStringDecoder = new QueryStringDecoder(request.getUri());
                System.out.println("uri" + queryStringDecoder.uri());
                System.out.println("path" + queryStringDecoder.path());
                String url = queryStringDecoder.path().substring(1);
                if (UrlTools.jedgeUrl(url)) {
                    String[] urls = url.split("/");
                    Map<String, String> map = UrlTools.parse(request);
                    responseStr = new ControllerManager().handler(urls[0], urls[1], map).toString();
                } else {
                    responseStr = "错误的请求";
                }
            } else {
                responseStr = "";
            }

            System.out.println("返回的包体" + responseStr);

            // 响应HTML

            byte[] responseBytes = responseStr.getBytes("UTF-8");
            int contentLength = responseBytes.length;

            // 构造FullHttpResponse对象，FullHttpResponse包含message body
            FullHttpResponse response =
                    new DefaultFullHttpResponse(
                            HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(responseBytes));
            response.headers().set("Content-Type", "text/html; charset=utf-8");
            response.headers().set("Content-Length", Integer.toString(contentLength));
            response.headers().set("Access-Control-Allow-Origin", "*");
            response
                    .headers()
                    .set(
                            "Access-Control-Allow-Headers",
                            "Origin, X-Requested-With, Content-Type, Accept,X-Token");
            response.headers().set("Access-Control-Allow-Methods", "GET, POST,PUT,DELETE,OPTIONS");

            ctx.writeAndFlush(response);
        }
    }
}
