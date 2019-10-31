var loginFlag = false;

$(function(){

var v_navHtml='<nav class="navbar  navbar-inverse">\n' +
    '    <div class="container-fluid" >\n' +
    '\n' +
    '        <div class="navbar-header">\n' +
    '            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">\n' +
    '                <span class="sr-only">Toggle navigation</span>\n' +
    '                <span class="icon-bar"></span>\n' +
    '                <span class="icon-bar"></span>\n' +
    '                <span class="icon-bar"></span>\n' +
    '            </button>\n' +
    '            <a class="navbar-brand" href="/index.html">飞狐首页</a>\n' +
    '        </div>\n' +
    '\n' +
    '\n' +
    '\n' +
    '            <ul class="nav navbar-nav navbar-right" id="memberInfoNav">\n' +
    '                <li ><a href="/login.html">登陆</a></li>\n' +
    '                <li><a href="/register.html" >注册</a></li>\n' +
    '            </ul>\n' +
    '            <ul class="nav navbar-nav navbar-right" >\n' +
    '                <li class="nav  navbar-right"><a href="/cart.html" >购物车</a></li>\n' +
    '            </ul>\n' +

    '    </div><!-- /.container-fluid -->\n' +
    '</nav>';

$("#nav").html(v_navHtml)

    $.ajaxSetup({
        beforeSend:function(XHR){
            var tokenHeader = $.cookie("token_idempotent");
            if(tokenHeader){
                XHR.setRequestHeader("token_idempotent",tokenHeader);
            }

            var fh_memberInfo = $.cookie("fh_memberInfo")
            XHR.setRequestHeader("x-auth",fh_memberInfo);
        }
    })

    showMemberInfo();

});



//导航条展示用户信息
function showMemberInfo(){

    $.ajax({
        url:"http://localhost:8087/members/findmemberInfo",
        type:"get",
        async:false,
        success:function(result) {
            if (result.code == 200) {
                $("#memberInfoNav").html('<li><a href="#">欢迎' + result.data.realName + '登陆</a></li>' +
                    '<li><a href="#" onclick="logout()">退出</a></li>')

                loginFlag = true;
            }
        }
    });
}


//退出登陆
function logout(){
    $.ajax({
        url:"http://localhost:8087/members/logout",
        type:"get",
        success:function(result){
            if(result.code == 200){
                $.removeCookie("fh_memberInfo");
                location.href="/index.html";
            }
        }


    });
}



//登陆模板
var loginStr = '<div style="width: 300px;" id="loginDiv">\n' +
    '\n' +
    '    <form class="form-signin">\n' +
    '        <h2 class="form-signin-heading">请登录</h2>\n' +
    '        <input type="text" id="userName" class="form-control" placeholder="用户名...." required autofocus>\n' +
    '        <input type="password" id="password" class="form-control" placeholder="密码...." required>\n' +
    '        <div class="checkbox">\n' +
    '            <label>\n' +
    '                <input type="checkbox" value="remember-me"> 记住密码\n' +
    '            </label>\n' +
    '            <label>\n' +
    '                <a href="/verifyUser.jsp">忘记密码</a>\n' +
    '            </label>\n' +
    '        </div>\n' +
    '\n' +
    '    </form>\n' +
    '\n' +
    '</div>';


//购买商品 判断
function buyProduct(id){

    //判断是否登陆
    if(!loginFlag){
        //没登陆  先登录 在买商品
        login(id);
        return ;
    }
    //登陆过直接买
    buy(id);
}

//购买
function buy (id){
    $.ajax({
        url:"http://localhost:8087/cart",
        type:"post",
        data:{"productId":id,"count":1},
        success:function(result){
            if(result.code == 200){
                location.href="/cart.html";
            }
        }

    })
}

//登陆
var loginbiitbox;
function login(id){

    loginbiitbox =  bootbox.dialog({
        message: loginStr,
        title:"登陆",
        backdrop:false,
        buttons: {
            confirm: {
                label: '<i class="glyphicon glyphicon-ok"></iclass>登陆',
                className: 'btn-success',
                callback:function(){
                    var userName = $("#userName",loginbiitbox).val();
                    var password = $("#password",loginbiitbox).val();

                    $.ajax({
                        url:"http://localhost:8087/members/login",
                        data:{"userName":userName,"password":password},
                        type:"post",
                        success:function(result){
                            if(result.code == 200){
                                loginFlag = true;
                                $.cookie("fh_memberInfo",result.data);
                                buy(id);
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
            },
            cancel: {
                label: '<i class="glyphicon glyphicon-remove"></i>取消',
                className: 'btn-danger'
            }
        },

    });
}