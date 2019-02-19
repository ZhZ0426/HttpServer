package com.zhz.tool;

import com.zhz.http.intercept.Intercepter;
import com.zhz.http.intercept.TokenIntercepter;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import io.netty.handler.codec.http.FullHttpRequest;

public class InterceptorCollection {
    static List<Intercepter> intercepterList =
            Arrays.asList(new Intercepter[]{new TokenIntercepter()});

    public static boolean interceptor(FullHttpRequest request) {
        for (Iterator<Intercepter> iterator = intercepterList.iterator(); iterator.hasNext(); ) {
            if (!iterator.next().intercept(request)) {
                return false;
            }
        }
        return true;
    }
}
