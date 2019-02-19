package com.zhz.http.intercept;

import io.netty.handler.codec.http.FullHttpRequest;

public interface Intercepter {

    boolean intercept(FullHttpRequest request);
}
