package com.fh.shop.api.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class HttpclientUtil {

    //post请求
    public static String HttpPost(String url,Map<String,String> headers,Map<String,String> params){
        String result = null;
        CloseableHttpClient client = null;
        HttpPost httpPost = null;
        CloseableHttpResponse response = null;
        try {
        //打开浏览器
         client = HttpClientBuilder.create().build();
        //填写url  创建请求
         httpPost = new HttpPost(url);
        //添加请求头信息  可能有多个   判断是否存在请求头信息
        if(null != headers && headers.size() > 0){
        Set<String> keySet = headers.keySet();
        for (String key : keySet) {
            String value = headers.get(key);
            httpPost.addHeader(key,value);
        }
        }

        List<BasicNameValuePair> paramList = new ArrayList<>();
        //添加请求参数 可能有多个   先判断是否有参数
        if(null != params && params.size()>0){
            Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String, String> next = iterator.next();
                String key = next.getKey();
                String value = next.getValue();
                //创建基本存储对象
                BasicNameValuePair basicNameValuePair = new BasicNameValuePair(key, value);
                paramList.add(basicNameValuePair);
            }
        }


                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(paramList, "utf-8");
                httpPost.setEntity(urlEncodedFormEntity);
                //执行请求
                 response = client.execute(httpPost);
                //获取响应值
                HttpEntity entity = response.getEntity();
                //将响应值转换格式、
                 result = EntityUtils.toString(entity, "utf-8");
        } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            if(null != response){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != httpPost){
                httpPost.releaseConnection();
            }
            if(null != client){
                try {
                    client.close();
                } catch (IOException e) {


                }
            }

        }

        return result;

    }





}
