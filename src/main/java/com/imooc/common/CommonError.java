package com.imooc.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: CommonError
 * Description: TODO
 * Author: Leo
 * Date: 2020/3/28-11:44
 * email 1437665365@qq.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonError {
    //错误码
    private Integer errCode;
    //错误描述
    private String errMsg;

    public CommonError(EmBusinessError emBusinessError){
        this.errCode = emBusinessError.getErrCode();
        this.errMsg = emBusinessError.getErrMsg();
    }


}
