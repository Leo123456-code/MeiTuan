package com.imooc.controller.admin;


import com.imooc.common.AdminPermission;
import com.imooc.common.BusinessException;
import com.imooc.common.Const;
import com.imooc.common.EmBusinessError;
import com.imooc.requset.AdminLoginRequest;
import com.imooc.service.ICategoryService;
import com.imooc.service.ISellService;
import com.imooc.service.IShopService;
import com.imooc.service.IUserService;
import com.imooc.util.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;


/**
 * ClassName: AndinController
 * Description: TODO 运营后台用户界面
 * Author: Leo
 * Date: 2020/3/29-10:11
 * email 1437665365@qq.com
 */
@Controller("/admin/admin")
@RequestMapping("/admin/admin")
@Slf4j
public class AndinController {

    @Value("${admin.encryptEmail}")
    private String encryptEmail;
    @Value("${admin.encryptPassword}")
    private String encryptPassword;

    @Autowired
    private IUserService userService;
    @Autowired
    private ISellService sellService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IShopService shopService;



    @RequestMapping("/index")
    //切面控制
    @AdminPermission
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("/admin/admin/index");
        //查询所有注册用户数量 userService.countAllFourUser()
        modelAndView.addObject("userCount",userService.countAllFourUser());
        modelAndView.addObject("sellerCount",sellService.selectAllFourSell());
        modelAndView.addObject("categoryCount",categoryService.selectCountAll());
        modelAndView.addObject("shopCount",shopService.selectAllCount());
        modelAndView.addObject("CONTROLLER_NAME","admin");
        modelAndView.addObject("ACTION_NAME","index");
        return modelAndView;
    }

    @RequestMapping("/loginpage")
    public ModelAndView loginPage(){
        ModelAndView modelAndView = new ModelAndView("/admin/admin/login");
        return modelAndView;
    }

    //登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam(name="email")String email,
                        @RequestParam(name="password")String password , HttpSession session) throws Exception {


        log.info("encryptEmail={},encryptPassword={}",encryptEmail,encryptPassword);
        //非空校验
        if(StringUtils.isEmpty(email) || StringUtils.isEmpty(password)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"用户名,密码不能为空");
        }
        if(email.equals(this.encryptEmail) && Md5Util.encodeByMd5(password).equals(this.encryptPassword)){
            //登录成功,将email放入内存
            session.setAttribute(Const.CURRENT_ADMIN_SESSION,email);
            //重定向 到运营后台首页
            return "redirect:/admin/admin/index";

        }else {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"用户名或密码错误");
        }
    }
}
