package com.zhz.tool;

import com.zhz.interfaces.Server;

import java.util.List;

/**
 * @author zyl
 * @description
 * @time 2019/2/19 15:49
 */
public class ServerCollection {
    private List<Server> serverList;

    public ServerCollection(List<Server> serverList) {
        this.serverList = serverList;
    }

    public void startServer() {
        serverList.forEach(server -> server.start());
    }

    public void stopServer() {
        serverList.forEach(server -> server.stop());
    }
}