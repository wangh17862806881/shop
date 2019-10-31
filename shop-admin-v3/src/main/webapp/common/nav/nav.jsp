<%--
  Created by IntelliJ IDEA.
  User: 、、
  Date: 2019/8/28
  Time: 12:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
   <jsp:include page="/common/ImportJsAndCss/css.jsp"></jsp:include>
    <title>导航条</title>
    <style>
        .dropdown-submenu {
            position: relative;
        }

        .dropdown-submenu > .dropdown-menu {
            top: 0;
            left: 100%;
            margin-top: -6px;
            margin-left: -1px;
            -webkit-border-radius: 0 6px 6px 6px;
            -moz-border-radius: 0 6px 6px;
            border-radius: 0 6px 6px 6px;
        }

        .dropdown-submenu:hover > .dropdown-menu {
            display: block;
        }

        .dropdown-submenu > a:after {
            display: block;
            content: " ";
            float: right;
            width: 0;
            height: 0;
            border-color: transparent;
            border-style: solid;
            border-width: 5px 0 5px 5px;
            border-left-color: #ccc;
            margin-top: 5px;
            margin-right: -10px;
        }

        .dropdown-submenu:hover > a:after {
            border-left-color: #fff;
        }

        .dropdown-submenu.pull-left {
            float: none;
        }

        .dropdown-submenu.pull-left > .dropdown-menu {
            left: -100%;
            margin-left: 10px;
            -webkit-border-radius: 6px 0 6px 6px;
            -moz-border-radius: 6px 0 6px 6px;
            border-radius: 6px 0 6px 6px;
        }
    </style>

</head>
<body>
<%-- nav导航条 --%>
<nav class="navbar  navbar-inverse">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">飞狐电商后台管理</a>
        </div>


        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="#">欢迎<span id="userName"></span>登陆</a></li>

                <li style="display: none" id="showDiv" ><a href="#">
                    上次登陆时间
                    <span id="userloginDate"></span>

                </a></li>

                <li class="active"><a href="#">今天是第<span id="userloginCount"></span>次登陆</a></li>
                <li><a href="/user/loginOut.jhtml">退出</a></li>
                <li><a href="/user/toUpdatePassword.jhtml">修改密码</a></li>

            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<jsp:include page="/common/ImportJsAndCss/script.jsp"></jsp:include>
<script >
    $(function(){
        //设置全局ajax  在success error 成功失败 回调函数后执行
        $.ajaxSetup({
            complete:function(result){
               var data = result.responseJSON;
                if(data.code  && data.code != 200){
                    bootbox.alert({
                        title:"提示信息",
                        message:"<i class='glyphicon glyphicon-exclamation-sign'></i>"+data.msg,
                        size: 'small',
                    });
                }
            }
        })

        showAllResource();
        findUserInfo();
    });


    var jsonArr ;
//查询导航条数据
    function showAllResource(){
        $.ajax({
            url:"/resource/findAllResourceByUserId.jhtml",
            type:"post",
            dataType:"json",
            async:true,
            data:{},
            success:function(result){
                if(result.code == 200){

                     jsonArr = result.data;
                    initNav(1,1);

                    $("#bs-example-navbar-collapse-1").append(str);
                    console.log(str)
                }else{
                    bootbox.alert({
                        title:"提示信息",
                        message:"<i class='glyphicon glyphicon-exclamation-sign'></i>查询失败",
                        size: 'small',
                    })
                }


            }

        });
    }
    var str = '';
//初始化导航条
    function initNav(pid,level){
        //查询当前父id的子节点id
        var childrenArr = getChildrenArr(pid);
        if(childrenArr.length > 0){
            if(level == 1){
                str += '  <ul class="nav navbar-nav">';
            }else{
                str += '<ul class="dropdown-menu">';
            }
           //循环拼接子节点
            for(var i = childrenArr.length-1 ; i >= 0 ; i-- ){
                //查询当前节点是否有子节点
                       var   flag =    hasChildren(childrenArr[i].id);
                   
                if(flag){
                    //如果当前等级是1
                    if(level == 1){
                        str +=' <li class="dropdown"><a href="#"  data-toggle="dropdown" >'+childrenArr[i].menuName+'<span class="caret"></span></a>';
                    }else{
                        str +='<li class="dropdown-submenu"><a href="#">'+childrenArr[i].menuName+'</a>';
                    }

                }else{
                    str += '<li><a href="'+childrenArr[i].url+'">'+childrenArr[i].menuName+'</a>';
                }
                initNav(childrenArr[i].id,level+1);
                str += '</li>';
            }


            str +='</ul>';
        }
    }
//获取子节点数组
function getChildrenArr(pid){
        var childrenArr = [];
        for(var i = jsonArr.length-1 ; i >=0 ; i--){
            if(pid == jsonArr[i].fatherId){
                childrenArr.push(jsonArr[i]);
            }
        }
        return childrenArr;
}
//判断当前节点是否有子节点  返回boolean
    function hasChildren(pid){
        for(var i = jsonArr.length-1 ; i >= 0 ; i--){
            if(pid == jsonArr[i].fatherId){
                return true;
            }
        }
        return false;
    }

//查询后台登陆相关数据
    function findUserInfo(){
        $.ajax({
            url: "/user/findUserInfo.jhtml",
            type: "post",
            async: true,
            data: {},
            success: function (result) {
                if(result.code == 200){
                  var user = result.data;
                $("#userName").html(user.userName);
                $("#userloginCount").html(user.loginCount);

                if(!!user.loginDate){
                $("#userloginDate").html(user.loginDateStr);
                $("#showDiv").show();
                }
                }
            }
        })
    }



</script>
</body>
</html>
