package com.imooc.dao;

import com.imooc.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    //根据用户手机和密码查询
    User selectByTelphoneAndPassword(@Param("telphone") String telphone, @Param("password") String password);
    //查询所有注册用户数量
    Integer selectCountAllUser();

}