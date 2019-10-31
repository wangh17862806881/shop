package com.fh.shop.admin.biz.user;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.fh.shop.admin.po.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-common.xml"})
public class TestUserService extends AbstractJUnit4SpringContextTests {
@Resource
private IUserService userService ;


@Test
    public void TestAddUser(){
    User user = new User();
  user.setUserName("1111111111");
    user.setPassword("1111111");
    userService.addUser(user);
}

// 短信通知
/*    @Test
    public  void aaaaa() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FjUYbkATaMEwBjKBx6j", "iMkZ0iZRxOIY08yYUGQlnz5ohp70zR");
        IAcsClient client = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers("15136302759");
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("飞狐教育");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_174985348");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");
        request.setTemplateParam("{\"code\":\"123456\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }

    }*/

//语音通知
    private static String Url = "http://api.voice.ihuyi.com/webservice/voice.php?method=Submit";
/*@Test
    public  void bbbb() {

        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);

        //client.getParams().setContentCharset("GBK");
        client.getParams().setContentCharset("UTF-8");
        method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");

        NameValuePair[] data = {//提交短信
                new NameValuePair("account", "V20722424"),//用户名是登录用户中心->语音验证码->产品总览->APIID
                new NameValuePair("password", "5ebbb7b7a9a91307393e9eb76a2261f2 "),//查看密码请登录用户中心->语音验证码->产品总览->APIKEY
                new NameValuePair("mobile", "13405409265"),//手机号码
                new NameValuePair("content", "1125"),
        };

        method.setRequestBody(data);

        try {
            try {
                client.executeMethod(method);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String SubmitResult = method.getResponseBodyAsString();

            //System.out.println(SubmitResult);

            Document doc = null;
            try {
                doc = DocumentHelper.parseText(SubmitResult);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            Element root = doc.getRootElement();

            String code = root.elementText("code");
            String msg = root.elementText("msg");
            String voiceid = root.elementText("voiceid");

            System.out.println(code);
            System.out.println(msg);
            System.out.println(voiceid);

            if("2".equals(code)){
                System.out.println("短信提交成功");
            }

        } catch (HttpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            // Release connection
            method.releaseConnection();
            client.getHttpConnectionManager().closeIdleConnections(0);
           // client.getConnectionManager().shutdown();
            List<Integer> a = new ArrayList<>();
            List<Integer> a1 = JSONArray.parseArray("a", Integer.class);
        }

    }*/




}
