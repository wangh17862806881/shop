<%--
  Created by IntelliJ IDEA.
  User: 、、
  Date: 2019/8/15
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<html>
<head>

    <title>展示页面</title>


</head>
<body>
<%-- nav导航条 --%>
<jsp:include page="/common/nav/nav.jsp"></jsp:include>
<%-- 栅格系统  网格系统  最外层 容器 container --%>
<div id="container" class="container">
<%-- 面板 条件查询的面板 --%>
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel panel-info">
<%-- 条件查询标题 --%>
                <div class="panel-heading">用户查询</div>
<%-- 条件查询面板内容 --%>
                <div class="panel-body">
                    <form class="form-horizontal" id="userForm">
                        <div class="form-group">
                            <label  class="col-sm-2 control-label">用户名</label>
                            <div class="col-sm-4">
                                <input type="text" name="userName" class="form-control" id="find_userName" placeholder="请输入用户名...">
                            </div>
                            <label  class="col-sm-2 control-label">真实姓名</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="realName" id="find_realName" placeholder="请输入真实姓名...">
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-2 control-label">年龄范围</label>
                            <div class="col-sm-4">
                                <div class="input-group">
                                    <input type="text" class="form-control" name="beginTime" id="find_beginAge" placeholder="开始年龄..." >
                                    <span class="input-group-addon" ><i class="glyphicon glyphicon-transfer"></i></span>
                                    <input type="text" class="form-control" name="endTime" id="find_endAge" placeholder="结束年龄..." >
                                </div>
                            </div>
                            <label  class="col-sm-2 control-label">薪资范围</label>
                            <div class="col-sm-4">
                                <div class="input-group">
                                    <input type="text" id="find_beginSalary" name="beginSalary" class="form-control" placeholder="最小薪资..." >
                                    <span class="input-group-addon" ><i class="glyphicon glyphicon-yen"></i></span>
                                    <input type="text" id="find_endSalary" name="endSalary"  class="form-control" placeholder="最大薪资..." >
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-2 control-label">入职时间</label>
                            <div class="col-sm-4">
                                <div class="input-group">
                                    <input type="text" name="datedate" name="beginAge"  id="find_beginTime" class="form-control" placeholder="开始时间..." >
                                    <span class="input-group-addon" ><i class="glyphicon glyphicon-calendar"></i></span>
                                    <input type="text" name="datedate" name="endAge"  id="find_endTime" class="form-control" placeholder="结束时间..." >
                                </div>
                            </div>
                            <label  class="col-sm-2 control-label">角色</label>
                            <div class="col-sm-4">
                                <div class="input-group"  id="find_rolecheckbox">

                                </div>
                            </div>
                        </div>
                        <div class="form-group" id="find_areaSelectList">
                            <label  class="col-sm-2 control-label">地区</label>

                        </div>


                        <div style="text-align: center">
                            <button class="btn btn-primary" onclick="searcher()" type="button"><span class="glyphicon glyphicon-search"></span >查询</button>
                            <button class="btn btn-default" onclick="initForm()" type="reset"><span class="glyphicon glyphicon-refresh"></span >重置</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>
<%-- 按钮  工具栏 --%>
     <div class="row">
         <div class="col-md-12">

                 <div style="background-color: #ffd3d3">
                     <button class="btn btn-primary" onclick="addUser()"><span class="glyphicon glyphicon-plus"></span>添加</button>
                     <button class="btn btn-danger" onclick="deletebatchids()"  id="deletebatchids"><span class="glyphicon glyphicon-trash"></span>批量删除</button>
                     <button class="btn btn-info"  onclick="excelDownLoad()" ><span class="glyphicon glyphicon-download-alt"></span>excel导出</button>
                 </div>

         </div>
     </div>
