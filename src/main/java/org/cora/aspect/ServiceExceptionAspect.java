package org.cora.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.cora.constant.ReturnEnum;
import org.springframework.stereotype.Component;

/**
 * @author Colin
 */
@Component
@Aspect
public class ServiceExceptionAspect {
    private static final Logger LOGGER = Logger.getLogger(ServiceExceptionAspect.class);

    @Pointcut("execution(* org.cora.service.*.*(..))")
    public void serviceMethodPointcut() {
    }

    @Around("serviceMethodPointcut()")
    public JSONObject aroundServiceMethod(ProceedingJoinPoint joinPoint) {
        try {
            Object result = joinPoint.proceed();
            return (JSONObject) result;
        } catch (Exception e) {
            LOGGER.error(joinPoint.getSignature().getName() + " error!\n" + JSON.toJSONString(joinPoint.getArgs()), e);
            return ReturnEnum.ERROR.toJSONObject();
        } catch (Throwable t) {
            LOGGER.fatal(joinPoint.getSignature().getName() + " error!\n" + JSON.toJSONString(joinPoint.getArgs()), t);
            return ReturnEnum.ERROR.toJSONObject();
        }
    }
}
