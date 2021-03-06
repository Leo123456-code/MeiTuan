package com.imooc.dao;

import com.imooc.pojo.Category;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
    //查询所有
    List<Category> selectAll();
    //查询品类个数
    Integer selectAllCount();
}