package com.zx.api.dto;

/**
 * Created by 周鑫 on 2018-1-4
 * 滑块验证码
 */
public class VerifyDTO {
    private Integer captchaType = 9;
    private Integer clientType;
    private String ticket;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Integer getCaptchaType() {
        return captchaType;
    }

    public void setCaptchaType(Integer captchaType) {
        this.captchaType = captchaType;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }
}
