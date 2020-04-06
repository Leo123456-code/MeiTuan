package com.imooc.common;

import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * ClassName: CommonUtil
 * Description: TODO
 * Author: Leo
 * Date: 2020/3/28-17:18
 * email 1437665365@qq.com
 */
@Data
public class CommonUtil {

    public static String processErrorString(BindingResult bindingResult){
        //如果没有异常
        if(!bindingResult.hasErrors()){
            //返回空字符串
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            //将异常原因拼接
            stringBuilder.append(fieldError.getDefaultMessage()+",");
        }
        return stringBuilder.substring(0,stringBuilder.length()-1);
    }
}
