<%--
  Created by IntelliJ IDEA.
  User: 、、
  Date: 2019/9/11
  Time: 15:32
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
    <title>找回密码</title>
    <link href="/js/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div style="width: 300px; margin: auto ">

    <form class="form-signin">
        <h2 class="form-signin-heading">找回密码</h2>
        <label  class="sr-only">用户名:</label>
        <input type="text" id="userName" class="form-control" placeholder="用户名...." required autofocus>
        <label  class="sr-only">邮箱:</label>
        <input type="text" id="email" class="form-control" placeholder="邮箱...." required autofocus>

        <button class="btn btn-lg btn-primary btn-block" onclick="verifyUserIsExist()" type="button">找回密码</button>
    </form>

</div>
<script src="/js/jquery-3.4.1.min.js"></script>
<script src="/js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<!-- bootbox code -->
<script src="/js/botbox/bootbox.min.js"></script>
<script src="/js/botbox/bootbox.locales.min.js"></script>
<script>
//找回密码
    function verifyUserIsExist(){
        var v_userName = $("#userName").val();
        var v_email = $("#email").val();
        var param ={};
        param.userName = v_userName;
        param.email = v_email;
        $.ajax({
            url:"/user/forgetPassword.jhtml",
            type:"post",
            dataType:"json",
            data:param,
            success:function(result){
                if(result == 200){
                    bootbox.alert({
                        title:"提示信息",
                        message:"<i class='glyphicon glyphicon-exclamation-sign'></i>请查看邮箱进行找回密码！",
                        size: 'small',
                    });
                }else{
                    bootbox.alert({
                        title:"提示信息",
                        message:"<i class='glyphicon glyphicon-exclamation-sign'></i>"+result.msg,
                        size: 'small',
                    });
                }
            }


        });
    }

</script>
</body>
</html>
