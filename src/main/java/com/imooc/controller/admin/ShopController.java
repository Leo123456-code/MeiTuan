package com.imooc.controller.admin;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.common.AdminPermission;
import com.imooc.common.BusinessException;
import com.imooc.common.CommonUtil;
import com.imooc.common.EmBusinessError;
import com.imooc.pojo.Shop;
import com.imooc.requset.PageQuery;
import com.imooc.requset.ShopCreateRequest;
import com.imooc.service.ICategoryService;
import com.imooc.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * ClassName: ShopController
 * Description: TODO 运营后台门店界面
 * Author: Leo
 * Date: 2020/3/29-20:49
 * email 1437665365@qq.com
 */
@Controller("/admin/shop")
@RequestMapping("/admin/shop")
public class ShopController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IShopService shopService;

    @RequestMapping("/index")
    //门店列表
    //权限
    @AdminPermission
    public ModelAndView index(PageQuery pageQuery){

        PageHelper.startPage(pageQuery.getPage(),pageQuery.getSize());
        List<Shop> shopList = shopService.selectAll();
        PageInfo<Shop> pageInfo = new PageInfo<>(shopList);
        pageInfo.setList(shopList);

        ModelAndView modelAndView = new ModelAndView("/admin/shop/index.html");
        modelAndView.addObject("data",pageInfo);
        modelAndView.addObject("CONTROLLER_NAME","shop");
        modelAndView.addObject("ACTION_NAME","index");
        return modelAndView;
    }

    //添加创建商户的页面
    @RequestMapping("/createpage")
    @AdminPermission
    public ModelAndView createPage(){
        ModelAndView modelAndView = new ModelAndView("/admin/shop/create.html");
        modelAndView.addObject("CONTROLLER_NAME","shop");
        modelAndView.addObject("ACTION_NAME","create");
        return modelAndView;
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String create(@Valid ShopCreateRequest request,
                         BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,
                    CommonUtil.processErrorString(bindingResult));
        }
        Shop shop = new Shop();
        shop.setIconUrl(request.getIconUrl());
        shop.setAddress(request.getAddress());
        shop.setCategoryId(request.getCategoryId());
        shop.setEndTime(request.getEndTime());
        shop.setStartTime(request.getStartTime());
        shop.setLongitude(request.getLongitude());
        shop.setLatitude(request.getLatitude());
        shop.setName(request.getName());
        shop.setPricePerMan(request.getPricePerMan());
        shop.setSellerId(request.getSellerId());
        shop.setTags(request.getTags());
        shopService.create(shop);
        //重定向到列表页
        return "redirect:/admin/shop/index";
    }

}
