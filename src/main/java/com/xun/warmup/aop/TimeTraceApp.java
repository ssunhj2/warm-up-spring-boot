package com.xun.warmup.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * AOP 등록
 */
@Component
@Aspect
public class TimeTraceApp {

    // 메소드 실행 측정시간
    @Around("execution(* com.xun.warmup..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        System.out.println("START: " + joinPoint.toString());

        try{
            return joinPoint.proceed();

        } finally {
            long end = System.currentTimeMillis();
            long timeMs = end - start;

            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }

    }
}
