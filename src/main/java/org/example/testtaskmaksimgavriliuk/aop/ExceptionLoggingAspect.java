package org.example.testtaskmaksimgavriliuk.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Aspect
@Component
@Slf4j
public class ExceptionLoggingAspect {

    @AfterThrowing(pointcut = "execution(* org.example.testtaskmaksimgavriliuk..*(..))", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        log.error("Exception in method: {}", joinPoint.getSignature().getName());
        log.error("Arguments: {}", Arrays.toString(joinPoint.getArgs()));
        log.error("Exception message: {}", exception.getMessage());
    }

}