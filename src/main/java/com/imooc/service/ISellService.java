package com.imooc.service;

import com.imooc.common.BusinessException;
import com.imooc.pojo.Seller;

import java.util.List;

/**
 * @program: miniprogram-demo
 * @ClassName: ISellService
 * @Description: TODO 商户接口
 * @Author: Leo
 * @Date: 2020/3/29-14:14
 */
public interface ISellService {
    //创建商户
    Seller create(Seller seller);
    //通过Id查询
    Seller get(Integer id);
    //List查询
    List<Seller> selectAll();
    //启用,禁用状态
    Seller changeStatus(Integer id,Integer disabledFlag) throws Exception;
    //查询所有的商户数量
    Integer selectAllFourSell();


}
