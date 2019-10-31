<%--
  Created by IntelliJ IDEA.
  User: 、、
  Date: 2019/9/11
  Time: 9:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>修改密码</title>
</head>
<body>
<jsp:include page="/common/nav/nav.jsp"></jsp:include>

<div id="container" class="container">
    <%-- 面板 条件查询的面板 --%>
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel panel-info">
                <%-- 条件查询标题 --%>
                <div class="panel-heading">密码修改</div>
                <form class="form-horizontal" id="update_form">
                    <div class="form-group">
                        <label  class="col-sm-2 control-label">原密码</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="oldPassword"  placeholder="请输入原密码...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-sm-2 control-label">新密码</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="newPassword"  placeholder="请输入新密码...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-sm-2 control-label">确认密码</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="confirmPassword"  placeholder="请确认密码...">
                        </div>
                    </div>
                    <div class="form-group" style="text-align: center">
                        <button class="btn btn-primary" type="button" onclick="update_Password()"><span class="glyphicon glyphicon-pencil"></span>修改</button>
                        <button class="btn btn-primary"  type="reset"><span class="glyphicon glyphicon-pencil"></span>重置</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>

    function update_Password(){
        //获取输入框的值
        var  v_oldPassword = $("#oldPassword").val();
        var  v_newPassword = $("#newPassword").val();
        var  v_confirmPassword = $("#confirmPassword").val();

        //验证两次密码不一致  新密码和确认密码
        if(v_newPassword != v_confirmPassword){
            bootbox.alert({
                title:"提示信息",
                message:"<i class='glyphicon glyphicon-exclamation-sign'></i>新密码和确认密码不一致！！！",
                size: 'small',
            });
            return false;
        }
        var param = {};
        param.oldPassword = v_oldPassword;
        param.newPassword = v_newPassword;
        param.confirmPassword = v_confirmPassword;
        param.userId = '${user.id}';
        //调用方法 修改密码
        $.ajax({
            url:"/user/updatePasswordByid.jhtml",
            type:"post",
            data:param,
            dataType:"json",
            success:function(result){
                if(result.code == 200 ){
                    bootbox.alert({
                        title:"提示信息",
                        message:"<i class='glyphicon glyphicon-exclamation-sign'></i>修改密码成功请重新登陆！！！",
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
