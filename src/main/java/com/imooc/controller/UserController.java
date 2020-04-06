package com.imooc.controller;

import com.imooc.common.*;
import com.imooc.pojo.User;
import com.imooc.requset.LoginRequest;
import com.imooc.requset.RegisterRequset;
import com.imooc.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * ClassName: UserController
 * Description: TODO 前台用户界面接入
 * Author: Leo
 * Date: 2020/3/28-11:12
 * email 1437665365@qq.com
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/get")
    public CommonResponse getUser(@RequestParam("id") Integer id) throws BusinessException {
        User user = userService.getUser(id);
        if(user == null){
//           return CommonResponse.create(new CommonError(EmBusinessError.NO_OBJECT_FOUND),"fail");
            throw new BusinessException(EmBusinessError.NO_OBJECT_FOUND);
        }
        return CommonResponse.create(user);

    }

    @GetMapping("/index")
    public ModelAndView index(){
        String userName = "xzc";
        ModelAndView modelAndView = new ModelAndView("/index.html");
        //把xzc 传到 index 页面上
        modelAndView.addObject("name",userName);
        return modelAndView;
    }

    //注册
    @PostMapping("/register")
    public CommonResponse register(@Valid @RequestBody RegisterRequset requset,
                                   BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
           throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,
                   CommonUtil.processErrorString(bindingResult)) ;
        }
        User registerUser = new User();
        registerUser.setTelphone(requset.getTelphone());
        registerUser.setPassword(requset.getPassword());
        registerUser.setNickName(requset.getNickName());
        registerUser.setGender(requset.getGender());
        User user = userService.register(registerUser);
        return CommonResponse.create("注册成功");
    }

    //登录
    @RequestMapping("/login")
    public CommonResponse login(@Valid @RequestBody LoginRequest request,
                                BindingResult bindingResult, HttpSession session) throws Exception {
        if(bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,
            CommonUtil.processErrorString(bindingResult));
        }
        User user = userService.login(request.getTelphone(), request.getPassword());
        //设置session
        session.setAttribute(Const.CURRENT_USER_SESSION,user);
        return CommonResponse.create(user);
    }


    //获取当前用户信息
    @RequestMapping("/getcurrentuser")
    public CommonResponse getCurrentuser(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER_SESSION);
        if(user == null){
            CommonError commonError = new CommonError(EmBusinessError.NEED_LOGIN);
            return CommonResponse.create(commonError,"fail");
        }
        return CommonResponse.create(user);
    }

    //登出
    @RequestMapping("/logout")
    public CommonResponse logout(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER_SESSION);
        if(user == null){
            CommonError commonError = new CommonError(EmBusinessError.NEED_LOGIN);
            return CommonResponse.create(commonError,"fail");
        }
        session.removeAttribute(Const.CURRENT_USER_SESSION);
        return CommonResponse.create("登出成功");
    }



}
