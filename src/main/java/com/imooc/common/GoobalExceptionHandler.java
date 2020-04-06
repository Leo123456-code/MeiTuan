package com.imooc.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: GoobalExceptionHandler
 * Description: TODO
 * Author: Leo
 * Date: 2020/3/28-12:13
 * email 1437665365@qq.com
 */
//所有执行的controller都会被这个切面所包含
@ControllerAdvice
@Slf4j
public class GoobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResponse doError(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse,
                                 Exception ex ){

        log.error("异常原因={}",ex);
        if(ex instanceof BusinessException){
            return CommonResponse.create(((BusinessException)ex).getCommonError(),"fail");
            //找不到路径
        }else if(ex instanceof NoHandlerFoundException){
            CommonError commonError = new CommonError(EmBusinessError.NO_HANDLER_FOUND);
            return CommonResponse.create(commonError,"fail");
            //请求参数错误
        }else if(ex instanceof ServletRequestBindingException){
            CommonError commonError = new CommonError(EmBusinessError.BIND_EXCEPTION_ERROR);
            return CommonResponse.create(commonError,"fail");
        } else {
            //未知错误
            CommonError commonError = new CommonError(EmBusinessError.UNKNOWN_ERROR);
            return CommonResponse.create(commonError,"fail");
        }
    }

    //针对404,需要配置
//    spring.resources.add-mappings=false
//    spring.mvc.throw-exception-if-no-handler-found=true

}
