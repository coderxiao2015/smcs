package com.tianwen.common.redisutil;

public class QueryContextHolder {
    private ThreadLocal<QueryContext> local=new ThreadLocal<>();

    public void setOp(int type){
         local.get().setWrite_read_op(type);
    }

    public int getOp(){
        return local.get().getWrite_read_op();
    }


}
