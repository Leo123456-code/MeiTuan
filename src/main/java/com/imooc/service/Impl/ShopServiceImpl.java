package com.imooc.service.Impl;

import com.imooc.common.BusinessException;
import com.imooc.common.EmBusinessError;
import com.imooc.dao.ShopMapper;
import com.imooc.pojo.Category;
import com.imooc.pojo.Seller;
import com.imooc.pojo.Shop;
import com.imooc.service.ICategoryService;
import com.imooc.service.ISellService;
import com.imooc.service.IShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ClassName: ShopServiceImpl
 * Description: TODO
 * Author: Leo
 * Date: 2020/3/29-20:48
 * email 1437665365@qq.com
 */
@Slf4j
@Service
public class ShopServiceImpl implements IShopService {
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private ISellService sellService;

    @Override
    public Shop create(Shop shop) throws BusinessException {
        /**
         * @Description //TODO 创建门店
           @Author Leo
         * @Date 20:53 2020/3/29
         * @Param [shop]
         * @return com.imooc.pojo.Shop
        */
        shop.setCreatedAt(new Date());
        shop.setUpdatedAt(new Date());
        //校验商家是否存在正确
        Seller sellerModel = sellService.get(shop.getSellerId());
        if(sellerModel == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,
                    "商户不存在");
        }
        if(sellerModel.getDisabledFlag().intValue() == 1){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,
                    "商户已禁用");
        }
        //校验类目
        Category categoryModel = categoryService.get(shop.getCategoryId());
        if(categoryModel == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,
                    "类目不存在");
        }
        int rowCount = shopMapper.insertSelective(shop);
        if(rowCount == 0){
            throw  new RuntimeException("新增门店失败");
        }
        return get(shop.getId());
    }

    @Override
    public Shop get(Integer id) {
        /**
         * @Description //TODO 根据id查询门店信息
           @Author Leo
         * @Date 20:54 2020/3/29
         * @Param [id]
         * @return com.imooc.pojo.Shop
        */
        Shop shop = shopMapper.selectByPrimaryKey(id);
        if(shop == null){
           return null;
        }
        //给Category赋值
        shop.setCategoryModel(categoryService.get(shop.getCategoryId()));
        //给Seller赋值
        shop.setSellerModel(sellService.get(shop.getSellerId()));
        return shop;
    }

    @Override
    public List<Shop> selectAll() {
        /**
         * @Description //TODO List查询
           @Author Leo
         * @Date 20:54 2020/3/29
         * @Param []
         * @return java.util.List<com.imooc.pojo.Shop>
        */
        List<Shop> shopList = shopMapper.selectAll();
        //遍历循环
        shopList.forEach(shop -> {
            shop.setCategoryModel(categoryService.get(shop.getCategoryId()));
            shop.setSellerModel(sellService.get(shop.getSellerId()));
        });
        return shopList;
    }

    @Override
    public Integer selectAllCount() {
        /**
         * @Description //TODO 查询门店总个数
           @Author Leo
         * @Date 21:33 2020/3/29
         * @Param []
         * @return java.lang.Integer
        */
        return shopMapper.selectAllCount();
    }

    @Override
    public List<Shop> recommend(BigDecimal latitude, BigDecimal longitude) {
        /**
         * @Description //TODO 搜索推荐服务
           @Author Leo
         * @Date 15:39 2020/3/30
         * @Param [latitude, longitude]
         * @return java.util.List<com.imooc.pojo.Shop>
        */
        List<Shop> shopModelList = shopMapper.recommend(latitude, longitude);
        shopModelList.forEach(shop -> {
            shop.setSellerModel(sellService.get(shop.getSellerId()));
            shop.setCategoryModel(categoryService.get(shop.getCategoryId()));
        });

        return shopModelList;
    }

    @Override
    public List<Shop> search(BigDecimal latitude, BigDecimal longitude, String keyword,
                             Integer orderby,Integer categoryId,String tgas) {
        /**
         * @Description //TODO 搜索服务V1.0
           @Author Leo
         * @Date 20:12 2020/3/30
         * @Param [latitude, longitude, keyword]
         * @return java.util.List<com.imooc.pojo.Shop>
        */
        List<Shop> shopModelList = shopMapper.search(latitude, longitude, keyword,orderby,categoryId,tgas);
        shopModelList.forEach(shop -> {
            shop.setCategoryModel(categoryService.get(shop.getCategoryId()));
            shop.setSellerModel(sellService.get(shop.getSellerId()));
        });
        return shopModelList;
    }

    @Override
    public List<Map<String, Object>> searchGroupByTags(String keyword, Integer categoryId, String tags) {
        return shopMapper.searchGroupByTags(keyword,categoryId,tags);
    }
}
