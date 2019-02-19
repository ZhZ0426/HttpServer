package com.zhz;

import com.zhz.interfaces.Server;
import com.zhz.server.WebServer;
import com.zhz.tool.PropertiesTool;
import com.zhz.tool.ServerCollection;

import java.util.Arrays;

/**
 * @author zyl
 * @description
 * @time 2019/2/19 15:20
 */
public class ServerApplication {

    public static void main(String[] args) {
        int serverPort = Integer.parseInt(PropertiesTool.getPropertiesName("server_port"));
        ServerCollection serverCollection =
                new ServerCollection(
                        Arrays.asList(new Server[]{new WebServer(serverPort)}));
        serverCollection.startServer();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> serverCollection.stopServer()));
        System.out.println("服务端已启动");
    }
}