package com.imooc.controller;

import com.imooc.common.CommonResponse;
import com.imooc.pojo.Category;
import com.imooc.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * ClassName: CategoryController
 * Description: TODO 前台品类页面接入
 * Author: Leo
 * Date: 2020/3/29-19:38
 * email 1437665365@qq.com
 */
@RequestMapping("/category")
@Controller("/category")
@Slf4j
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @ResponseBody
    @GetMapping ("/list")
    public CommonResponse list(){
        List<Category> categoryList = categoryService.selectAll();
        return CommonResponse.create(categoryList);
    }
}

