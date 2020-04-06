package com.imooc.service;

import com.imooc.pojo.User;

/**
 * @program: miniprogram-demo
 * @ClassName: IUserService
 * @Description: TODO
 * @Author: Leo
 * @Date: 2020/3/28-11:07
 */
public interface IUserService {
    //根据id查找User信息
    User getUser(Integer id);
    //用户注册
    User register(User regisyerUser) throws Exception;
    //用户登录
    User login(String telphone,String password) throws Exception;
    //查询所有注册用户数量
    Integer countAllFourUser();

}
