package com.imooc.requset;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * ClassName: LoginRequest
 * Description: TODO 登录form表单
 * Author: Leo
 * Date: 2020/3/28-18:06
 * email 1437665365@qq.com
 */
@Data
public class LoginRequest {
    //手机号
    @NotBlank(message = "手机号不能为空")
    private String telphone;
    @NotBlank(message = "密码不能为空")
    private String password;
}
