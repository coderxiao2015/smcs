package com.tianwen.common.redisutil;

public class QueryContextHolder {
    private ThreadLocal<QueryContext> local=new ThreadLocal<>();

    public int getOp(int type){
        QueryContext queryContext=local.get();
        if(queryContext==null){
            QueryContext context=new QueryContext();
            context.setWrite_read_op(type);
            local.set(context);
        }
        return local.get().getWrite_read_op();
    }


}
