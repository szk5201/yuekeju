package org.yuekeju.common.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.yuekeju.common.auth.AuthSecurityAnnotation;

@Aspect
@Component
public class AuthSecurityAspect {
	
	
    @Pointcut("@annotation(org.yuekeju.common.auth.AuthSecurityAnnotation)")
    public void authSecurity(){}


    /**
     * 环绕增强，相当于MethodInterceptor
     */
    /*@Around("")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
    	
		return joinPoint;
       
    }*/

  
    @Before(value = "authSecurity()")
    public void doBeforeAdvice(JoinPoint joinPoint){
    	  ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
          HttpServletRequest request = attributes.getRequest();
          HttpServletResponse response = attributes.getResponse();
          String requestURI = request.getRequestURI();
          MethodSignature signature = (MethodSignature) joinPoint.getSignature();
          AuthSecurityAnnotation annotation = signature.getMethod().getAnnotation(AuthSecurityAnnotation.class);
          boolean auth = annotation.isAuth();
          String perms = annotation.perms();
          System.out.println("进入方法前执行.....");
          System.out.println("进入方法前执行.....是否验证"+auth);
          System.out.println("进入方法前执行.....权限"+perms);
          System.out.println("进入方法前执行.....地址"+requestURI);
    }

    /**
     * 处理完请求，返回内容
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "authSecurity()")
    public void doAfterReturning(Object ret) {
        System.out.println("方法的返回值 : " + ret);
    }

    /**
     * 后置异常通知
     */
    @AfterThrowing("authSecurity()")
    public void throwss(JoinPoint jp){
        System.out.println("方法异常时执行.....");
    }


    /**
     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     */
    @After("authSecurity()")
    public void after(JoinPoint jp){
        System.out.println("方法最后执行.....");
    }

}
