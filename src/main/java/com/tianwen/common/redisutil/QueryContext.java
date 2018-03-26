package com.tianwen.common.redisutil;

/*定义redis集群上下文读写操作*/
public class QueryContext {
    private  int write_read_op=1; //默认为1 mater 读写操作 2 为slave只读操作

    public int getWrite_read_op() {
        return write_read_op;
    }

    public void setWrite_read_op(int write_read_op) {
        this.write_read_op = write_read_op;
    }
}
