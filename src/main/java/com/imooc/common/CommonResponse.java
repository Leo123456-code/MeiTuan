package com.imooc.common;

import lombok.Data;

/**
 * ClassName: CommonResponse
 * Description: TODO 统一页面返回
 * Author: Leo
 * Date: 2020/3/28-11:30
 * email 1437665365@qq.com
 */
@Data
public class CommonResponse {
    //表明请求的返回处理结果
    private String status;
    //若status = success时,表明对应的返回Json类数据;
    //若status = fail时,则data内将使用通用的错误码对应的格式
    private Object data;

    //定义一个通用的创建返回对象的方法
    public static CommonResponse create(Object result){
        return CommonResponse.create(result,"success");
    }

    public static CommonResponse create(Object result,String status){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(status);
        commonResponse.setData(result);
        return commonResponse;
    }

}
