package com.imooc.requset;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * ClassName: ShopCreateRequest
 * Description: TODO
 * Author: Leo
 * Date: 2020/3/29-21:58
 * email 1437665365@qq.com
 */
@Data
public class ShopCreateRequest {
    //门店名字
    @NotBlank(message = "服务名不能为空")
    private String name;
    //人均价格
    @NotNull(message = "人均价格不能为空")
    private BigDecimal pricePerMan;
    //地址信息,经度
//    @NotNull(message = "经度不能为空")
    private BigDecimal latitude;
    //纬度
//    @NotNull(message = "纬度不能为空")
    private BigDecimal longitude;
    //商品id
    @NotNull(message = "商品id不能为空")
    private Integer categoryId;
    //标签
    private String tags;
    //营业开始时间
    @NotBlank(message = "营业开始时间不能为空")
    private String startTime;
    //营业结束时间
    @NotBlank(message = "营业结束时间不能为空")
    private String endTime;
    //地址位置
    @NotBlank(message = "地址不能为空")
    private String address;
    //商户id
    @NotNull(message = "商户id不能为空")
    private Integer sellerId;
    //图片地址
    @NotBlank(message = "图片地址不能为空")
    private String iconUrl;

}
