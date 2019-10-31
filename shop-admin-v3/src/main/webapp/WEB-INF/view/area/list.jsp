<%--
  Created by IntelliJ IDEA.
  User: 、、
  Date: 2019/8/22
  Time: 23:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>

    <title>地区管理</title>

</head>
<body>
<%-- nav导航条 --%>
<jsp:include page="/common/nav/nav.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel panel-info">

                    <div class="panel-heading">菜单管理
                        <button type="button" class="btn btn-primary" onclick="addArea()">新增</button>
                        <button type="button" class="btn btn-info" onclick="updateArea()">修改</button>
                        <button type="button" class="btn btn-danger" onclick="deleteArea()">删除</button>
                    </div>


                                <ul id="tree" class="ztree" style="width:230px; overflow:auto;"></ul>

                     </div>
                </div>
            </div>
        </div>
        </div>
    </div>
</div>

<%-- ztree容器 --%>

<%-- 新增 --%>

<script>
$(function(){
    getAllAreaList();
})

//查询所有地区集合
function getAllAreaList(){
    $.ajax({
        url:"/area/findAllArea.jhtml",
        data:{},
        type:"post",
        dataType:"json",
        async:true,
        success:function(result){
            if(result.code==200){

                var zTreeObj,
                    setting = {
                        view: {
                            selectedMulti: false
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

//修改 地区  名
    function updateArea(){


        var treeObj = $.fn.zTree.getZTreeObj("tree");
     var selectedNodes =    treeObj.getSelectedNodes();

     if(selectedNodes.length==1){
         var nodeName = selectedNodes[0].name;
         var nodeid = selectedNodes[0].id;
         bootbox.dialog({
             message: "<input type='text' class=\'form-control\' value='"+nodeName+"'  placeholder=\'修改地区名...\' id = 'update_areaName'>",
             title:"修改地区名",
             buttons: {
                 confirm: {
                     label: '<i class="glyphicon glyphicon-ok"></iclass>确定',
                     className: 'btn-success',
                     callback:function() {
                         var newAreaName = $("#update_areaName").val();

                            $.ajax({
                                url:"/area/updateAreaByid.jhtml",
                                data:{"id":nodeid,"name":newAreaName},
                                dataType:"json",
                                type:"post",
                                success:function(result){
                                    if(result.code == 200){
                                        bootbox.alert("修改成功")
                                        selectedNodes[0].name = newAreaName;
                                        treeObj.updateNode(selectedNodes[0]);
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



     }else if(selectedNodes.length >1){
         bootbox.alert("只能选择一个");
     }else{
         bootbox.alert("请选择节点修改");
     }

    }


//新增
function addArea(){
    bootbox.dialog({
        message: "<input type='text' class=\'form-control\'  placeholder=\'新增地区名...\' id = 'add_areaName'>",
        title:"新增地区名",
        buttons: {
            confirm: {
                label: '<i class="glyphicon glyphicon-ok"></iclass>确定',
                className: 'btn-success',
                callback:function() {
                    var treeObj = $.fn.zTree.getZTreeObj("tree");
                    var selectedNodes =    treeObj.getSelectedNodes();
                    var pid;
                    var parentNode;
                    var name = $("#add_areaName").val();

                    if(selectedNodes.length == 1){
                         pid =  selectedNodes[0].id;
                        parentNode =selectedNodes[0] ;
                    }else{
                        pid = 5555;
                    }
                    $.ajax({
                        url:"/area/addArea.jhtml",
                        data:{"pid":pid,"name":name},
                        dataType:"json",
                        type:"post",
                        success:function(result){
                            if(result.code == 200){
                                bootbox.alert("新增成功")
                                var newNodes = {"name":name,"pId":pid,"id":result.data};
                                treeObj.addNodes(parentNode, newNodes);
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

}

//删除
    function deleteArea(){
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        var selectedNodes =    treeObj.getSelectedNodes();
        if(selectedNodes.length>0){
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
                        var id = selectedNodes[0].id;
                        var idarr =[];
                        var selectedarr =  treeObj.transformToArray(selectedNodes[0]);
                        for (var i = 0; i < selectedarr.length;i++) {
                            idarr.push(selectedarr[i].id);
                        } 

                        $.ajax({
                            url:"/area/deleteAreaById.jhtml",
                            data:{"idarr":idarr.toString()},
                            dataType:"json",
                            type:"post",
                            success:function(result){
                                if(result.code == 200){
                                    bootbox.alert("删除成功")
                                    //前台删除
                                    treeObj.removeNode(selectedNodes[0]);
                                }
                            }
                        });



                    }
                }
            });

        }else{
            alert("请选择节点")
        }
    }

</script>
</body>
</html>
