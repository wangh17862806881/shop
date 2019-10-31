<%--
  Created by IntelliJ IDEA.
  User: 、、
  Date: 2019/8/26
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>

    <title>角色操作</title>

</head>
<body>
<%-- nav导航条 --%>
<jsp:include page="/common/nav/nav.jsp"></jsp:include>
<%-- 栅格系统  网格系统  最外层 容器 container --%>
<div id="container" class="container">

    <%-- 按钮  工具栏 --%>
    <div class="row">
        <div class="col-md-12">

            <div style="background-color: #ffd3d3">
                <button class="btn btn-primary" onclick="addRole()"><span class="glyphicon glyphicon-plus"></span>添加</button>
                <button class="btn btn-danger"  id="deletemultibyids"><span class="glyphicon glyphicon-trash"></span>批量删除</button>
            </div>

        </div>
    </div>
    <%--  面板   品牌展示面板 --%>
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-info">
                <div class="panel-heading">角色列表</div>
                <%--用户表格展示--%>
                <table id="roleTable" class="table table-striped table-bordered" style="width:100%">
                    <thead>
                    <tr>
                        <td>选择</td>
                        <td>角色名</td>
                        <td>资源名</td>
                        <td>操作</td>
                    </tr>
                    </thead>
                    <tbody></tbody>
                    <tfoot>
                    <tr>
                        <td>选择</td>
                        <td>角色名</td>
                        <td>资源名</td>
                        <td>操作</td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>
</div>
<%-- 新增 弹出框 --%>
<div id="roleInsertFormData" style="display: none;">
    <form class="form-horizontal" >
        <div class="form-group">
        <label  class="col-sm-2 control-label">角色名:</label>
        <div class="col-sm-10">
            <input type="text"  id="add_roleName" class="form-control"  placeholder="角色名...">
        </div>
    </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">菜单:</label>
            <div class="col-sm-10">
                <ul id="addtree" class="ztree" style="width:230px; overflow:auto;"></ul>
            </div>
        </div>
    </form>
</div>
<%-- 修改 弹出框 --%>
<div id="roleUpdateFormData" style="display: none">

    <form class="form-horizontal" >
        <div class="form-group">
            <label  class="col-sm-2 control-label">角色名:</label>
            <div class="col-sm-10">
                <input type="text"  id="update_roleName" class="form-control"  placeholder="角色名...">
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">菜单:</label>
            <div class="col-sm-10">
                <ul id="updatetree" class="ztree" style="width:230px; overflow:auto;"></ul>
            </div>
        </div>
        <input type="hidden" id = "update_id">
    </form>
</div>


<script>

    $(function(){
        getALLRoleList();
        initAddDiv();
        initUpdateDiv();
    });
//初始化新增div
    var roleInsertFormData;
    function initAddDiv(){
        roleInsertFormData = $("#roleInsertFormData").html();
        $("#roleInsertFormData").html("");
    }
//初始化修改div
    var roleUpdateFormData;
    function initUpdateDiv(){
        roleUpdateFormData = $("#roleUpdateFormData").html();
        $("#roleUpdateFormData").html("");
    }

//获取所有品牌集合
    function getALLRoleList(){

        userDataTable =  $("#roleTable").DataTable({
            "language": {
                "url":"/js/Chinese.json"
            },
            "processing": true,
            "serverSide": true,
            "destroy":true,
            "ajax": {
                "url": "/role/getRoleList.jhtml",
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
                { "data": "roleName" },
                { "data": "menuNames" },
                { "data": "id",
                    "render": function(data, type, row, meta){
                        return "<div class=\"btn-group\" role=\"group\" >\n" +
                            "  <button type=\"button\" onclick='deleteRoleById(\""+data+"\")' class=\"btn btn-danger\"><i class='glyphicon glyphicon-trash'></i>删除</button>\n" +
                            "  <button type=\"button\" onclick='updateRole(\""+data+"\")' class=\"btn btn-primary\"><i class='glyphicon glyphicon-pencil'></i>修改</button>\n" +
                            "</div>";
                    } },

            ],
            "lengthMenu": [ 5, 10, 20, 40, 100 ],
            "searching":false,

        });


    }

