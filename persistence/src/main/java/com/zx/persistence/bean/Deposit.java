package com.zx.persistence.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Deposit {
    private String id;

    private String userId;

    private BigDecimal rechargeMoney;

    private Integer issuccess;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime rechargeDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(BigDecimal rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public Integer getIssuccess() {
        return issuccess;
    }

    public void setIssuccess(Integer issuccess) {
        this.issuccess = issuccess;
    }

    public LocalDateTime getRechargeDate() {
        return rechargeDate;
    }

    public void setRechargeDate(LocalDateTime rechargeDate) {
        this.rechargeDate = rechargeDate;
    }
}