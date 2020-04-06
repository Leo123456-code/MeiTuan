package com.imooc.service.Impl;

import com.imooc.common.BusinessException;
import com.imooc.common.EmBusinessError;
import com.imooc.dao.SellerMapper;
import com.imooc.pojo.Seller;
import com.imooc.service.ISellService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * ClassName: ISellServiceImpl
 * Description: TODO 商户接口实现类
 * Author: Leo
 * Date: 2020/3/29-14:14
 * email 1437665365@qq.com
 */
@Slf4j
@Service
public class SellServiceImpl implements ISellService {
    @Autowired
    private SellerMapper sellerMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Seller create(Seller seller) {
        /**
         * @Description //TODO 创建商户
           @Author Leo
         * @Date 14:22 2020/3/29
         * @Param [seller]
         * @return com.imooc.pojo.Seller
        */
        seller.setCreatedAt(new Date());
        seller.setUpdatedAt(new Date());
        seller.setRemarkScore(new BigDecimal(0));
        seller.setDisabledFlag(0);
        sellerMapper.insertSelective(seller);

        return get(seller.getId());
    }

    @Override
    public Seller get(Integer id) {
        /**
         * @Description //TODO 通过Id查询
           @Author Leo
         * @Date 14:22 2020/3/29
         * @Param [id]
         * @return com.imooc.pojo.Seller
        */
        return sellerMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Seller> selectAll() {
        /**
         * @Description //TODO List查询
           @Author Leo
         * @Date 14:22 2020/3/29
         * @Param []
         * @return java.util.List<com.imooc.pojo.Seller>
        */
        return sellerMapper.selectAll();
    }

    @Override
    public Seller changeStatus(Integer id, Integer disabledFlag) throws Exception {
        /**
         * @Description //TODO 启用,禁用状态
           @Author Leo
         * @Date 14:24 2020/3/29
         * @Param [id, disabledFlag]
         * @return com.imooc.pojo.Seller
        */
        Seller seller = get(id);
        if(seller == null){
           throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        seller.setDisabledFlag(disabledFlag);
        int rowCount = sellerMapper.updateByPrimaryKeySelective(seller);
        if(rowCount == 0){
            throw new RuntimeException("修改状态失败");
        }
        return seller;
    }

    @Override
    public Integer selectAllFourSell() {
        /**
         * @Description //TODO 查询所有的商户数量
           @Author Leo
         * @Date 14:52 2020/3/29
         * @Param []
         * @return java.lang.Integer
        */
        return sellerMapper.selectAllFourSeller();
    }
}
