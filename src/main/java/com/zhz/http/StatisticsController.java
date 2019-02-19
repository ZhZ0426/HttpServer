package com.zhz.http;


import com.zhz.tool.StatisticsCollection;

@Rest("count")
public class StatisticsController {
    @GET("getAll")
    public Object findAll() {
        return StatisticsCollection.getBytes();
    }
}
