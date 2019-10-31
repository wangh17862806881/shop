<%--
  Created by IntelliJ IDEA.
  User: 、、
  Date: 2019/8/14
  Time: 23:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户添加</title>
</head>
<body>
<form class="form-horizontal" id="userFormData">
    <div class="form-group">
        <label  class="col-sm-2 control-label">用户名:</label>
        <div class="col-sm-10">
            <input type="text" name="userName" class="form-control"  placeholder="用户名...">
        </div>
    </div>
    <div class="form-group">
        <label  class="col-sm-2 control-label">真实姓名:</label>
        <div class="col-sm-10">
            <input type="text" name="realName" class="form-control"  placeholder="真实姓名...">
        </div>
    </div>
    <div class="form-group">
        <label  class="col-sm-2 control-label">密码:</label>
        <div class="col-sm-10">
            <input type="password" name="password" class="form-control"  placeholder="密码...">
        </div>
    </div>
    <div class="form-group">
        <label  class="col-sm-2 control-label">性别：</label>
        <div class="col-sm-10">
            <input type="radio" name="sex" value="1"  >男
            <input type="radio" name="sex" value="0"  >女
        </div>
    </div>
    <div class="form-group">
        <label  class="col-sm-2 control-label">年龄：</label>
        <div class="col-sm-10">
            <input type="text" name="age" class="form-control"  placeholder="年龄...">
        </div>
    </div>
    <div class="form-group">
        <label  class="col-sm-2 control-label">手机号码：</label>
        <div class="col-sm-10">
            <input type="text" name="phone" class="form-control"  placeholder="手机号码...">
        </div>
    </div>
    <div class="form-group">
        <label  class="col-sm-2 control-label">邮箱：</label>
        <div class="col-sm-10">
            <input type="text" name="email" class="form-control"  placeholder="邮箱...">
        </div>
    </div>
</form>

<div style="display: none">
    <input type="reset" id="userReset">

</div>


</body>
</html>
