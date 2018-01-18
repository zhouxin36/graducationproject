package com.zx.api.exception;


public class AppBusinessException extends RuntimeException {

    private String code;

    //类似Http状态码
    private int httpStatus;

    private AppBusinessException(String code, int httpStatus, String message) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public AppBusinessException(String message) {
        super(message);
    }

    public String getCode() {
        return code;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
