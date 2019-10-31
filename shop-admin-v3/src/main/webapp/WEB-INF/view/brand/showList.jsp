
<%--
  Created by IntelliJ IDEA.
  User: 、、
  Date: 2019/8/26
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
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
                <div class="panel-heading">品牌查询</div>
                <%-- 条件查询面板内容 --%>
                <div class="panel-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label  class="col-sm-2 control-label">品牌名</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="find_brandName" placeholder="请输入品牌名...">
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
                <button class="btn btn-primary" onclick="addBrand()"><span class="glyphicon glyphicon-plus"></span>添加</button>
                <button class="btn btn-danger"  id="deletemultibyids"><span class="glyphicon glyphicon-trash"></span>批量删除</button>
            </div>

        </div>
    </div>
    <%--  面板   品牌展示面板 --%>
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-info">
                <div class="panel-heading">品牌列表</div>
                <%--用户表格展示--%>
                <table id="brandTable" class="table table-striped table-bordered" style="width:100%">
                    <thead>
                    <tr>
                        <td>选择</td>
                        <td>品牌名</td>
                        <td>图片</td>
                        <td>排序</td>
                        <td>状态</td>
                        <td>操作</td>
                    </tr>
                    </thead>
                    <tbody></tbody>
                    <tfoot>
                    <tr>
                        <td>选择</td>
                        <td>品牌名</td>
                        <td>图片</td>
                        <td>排序</td>、
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
<div id="brandInsertFormData" style="display: none;">

    <form class="form-horizontal" >
        <div class="form-group">
            <label  class="col-sm-2 control-label">品牌名:</label>
            <div class="col-sm-10">
                <input type="text"  id="add_brandName" class="form-control"  placeholder="品牌名...">
            </div>
        </div>


        <div class="form-group">
            <label  class="col-sm-2 control-label">图片:</label>
            <div class="col-sm-10">
                <input type="file" name="myfile"  id="add_uploadImage"   class="myfile"  />
                <input type="hidden"  id="add_ImagePath"  />

            </div>
        </div>


    </form>
</div>
<%-- 修改 弹出框 --%>
<div id="brandUpdateFormData" style="display: none">

    <form class="form-horizontal" >
        <div class="form-group">
            <label  class="col-sm-2 control-label">品牌名:</label>
            <div class="col-sm-10">
                <input type="text"  id="update_brandName" class="form-control"  placeholder="品牌名...">
            </div>
        </div>

        <div class="form-group">
            <label  class="col-sm-2 control-label">图片:</label>
            <div class="col-sm-10">
                <input type="file" name="myfile"  id="update_uploadImage"   class="myfile"  />
                <input type="hidden"  id="update_ImagePath" />
                <input type="hidden"  id="update_oldImagePath" />

            </div>
        </div>


        <input type="hidden" id = "update_id">
    </form>
</div>





<script>
    $(function(){
        getALLBrandList();
        initAddDiv();
        initUpdateDiv();
    });
    //初始化新增div
    var brandInsertFormData;
    function initAddDiv(){

        brandInsertFormData = $("#brandInsertFormData").html();
        $("#brandInsertFormData").html("");
    }
    //初始化修改div
    var brandUpdateFormData;
    function initUpdateDiv(){

        brandUpdateFormData = $("#brandUpdateFormData").html();
        $("#brandUpdateFormData").html("");
    }


//获取查询条件
    function searcher(){

        var v_brandName = $("#find_brandName").val();

        var param = {};

        param.brandName=v_brandName;
        brandDataTable.settings()[0].ajax.data=param;
        brandDataTable.ajax.reload();

    }



//获取所有品牌集合
    function getALLBrandList(){

        brandDataTable =  $("#brandTable").DataTable({
            "language": {
                "url":"/js/Chinese.json"
            },
            "processing": true,
            "serverSide": true,
            "destroy":true,
            "ajax": {
                "url": "/brand/getBrandList.jhtml",
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
                { "data": "brandName" },
                { "data": function (data) {
                        return '<img src="'+data.imgURL+'">';
                    } },
                { "data": "sort" ,
                    "render":function(data, type, row, meta){
                    return '<input type="text" value="'+data+'" id="sort_'+row.id+'"  class="form-control"  style="width: 100px">' +
                        '<button type="button" onclick="updateBrandSort(\''+row.id+'\')" class="btn btn-primary"><i class="glyphicon glyphicon-pencil"></i>更新</button>';
                    }
                },
                { "data": function(data){
                    return data.popular == 1 ?"热销":"正常";
                }
                },
                { "data": "id",
                    "render": function(data, type, row, meta){
                    var v_text = "";
                    var v_buttonclass = "";
                    var v_iclass ="";
                    var v_popular = "";
                    if(row.popular == 1){
                         v_text = "正常";
                         v_buttonclass = "btn btn-warning";
                         v_iclass ="glyphicon glyphicon-chevron-down";
                        v_popular = 0;
                    }else{
                         v_text = "热销";
                         v_buttonclass = "btn btn-info";
                         v_iclass ="glyphicon glyphicon-chevron-up";
                        v_popular = 1;
                    }

                        return "<div class=\"btn-group\" role=\"group\" >\n" +
                            "  <button type=\"button\" onclick='deleteBrandById(\""+data+"\")' class=\"btn btn-danger\"><i class='glyphicon glyphicon-trash'></i>删除</button>\n" +
                            "  <button type=\"button\" onclick='updateBrand(\""+data+"\")' class=\"btn btn-primary\"><i class='glyphicon glyphicon-pencil'></i>修改</button>\n" +
                            "  <button type=\"button\" onclick='updateBrandPopular(\""+data+"\",\""+v_popular+"\")' class=\""+v_buttonclass+"\"><i class='"+v_iclass+"'></i>"+v_text+"</button>\n" +
                            "</div>";
                    } },
            ],
            "lengthMenu": [ 5, 10, 20, 40, 100 ],
            "searching":false,

        });
    }