<%--  面板   用户展示面板 --%>
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-info">
                <div class="panel-heading">用户列表</div>
                <%--用户表格展示--%>
                <table id="userTable" class="table table-striped table-bordered" style="width:100%">
                    <thead>
                    <tr>
                        <td>选择</td>
                        <td>用户名</td>
                        <td>真实姓名</td>
                        <td>头像</td>
                        <td>地区</td>
                        <td>薪资</td>
                        <td>入职时间</td>
                        <td>性别</td>
                        <td>年龄</td>
                        <td>手机号</td>
                        <td>邮箱</td>
                        <td>角色</td>
                        <td>状态</td>
                        <td>操作</td>
                    </tr>
                    </thead>
                    <tbody></tbody>
                    <tfoot>
                    <tr>
                        <td>选择</td>
                        <td>用户名</td>
                        <td>真实姓名</td>
                        <td>头像</td>
                        <td>地区</td>
                        <td>薪资</td>
                        <td>入职时间</td>
                        <td>性别</td>
                        <td>年龄</td>
                        <td>手机号</td>
                        <td>邮箱</td>
                        <td>角色</td>
                        <td>状态</td>
                        <td>操作</td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>
</div>
<%-- 新增 弹出框 --%>
<div id="userInsertFormData" style="display: none;">

    <form class="form-horizontal" >
        <div class="form-group">
            <label  class="col-sm-2 control-label">用户名:</label>
            <div class="col-sm-10">
                <input type="text"  id="add_userName" class="form-control"  placeholder="用户名...">
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">真实姓名:</label>
            <div class="col-sm-10">
                <input type="text" id="add_realName" class="form-control"  placeholder="真实姓名...">
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">头像:</label>
            <div class="col-sm-10">
                <input type="file" name="myfile"  id="add_fileInput"   class="myfile"  />
                <input type="hidden" id="add_imgURL" >
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">密码:</label>
            <div class="col-sm-10">
                <input type="password" id="add_password" class="form-control"  placeholder="密码...">
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">确认密码:</label>
            <div class="col-sm-10">
                <input type="password" id="add_confirmpassword" class="form-control"  placeholder="密码...">
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
                <input type="text" id="add_age" class="form-control"  placeholder="年龄...">
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">薪资：</label>
            <div class="col-sm-10">
                <input type="text" id="add_salary" class="form-control"  placeholder="薪资...">
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">入职时间：</label>
            <div class="col-sm-10">
                <input type="text" id="add_entryTime" name="datedate"  class="form-control"  placeholder="入职时间...">
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">手机号码：</label>
            <div class="col-sm-10">
                <input type="text" id="add_phone" class="form-control"  placeholder="手机号码...">
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">邮箱：</label>
            <div class="col-sm-10">
                <input type="text" id="add_email" class="form-control"  placeholder="邮箱...">
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">角色：</label>
            <div class="col-sm-10" id="add_rolecheckbox">

            </div>
        </div>
        <div class="form-group" id="add_areaSelectList">
            <label  class="col-sm-2 control-label">地区：</label>

        </div>
    </form>
</div>
<%-- 修改 弹出框 --%>
<div id="userUpdateFormData"  style="display: none">

    <form class="form-horizontal" >
        <div class="form-group">
            <label  class="col-sm-2 control-label">用户名:</label>
            <div class="col-sm-10">
                <input type="text"  id="update_userName" class="form-control"  placeholder="用户名...">
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">真实姓名:</label>
            <div class="col-sm-10">
                <input type="text" id="update_realName" class="form-control"  placeholder="真实姓名...">
            </div>
        </div>

        <div class="form-group">
            <label  class="col-sm-2 control-label">头像:</label>
            <div class="col-sm-10">
                <input type="file" name="myfile" id="update_fileInput"   class="myfile"  />
                <input type="hidden" id="update_imgURL" >
                <img src="" id="update_img">
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">性别：</label>
            <div class="col-sm-10">
                <input type="radio" name="update_sex" value="1"  >男
                <input type="radio" name="update_sex" value="0"  >女
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">年龄：</label>
            <div class="col-sm-10">
                <input type="text" id="update_age" class="form-control"  placeholder="年龄...">
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">薪资：</label>
            <div class="col-sm-10">
                <input type="text" id="update_salary" class="form-control"  placeholder="薪资...">
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">入职时间：</label>
            <div class="col-sm-10">
                <input type="text" id="update_entryTime" name="datedate"  class="form-control"  placeholder="入职时间...">
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">手机号码：</label>
            <div class="col-sm-10">
                <input type="text" id="update_phone" class="form-control"  placeholder="手机号码...">
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">邮箱：</label>
            <div class="col-sm-10">
                <input type="text" id="update_email" class="form-control"  placeholder="邮箱...">
            </div>
        </div>
        <div class="form-group" >
            <label  class="col-sm-2 control-label">地区:</label>
            <div class="col-sm-10">
            <div id="update_areaSelectList" style="width: auto">

            </div>
            <span id="update_AreaSpan"></span>

            <button type="button" value="1" onclick="update_AreaStyle(this)" class="btn btn-primary" id="update_AreaButton"><i class='glyphicon glyphicon-pencil'></i>编辑</button>
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">角色：</label>
            <div class="col-sm-10" id="update_rolecheckbox">

            </div>
        </div>
        <input type="hidden" id = "update_id">
    </form>
