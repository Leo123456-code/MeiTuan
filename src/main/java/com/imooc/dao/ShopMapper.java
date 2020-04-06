package com.imooc.dao;

import com.imooc.pojo.Shop;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ShopMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shop record);

    int insertSelective(Shop record);

    Shop selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);
    //查询所有
    List<Shop> selectAll();
    //查询门店总个数
    Integer selectAllCount();
    //推荐服务V1.0
    List<Shop> recommend(@Param("latitude") BigDecimal latitude,@Param("longitude") BigDecimal longitude);
    //搜索服务V1.0
    List<Shop> search(@Param("latitude") BigDecimal latitude,@Param("longitude") BigDecimal longitude,@Param("keyword") String keyword,
                      @Param("orderby") Integer orderby,@Param("categoryId") Integer categoryId,
                      @Param("tags") String tags);
    //根据标签分组
    List<Map<String,Object>> searchGroupByTags(@Param("keyword") String keyword,@Param("categoryId") Integer categoryId,
                                               @Param("tags") String tags);
}