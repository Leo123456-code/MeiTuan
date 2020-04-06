package com.imooc.service;

import com.imooc.common.BusinessException;
import com.imooc.pojo.Shop;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @program: miniprogram-demo
 * @ClassName: IShopService
 * @Description: TODO
 * @Author: Leo
 * @Date: 2020/3/29-20:48
 */
public interface IShopService {
    //创建门店
    Shop create(Shop shop) throws BusinessException;
    //根据id查询门店信息
    Shop get(Integer id);
    //List查询
    List<Shop> selectAll();
    //查询门店总个数
    Integer selectAllCount();
    //推荐门店服务V1.0
    List<Shop> recommend(BigDecimal latitude, BigDecimal longitude);
    //搜索服务V1.0
    List<Shop> search(BigDecimal latitude, BigDecimal longitude,
                      String keyword,Integer orderby,Integer categoryId,String tags);
    //
    List<Map<String,Object>> searchGroupByTags(String keyword, Integer categoryId, String tags);


}
