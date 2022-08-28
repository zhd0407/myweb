package com.base.myweb.lanlanTest;


import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.*;
import java.util.Map.Entry;
/**
 * author zhanghaidong
 * createTime 2022-07-20 14:40
 */
public class GetTokenUtil {

    public static void main(String[] args) {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("client_id","21365057");
        paramsMap.put("client_secret","Kwouy63dsBD5tQgwBA6Y");
        postRequest("https://219.159.146.78:1443/artemis/oauth/token",paramsMap);
    }

    /**
     * 调用对方接口方法
     * @param path 对方或第三方提供的路径
     * @param params 向对方或第三方发送的数据，大多数情况下给对方发送JSON数据让对方解析
     */
    public static void postRequest(String path, Map<String,String> params) {
        OutputStream outputStream = null;
        InputStream is = null;
        HttpURLConnection conn = null;
        URL url = null;
        try {
            // 拼接请求参数
            StringBuffer sb = new StringBuffer();
            String sbRlt = null;
            if (params != null) {
                for (Entry<String, String> e : params.entrySet()) {
                    sb.append(e.getKey());
                    sb.append("=");
                    sb.append(e.getValue());
                    sb.append("&");
                }
                sbRlt = sb.substring(0, sb.length() - 1);
            }
            url = new URL(path);
            //打开和url之间的连接
            conn = (HttpURLConnection) url.openConnection();

            //设置请求的方法类型
            conn.setRequestMethod("POST");
            //设置请求的内容类型
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            //设置发送数据
            conn.setDoOutput(true);
            //设置接受数据
            conn.setDoInput(true);
            //发送数据,使用输出流
            outputStream = conn.getOutputStream();
            outputStream.write(sbRlt.getBytes("UTF-8"));
            //获取URLConnection对象对应的输入流
            is = conn.getInputStream();
            if(conn.getResponseCode() == 200) {
                //构造一个字符流缓存
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String str = "";
                while ((str = br.readLine()) != null) {
                    //logger.info(str);
                    System.out.println(str);
                }
            }else {
                // logger.error("访问失败");
                System.out.println("访问失败");
            }
        } catch (Exception e) {
            // logger.error("访问失败");
            e.printStackTrace();
        } finally {
            if(is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null) {
                conn.disconnect();
            }
        }
    }
}
