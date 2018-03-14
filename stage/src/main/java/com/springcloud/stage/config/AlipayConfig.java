package com.springcloud.stage.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.FileWriter;
import java.io.IOException;

@Configuration
public class AlipayConfig {

    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016082100307812";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDfnUT84VNKFgM+xqOVqbklnfefLW0cR9bj76qk9nkAB9wuCbdi7nNuuCITIiM6HYoWbqK8oGJUamP9NVZwOrO+iUYr50F8E0Q+JQjaYScIt5znfXDFZgTra6N0v2sqdAAwgIXFFdHhnpNm6pW2antY4GSUur6A5kPMXHonZjnf4ExxsLug4n+s0tlfUNxNmqEfLVAwJ/QFYFZGOlmCll1rwdkLLZ4acNwzWELIFaBaKu/2+85cYC9xtPwMlQrlmr0zXptAN8OoJudmdB+qEvPjp/XUcvnLco6nVTeCvnrLiXr2kbCzYjy2+CJydFrH0oSRHe/6P6Va1067W6mOasMBAgMBAAECggEBAII4XtV5DnzlZsxm9xqP9R8f5/UNii3e9BhXGKPqWLqY2TMCO4ZkYFqI5mcICUWtsB2yHhkEuV55enMZXZYLyWLbJD8q8+qieapK/OXXj9i2vKveVA7Qg2rpiw3Q70KQvQ+BuOT1+PyP0T0svTS2u9AdFNW8DnaYIcFf1U8ea/Of30qv+1REa40lzuaGLaJT4pTZYR6qwtahUGdUE4MrMNQuTXId4oqrJp9JvtciJbVtHvUIg5Shy0QUnURltsUbyMYl7F8qRQ0L82WOslhc8wuyP1P9jbme2ayKGR3wgEcqd7hIJDImD0RCaFmsQDiZOUiUZhobRDR579dsJeNKo5UCgYEA+s12TWm7bhCEskZEIBuEOI2YSjMHQB7HEgEubeWlvHXINdqpZ9rzwybQJTvMBA/JEjY3vjiMndL76zNAyWPOI5bMbhE5Y61/t0pcZBgF/nvtkXFs1JwMWUDX3e2g8u7WfkyGSy5fdfu3eCKLzoqbnivhz2+7mxpRvKk2uaUUAGcCgYEA5D+SBsfhRrY1REtX+zKlwnsO0MvY0cfxg18hMQy4JlkWZVyXNCh/JgFcYw+Vn5f3An14jNeVgnz9mKIXWURW4xoPnTA8qwQmtJIAFPot4lFBWeoa07m4z+j0zaHnH3qWTIBL71x3urBf91KpRk4zvc7xo9rKkBl7XbOJ6bzIYFcCgYBOwOOsDU5oklFItZ3Aw0MyiuvOKNXXAZMbCTnuElWHpyh2GLJnRS6UEgT8HBnFELvftxyr44/sIwst4MswMeHR9RE+YrQ1o45eHMCEQbLoJTwFFqvI2zNZd24RZwPKJMs/n4Hv66DYKHGFDLv/4DBba4YdoAU1//64kh34D2kHcwKBgHAgdpkUKiv1qNdC+Baz+9A9abrS2VG9Gn0CB6kz6WcVTrY+tRzvY/8EniEf78319mdnwgfWhGEVC/OmdTfqJZDtJYx/vN36bRU5wAaOHGpYpnUIV2N5kju44kH+bi79psYwJJ2S3FJ/ALXcCsI5+psdBYIMIoSFsCAI6hBEKF+DAoGAT3PjHemvSdk+IOFStDxyMgw7kNsCWrAEE4HECzBZ+s2dGnbT+Z9sWdEQKX730Dz6W2p/EZmHC8MfPoeKKdseVzq58kcahVDrVo9VZSoeYvMm21OUc0f4xuhFonYedik+RW+RmWtGQU1cv9nwtaL0X3GgCfWoWilo+uOxValAKPY=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAw10zqVz6cTM67CLvVDSc71dMhqT+r/FvM1zjHkMJ5jMVOd4KPAIPpDw4nxA6EbMoIU6gjyrSo+dBOoBtYUqyCb/qDHHgIJwtXCaTpv3htOXTLK2zXRk/dLiZ3FyD2G2jJK3PE0XOf2dTjuxVOMMmrfbAvAKbZ3D43t42m9D9sLq4ChpslnPHF0YOE2q52GhrrgjwQNzEN63BvZjrU33tpYQ3sxwGY+X3ugcHqw5PNb1wajAa5qY91Aw9xKxjZn+CAZyogKmz0HHVJLssnZbR0mCSWXBtfaUeiggei1xioNTEBZk+88h3+mpp/25z5TxXuEwsx/kI7itKnFdPi8m2rwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    @Value("${notify_url}")
    public static String notify_url;

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    @Value("${return_url}")
    public static String return_url;

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
