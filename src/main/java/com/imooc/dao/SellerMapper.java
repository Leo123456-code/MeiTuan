package com.imooc.dao;

import com.imooc.pojo.Seller;

import java.util.List;

public interface SellerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Seller record);

    int insertSelective(Seller record);

    Seller selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Seller record);

    int updateByPrimaryKey(Seller record);
    //查询所有
    List<Seller> selectAll();
    //查询所有的商户数量
    Integer selectAllFourSeller();
}