//修改排序字段
    function updateBrandSort(id){

        bootbox.confirm({
            message: "您确定要更新排序吗？",
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
                    var v_sort = $("#sort_"+id).val();
                    $.ajax({
                        url:"/brand/updateBrandSort.jhtml",
                        type:"post",
                        data:{"id":id,"sort":v_sort},
                        dataType:"json",
                        success:function(data){
                            if(data.code==200){
                                getALLBrandList();
                            }
                        }

                    });
                }
            }
        });
    }

//更改热销状态
   function updateBrandPopular(id,popular){

       bootbox.confirm({
           message: "您确定要修改状态吗？",
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
                       url:"/brand/updateBrandPopular.jhtml",
                       type:"post",
                       data:{"id":id,"popular":popular},
                       dataType:"json",
                       success:function(data){
                           if(data.code==200){
                               getALLBrandList();
                           }


                       }

                   });
               }
           }
       });

   }

// 删除单个
    function deleteBrandById(id){
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
                        url:"/brand/deleteBrandById.jhtml",
                        type:"post",
                        data:{"id":id},
                        dataType:"json",
                        success:function(data){
                            if(data.code==200){
                              getALLBrandList();
                            }


                        }

                    });
                }
            }
        });
    }

//新增品牌
    function addBrand(){
       var  productbootbox =  bootbox.dialog({
            message: brandInsertFormData,
            title:"新增品牌",
            buttons: {
                confirm: {
                    label: '<i class="glyphicon glyphicon-ok"></iclass>确定',
                    className: 'btn-success',
                    callback:function(){
                        //获取输入框的值
                        var v_brandName = $("#add_brandName").val();
                        var v_imgURL = $("#add_ImagePath").val();


                        //定义json对象
                        var param = {};
                        param.brandName = v_brandName;
                        param.imgURL = v_imgURL;


                        $.ajax({
                            url:"/brand/addbrand.jhtml",
                            type:"post",
                            data:param,
                            dataType:"json",
                            success:function(data){

                                if(data.code == 200){
                                  getALLBrandList();
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
        fileinput5("add","");
    }

//修改品牌
    function updateBrand(id){
        //回显
        $.ajax({
            url:"/brand/findBrandById.jhtml",
            type:"post",
            data:{"id":id},
            dataType:"json",
            success:function(data){

                if(data.code == 200){
                    $("#brandUpdateFormData").html(brandUpdateFormData);
                    $("#update_brandName").val(data.data.brandName);
                    $("#update_oldImagePath").val(data.data.imgURL);
                    $("#update_id").val(data.data.id);

                    var productUpdatebootbox =  bootbox.dialog({
                        message: $("#brandUpdateFormData form"),
                        title:"修改商品",
                        buttons: {
                            confirm: {
                                label: '<i class="glyphicon glyphicon-ok"></iclass>确定',
                                className: 'btn-success',
                                callback:function(){
                                    //获取输入框的值
                                    var v_brandName = $("#update_brandName").val();
                                    var v_imgURL = $("#update_ImagePath").val();
                                    var v_oldimgURL = $("#update_oldImagePath").val();
                                    var v_id = $("#update_id").val();

                                    //定义json对象
                                    var param = {};
                                    param.brandName = v_brandName;
                                    param.imgURL = v_imgURL;
                                    param.oldimgURL = v_oldimgURL;
                                    param.id = v_id;


                                    $.ajax({
                                        url:"/brand/updateBrandById.jhtml",
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
                                               getALLBrandList();
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
                    fileinput5("update",data.data.imgURL);
                }else{
                    bootbox.alert({
                        title:"提示信息",
                        message:"<i class='glyphicon glyphicon-exclamation-sign'></i>方法失败",
                        size: 'small',
                    })
                }

            }
        });



    }


//图片上传
    function fileinput5(id,imgurl){
        $("#"+id+"_uploadImage").fileinput({
            language: 'zh', //设置语言,
            uploadUrl : "/file/uploadFile.jhtml", /*上传图片的url*/
            allowedFileExtensions : [ 'jpg', 'png', 'gif' ],//接收的文件后缀
            showUpload:true, //是否显示上传按钮
            // overwriteInitial : true, 默认时true  覆盖原始图片
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




</script>

</body>
</html>
