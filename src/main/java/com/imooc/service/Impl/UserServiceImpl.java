package com.imooc.service.Impl;

import com.imooc.common.BusinessException;
import com.imooc.common.CommonResponse;
import com.imooc.common.EmBusinessError;
import com.imooc.dao.UserMapper;
import com.imooc.pojo.User;
import com.imooc.service.IUserService;
import com.imooc.util.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * ClassName: UserServiceImpl
 * Description: TODO
 * Author: Leo
 * Date: 2020/3/28-11:07
 * email 1437665365@qq.com
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public User register(User regisyerUser) throws Exception {
        /**
         * @Description //TODO 注册
           @Author Leo
         * @Date 16:42 2020/3/28
         * @Param [regisyerUser]
         * @return com.imooc.pojo.User
        */
        regisyerUser.setPassword(Md5Util.encodeByMd5(regisyerUser.getPassword()));
        regisyerUser.setCreatedAt(new Date());
        regisyerUser.setUpdatedAt(new Date());
        try {
            userMapper.insertSelective(regisyerUser);
        }catch (Exception e){
            //用户已存在
            throw new BusinessException(EmBusinessError.REGISTER_DUP_FAIL);
        }
        regisyerUser.setId(regisyerUser.getId());
        return regisyerUser;
    }

    @Override
    public User login(String telphone, String password) throws Exception {
        /**
         * @Description //TODO 用户登录
           @Author Leo
         * @Date 17:53 2020/3/28
         * @Param [telphone, password]
         * @return com.imooc.pojo.User
        */
        User user = userMapper.selectByTelphoneAndPassword(telphone, Md5Util.encodeByMd5(password));
        if(user == null){
            throw new BusinessException(EmBusinessError.LOGIN_FAIL);
        }
        return user;
    }

    @Override
    public Integer countAllFourUser() {
        /**
         * @Description //TODO 查询所有注册用户数量
           @Author Leo
         * @Date 13:59 2020/3/29
         * @Param []
         * @return java.lang.Integer
        */
        return userMapper.selectCountAllUser();
    }

}
