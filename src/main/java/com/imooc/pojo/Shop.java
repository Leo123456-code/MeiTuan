package com.imooc.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class Shop {
    private Integer id;

    private Date createdAt;

    private Date updatedAt;
    //门店名字
    private String name;
    //权重,评分
    private BigDecimal remarkScore;
    //人均价格
    private BigDecimal pricePerMan;
    //地址信息,经度
    private BigDecimal latitude;
    //纬度
    private BigDecimal longitude;
    //距离
    private Integer distance;

    private Category categoryModel;
    //商品id
    private Integer categoryId;
    //标签
    private String tags;
    //营业开始时间
    private String startTime;
    //营业结束时间
    private String endTime;
    //地址位置
    private String address;

    private Seller sellerModel;
    //商户id
    private Integer sellerId;
    //图片地址
    private String iconUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRemarkScore() {
        return remarkScore;
    }

    public void setRemarkScore(BigDecimal remarkScore) {
        this.remarkScore = remarkScore;
    }

    public BigDecimal getPricePerMan() {
        return pricePerMan;
    }

    public void setPricePerMan(BigDecimal pricePerMan) {
        this.pricePerMan = pricePerMan;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Category getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(Category categoryModel) {
        this.categoryModel = categoryModel;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Seller getSellerModel() {
        return sellerModel;
    }

    public void setSellerModel(Seller sellerModel) {
        this.sellerModel = sellerModel;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}