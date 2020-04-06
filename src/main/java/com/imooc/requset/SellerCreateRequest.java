package com.imooc.requset;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * ClassName: SellerCreateRequest
 * Description: TODO
 * Author: Leo
 * Date: 2020/3/29-15:48
 * email 1437665365@qq.com
 */
@Data
public class SellerCreateRequest {
    @NotBlank(message = "商户名不能为空")
    private String name;
}
