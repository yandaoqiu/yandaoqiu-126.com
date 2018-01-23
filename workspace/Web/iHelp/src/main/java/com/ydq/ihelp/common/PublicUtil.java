package com.ydq.ihelp.common;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 2016/8/26.
 */
public class PublicUtil {
    /**
     * 获取32位GUID
     *
     * @return
     */
    public static String GUID() {
        return java.util.UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 获取当前系统时间
     *
     * @return
     */
    public static String getCurentTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }


    /**
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static String getIpAddress(HttpServletRequest request) throws IOException {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
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
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (String ip1 : ips) {
                if (!("unknown".equalsIgnoreCase(ip1))) {
                    ip = ip1;
                    break;
                }
            }
        }
        return ip;
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
       InputStream is = null;
       BufferedReader rd = null;
       try {
           is = new URL(url).openStream();
           rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
           String jsonText = readAll(rd);
           
           JSONObject json = JSONObject.parseObject(jsonText);
           return json;
       } finally {
           //关闭输入流
    	   rd.close();
           is.close();
       }
   }
    
   private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}

