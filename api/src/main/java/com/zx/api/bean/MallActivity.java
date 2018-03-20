package com.zx.api.bean;

public class MallActivity {
    private String id;

    private String picId;

    private String remark;

    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MallActivity{" +
                "id='" + id + '\'' +
                ", picId='" + picId + '\'' +
                ", remark='" + remark + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}