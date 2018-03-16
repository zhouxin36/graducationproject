package com.zx.api.bean;

import java.math.BigDecimal;

public class Sorder {
    private String id;

    private String name;

    private BigDecimal price;

    private Integer number;

    private String productId;

    private String userId;

    private Integer isbalanced;

    private String forderId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getIsbalanced() {
        return isbalanced;
    }

    public void setIsbalanced(Integer isbalanced) {
        this.isbalanced = isbalanced;
    }

    public String getForderId() {
        return forderId;
    }

    public void setForderId(String forderId) {
        this.forderId = forderId;
    }

    @Override
    public String toString() {
        return "Sorder{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", productId='" + productId + '\'' +
                ", userId='" + userId + '\'' +
                ", isbalanced=" + isbalanced +
                ", forderId='" + forderId + '\'' +
                '}';
    }
}