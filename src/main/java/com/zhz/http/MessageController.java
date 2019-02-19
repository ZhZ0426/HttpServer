package com.zhz.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Rest("message")
public class MessageController {

    @GET("get")
    public Object test() {
        List<Map> list = new ArrayList<Map>();
        return list;
    }
}
