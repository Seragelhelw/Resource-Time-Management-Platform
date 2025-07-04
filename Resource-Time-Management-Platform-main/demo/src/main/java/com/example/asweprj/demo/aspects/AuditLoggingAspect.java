package com.example.asweprj.demo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AuditLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(AuditLoggingAspect.class);

    @Before("execution(* com.example.asweprj.demo.controllers.*.*(..))")
    public void logActivity(JoinPoint joinPoint) {
        // Get the logged-in user's username using SecurityContextHolder
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Get the method details (method name and parameters)
        String methodName = joinPoint.getSignature().getName();
        String parameters = Arrays.toString(joinPoint.getArgs());

        // Log audit information to the app.log file
        String logMessage = String.format("User %s executed method %s with parameters %s", username, methodName, parameters);
        logger.info(logMessage);  // This will write to app.log as per the Logback configuration
    }
}
