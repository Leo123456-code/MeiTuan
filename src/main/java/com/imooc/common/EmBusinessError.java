package com.imooc.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName: EmBusinessError
 * Description: TODO 错误状态枚举
 * Author: Leo
 * Date: 2020/3/28-11:47
 * email 1437665365@qq.com
 */
@AllArgsConstructor
@Getter
public enum EmBusinessError {
    NO_OBJECT_FOUND(10001,"请求的对象不存在"),
    UNKNOWN_ERROR(10002,"未知错误"),
    NO_HANDLER_FOUND(10003,"找不到执行的路径操作"),
    BIND_EXCEPTION_ERROR(10004,"请求参数错误"),
    PARAMETER_VALIDATION_ERROR(10005,"请求参数校验失败"),

    //user相关
    REGISTER_DUP_FAIL(20001,"用户已存在"),

    LOGIN_FAIL(20002,"手机号或密码错误"),
    NEED_LOGIN(20003,"用户未登录,需要登录"),

    //admin相关错误
    ADMIN_SHOULD_LOGIN(30001,"管理员需要先登录"),

    //seller相关
    SELLER_NO_EXISTS(50001,"商户不存在"),

    //品类相关错误
    CATEGORY_NAME_DUPLICATED(40001,"品类名已存在"),


    ;
    private Integer errCode;
    private String errMsg;

}
