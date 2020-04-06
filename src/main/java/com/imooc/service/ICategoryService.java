package com.imooc.service;

import com.imooc.common.BusinessException;
import com.imooc.pojo.Category;

import java.util.List;

/**
 * @program: miniprogram-demo
 * @ClassName: ICategoryService
 * @Description: TODO 品类接口
 * @Author: Leo
 * @Date: 2020/3/29-16:56
 */
public interface ICategoryService {
    //创建品类
    Category create(Category category) throws BusinessException;
    //根据id查询
    Category get(Integer id);
    //list查询
    List<Category> selectAll();
    //查询所有品类个数
    Integer selectCountAll();

}
