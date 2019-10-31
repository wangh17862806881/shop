<%--
  Created by IntelliJ IDEA.
  User: 、、
  Date: 2019/9/9
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>日志管理</title>
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
                <div class="panel-heading">日志查询</div>
                <%-- 条件查询面板内容 --%>
                <div class="panel-body">
                    <form class="form-horizontal" id="find_form">
                        <div class="form-group">
                            <label  class="col-sm-2 control-label">用户名</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="find_userName" placeholder="请输入用户名...">
                            </div>
                            <label  class="col-sm-2 control-label">真实姓名</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control"  id="find_realName" placeholder="请输入真实姓名...">
                            </div>

                        </div>

                        <div class="form-group">
                            <label  class="col-sm-2 control-label">操作时间</label>
                            <div class="col-sm-4">
                                <div class="input-group">
                                    <input type="text"  name="beginTime" id="find_beginTime" class="form-control" placeholder="开始时间..." >
                                    <span class="input-group-addon" ><i class="glyphicon glyphicon-calendar"></i></span>
                                    <input type="text"  name="endTime" id="find_endTime" class="form-control" placeholder="结束时间..." >
                                </div>
                            </div>
                            <label  class="col-sm-2 control-label">操作信息</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control"  id="find_info" placeholder="请输入操作信息...">
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-2 control-label">状态</label>
                            <div class="col-sm-4">
                                <input type="radio" value="1"  name="find_status" >成功
                                <input type="radio" value="0"  name="find_status" >错误
                            </div>
                        </div>



                        <div style="text-align: center">
                            <button class="btn btn-primary" onclick="searcher()" type="button"><span class="glyphicon glyphicon-search"></span >查询</button>
                            <button class="btn btn-default" type="reset"><span class="glyphicon glyphicon-refresh"></span >重置</button>
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
                <button class="btn btn-primary" onclick="addProduct()"><span class="glyphicon glyphicon-plus"></span>添加</button>
                <button class="btn btn-danger" ><span class="glyphicon glyphicon-trash"></span>批量删除</button>
                <button class="btn btn-primary" onclick="excelDownLoad()" ><span class="glyphicon glyphicon-download-alt"></span>Excel导出</button>
                <button class="btn btn-primary" onclick="pdfDownLoad()" ><span class="glyphicon glyphicon-download-alt"></span>pdf导出</button>
                <button class="btn btn-primary" onclick="wordDownLoad()" ><span class="glyphicon glyphicon-download-alt"></span>word导出</button>
            </div>

        </div>
    </div>
    <%--  面板   用户展示面板 --%>
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-info">
                <div class="panel-heading">日志列表</div>
                <%--用户表格展示--%>
                <table id="logable" class="table table-striped table-bordered" style="width:100%">
                    <thead>
                    <tr>
                        <td>id</td>
                        <td>用户名</td>
                        <td>真实姓名</td>
                        <td>状态</td>
                        <td>操作时间</td>
                        <td>操作信息</td>
                        <td>日志信息</td>
                        <td>错误信息</td>
                        <td>详细参数</td>
                    </tr>
                    </thead>
                    <tbody></tbody>
                    <tfoot>
                    <tr>
                        <td>id</td>
                        <td>用户名</td>
                        <td>真实姓名</td>
                        <td>状态</td>
                        <td>操作时间</td>
                        <td>操作信息</td>
                        <td>日志信息</td>
                        <td>错误信息</td>
                        <td>详细参数</td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>
</div>
<script>

    $(function(){
        getCurrentList();
        initdatedate("find_beginTime");
        initdatedate("find_endTime");
    });

    //获取查询条件
    function searcher(){

        var v_beginTime = $("#find_beginTime").val();
        var v_endTime = $("#find_endTime").val();
        var v_userName = $("#find_userName").val();
        var v_realName = $("#find_realName").val();
        var v_info = $("#find_info").val();
        var v_status = $("input[name='find_status']:checked").val();
        var param = {};
        if(!!v_beginTime){
            param.beginTime=v_beginTime;
        }
        if(!!v_endTime){
            param.endTime=v_endTime;
        }

        param.userName=v_userName;
        param.realName=v_realName;
        param.info=v_info;
        param.status=v_status;
        logDataTable.settings()[0].ajax.data=param;
        logDataTable.ajax.reload();

    }



    var logDataTable;
    //获取当前页面数据
    function getCurrentList(){
        logDataTable =  $("#logable").DataTable({
            "language": {
                "url":"/js/Chinese.json"
            },
            "processing": true,
            "serverSide": true,
            "destroy":true,
            "ajax": {
                "url": "/log/getCurrentList.jhtml",
                "type": "POST",
                "dataSrc": function(result){
                    result.recordsFiltered =result.data.recordsFiltered;
                    result.recordsTotal =result.data.recordsTotal;
                    result.draw =result.data.draw;
                    return result.data.data;
                },
            },
            "columns": [
                { "data": "id" },
                { "data": "userName" },
                { "data": "realName" },
                { "data": function (data) {
                        return data.status==1?"成功":"错误";
                    } },

                {"data":"currentDate"},
                {"data":"content"},
                {"data":"info"},
                {"data":"errorMsg"},
                {"data":"detail",
                    "render":function(data, type, full, meta){
                        if(!!data){
                            if(data.length>20){
                                return data.substring(0,14)+"<a href='javaScript:showAll(\""+data+"\")'>更多</a>";
                            }else{
                                return data;
                            }
                        }else{
                            return "";
                        }
                    }
                },
            ],
            "lengthMenu": [ 5, 10, 20, 40, 100 ],
            "searching":false,

        });
    }

    //展示td上内容
    function showAll(data){

        var str = '<div style="display:block;word-break: break-all;word-wrap: break-word;\n">'+data+'</div>';

        bootbox.alert({

            width:' 200px',
            title:"详情",
            message:str,

        });
    }


    //时间插件调用  新增修改
    function initdatedate(initid) {
        $('#'+initid).datetimepicker({
            format: 'YYYY-MM-DD HH:mm',
            locale: moment.locale('zh-CN'),
            showClear:true,
        });
    }
</script>
</body>
</html>
