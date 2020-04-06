package com.imooc.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class Seller {
    private Integer id;

    private String name;

    private Date createdAt;

    private Date updatedAt;
    //商户评分,默认0分,最高5分
    private BigDecimal remarkScore;
    //启用信用标记 0启用 1禁用
    private Integer disabledFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public BigDecimal getRemarkScore() {
        return remarkScore;
    }

    public void setRemarkScore(BigDecimal remarkScore) {
        this.remarkScore = remarkScore;
    }

    public Integer getDisabledFlag() {
        return disabledFlag;
    }

    public void setDisabledFlag(Integer disabledFlag) {
        this.disabledFlag = disabledFlag;
    }
}