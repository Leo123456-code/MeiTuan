package com.imooc.requset;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * ClassName: CategoryCreateRequest
 * Description: TODO
 * Author: Leo
 * Date: 2020/3/29-17:18
 * email 1437665365@qq.com
 */
@Data
public class CategoryCreateRequest {
    //品类名称
    @NotBlank(message = "品类名称不能为空")
    private String name;
    //品类地址
    @NotBlank(message = "iconUrl不能为空")
    private String iconUrl;
    //排序规则
    @NotNull(message = "权重不能为空")
    private Integer sort;
}