//添加角色、
    function addRole(){
        var  productbootbox =  bootbox.dialog({
            message: roleInsertFormData,
            title:"新增角色",
            buttons: {
                confirm: {
                    label: '<i class="glyphicon glyphicon-ok"></iclass>确定',
                    className: 'btn-success',
                    callback:function(){
                        //获取输入框的值
                        var v_roleName = $("#add_roleName").val();
                        //获取菜单表中勾选的值
                        var checkedNodes = zTreeObj.getCheckedNodes(true);
                        console.log(checkedNodes)
                        var idarr = [];
                        for(var i = checkedNodes.length-1 ; i >= 0; i-- ){
                            idarr.push(checkedNodes[i].id)
                        }

                        $.ajax({
                            url:"/role/addRole.jhtml",
                            type:"post",
                            data:{"roleName":v_roleName,"idarr":idarr},
                            dataType:"json",
                            success:function(data){

                                if(data.code == 200){
                                  getALLRoleList();
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
        findAllResourceList("addtree");
    }


// 单个删除 通过id
    function deleteRoleById(id){
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
                        url:"/role/deleteRoleById.jhtml",
                        type:"post",
                        data:{"id":id},
                        dataType:"json",
                        success:function(data){
                            if(data.code==200){
                                getALLRoleList();
                            }


                        }

                    });
                }
            }
        });
    }

//回显角色
    function updateRole(id){
        console.log(zTreeObj);
        //回显
        $.ajax({
            url:"/role/findRoleById.jhtml",
            type:"post",
            data:{"id":id},
            dataType:"json",
            success:function(data){
                if(data.code == 200){
                    $("#roleUpdateFormData").html(roleUpdateFormData);
                    findAllResourceList("updatetree");
                    $("#update_roleName").val(data.data.roleName);
                    $("#update_id").val(data.data.id);
                    var idarr = data.data.resourceIdList;
                    for(var i = idarr.length-1; i >= 0 ; i--){
                        console.log(zTreeObj);
                        var node = zTreeObj.getNodeByParam("id", idarr[i], null);
                                if(!!node){
                                    zTreeObj.checkNode(node, true);
                                }



                    }
                    updateRoleAndResource();
                }

            }
        });
    }
//修改角色
    function updateRoleAndResource(){
        var productUpdatebootbox =  bootbox.dialog({
            message: $("#roleUpdateFormData form"),
            title:"修改商品",
            buttons: {
                confirm: {
                    label: '<i class="glyphicon glyphicon-ok"></iclass>确定',
                    className: 'btn-success',
                    callback:function(){
                        //获取输入框的值
                        var v_roleName = $("#update_roleName").val();
                        var v_id = $("#update_id").val();
                        //定义json对象
                        var param = {};
                        param.roleName = v_roleName;
                        param.id = v_id;
                        //获取菜单表中勾选的值
                        var checkedNodes = zTreeObj.getCheckedNodes(true);
                        console.log(checkedNodes)
                        var idarr = [];
                        for(var i = checkedNodes.length-1 ; i >= 0; i-- ){
                            idarr.push(checkedNodes[i].id)
                        }
                        param.idarr = idarr;
                        $.ajax({
                            url:"/role/updateRoleById.jhtml",
                            type:"post",
                            data:param,
                            dataType:"json",
                            success:function(data){
                                if(data.code == 200){
                                    bootbox.alert({
                                        title:"提示信息",
                                        message:"<i class='glyphicon glyphicon-exclamation-sign'></i>修改成功",
                                        size: 'small',
                                    })
                                    getALLRoleList();
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

    var  zTreeObj;
//获取所有资源 菜单 集合
    function findAllResourceList(id){
        $.ajax({
            url:"/resource/findAllResourceList.jhtml",
            type:"post",
            data:{},
            async:false,
            dataType:"json",
            success:function(result){
                if(result.code == 200){
                   var  setting = {
                        data: {
                            simpleData: {
                                enable: true,
                                idKey: "id",
                                pIdKey: "pId",
                                rootPId: 0
                            }
                        },
                        check: {
                            enable: true,
                            chkStyle:"checkbox",
                            chkboxType: { "Y": "ps", "N": "ps" }
                        }
                    };
                 var  zTreeNodes = result.data;
                    zTreeObj = $.fn.zTree.init($("#"+id), setting, zTreeNodes);
                    zTreeObj.expandAll(true);

                }
            }
        });
    }
</script>
</body>
</html>
