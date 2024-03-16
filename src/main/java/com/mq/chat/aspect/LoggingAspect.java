package com.mq.chat.aspect;

import com.mq.chat.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.marker.Markers;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    private static int MSG_COUNT = 0;

    @Before("execution(* com.mq.chat.controller..*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        if (!ObjectUtils.isEmpty(RequestContextHolder.getRequestAttributes())) {
            HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            Map<String, Object> paramMap = new HashMap<>();
            int msgCount = getMsgCount();
            httpRequest.setAttribute("msgCount", msgCount);
            for (int i = 0; i < signature.getParameterNames().length; i++) {

                if (ObjectUtils.isEmpty(joinPoint.getArgs()[i])) continue;
//            if(joinPoint.getArgs()[i].getClass().equals(AdminUser.class)) continue;

                paramMap.put(signature.getParameterNames()[i], joinPoint.getArgs()[i]);
            }
            String logInfo = httpRequest.getMethod() + " " + httpRequest.getRequestURI() + "  parameter: " + JsonUtil.marshalLog(paramMap);

            log.info(Markers.appendEntries(paramMap), "request[{}]: {}", msgCount, logInfo);
        }

    }

    private int getMsgCount() {
        MSG_COUNT++;
        if(MSG_COUNT > 100000)
            MSG_COUNT = 1;
        return MSG_COUNT;
    }
}
