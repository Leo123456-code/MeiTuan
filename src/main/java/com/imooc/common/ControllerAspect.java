package com.imooc.common;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * ClassName: ControllerAspect
 * Description: TODO
 * Author: Leo
 * Date: 2020/3/29-11:46
 * email 1437665365@qq.com
 */
@Aspect
@Configuration
public class ControllerAspect {
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private HttpServletResponse httpServletResponse;
    //调用AdminController之前的校验
    @Around("execution(* com.imooc.controller.admin.*.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping) )")
    public Object adminControllerBeforeValidation(ProceedingJoinPoint joinPoint) throws Throwable {
        //拿到函数签名
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        AdminPermission adminPermission = method.getAnnotation(AdminPermission.class);
        if(adminPermission == null){
            Object resultObject = joinPoint.proceed();
            return resultObject;
        }
        //判断当前管理员是否登录
        String email = (String) httpServletRequest.getSession().getAttribute(Const.CURRENT_ADMIN_SESSION);
        if(email == null){
            if(adminPermission.produceType().equals("text/html")){
                //重定向到登录页面
                httpServletResponse.sendRedirect("/admin/admin/loginpage");
                return null;
            }else {
                CommonError commonError = new CommonError(EmBusinessError.ADMIN_SHOULD_LOGIN);
                return CommonResponse.create(commonError,"fail");
            }

        }else {
            Object resultObject = joinPoint.proceed();
            return resultObject;
        }
    }
}
