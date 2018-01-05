package com.zx.backstage.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;


public class HttpUtil {
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }



    public static String getRequestDomain(HttpServletRequest request){
        String domain = request.getRequestURL().toString();
        if (StringUtils.isNotBlank(domain)) {
            URI uri = null;
            try {
                uri = new URI(domain);
                domain = uri.getHost();
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException("获取请求的domain出错.");
            }
            return domain;
        }
        return "";
    }

    public static String getRequestDomain(){
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attrs.getRequest();
        return getRequestDomain(request);
    }

    public static String getRootDomain(String domain){
        if(StringUtils.isBlank(domain)){
            return domain;
        }
        String[] arr = domain.split("\\.");
        if(arr.length>2){
            return getRootDomain(domain.substring(domain.indexOf(".")+1));
        }else{
            return domain;
        }
    }

    public static String getEncodeRequestVal(String url){
        try {
            return URLEncoder.encode(url,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static String getRootDomain(HttpServletRequest request){
        String domain = getRequestDomain(request);
        if(StringUtils.isBlank(domain)){
            return domain;
        }
        String[] arr = domain.split("\\.");
        if(arr.length>2){
            return getRootDomain(domain.substring(domain.indexOf(".")+1));
        }else{
            return domain;
        }
    }

    public static String getRootDomainV2(HttpServletRequest request){
        String domain = getRequestDomain(request);
        if(StringUtils.isBlank(domain)){
            return domain;
        }
        return domain;
    }

}
