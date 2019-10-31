<%--
  Created by IntelliJ IDEA.
  User: 、、
  Date: 2019/9/29
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>分类</title>
</head>
<body>
<%-- nav导航条 --%>
<jsp:include page="/common/nav/nav.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel panel-info">
                <div class="panel-heading">分类管理
                    <button type="button" class="btn btn-primary" onclick="addClass()">新增</button>
                    <button type="button" class="btn btn-info" onclick="updateClass()">修改</button>
                    <button type="button" class="btn btn-danger" onclick="deleteClass()">删除</button>
                </div>
                <ul id="tree" class="ztree" style="width:230px; overflow:auto;"></ul>
            </div>
        </div>
    </div>
</div>
<%-- 新增div --%>
<div id="add_classDiv" style="display: none">
    <form class="form-horizontal" >
        <div class="form-group">
            <label  class="col-sm-2 control-label">分类名:</label>
            <div class="col-sm-10">
                <input type="text"  id="add_className" class="form-control"  placeholder="分类名...">
            </div>
        </div>
    </form>
</div>
<%-- 修改div --%>
<div id="update_classDiv" style="display: none">
    <form class="form-horizontal" >
        <div class="form-group">
            <label  class="col-sm-2 control-label">分类名:</label>
            <div class="col-sm-10">
                <input type="text"  id="update_className" class="form-control"  placeholder="分类名...">
            </div>
        </div>
    </form>
</div>

<script>
    $(function(){
        findAllClassList();
        initadd_classDiv();
        initupdate_classDiv();

    });
    var zTreeObj;
    var add_classDiv;
    var update_classDiv;
    //初始化新增div
    function initadd_classDiv(){
        add_classDiv = $("#add_classDiv").html();
    }
    //初始化修改div
    function initupdate_classDiv(){
        update_classDiv = $("#update_classDiv").html();
    }
    //获取所有
    function findAllClassList(){
        $.ajax({
            url:"/class/findAllClass.jhtml",
            type:"post",
            data:{},
            dataType:"json",
            success:function(result){
                if(result.code == 200){

                    setting = {
                        view: {
                            selectedMulti: true
                        },
                        data: {
                            simpleData: {
                                enable: true,
                                idKey: "id",
                                pIdKey: "pid",
                                rootPId: 0
                            }
                        }
                    };
                    zTreeNodes = result.data;


                    zTreeObj = $.fn.zTree.init($("#tree"), setting, zTreeNodes);


                }
            }
        });
    }

    // 删除
    function deleteClass(){
        var nodes = zTreeObj.getSelectedNodes();
        if(nodes.length > 0){
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
                        //后台删除
                        var nodearr =   zTreeObj.transformToArray(nodes);
                        var idarr =[];
                        for(var i =0 ; i <nodearr.length ; i++ ){
                            idarr.push(nodearr[i].id)
                        }
                        //调用后台删除方法
                        $.ajax({
                            url:"/class/deleteClass.jhtml",
                            data:{"list":idarr},
                            type:"post",
                            dataType:"json",
                            success:function(result){
                                if(result.code == 200){
                                    bootbox.alert({
                                        title:"提示信息",
                                        message:"<i class='glyphicon glyphicon-exclamation-sign'></i>删除成功",
                                        size: 'small',
                                    });
                                    for(var i = nodearr.length - 1; i >= 0 ; i-- ) {
                                        zTreeObj.removeNode(nodes[0]);
                                    }
                                }
                            }
                        });
                    }
                }
            });



        }else{
            bootbox.alert({
                title:"提示信息",
                message:"<i class='glyphicon glyphicon-exclamation-sign'></i>请选择一个节点",
                size: 'small',
            });
        }

    }

    //新增
    function addClass(){
        var nodes =  zTreeObj.getSelectedNodes();
        if(nodes.length == 1){
            var  addclass =    bootbox.dialog({
                message: $("#add_classDiv form"),
                title:"新增菜单",
                buttons: {
                    confirm: {
                        label: '<i class="glyphicon glyphicon-ok"></iclass>确定',
                        className: 'btn-success',
                        callback:function() {
                            var v_pid =nodes[0].id ;
                            var v_name = $("#add_className",addclass).val();

                            var  v_parentNode =nodes[0] ;
                            $.ajax({
                                url:"/class/addClass.jhtml",
                                data:{"pid":v_pid,"name":v_name},
                                dataType:"json",
                                type:"post",
                                success:function(result){
                                    if(result.code == 200){
                                        bootbox.alert({
                                            title:"提示信息",
                                            message:"<i class='glyphicon glyphicon-exclamation-sign'></i>新增成功",
                                            size: 'small',
                                        })
                                        var newNodes = {"name":v_name,"pid":v_pid,"id":result.data};
                                        zTreeObj.addNodes(v_parentNode, newNodes);
                                    }
                                }

                            })


                        }
                    },
                    cancel: {
                        label: '<i class="glyphicon glyphicon-remove"></i>取消',
                        className: 'btn-danger'
                    }
                },

            });
            $("#add_classDiv").html(add_classDiv);

        }else if(nodes.length > 1){
            bootbox.alert({
                title:"提示信息",
                message:"<i class='glyphicon glyphicon-exclamation-sign'></i>只能选择一个节点",
                size: 'small',
            });
        }else{
            bootbox.alert({
                title:"提示信息",
                message:"<i class='glyphicon glyphicon-exclamation-sign'></i>请选择一个节点",
                size: 'small',
            });
        }


    }


    //修改
    function updateClass(){
        var nodes =  zTreeObj.getSelectedNodes();
        if(nodes.length == 1){
            var v_name = nodes[0].name;
            $("#update_className").val(v_name);

            var updateclass =    bootbox.dialog({
                message: $("#update_classDiv form"),
                title:"修改菜单",
                buttons: {
                    confirm: {
                        label: '<i class="glyphicon glyphicon-ok"></iclass>确定',
                        className: 'btn-success',
                        callback:function() {
                            var v_id =nodes[0].id ;
                            var v_newName = $("#update_className",updateclass).val();
                            $.ajax({
                                url:"/class/updateClass.jhtml",
                                data:{"id":v_id,"name":v_newName},
                                dataType:"json",
                                type:"post",
                                success:function(result){
                                    if(result.code == 200){
                                        bootbox.alert({
                                            title:"提示信息",
                                            message:"<i class='glyphicon glyphicon-exclamation-sign'></i>修改成功",
                                            size: 'small',
                                        })
                                        nodes[0].name = v_newName;
                                        zTreeObj.updateNode(nodes[0]);
                                    }
                                }

                            })


                        }
                    },
                    cancel: {
                        label: '<i class="glyphicon glyphicon-remove"></i>取消',
                        className: 'btn-danger'
                    }
                },

            });
            $("#update_classDiv").html(update_classDiv);

        }else if(nodes.length > 1){
            bootbox.alert({
                title:"提示信息",
                message:"<i class='glyphicon glyphicon-exclamation-sign'></i>只能选择一个节点",
                size: 'small',
            });
        }else{
            bootbox.alert({
                title:"提示信息",
                message:"<i class='glyphicon glyphicon-exclamation-sign'></i>请选择一个节点",
                size: 'small',
            });
        }


    }

</script>

</body>
</html>
