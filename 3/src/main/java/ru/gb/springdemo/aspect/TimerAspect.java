package ru.gb.springdemo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TimerAspect {

    @Pointcut("within(@ru.gb.springdemo.aspect.ITimer *")
    public void timerPointCut() {

    }
    @Pointcut("@annotation(@ru.gb.springdemo.aspect.ITimer")
    public void timerPointCut2() {

    }

    @Around("timerPointCut() || timerPointCut2")
    public Object timerAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        log.info("target = {}", joinPoint.getTarget().getClass());
        log.info("method = {}", joinPoint.getSignature().getName());
        try {
            Object returnValue = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            log.info("result = {}", returnValue);
            log.info("complitedFor = {}", elapsedTime);
            return returnValue;
        } catch (Throwable e) {
            log.info("exception = [{}, {}]", e.getClass(), e.getMessage());
            throw e;
        }
    }

}
