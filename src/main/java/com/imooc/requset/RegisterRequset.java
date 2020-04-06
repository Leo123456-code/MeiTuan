package com.imooc.requset;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * ClassName: RegisterRequset
 * Description: TODO
 * Author: Leo
 * Date: 2020/3/28-16:32
 * email 1437665365@qq.com
 */
@Data
public class RegisterRequset {

    //手机号
    @NotBlank(message = "手机号不能为空")
    private String telphone;
    @NotBlank(message = "密码不能为空")
    private String password;
    //昵称
    @NotBlank(message = "昵称不能为空")
    private String nickName;
    //性别
    @NotNull(message = "性别不能为空")
    private Integer gender;
}