</div>


<script>
    var v_add_html;
//页面加载函数
    $(function(){
        getUserList();
        initbootbox();
        showdatedate();
        findroleall("add_rolecheckbox","add");
        findroleall("update_rolecheckbox","update");
        findroleall("find_rolecheckbox","find");
        initTableTr();
        areaSelectList("find");
    });

    //修改地区
    function update_AreaStyle(obj){

        if($(obj).val() == 1){
            $(obj).val(2)
            $("#update_AreaButton").html("<i class='glyphicon glyphicon-pencil'></i>退出编辑")
            $("#update_AreaSpan").hide();
            areaSelectList("update");
            return ;
        }

        $(obj).val(1)
        $("#update_AreaButton").html("<i class='glyphicon glyphicon-pencil'></i>编辑")
        $("#update_AreaSpan").show();
        $("select[name='update_selectArea']").parent().remove();

    }



    function areaSelectList(prefix,obj){
        var id = 0 ;
        if(obj){
            id =  obj.val();
            obj.parent().nextAll().remove();
        }
        $.ajax({
            url:"/area/getAreaByPid.jhtml",
            type:"post",
            data:{"id":id},
            async:false,
            success:function(result){
                if(result.code == 200){

                    var dataArr = result.data;
                    //判断传进来的data是否为空  为空不进行拼接字符串 直接返回
                    if(dataArr.length == 0){
                        return ;
                    }

                    //拼接前缀
                    var str = '<div class="col-sm-2">' +
                        '<select name ="'+prefix+'_selectArea" class="form-control"  onchange="areaSelectList(\''+prefix+'\',$(this))" >' +
                        '<option value="-1">==请选择==</option>';

                    //拼接 动态部分
                    for(var i = dataArr.length-1 ; i>=0 ; i--){
                        str += '<option value ="'+dataArr[i].id+'">'+dataArr[i].name+'</option>';
                    }

                    //拼接后缀
                    str += '</select>' +
                        '</div>';
                    console.log(str)
                    //将数据拼接到div中
                    $("#"+prefix+"_areaSelectList").append(str);


                }
            }
        });


    }


//初始化bootbox 并创建全局字符串
    var v_userInsertFormData;
    var v_userUpdateFormData;
    function initbootbox(){
        v_userInsertFormData = $("#userInsertFormData").html();
        $("#userInsertFormData").html("");
        v_userUpdateFormData = $("#userUpdateFormData").html();
        $("#userUpdateFormData").html("")
    }


   var idarr = [];
