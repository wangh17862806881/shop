
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
                <div class="panel-heading">会员查询</div>
<%-- 条件查询面板内容 --%>
                <div class="panel-body">
                    <form class="form-horizontal" id="userForm">
                        <div class="form-group">
                            <label  class="col-sm-2 control-label">会员名</label>
                            <div class="col-sm-4">
                                <input type="text" name="userName" class="form-control" id="find_userName" placeholder="请输入会员名...">
                            </div>
                            <label  class="col-sm-2 control-label">真实姓名</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="realName" id="find_realName" placeholder="请输入真实姓名...">
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-2 control-label">生日范围</label>
                            <div class="col-sm-4">
                                <div class="input-group">
                                    <input type="text" class="form-control" name="datedate" id="find_beginTime" placeholder="开始年龄..." >
                                    <span class="input-group-addon" ><i class="glyphicon glyphicon-transfer"></i></span>
                                    <input type="text" class="form-control" name="datedate" id="find_endTime" placeholder="结束年龄..." >
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
   <%--  <div class="row">
         <div class="col-md-12">

                 <div style="background-color: #ffd3d3">
                     <button class="btn btn-primary" onclick="addUser()"><span class="glyphicon glyphicon-plus"></span>添加</button>
                     <button class="btn btn-danger" onclick="deletebatchids()"  id="deletebatchids"><span class="glyphicon glyphicon-trash"></span>批量删除</button>
                     <button class="btn btn-info"  onclick="excelDownLoad()" ><span class="glyphicon glyphicon-download-alt"></span>excel导出</button>
                 </div>

         </div>
     </div>--%>
<%--  面板   用户展示面板 --%>
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-info">
                <div class="panel-heading">会员列表</div>
                <%--用户表格展示--%>
                <table id="userTable" class="table table-striped table-bordered" style="width:100%">
                    <thead>
                    <tr>
                        <td>选择</td>
                        <td>用户名</td>
                        <td>真实姓名</td>
                        <td>地区</td>
                        <td>生日</td>

                        <td>手机号</td>
                        <td>邮箱</td>
                    </tr>
                    </thead>
                    <tbody></tbody>
                    <tfoot>
                    <tr>
                        <td>选择</td>
                        <td>用户名</td>
                        <td>真实姓名</td>
                        <td>地区</td>
                        <td>生日</td>
                        <td>手机号</td>
                        <td>邮箱</td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>
</div>



<script>

//页面加载函数
    $(function(){
        getUserList();
        showdatedate();
        areaSelectList("find");
    });

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
                        "url": "/member/findPage.jhtml",
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
                        { "data": "areaName" },
                        { "data": "birthday" },
                        { "data": "phone" },
                        { "data": "email" },



                    ],
                    "lengthMenu": [ 5, 10, 20, 40, 100 ],
                    "searching":false,

                });

    }

//条件查询需要用的参数
    var param = {};
function  searcher(){
    var v_beginTime = $("#find_beginTime").val();
    var v_endTime = $("#find_endTime").val();
    var v_userName = $("#find_userName").val();
    var v_realName = $("#find_realName").val();

    var a1 =$($("select[name='find_selectArea']")[0]).val();
    var a2 =$($("select[name='find_selectArea']")[1]).val();
    var a3 =$($("select[name='find_selectArea']")[2]).val();


    if(!!v_beginTime){
        param.beginTime=v_beginTime;
    }
    if(!!v_endTime){
        param.endTime=v_endTime;
    }
    param.userName=v_userName;
    param.realName=v_realName;
    param.areaId1=a1;
    param.areaId2=a2;
    param.areaId3=a3;
    userDataTable.settings()[0].ajax.data=param;
    userDataTable.ajax.reload();

}


//时间插件调用  时间区间
    function showdatedate() {
        $("input[name='datedate']").datetimepicker({
            format: 'YYYY-MM-DD',
            locale: moment.locale('zh-CN'),
            showClear:true,
        });
    }







</script>
</body>
</html>
