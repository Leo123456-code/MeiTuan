package com.imooc.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.common.*;
import com.imooc.pojo.Seller;
import com.imooc.requset.PageQuery;
import com.imooc.requset.SellerCreateRequest;
import com.imooc.service.ISellService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.recycler.Recycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * ClassName: SellerController
 * Description: TODO 运营后台商品界面
 * Author: Leo
 * Date: 2020/3/29-14:23
 * email 1437665365@qq.com
 */
@Controller("/admin/seller")
@RequestMapping("/admin/seller")
@Slf4j
public class SellerController {
    @Autowired
    private ISellService sellService;

    @RequestMapping("/index")
    //商户列表
    //权限
    @AdminPermission
    public ModelAndView index(PageQuery pageQuery){
        PageHelper.startPage(pageQuery.getPage(),pageQuery.getSize());
        List<Seller> sellerModelList = sellService.selectAll();
        PageInfo<Seller> pageInfo = new PageInfo<>(sellerModelList);
        pageInfo.setList(sellerModelList);

        ModelAndView modelAndView = new ModelAndView("/admin/seller/index.html");
        modelAndView.addObject("data",pageInfo);
        modelAndView.addObject("CONTROLLER_NAME","seller");
        modelAndView.addObject("ACTION_NAME","index");
        return modelAndView;
    }

    //添加创建商户的页面
    @RequestMapping("/createpage")
    @AdminPermission
    public ModelAndView createPage(){
        ModelAndView modelAndView = new ModelAndView("/admin/seller/create.html");
        modelAndView.addObject("CONTROLLER_NAME","seller");
        modelAndView.addObject("ACTION_NAME","create");
        return modelAndView;
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String create(@Valid SellerCreateRequest request,
                         BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,
                    CommonUtil.processErrorString(bindingResult));
        }
        Seller seller = new Seller();
        seller.setName(request.getName());
        sellService.create(seller);
        //重定向到列表页
        return "redirect:/admin/seller/index";
    }

    //禁用状态
    @RequestMapping(value = "down",method = RequestMethod.POST)
    @ResponseBody
    @AdminPermission
    public CommonResponse down(Integer id) throws Exception {
        Seller seller = sellService.changeStatus(id, 1);
        return CommonResponse.create(seller);
    }

    //启用状态
   @RequestMapping(value = "up",method = RequestMethod.POST)
    @ResponseBody
    @AdminPermission
    public CommonResponse up(Integer id) throws Exception {
        Seller seller = sellService.changeStatus(id, 0);
        return CommonResponse.create(seller);
    }



}
