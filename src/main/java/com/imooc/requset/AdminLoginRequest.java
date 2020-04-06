package com.imooc.requset;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * ClassName: AdminLoginRequest
 * Description: TODO
 * Author: Leo
 * Date: 2020/3/29-10:48
 * email 1437665365@qq.com
 */
@Data
public class AdminLoginRequest {
    @NotBlank(message = "邮箱不能为空")
    private String email;
    @NotBlank(message = "密码不能为空")
    private String password;
}