// 选中 批量删除 上一页 下一页  赋值
function initTableTr(){
        $("#userTable tbody").on("click","tr",function(){

            var v_checkbox = $(this).find("input[type='checkbox']");
            var v_check = $(this).attr("check");

            if(!!v_check){

                v_checkbox.prop("checked",false);
                $(this).css("background-color","");
                $(this).attr("check","");
                idarr.splice(jQuery.inArray(v_checkbox.val(),idarr),1);
            }else{

                v_checkbox.prop("checked",true);
                $(this).css("background-color","blue");
                $(this).attr("check","ok");
                idarr.push(v_checkbox.val());
            }



        });
}


    var userDataTable;

 //查询用户集合
    function  getUserList(){

         userDataTable =  $("#userTable").DataTable({
                    "language": {
                        "url":"/js/Chinese.json"
                    },
                    "processing": true,
                    "serverSide": true,
                    "destroy":true,
                    "ajax": {
                        "url": "/user/getUserList.jhtml",
                        "type": "POST",
                        "dataSrc": function(result){
                            result.recordsFiltered =result.data.recordsFiltered;
                            result.recordsTotal =result.data.recordsTotal;
                            result.draw =result.data.draw;
                            return result.data.data;
                        },

                    },
                    "columns": [
                        { "data": function(data){
                            return '<input  type="checkbox"  value=\"'+data.id+'\">';
                            } },
                        { "data": "userName" },
                        { "data": "realName" },
                        { "data": function (data) {
                                return '<img style="height: 50px" src="http://192.168.150.128'+data.imgURL+'"/>';
                            } },

                        { "data": "areaNames" },
                        { "data": "salary" },
                        { "data": "entryTime" },
                        { "data": function(data){
                                return data.sex==1?"男":"女";
                            } },
                        { "data": "age" },
                        { "data": "phone" },
                        { "data": "email" },
                        {"data":"roleNames"},
                        {"data":"status",
                            "render":function (data, type, row, meta) {
                                return data?"锁定":"正常"
                            }
                        },
                        { "data": "id",
                            "render": function(data, type, row, meta){
                            return "<div class=\"btn-group\" role=\"group\" >\n" +
                                "  <button type=\"button\" onclick='deleteUserById(\""+data+"\")' class=\"btn btn-danger\"><i class='glyphicon glyphicon-trash'></i>删除</button>\n" +
                                "  <button type=\"button\" onclick='updateUser(\""+data+"\")' class=\"btn btn-primary\"><i class='glyphicon glyphicon-pencil'></i>修改</button>\n" +
                                "  <button type=\"button\"    "+(row.status?'':'disabled')+"  onclick='updateStatus(\""+data+"\")' class=\"btn btn-success\"><i class='glyphicon glyphicon-lock'></i>解锁</button>\n" +
                                "  <button type=\"button\" onclick='resetPasswordById(\""+data+"\")' class=\"btn btn-danger\"><i class='glyphicon glyphicon-refresh'></i>重置密码</button>\n" +

                                "</div>";
                            } },
                    ],
                    "lengthMenu": [ 5, 10, 20, 40, 100 ],
                    "searching":false,
                     "drawCallback": function( settings ) {
                       var inputCheck =   $("#userTable tbody tr")
                       for(var i = inputCheck.length-1 ; i >=0 ; i-- ){

                           var v_check = $(inputCheck[i]).find("input[type='checkbox']");

                           if(jQuery.inArray(v_check.val(),idarr) != -1){
                               v_check[0].checked = true;
                               $(inputCheck[i]).css("background-color","blue");
                               $(inputCheck[i]).attr("check","ok");
                           }
                       }
                     }
                });

    }

//条件查询需要用的参数
    var param = {};
function  searcher(){
    var v_beginTime = $("#find_beginTime").val();
    var v_endTime = $("#find_endTime").val();
    var v_beginAge = $("#find_beginAge").val();
    var v_endAge = $("#find_endAge").val();
    var v_beginSalary = $("#find_beginSalary").val();
    var v_endTSalary = $("#find_endSalary").val();
    var v_userName = $("#find_userName").val();
    var v_realName = $("#find_realName").val();
    var v_find_rolecheckbox = $("#find_roleselect").val();

    var a1 =$($("select[name='find_selectArea']")[0]).val();
    var a2 =$($("select[name='find_selectArea']")[1]).val();
    var a3 =$($("select[name='find_selectArea']")[2]).val();


    if(!!v_beginTime){
        param.beginTime=v_beginTime;
    }
    if(!!v_endTime){
        param.endTime=v_endTime;
    }
    param.beginAge=v_beginAge;
    param.endAge=v_endAge;
    param.beginSalary=v_beginSalary;
    param.endSalary=v_endTSalary;
    param.userName=v_userName;
    param.realName=v_realName;
    param.roleidarr=v_find_rolecheckbox.toString();
    param.roleidarrcount=v_find_rolecheckbox.length;
    param.areaId1=a1;
    param.areaId2=a2;
    param.areaId3=a3;
    userDataTable.settings()[0].ajax.data=param;
    userDataTable.ajax.reload();

}

