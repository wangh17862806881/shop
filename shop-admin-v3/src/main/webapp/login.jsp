<%--
  Created by IntelliJ IDEA.
  User: 、、
  Date: 2019/8/28
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>登陆页面</title>
    <link href="/js/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div style="width: 300px; margin: auto ">

<form class="form-signin">
    <h2 class="form-signin-heading">请登录</h2>
    <label  class="sr-only">用户名:</label>
    <input type="text" id="userName" class="form-control" placeholder="用户名...." required autofocus>
    <label  class="sr-only">密码:</label>
    <input type="password" id="password" class="form-control" placeholder="密码...." required>
    <img src="/imgCode" id="imgCodeSRC"><a href="#" onclick="getImgCode()">看不清？</a>
    <label  class="sr-only">验证码:</label>
    <input type="text" id="imgCode" class="form-control" placeholder="验证码...." required>
    <div class="checkbox">
        <label>
            <input type="checkbox" value="remember-me"> 记住密码
        </label>
        <label>
            <a href="/verifyUser.jsp">忘记密码</a>
        </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" onclick="logincheck()" type="button">登陆</button>
</form>

</div>


<script src="/js/jquery-3.4.1.min.js"></script>
<script src="/js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<!-- bootbox code -->
<script src="/js/botbox/bootbox.min.js"></script>
<script src="/js/botbox/bootbox.locales.min.js"></script>
<script>
    function logincheck(){
        var userName = $("#userName").val();
        var password = $("#password").val();
        var imgCodeSRC = $("#imgCode").val();
        $.ajax({
            url:"/user/loginCheck.jhtml",
            data:{"userName":userName,"password":password,"imgCode":imgCodeSRC},
            type:"post",
            dataType:"json",
            async:true,
            success:function(result){
               if(result.code == 200){
                   location.href="/index.jhtml";
               }else{
                   bootbox.alert({
                       title:"提示信息",
                       message:"<i class='glyphicon glyphicon-exclamation-sign'></i>"+result.msg,
                       size: 'small',
                   })
               }
            }

        });

    }

    function getImgCode(){
        var t =  new Date();
        $("#imgCodeSRC").prop("src","/imgCode?"+t.getTime());
    }

</script>
</body>
</html>
