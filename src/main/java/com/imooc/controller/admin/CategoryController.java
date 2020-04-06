package com.imooc.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.common.*;
import com.imooc.pojo.Category;
import com.imooc.pojo.Seller;
import com.imooc.requset.CategoryCreateRequest;
import com.imooc.requset.PageQuery;
import com.imooc.requset.SellerCreateRequest;
import com.imooc.service.ICategoryService;
import com.imooc.service.ISellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * ClassName: CategoryController
 * Description: TODO 运营后台品类界面
 * Author: Leo
 * Date: 2020/3/29-17:12
 * email 1437665365@qq.com
 */
@Controller("/admin/category")
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    private ISellService sellService;
    @Autowired
    private ICategoryService categoryService;

    @RequestMapping("/index")
    //品类列表
    //权限
    @AdminPermission
    public ModelAndView index(PageQuery pageQuery){
        PageHelper.startPage(pageQuery.getPage(),pageQuery.getSize());

        List<Category> categoryModelList = categoryService.selectAll();
        PageInfo<Category> pageInfo = new PageInfo<>(categoryModelList);
        pageInfo.setList(categoryModelList);

        ModelAndView modelAndView = new ModelAndView("/admin/category/index.html");
        modelAndView.addObject("data",pageInfo);
        modelAndView.addObject("CONTROLLER_NAME","category");
        modelAndView.addObject("ACTION_NAME","index");
        return modelAndView;
    }

    //添加创建商户的页面
    @RequestMapping("/createpage")
    @AdminPermission
    public ModelAndView createPage(){
        ModelAndView modelAndView = new ModelAndView("/admin/category/create.html");
        modelAndView.addObject("CONTROLLER_NAME","category");
        modelAndView.addObject("ACTION_NAME","create");
        return modelAndView;
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String create(@Valid CategoryCreateRequest request,
                         BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,
                    CommonUtil.processErrorString(bindingResult));
        }
        Category category = new Category();
        category.setName(request.getName());
        category.setIconUrl(request.getIconUrl());
        category.setSort(request.getSort());
        categoryService.create(category);
        //重定向到列表页
        return "redirect:/admin/category/index";
    }

}