//单个删除 用户
    function deleteUserById(id){
        event.stopPropagation();
        bootbox.confirm({
            message: "您确定要删除吗？",
            buttons: {
                confirm: {
                    label: '<i class="glyphicon glyphicon-ok"></iclass>确定',
                    className: 'btn-success'
                },
                cancel: {
                    label: '<i class="glyphicon glyphicon-remove"></i>取消',
                    className: 'btn-danger'
                }
            },
            callback: function (result) {
                if(result==true){
                   $.ajax({
                       url:"/user/deleteUserById.jhtml",
                       type:"post",
                       data:{"id":id},
                       dataType:"json",
                       success:function(data){
                           console.log(data);
                           if(data.code==200){

                               getUserList();
                           }


                       },
                       error:function(data){

                           console.log(data)
                           bootbox.alert({
                               title:"提示信息",
                               message:"<i class='glyphicon glyphicon-exclamation-sign'></i>方法失败",
                               size: 'small',

                           });
                       }

                   });
                }
            }
        });

    }


//批量删除
    function deletebatchids(){
        if(idarr.length > 0){
            $.ajax({
                url:"/user/deletebatchids.jhtml",
                type:"post",
                data:{"idarr":idarr},
                dataType:"json",
                success:function(result){
                    if(result.code == 200){
                        bootbox.alert({
                            title:"提示信息",
                            message:"<i class='glyphicon glyphicon-exclamation-sign'></i>删除成功",
                            size: 'small',

                        });
                        idarr = [];
                        searcher();
                    }
                }

            });
        }else{
            bootbox.alert({
                title:"提示信息",
                message:"<i class='glyphicon glyphicon-exclamation-sign'></i>请选择后再删除",
                size: 'small',
            });
        }



    }


