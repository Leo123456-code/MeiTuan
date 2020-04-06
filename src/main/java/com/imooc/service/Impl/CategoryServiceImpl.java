package com.imooc.service.Impl;

import com.imooc.common.BusinessException;
import com.imooc.common.EmBusinessError;
import com.imooc.dao.CategoryMapper;
import com.imooc.pojo.Category;
import com.imooc.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * ClassName: CategoryServiceImpl
 * Description: TODO 品类接口实现类
 * Author: Leo
 * Date: 2020/3/29-16:56
 * email 1437665365@qq.com
 */
@Slf4j
@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    @Transactional
    public Category create(Category category) throws BusinessException {
        /**
         * @Description //TODO 创建品类
           @Author Leo
         * @Date 17:00 2020/3/29
         * @Param [category]
         * @return com.imooc.pojo.Category
        */
        category.setCreatedAt(new Date());
        category.setUpdatedAt(new Date());
        try {
            categoryMapper.insertSelective(category);
        }catch (Exception e){
            throw new BusinessException(EmBusinessError.CATEGORY_NAME_DUPLICATED);
        }

        return get(category.getId());
    }

    @Override
    public Category get(Integer id) {
        /**
         * @Description //TODO 根据id查询
           @Author Leo
         * @Date 17:00 2020/3/29
         * @Param [id]
         * @return com.imooc.pojo.Category
        */
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Category> selectAll() {
        /**
         * @Description //TODO list查询所有
           @Author Leo
         * @Date 17:00 2020/3/29
         * @Param []
         * @return java.util.List<com.imooc.pojo.Category>
        */
        return categoryMapper.selectAll();
    }

    @Override
    public Integer selectCountAll() {
        /**
         * @Description //TODO 查询所有品类个数
           @Author Leo
         * @Date 17:28 2020/3/29
         * @Param []
         * @return java.lang.Integer
        */
        return categoryMapper.selectAllCount();
    }
}
