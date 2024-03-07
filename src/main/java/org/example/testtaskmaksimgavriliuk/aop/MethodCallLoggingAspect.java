package org.example.testtaskmaksimgavriliuk.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;


@Aspect
@Component
@Slf4j
public class MethodCallLoggingAspect {


    @Pointcut("execution(* org.example.testtaskmaksimgavriliuk..*(..))")
    public void allMethods() {
    }

    @Around("allMethods()")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName = joinPoint.getSignature().getName();
        String returnType = joinPoint.getSignature().toShortString().split(" ")[0];

        log.info("Method {} called with arguments: {}", methodName, Arrays.toString(joinPoint.getArgs()));

        LocalDateTime startTime = LocalDateTime.now();
        Object result = joinPoint.proceed();
        LocalDateTime endTime = LocalDateTime.now();

        log.info("Method {} {} returned {} in {} milliseconds", returnType, methodName, result, calculateExecutionTime(startTime, endTime));

        return result;

    }

    private long calculateExecutionTime(LocalDateTime startTime, LocalDateTime endTime) {
        return Duration.between(startTime, endTime).toMillis();
    }

}