//新增用户
    var userbootbox;
    function addUser(){

          userbootbox =  bootbox.dialog({
                    message: v_userInsertFormData,
                    title:"新增用户",
                    size:"large",
                    buttons: {
                        confirm: {
                            label: '<i class="glyphicon glyphicon-ok"></iclass>确定',
                            className: 'btn-success',
                            callback:function(){
                                //获取输入框的值
                                var v_userName = $("#add_userName",userbootbox).val();
                                var v_realName = $("#add_realName",userbootbox).val();
                                var v_password = $("#add_password",userbootbox).val();
                                var  v_confirmpassword = $("#add_confirmpassword",userbootbox).val();
                                var v_sex = $("input[name='sex']:checked",userbootbox).val();
                                var v_age = $("#add_age",userbootbox).val();
                                var v_phone = $("#add_phone",userbootbox).val();
                                var v_email = $("#add_email",userbootbox).val();
                                var v_salary = $("#add_salary",userbootbox).val();
                                var v_entryTime = $("#add_entryTime",userbootbox).val();
                                var v_imgURL = $("#add_imgURL",userbootbox).val();
                                var v_ids =  $("#add_roleselect",userbootbox).val().join(",") ;
                                var a1 =$($("select[name='add_selectArea']")[0]).val();
                                var a2 =$($("select[name='add_selectArea']")[1]).val();
                                var a3 =$($("select[name='add_selectArea']")[2]).val();
                                //定义json对象
                                var param = {};
                                param.userName = v_userName;
                                param.realName =v_realName;
                                param.password = v_password;
                                param.sex = v_sex;
                                param.age =v_age;
                                param.phone =v_phone;
                                param.email = v_email;
                                param.salary = v_salary;
                                param.entryTime = v_entryTime;
                                param.roleIds = v_ids;
                                param.imgURL = v_imgURL;
                                param.areaId1 = a1;
                                param.areaId2 = a2;
                                param.areaId3 = a3;



                                $.ajax({
                                     url:"/user/add.jhtml",
                                     type:"post",
                                     data:param,
                                     dataType:"json",
                                     success:function(data){

                                         if(data.code == 200){
                                             searcher();
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
        areaSelectList("add");
        initdatedate("add_entryTime");
        findroleall("add_rolecheckbox","add");
        FileInput("add");
    }

//查询所有角色 并赋值给下拉框
    function findroleall(divid,prifix){
        $.ajax({
            url: "/user/findroleall.jhtml",
            type: "post",
            data: {},
            dataType: "json",
            async:false,
            success:function(result){
                if(result.code==200) {
                    var str = '<select name="roleidarr" multiple="multiple" id="'+prifix+'_roleselect">';
                    var data = result.data;

                    for (var i = 0; i < data.length; i++) {
                        str += "<option  name='" + prifix + "_rolecheckbox' value='" + data[i].id + "' >"+data[i].roleName+"</option>";
                    }
                    str += '</select>';
                    $("#" + divid).html(str);
                    $("#" + prifix+"_roleselect").selectpicker('refresh');
                    $("#" + prifix+"_roleselect").selectpicker('render');
                }
            }

        });
    }




//修改用户方法
    function updateUser(id){
        event.stopPropagation();
       //回显    回填 数据
        $.ajax({
            url:"/user/findUserById.jhtml",
            data:{"id":id},
            type:"post",
            dataType:"json",
            success:function(data){
                if(data.code==200){

                    $("#userUpdateFormData").html(v_userUpdateFormData)  ;

                    findroleall("update_rolecheckbox","update");
                    //为表单赋值   此时表单 并未被bootbox剪切 所以目前id唯一  可以直接 $("#") 获取
                    $("#update_userName").val(data.data.userName);
                   $("#update_realName").val(data.data.realName);
                    $("#update_password").val(data.data.password);
                    $("#update_salary").val(data.data.salary);
                    $("#update_entryTime").val(data.data.entryTime);
                    $("#update_AreaSpan").html(data.data.areaNames);
                     $("input[name='update_sex']").each(function(){
                         if($(this).val() == data.data.sex){
                             $(this).attr("checked",true);
                         }
                     });

                    $('#update_roleselect').selectpicker('val', data.data.roleIdlist);

                    $("#update_imgURL").val(data.data.imgURL);
                   $("#update_age").val(data.data.age);
                     $("#update_phone").val(data.data.phone);
                     $("#update_email").val(data.data.email);
                     $("#update_id").val(data.data.id);
                     $("#update_img").prop("src","http://192.168.150.128"+data.data.imgURL);

              //调用弹出框
                    var userUpdatebootbox =  bootbox.dialog({
                        message: $("#userUpdateFormData form"),
                        title:"修改用户",
                        size:'large',
                        buttons: {
                            confirm: {
                                label: '<i class="glyphicon glyphicon-ok"></iclass>确定',
                                className: 'btn-success',
                                callback:function(){
                                    //获取输入框的值
                                    var v_userName = $("#update_userName",userUpdatebootbox).val();
                                    var v_realName = $("#update_realName",userUpdatebootbox).val();
                                    var v_password = $("#update_password",userUpdatebootbox).val();
                                    var v_salary = $("#update_salary",userUpdatebootbox).val();
                                    var v_entryTime = $("#update_entryTime",userUpdatebootbox).val();
                                    var v_imgURL = $("#update_imgURL",userUpdatebootbox).val();

                                    var v_sex = $("input[name='update_sex']:checked",userUpdatebootbox).val();
                                    var v_age = $("#update_age",userUpdatebootbox).val();
                                    var v_phone = $("#update_phone",userUpdatebootbox).val();
                                    var v_email = $("#update_email",userUpdatebootbox).val();
                                    var v_id =  $("#update_id",userUpdatebootbox).val();
                                    var v_roleids =$("#update_roleselect",userUpdatebootbox).val().join();
                                    var a1;
                                    var a2;
                                    var a3;

                                    if($("select[name='update_selectArea']").length == 3){
                                         a1 =$($("select[name='update_selectArea']")[0]).val();
                                         a2 =$($("select[name='update_selectArea']")[1]).val();
                                         a3 =$($("select[name='update_selectArea']")[2]).val();
                                    }else{
                                        a1 =data.data.classId1;
                                        a2 =data.data.classId2;
                                        a3 =data.data.classId3;
                                    }


                                    //定义json对象
                                    var param = {};
                                    param.userName = v_userName;
                                    param.realName =v_realName;
                                    param.password = v_password;
                                    param.sex = v_sex;
                                    param.age =v_age;
                                    param.phone =v_phone;
                                    param.email = v_email;
                                    param.id = v_id;
                                    param.salary = v_salary;
                                    param.entryTime = v_entryTime;
                                    param.roleIds = v_roleids;
                                    param.imgURL = v_imgURL;
                                    param.areaId1 = a1;
                                    param.areaId2 = a2;
                                    param.areaId3 = a3;

                                    $.ajax({
                                        url:"/user/updateUserById.jhtml",
                                        type:"post",
                                        data:param,
                                        dataType:"json",
                                        success:function(data){
                                            if(data.code == 200){

                                                searcher();
                                            }

                                        },
                                        error:function(){
                                            bootbox.alert({
                                                title:"提示信息",
                                                message:"<i class='glyphicon glyphicon-exclamation-sign'></i>方法失败",
                                                size: 'small',

                                            });
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
                    FileInput("update");
                    initdatedate("update_entryTime");


                }

            },
        });

    }

    //修改用户状态
    function updateStatus(id){
        event.stopPropagation();
        bootbox.confirm({
            message: "您确定要解锁吗？",
            buttons: {
                confirm: {
                    label: '<i class="glyphicon glyphicon-ok"></iclass>确定',
                    className: 'btn-success'
                },
                cancel: {
                    label: '<i class="glyphicon glyphicon-remove"></i>取消',
                    className: 'btn-danger'
                }
            },
            callback: function (result) {
                if(result==true){
                    $.ajax({
                        url:"/user/updateStatus.jhtml",
                        data:{"id":id},
                        dataType:"json",
                        type:"post",
                        success:function(result){
                            if(result.code == 200){
                                bootbox.alert({
                                    title:"提示信息",
                                    message:"<i class='glyphicon glyphicon-exclamation-sign'></i>修改状态成功",
                                    size: 'small',
                                });
                                searcher();
                            }

                        }

                    });
                }
            }
        });

    }




    //重置密码
    function resetPasswordById(id){
        event.stopPropagation();
        bootbox.confirm({
            message: "您确定要重置密码吗？",
            buttons: {
                confirm: {
                    label: '<i class="glyphicon glyphicon-ok"></iclass>确定',
                    className: 'btn-success'
                },
                cancel: {
                    label: '<i class="glyphicon glyphicon-remove"></i>取消',
                    className: 'btn-danger'
                }
            },
            callback: function (result) {
                if(result==true){
                    $.ajax({
                        url:"/user/resetPasswordById.jhtml",
                        data:{"id":id},
                        dataType:"json",
                        type:"post",
                        success:function(result){
                            if(result.code == 200){
                                bootbox.alert({
                                    title:"提示信息",
                                    message:"<i class='glyphicon glyphicon-exclamation-sign'></i>重置密码成功",
                                    size: 'small',
                                });

                            }

                        }

                    });
                }
            }
        });
    }


//初始化表单   重置下拉框
    function initForm(){
        $('#find_roleselect').selectpicker('val', '');

    }

//时间插件调用  时间区间
    function showdatedate() {
        $("input[name='datedate']").datetimepicker({
            format: 'YYYY-MM-DD',
            locale: moment.locale('zh-CN'),
            showClear:true,
        });
    }
//时间插件调用  新增修改
    function initdatedate(initid) {
        $('#'+initid).datetimepicker({
            format: 'YYYY-MM-DD',
            locale: moment.locale('zh-CN'),
            showClear:true,
        });
    }
// 上传图片 插件 FileInput
function   FileInput(id){
$("#"+id+"_fileInput").fileinput({
    //上传的地址
    uploadUrl:"/user/imgFileInputupload.jhtml",
    uploadAsync : true, //默认异步上传
    showUpload : false, //是否显示上传按钮,跟随文本框的那个
    showRemove : false, //显示移除按钮,跟随文本框的那个
    showCaption : true,//是否显示标题,就是那个文本框
    showPreview : true, //是否显示预览,不写默认为true
    dropZoneEnabled : false,//是否显示拖拽区域，默认不写为true，但是会占用很大区域
    //minImageWidth: 50, //图片的最小宽度
    //minImageHeight: 50,//图片的最小高度
    //maxImageWidth: 1000,//图片的最大宽度
    //maxImageHeight: 1000,//图片的最大高度
    //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
    //minFileCount: 0,
    maxFileCount : 1, //表示允许同时上传的最大文件个数
    enctype : 'multipart/form-data',
    validateInitialCount : true,
    previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
    msgFilesTooMany : "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
    allowedFileTypes : [ 'image' ],//配置允许文件上传的类型
    allowedPreviewTypes : [ 'image' ],//配置所有的被预览文件类型
    allowedPreviewMimeTypes : [ 'jpg', 'png', 'gif' ],//控制被预览的所有mime类型
    language : 'zh'
})
//异步上传返回结果处理
$("#"+id+"_fileInput").on('fileerror', function(event, data, msg) {
    console.log("fileerror");
    console.log(data);
});
//异步上传返回结果处理
$("#"+id+"_fileInput").on("fileuploaded", function(event, data, previewId, index) {

    $("#"+id+"_imgURL").val(data.response.data);
    if(id == "update"){
        $("#update_img").prop("src","");
    }

});

//同步上传错误处理
$("#"+id+"_fileInput").on('filebatchuploaderror', function(event, data, msg) {
    console.log("filebatchuploaderror");
    console.log(data);
});

//同步上传返回结果处理
$("#"+id+"_fileInput").on("filebatchuploadsuccess",
    function(event, data, previewId, index) {
        console.log("filebatchuploadsuccess");
        console.log(data);
    });
//上传前
$("#"+id+"_fileInput").on('filepreupload', function(event, data, previewId, index) {

});
}

//图片上传
function fileinput5(id,imgurl){
        $("#"+id+"_uploadImage").fileinput({
            language: 'zh', //设置语言,
            uploadUrl : "/file/uploadFile.jhtml", /*上传图片的url*/
            allowedFileExtensions : [ 'jpg', 'png', 'gif' ],//接收的文件后缀
            showUpload:true, //是否显示上传按钮
            /* maxFileCount:5, //表示允许同时上传的最大文件个数*/
            /* maxFileCount:5, //表示允许同时上传的最大文件个数*/
            initialPreviewAsData:true,
            initialPreview: [
                imgurl
            ],
            slugCallback : function(filename) {
                return filename.replace('(', '_').replace(']', '_');
            }
        });
        $("#"+id+"_uploadImage").on('fileuploaded', function(event, data, previewId, index) {
            $("#"+id+"_ImagePath").val(data.response.data);
        });
    }

//导出excel
    function excelDownLoad(){
        var a1 =$($("select[name='find_selectArea']")[0]).val();
        var a2 =$($("select[name='find_selectArea']")[1]).val();
        var a3 =$($("select[name='find_selectArea']")[2]).val();
        var form = $("#userForm")
        form.attr("action","/user/excelDownLoad.jhtml")
        form.attr("method","post")
        if(!$("#excelDownAreaA1").val()){
            var areaId1=$("<input type='hidden' id='excelDownAreaA1' name='areaId1'/>");
            var areaId2=$("<input type='hidden'id='excelDownAreaA2'  name='areaId2'/>");
            var areaId3=$("<input type='hidden' id='excelDownAreaA3'  name='areaId3'/>");
            form.append(areaId1);
            form.append(areaId2);
            form.append(areaId3);
        }
            $("#excelDownAreaA1").val(a1);
            $("#excelDownAreaA2").val(a2);
            $("#excelDownAreaA3").val(a3);
        form.submit();


    }


</script>
</body>
</html>
