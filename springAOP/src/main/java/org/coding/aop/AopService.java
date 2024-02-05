package org.coding.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@EnableAspectJAutoProxy()
@Aspect
@Component
public class AopService {
    @Pointcut("execution(* org.coding.service.MyServiceImpl.*(..))")
    public void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around before");
        Object proceed = joinPoint.proceed();
        System.out.println("around after");
        return proceed;
    }

    @Before("pointCut()")
    public void before() {
        System.out.println("before");
    }

    @After("pointCut()")
    public void after() {
        System.out.println("after");
    }

    @AfterReturning("pointCut()")
    public void afterRunning() {
        System.out.println("afterRunning");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        System.out.println("afterThrowing");
    }

}
