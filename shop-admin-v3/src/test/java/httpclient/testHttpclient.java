package httpclient;

import com.alibaba.fastjson.JSONObject;
import com.fh.shop.admin.po.area.Area;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class testHttpclient {

    @Test
    public void test() throws IOException {
        //打开浏览器
        CloseableHttpClient client = HttpClientBuilder.create().build();
        //填写url路径
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        //回车
        CloseableHttpResponse response = client.execute(httpGet);
        //接受响应  获取内容
        HttpEntity entity = response.getEntity();
        //将内容转化为String格式
        String s = EntityUtils.toString(entity);
        //输出内容
        System.out.println(s);

    }


    //调用接口
    @Test
    public void testport(){

String as = "";
        //打开浏览器
        CloseableHttpClient client = HttpClientBuilder.create().build();
        //填写url路径
        HttpPost httpPost = new HttpPost("http://localhost:8087/members.jhtml");

        //添加参数
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("userName","wwwuserName");
        BasicNameValuePair password = new BasicNameValuePair("password", "111");
        BasicNameValuePair basicNameValuePair1 = new BasicNameValuePair("realName", "wwww");
        List<BasicNameValuePair>  basicnamevaluepairList = new ArrayList<>();
        basicnamevaluepairList.add(basicNameValuePair);
        basicnamevaluepairList.add(password);
        basicnamevaluepairList.add(basicNameValuePair1);
        //转换格式  符合传参
        try {
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(basicnamevaluepairList,"utf-8");
            //添加参数 进请求
            httpPost.setEntity(urlEncodedFormEntity);
            //执行请求
            CloseableHttpResponse response = client.execute(httpPost);
            //获取响应信息
            HttpEntity entity = response.getEntity();
            //将响应格式先转换为字符串
            String s = EntityUtils.toString(entity, "utf-8");
            //输出响应
            System.out.println(s);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;


    }













}
