package com.zx.api.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.util.StringUtils.isEmpty;

public class MyUtils {
    private static final String sail = "zhouxin";
    public static String getId(String str){
        return MD5Util.MD5(str+sail);
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().toUpperCase();
    }

    private static final Map<String, BeanCopier> beanCopierMap = new HashMap<String, BeanCopier>();


    /**
     * 用于数据对比接口，其它方法调用要认真阅读本方法的实现
     * @param source
     * @param target
     * @return
     */
    public static boolean isEquals(String source, String target) {
        if ((source == null || isEmpty(source)) && (target == null || isEmpty(target))) {
            return true;
        } else if ((source != null && target != null && source.trim().equals(target.trim()))) {
            return true;
        }
        return false;
    }

    /**
     * 检查参数是否为标准UUID值
     * @param uuid
     * @return
     */
    public static boolean isUUID(String uuid) {
        String reg = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
        return uuid != null && uuid.length() == 36 && uuid.matches(reg);
    }

    /**
     * 能够针对BeanUtils所无法拷贝的属性类型进行拷贝，例如：Boolean
     * @param source
     * @param target
     */
    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
        Class<?> cls = target.getClass();
        for(Field f : source.getClass().getDeclaredFields()) {
            if(f.getType() == Boolean.class) {
                f.setAccessible(true);
                try {
                    Field tf = cls.getDeclaredField(f.getName());
                    tf.setAccessible(true);
                    tf.set(target, f.get(source));
                } catch (IllegalAccessException e) {
                } catch (NoSuchFieldException e) {
                }
            }
        }
    }
    public static String md5Passwrod(String password) {
        // 加密码
        StringBuffer str = new StringBuffer();
        str.append("tuan").append(password).append("dai");
        String results = null;
        MessageDigest mDigest;
        try {
            mDigest = MessageDigest.getInstance("SHA1");
            byte[] result = mDigest.digest(str.toString().getBytes());
            String sha1_input = byteToStringHex(result);// sb.toString().toUpperCase();
            mDigest = MessageDigest.getInstance("MD5");
            String md5input = sha1_input.toUpperCase();
            mDigest.update(md5input.getBytes());
            result = mDigest.digest();
            results = byteToStringHex(result);// buf.toString();
        } catch (NoSuchAlgorithmException e) {
            //
        }

        return results;
    }

    public static String byteToStringHex(byte[] result) {
        StringBuffer sb = new StringBuffer();
        for (byte element : result) {
            sb.append(Integer.toString((element & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString().toUpperCase();
    }


    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     *
     * 使用Cglib的BeanCopier实现Bean的拷贝
     *
     * @param source
     * @param target
     */
    public static void copyPropertiesCglib(Object source, Object target) {
        String beanKey = generateBeanKey(source.getClass(), target.getClass());
        BeanCopier copier = null;
        if (!beanCopierMap.containsKey(beanKey)) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            beanCopierMap.put(beanKey, copier);
        } else {
            copier = beanCopierMap.get(beanKey);
        }
        copier.copy(source, target, null);
    }

    public static String generateBeanKey(Class<?> source, Class<?> target) {
        return source.getName() + "@" + target.getName();
    }


    /**
     * 验证Email地址
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        return email != null && email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+");
    }

    /**
     * 验证手机号
     * @param phoneNum
     * @return
     */
    public static boolean isPhone(String phoneNum){
        String regex = "^(13|14|15|17|18)[0-9]{9}$";//手机号正则表达式
        return phoneNum.matches(regex);
    }
    /**
     * 验证身份证号
     * @param idCard
     * @return
     */
    public static boolean isIDCard(String idCard){
        String regex = "^(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[Xx])$)$";//身份证正则表达式
        return idCard.matches(regex);
    }

    /**
     * bool to Int
     * @param value
     * @return
     */
    public static Integer boolToInt(Boolean value){
        Boolean val = Optional.ofNullable(value).orElse(false);
        return val?1:0;
    }

    /**
     * 替换https,http公用方法
     * @param url
     * @return
     */
    public static String convertHttpOrHttpsUrl(String url) {
        if(isEmpty(url)){
            return "//js2.tdw.cn/images/users/avatar/bav_head.png";
        }
        return url.trim().replace("https:", "").replace("http:", "");
    }


}
