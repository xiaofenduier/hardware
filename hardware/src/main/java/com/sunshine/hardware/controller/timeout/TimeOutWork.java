package com.sunshine.hardware.controller.timeout;

import com.sunshine.hardware.model.ErrorCode;
import com.sunshine.hardware.model.ReturnBody;
import com.sunshine.hardware.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * DeferredResult 超时返回类
 * @author lyc
 *
 */
public class TimeOutWork implements Runnable {
    
    private static final Logger log = LoggerFactory.getLogger(TimeOutWork.class);
    
    
    private  String responseMapKey;
    
    /**
     * 实例化
     * @param responseMapKey
     */
    public TimeOutWork(String responseMapKey) {
        this.responseMapKey = responseMapKey;
    }

    @Override
    public void run() {
        log.info("请求指令超时key:"+responseMapKey);
        DeferredResult<Object> deferredResult=Constants.responseMap.get(responseMapKey);
        deferredResult.setResult(ReturnBody.fail(ErrorCode.REQUEST_TIMEOUT.getCode(), ErrorCode.REQUEST_TIMEOUT.getMsg()));
        Constants.responseMap.remove(responseMapKey);
    }


}
