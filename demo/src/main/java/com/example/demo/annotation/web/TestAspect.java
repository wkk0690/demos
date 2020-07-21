package com.example.demo.annotation.web;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @ClassName TestAspect
 * http://localhost:8080/add1?deviceId=1
 **/
@Aspect //声明当前的类就是一个切面
@Component
public class TestAspect {

    //自定义切点表达式
//    @Pointcut("execution(* *.s*(..))")
//    private void myPointCut(){}
//    @Pointcut("execution(* *.s*(..))")
//    private void myPointCut2(){}
    @Pointcut("@annotation(com.example.demo.annotation.web.MyAnnotation)")
    public void addAdvice() {
    }

    //前置通知
    @Before("addAdvice()")
    public void before(JoinPoint joinPoint) {
        System.out.println("-----------前置通知----------");
        Signature signature = joinPoint.getSignature();
        String name = signature.getName(); //方法名
        System.out.println("方法名: " + name);
        MethodSignature sign = (MethodSignature) joinPoint.getSignature();
        Method method = sign.getMethod();
        MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
        System.out.println("注解值: " + annotation.value());
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            String deviceId = (String) args[0];
            System.out.println("参数: " + deviceId);
        }
        System.out.println("-------------------------------------------");
    }

    //后置通知
    @AfterReturning(value = "addAdvice()", returning = "value")
    public void afterReturning(JoinPoint joinPoint, Object value) {
        System.out.println("--------------后置通知----------------");
        System.out.println("返回值: " + value);
        System.out.println("-------------------------------------------");
    }

    //环绕通知
    @Around("addAdvice()")
    public Object arount(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕前...");
        Object value = joinPoint.proceed();
        System.out.println("环绕后...");
        return value;
    }

    //异常抛出通知
    @AfterThrowing(value = "addAdvice()", throwing = "e")
    public void afterThroing(JoinPoint joinpoint, Throwable e) {
        System.out.println("异常通知..." + e);
    }

    //最终通知
    @After("addAdvice()")
    public void after() {
        System.out.println("最终通知...");
    }
}
