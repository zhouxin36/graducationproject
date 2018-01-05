package com.zx.backstage.config;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * Demo
 */
public class CaptchaAPI extends BaseAPI {

    private volatile static CaptchaAPI instance = null;

    private CaptchaAPI() {
        super();
        // 密钥,请进行替换,密钥申请地址 https://console.qcloud.com/capi
        this.secretId = "AKIDCBvQfexp2ye4FHmZpqb6LJ1TeaGfKiMj";
        this.secretKey = "GG0Vs0tVr54nGSP6WDhy2sUt42nLo47w";
    }

    public static CaptchaAPI getInstance() {
        if (instance==null) {
            synchronized (CaptchaAPI.class) {
                if (instance == null) {
                    instance = new CaptchaAPI();
                }
            }
        }

        return instance;
    }

    /**
     * 验证验证码票据
     * IFrame接入请使用该方法校验用户输入的验证码
     * @param args 用户输入票据
     * @return
     */
    public ApiResponse check(Map<String, String> args) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String url = this.makeURL("GET", "CaptchaCheck", "gz", args, "utf-8");
        return ApiRequest.sendGet(url, "");
    }

    /**
     * 获取JS Url
     * IFrame接入请使用该方法拉取js url
     * @param args
     * @return
     */
    public ApiResponse getJsUrl(Map<String, String> args) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException  {
        String url = this.makeURL("GET", "CaptchaIframeQuery", "gz", args, "utf-8");
//        ApiResponse resp = ApiRequest.sendGet(url, "");
//        $jsUrl = resp.getBody();
        return ApiRequest.sendGet(url, "");
    }
}
