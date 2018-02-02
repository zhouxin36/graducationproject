package com.zx.api.dto;


import org.springframework.http.HttpStatus;

public class ResultDTO<T> {

    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setOK(){
        this.setCode(HttpStatus.OK.value());
    }

    public void setError(){
        this.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    public void setData(T data) {
        this.data = data;
    }

    public static ResultDTO error() {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统出现异常");
    }

    public static ResultDTO error(String msg) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    public static ResultDTO error(int code, String msg) {
        ResultDTO r = new ResultDTO();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public static ResultDTO ok() {
        ResultDTO r = new ResultDTO();
        r.setCode(HttpStatus.OK.value());
        return r;
    }

    public static ResultDTO ok(String msg) {
        ResultDTO r = ok();
        r.setMsg(msg);
        return r;
    }

    public static ResultDTO buildSuccessData(Object data) {
        ResultDTO r = ok();
        r.setData(data);
        return r;
    }
}
