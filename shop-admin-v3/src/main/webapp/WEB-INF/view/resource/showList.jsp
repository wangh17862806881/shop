<%--
  Created by IntelliJ IDEA.
  User: 、、
  Date: 2019/8/25
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>

    <title>ztree展示资源 / 菜单</title>

</head>
<body>
<%-- nav导航条 --%>
<jsp:include page="/common/nav/nav.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel panel-info">
                <div class="panel-heading">资源管理
                    <button type="button" class="btn btn-primary" onclick="addResource()">新增</button>
                    <button type="button" class="btn btn-info" onclick="updateResource()">修改</button>
                    <button type="button" class="btn btn-danger" onclick="deleteResource()">删除</button>
                </div>
                <ul id="tree" class="ztree" style="width:230px; overflow:auto;"></ul>
            </div>
        </div>
    </div>
</div>

<%-- 新增div --%>
<div id="add_resourceDiv" style="display: none">
    <form class="form-horizontal" >
    <div class="form-group">
        <label  class="col-sm-2 control-label">菜单名:</label>
        <div class="col-sm-10">
            <input type="text"  id="add_menuName" class="form-control"  placeholder="菜单名...">
        </div>
    </div>
    <div class="form-group">
        <label  class="col-sm-2 control-label">类型:</label>
        <div class="col-sm-10">
            <input type="radio"  name="add_type" value="1" >菜单
            <input type="radio"  name="add_type" value="2" >按钮
        </div>
    </div>
    <div class="form-group">
        <label  class="col-sm-2 control-label">路径:</label>
        <div class="col-sm-10">
            <input type="text"  id="add_url" class="form-control"  placeholder="路径...">
        </div>
    </div>
    </form>
</div>
<%-- 修改div --%>
<div id="update_resourceDiv" style="display: none">
    <form class="form-horizontal" >
    <div class="form-group">
        <label  class="col-sm-2 control-label">菜单名:</label>
        <div class="col-sm-10">
            <input type="text"  id="update_menuName" class="form-control"  placeholder="菜单名...">
        </div>
    </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">类型:</label>
            <div class="col-sm-10">
                <input type="radio"  name="update_type" value="1" >菜单
                <input type="radio"  name="update_type" value="2" >按钮
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">路径:</label>
            <div class="col-sm-10">
                <input type="text"  id="update_url" class="form-control"  placeholder="路径...">
            </div>
        </div>
    </form>
</div>



<script>
    $(function(){
        findAllResourceList();
        initadd_resourceDiv();
        initupdate_resourceDiv();

    });
    var zTreeObj;
    var add_resourceDiv;
    var update_resourceDiv;
    //初始化新增div
    function initadd_resourceDiv(){
        add_resourceDiv = $("#add_resourceDiv").html();
    }
    //初始化修改div
    function initupdate_resourceDiv(){
        update_resourceDiv = $("#update_resourceDiv").html();
    }
//获取所有资源 菜单 集合
    function findAllResourceList(){
        $.ajax({
            url:"/resource/findAllResourceList.jhtml",
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
                                    pIdKey: "pId",
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
    function deleteResource(){
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
                            url:"/resource/deleteResource.jhtml",
                            data:{"idarr":idarr},
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
    function addResource(){
      var nodes =  zTreeObj.getSelectedNodes();
        if(nodes.length == 1){
        var  addreaource =    bootbox.dialog({
                message: $("#add_resourceDiv form"),
                title:"新增菜单",
                buttons: {
                    confirm: {
                        label: '<i class="glyphicon glyphicon-ok"></iclass>确定',
                        className: 'btn-success',
                        callback:function() {
                            var v_pid =nodes[0].id ;
                            var v_name = $("#add_menuName",addreaource).val();
                            var v_add_type = $("input[name='add_type']:checked",addreaource).val();
                            var v_url = $("#add_url",addreaource).val();
                            var  v_parentNode =nodes[0] ;
                            $.ajax({
                                url:"/resource/addResource.jhtml",
                                data:{"fatherId":v_pid,"menuName":v_name,"type":v_add_type,"url":v_url},
                                dataType:"json",
                                type:"post",
                                success:function(result){
                                    if(result.code == 200){
                                        bootbox.alert({
                                            title:"提示信息",
                                            message:"<i class='glyphicon glyphicon-exclamation-sign'></i>新增成功",
                                            size: 'small',
                                        })
                                        var newNodes = {"name":v_name,"pId":v_pid,"id":result.data,"type":v_add_type,"menuUrl":v_url};
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
            $("#add_resourceDiv").html(add_resourceDiv);

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
    function updateResource(){
      var nodes =  zTreeObj.getSelectedNodes();
        if(nodes.length == 1){
        var v_name = nodes[0].name;
        var v_url = nodes[0].menuUrl;
        var v_type = nodes[0].type;
        $("#update_menuName").val(v_name);
        $("input[name=update_type]").each(function(){
            if(this.value == v_type){
                this.checked = true;
                return;
            }
        });
            $("#update_url").val(v_url);
        var updatereaource =    bootbox.dialog({
                message: $("#update_resourceDiv form"),
                title:"修改菜单",
                buttons: {
                    confirm: {
                        label: '<i class="glyphicon glyphicon-ok"></iclass>确定',
                        className: 'btn-success',
                        callback:function() {
                            var v_id =nodes[0].id ;
                            var v_newName = $("#update_menuName",updatereaource).val();
                            var v_url = $("#update_url",updatereaource).val();
                            var v_type = $("input[name='update_type']:checked",updatereaource).val();
                            $.ajax({
                                url:"/resource/updateResource.jhtml",
                                data:{"id":v_id,"menuName":v_newName,"url":v_url,"type":v_type},
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
                                        nodes[0].type = v_type;
                                        nodes[0].menuUrl = v_url;
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
            $("#update_resourceDiv").html(update_resourceDiv);

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
