package com.imooc.controller;

import com.imooc.common.BusinessException;
import com.imooc.common.CommonResponse;
import com.imooc.common.EmBusinessError;
import com.imooc.pojo.Category;
import com.imooc.pojo.Shop;
import com.imooc.service.ICategoryService;
import com.imooc.service.IShopService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: ShopController
 * Description: TODO 前台门店服务
 * Author: Leo
 * Date: 2020/3/30-15:27
 * email 1437665365@qq.com
 */
@RestController
@RequestMapping("/shop")
@Slf4j
public class ShopController {

    @Autowired
    private IShopService shopService;
    @Autowired
    private ICategoryService categoryService;

    //推荐服务V1.0
    @RequestMapping("/recommend")
    public CommonResponse recommend(@RequestParam(value = "latitude") BigDecimal latitude,
                                    @RequestParam(value = "longitude") BigDecimal longitude) throws BusinessException {
        //如果任意一个为空
        if(latitude ==null || longitude == null){
            //抛出异常
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        List<Shop> shopModelList = shopService.recommend(latitude, longitude);
        return CommonResponse.create(shopModelList);
    }

    //搜索服务V1.0
    @RequestMapping("/search")
    public CommonResponse search(@RequestParam("latitude") BigDecimal latitude,
                                 @RequestParam("longitude") BigDecimal longitude,
                                 @RequestParam("keyword") String keyword,
                                 @RequestParam(value = "orderby",required = false) Integer orderby,
                                 @RequestParam(value = "categoryId",required = false) Integer categoryId,
                                 @RequestParam(value = "tags",required = false) String tags) throws BusinessException {
        //表示有一个为空,就抛出异常
        if(StringUtils.isEmpty(keyword) || latitude == null || longitude == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        List<Shop> shopModelList = shopService.search(latitude, longitude, keyword,orderby,categoryId,tags);
        List<Category> categoryModelList = categoryService.selectAll();

        //根据标签分组
        List<Map<String, Object>> tagsAggregation = shopService.searchGroupByTags(keyword, categoryId, tags);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("shop",shopModelList);
        resultMap.put("category",categoryModelList);
        //根据标签分组
        resultMap.put("tags",tagsAggregation);
        return CommonResponse.create(resultMap);
    }


}
