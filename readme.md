#注意事项：

####在AlipayConfig中配置回调地址
######mvn install:install-file -DgroupId=com.alipay -DartifactId=sdk-java -Dversion=20171027120314 -Dpackaging=jar -Dfile=alipay-sdk-java20171027120314.jar

## 支付宝配置：
* APPID：2016082100307812
####商家信息
* 商家账号qagxeu0187@sandbox.com
* 商户UID2088102172470116
* 登录密码111111
####买家信息
* 买家账号ysjsvo0151@sandbox.com
* 登录密码111111
* 支付密码111111
* 用户名称沙箱环境
* 证件类型身份证(IDENTITY_CARD)
* 证件号码175394195904165319

##修改环境变量,记得重启
```
E_URL1:http://root:123456@peer1:8761/graducation/eureka/
E_URL2:http://root:123456@peer2:8762/graducation/eureka/
E_URL3:http://root:123456@peer3:8763/graducation/eureka/
```
##滑块验证码 CaptchaAPI
```
密钥申请地址 https://console.qcloud.com/capi
```
未完成表：coupon

* 重新生成bean,dao,mapper时，example要有默认构造函数和set函数