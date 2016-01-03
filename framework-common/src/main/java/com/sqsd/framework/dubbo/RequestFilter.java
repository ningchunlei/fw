package com.sqsd.framework.dubbo;

import com.alibaba.dubbo.rpc.*;
import com.sqsd.framework.log.RequestId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2015/4/29.
 */
public class RequestFilter implements Filter{
    static Logger logger = LoggerFactory.getLogger(RequestFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        if(RpcContext.getContext().isConsumerSide()) {
            String id = RequestId.getRequestId();
            RpcContext.getContext().setAttachment("requestId",id);
        }else if(RpcContext.getContext().isProviderSide()){
            String id = RpcContext.getContext().getAttachment("requestId");
            RequestId.setRequestId(id);
        }
        logger.debug("call:In,method:{}.{},request:{}",invoker.getInterface(),invocation.getMethodName(),invocation.getArguments());
        Result result = invoker.invoke(invocation);
        RpcContext.getContext().removeAttachment("requestId");
        logger.debug("call:Out,method:{}.{},request:{}",invoker.getInterface(),invocation.getMethodName(),result);
        return result;
    }
}
