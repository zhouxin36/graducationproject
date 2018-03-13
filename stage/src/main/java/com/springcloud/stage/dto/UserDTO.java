package com.springcloud.stage.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UserDTO {
    private String id;

    private String nickname;

    private String name;

    private String password;

    private String birthday;

    private Integer sex;

    private String phone;

    private String email;

    private String lastIp;

    private String avatar;

    private Integer memberLevel;

    private Integer accountSecurity;

    private Integer isabled;

    private BigDecimal accountBalance;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex=" + sex +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", lastIp='" + lastIp + '\'' +
                ", avatar='" + avatar + '\'' +
                ", memberLevel=" + memberLevel +
                ", accountSecurity=" + accountSecurity +
                ", isabled=" + isabled +
                ", accountBalance=" + accountBalance +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(Integer memberLevel) {
        this.memberLevel = memberLevel;
    }

    public Integer getAccountSecurity() {
        return accountSecurity;
    }

    public void setAccountSecurity(Integer accountSecurity) {
        this.accountSecurity = accountSecurity;
    }

    public Integer getIsabled() {
        return isabled;
    }

    public void setIsabled(Integer isabled) {
        this.isabled = isabled;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }
}