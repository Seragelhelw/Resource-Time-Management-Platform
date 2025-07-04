package com.example.asweprj.demo.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class PerformanceMonitoringAspect {

    private static final Logger logger = LoggerFactory.getLogger(PerformanceMonitoringAspect.class);

    @Around("execution(* com.example.asweprj.demo.controllers.AuthController.register(..))")
    public Object monitorRegisterPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.nanoTime();
        Object result = joinPoint.proceed();
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        logger.info("Register method executed in {} nanoseconds.", duration);

        return result;
    }

    @Around("execution(* com.example.asweprj.demo.controllers.AuthController.login(..))")
    public Object monitorLoginPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.nanoTime();
        Object result = joinPoint.proceed();
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        logger.info("Login method executed in {} nanoseconds.", duration);

        return result;
    }

    @Around("execution(* com.example.asweprj.demo.controllers.AuthController.logout(..))")
    public Object monitorLogoutPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.nanoTime();
        Object result = joinPoint.proceed();
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        logger.info("Logout method executed in {} nanoseconds.", duration);

        return result;
    }
}
