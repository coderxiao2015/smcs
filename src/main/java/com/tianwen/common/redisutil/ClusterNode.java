package com.tianwen.common.redisutil;

/*集群的节点*/
public class ClusterNode {

    /*节点ip*/
    private String host;

    /*节点端口*/
    private int port;

    /*起始槽*/
   private long startSlot;

    /*终止槽*/
   private long endSlot;

   /*父节点id 没有父节点id初始化为0*/
    private String parentId;

    /*当前节点id*/
    private String currentId;

    /*节点名称*/
    private String nodeName;

    /*节点connected状态*/
    private String connected;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public long getStartSlot() {
        return startSlot;
    }

    public void setStartSlot(long startSlot) {
        this.startSlot = startSlot;
    }

    public long getEndSlot() {
        return endSlot;
    }

    public void setEndSlot(long endSlot) {
        this.endSlot = endSlot;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCurrentId() {
        return currentId;
    }

    public void setCurrentId(String currentId) {
        this.currentId = currentId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getConnected() {
        return connected;
    }

    public void setConnected(String connected) {
        this.connected = connected;
    }
}
