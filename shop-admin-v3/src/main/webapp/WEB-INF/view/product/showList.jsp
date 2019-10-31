<%--
  Created by IntelliJ IDEA.
  User: 、、
  Date: 2019/8/26
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>

    <title>商品展示</title>

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
                <div class="panel-heading">商品查询</div>
                <%-- 条件查询面板内容 --%>
                <div class="panel-body">
                    <form class="form-horizontal" id="find_form">
                        <div class="form-group">
                            <label  class="col-sm-2 control-label">商品名</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="productName" id="find_productName" placeholder="请输入商品名...">
                            </div>
                            <label  class="col-sm-2 control-label">价格</label>
                            <div class="col-sm-4">
                                <div class="input-group">
                                    <input type="text" name="beginPrice"  id="find_beginPrice" class="form-control" placeholder="开始价格..." >
                                    <span class="input-group-addon" ><i class="glyphicon glyphicon-minus"></i></span>
                                    <input type="text" name="endPrice"  id="find_endPrice" class="form-control" placeholder="结束价格..." >
                                </div>
                                </div>
                        </div>

                        <div class="form-group">
                            <label  class="col-sm-2 control-label">生产日期</label>
                            <div class="col-sm-4">
                                <div class="input-group">
                                    <input type="text" name="beginTime"  id="find_beginTime" class="form-control" placeholder="开始时间..." >
                                    <span class="input-group-addon" ><i class="glyphicon glyphicon-calendar"></i></span>
                                    <input type="text" name="endTime"  id="find_endTime" class="form-control" placeholder="结束时间..." >
                                </div>
                            </div>
                            <label  class="col-sm-2 control-label">品牌</label>
                            <div class="col-sm-4">
                                <div class="input-group" id="select_div_find">
                                </div>
                            </div>

                        </div>
                        <div class="form-group" id="find_selectClass">
                            <label  class="col-sm-2 control-label">类别</label>

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
                <button class="btn btn-danger"  id="deletemultibyids"><span class="glyphicon glyphicon-trash"></span>批量删除</button>
                <button class="btn btn-primary" onclick="excelDownLoad()" ><span class="glyphicon glyphicon-download-alt"></span>Excel导出</button>
                <button class="btn btn-info" onclick="cleanCache()"  id="cleanCache"><span class="glyphicon glyphicon-trash"></span>清除缓存</button>


            </div>

        </div>
    </div>
    <%--  面板   用户展示面板 --%>
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-info">
                <div class="panel-heading">商品列表</div>
                <%--用户表格展示--%>
                <table id="productTable" class="table table-striped table-bordered" style="width:100%">
                    <thead>
                    <tr>
                        <td>选择</td>
                        <td>商品名</td>
                        <td>商品价格</td>
                        <td>品牌名</td>
                        <td>类别</td>
                        <td>状态</td>
                        <td>图片</td>
                        <td>生产日期</td>
                        <td>操作</td>
                    </tr>
                    </thead>
                    <tbody></tbody>
                    <tfoot>
                    <tr>
                        <td>选择</td>
                        <td>商品名</td>
                        <td>商品价格</td>
                        <td>品牌名</td>
                        <td>类别</td>
                        <td>状态</td>
                        <td>图片</td>
                        <td>生产日期</td>
                        <td>操作</td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>
</div>
<%-- 新增 弹出框 --%>
<div id="productInsertFormData" style="display: none;">

    <form class="form-horizontal" >
        <div class="form-group">
            <label  class="col-sm-2 control-label">商品名:</label>
            <div class="col-sm-10">
                <input type="text"  id="add_productName" class="form-control"  placeholder="商品名...">
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">价格:</label>
            <div class="col-sm-10">
                <input type="text" id="add_price" class="form-control"  placeholder="价格...">
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">品牌:</label>
            <div class="col-sm-10" id = "select_div_add">

            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">图片:</label>
            <div class="col-sm-10">
                <input type="file" name="myfile"  id="add_uploadImage"   class="myfile"  />
                <input type="hidden"  id="add_ImagePath"  />
            </div>
        </div>

        <div class="form-group">
            <label  class="col-sm-2 control-label">生产日期：</label>
            <div class="col-sm-10">
                <input type="text" id="add_productDate" name="datedate"  class="form-control"  placeholder="生产日期...">
            </div>
        </div>
        <div class="form-group" id="add_selectClass">
            <label  class="col-sm-2 control-label">分类：</label>

        </div>
    </form>
</div>
<%-- 修改 弹出框 --%>
<div id="productUpdateFormData" style="display: none">

    <form class="form-horizontal" >
        <div class="form-group">
            <label  class="col-sm-2 control-label">商品名:</label>
            <div class="col-sm-10">
                <input type="text"  id="update_productName" class="form-control"  placeholder="商品名...">
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">商品价格:</label>
            <div class="col-sm-10">
                <input type="text" id="update_price" class="form-control"  placeholder="商品价格...">
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">品牌:</label>
            <div class="col-sm-10" id = "select_div_update">

            </div>
        </div>
        <div class="form-group" >
            <label  class="col-sm-2 control-label">类别:</label>
            <div id="update_selectClass" style="width: auto">

            </div>
            <span id="update_ClassSpan"></span>

            <button type="button" value="1" onclick="update_ClassStyle(this)" class="btn btn-primary" id="update_ClassButton"><i class='glyphicon glyphicon-pencil'></i>编辑</button>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">图片:</label>
            <div class="col-sm-10">
                <input type="file" name="myfile"  id="update_uploadImage"   class="myfile"  />
                <input type="hidden"  id="update_ImagePath"  />
                <input type="hidden"  id="update_oldImagePath"  />

            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">生产日期：</label>
            <div class="col-sm-10">
                <input type="text" id="update_productDate" name="datedate"  class="form-control"  placeholder="生产日期...">
            </div>
        </div>

        <input type="hidden" id = "update_id">
    </form>
</div>





<script>
    $(function(){
    getALLProductData();
    initdatedate("find_beginTime");
    initdatedate("find_endTime");
    initAddDiv();
    initUpdateDiv();
    findAllBrand("find");
    findClassByPid("find");
    });

var userDataTable;
var productAddFormData;
var productUpdateFormData;
// 初始化新增div
function initAddDiv(){
    productAddFormData = $("#productInsertFormData").html();
    $("#productInsertFormData").html("");
}
// 初始化修改div
function initUpdateDiv(){
    productUpdateFormData = $("#productUpdateFormData").html();
    $("#productUpdateFormData").html("");
}

//获取查询条件
    var param = {};
      function searcher(){

          var v_beginTime = $("#find_beginTime").val();
          var v_endTime = $("#find_endTime").val();
          var v_beginPrice = $("#find_beginPrice").val();
          var v_endPrice = $("#find_endPrice").val();
          var v_productName = $("#find_productName").val();
          var v_find_select = $("#find_select").val();
          var v_classId1 = $($("select[name='find_selectClass']")[0]).val();
          var v_classId2 = $($("select[name='find_selectClass']")[1]).val();
          var v_classId3 = $($("select[name='find_selectClass']")[2]).val();


          if(!!v_beginTime){
              param.beginTime=v_beginTime;
          }
          if(!!v_endTime){
              param.endTime=v_endTime;
          }
          param.beginPrice=v_beginPrice;
          param.endPrice=v_endPrice;
          param.productName=v_productName;
          param.brandId=v_find_select;
          param.classId1 = v_classId1;
          param.classId2 = v_classId2;
          param.classId3 = v_classId3;
          userDataTable.settings()[0].ajax.data=param;
          userDataTable.ajax.reload();

      }

//查询所有商品数据
    function getALLProductData(){

        userDataTable =  $("#productTable").DataTable({
            "language": {
                "url":"/js/Chinese.json"
            },
            "processing": true,
            "serverSide": true,
            "destroy":true,
            "ajax": {
                "url": "/product/getProductList.jhtml",
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
                { "data": "productName" },
                { "data": "price" },
                { "data": "brandName" },
                { "data": "categoryName" },
                { "data": "popular",
                    "render":function (data, type, row, meta) {
                        return data == 1?"上架":"下架";
                    }
                },
                { "data": function (data) {
                        return '<img src="'+data.imgURL+'" width="50px" height="50px"/>';
                    } },

                {"data":"productDate"},
                { "data": "id",
                    "render": function(data, type, row, meta){

                        var v_text = "";
                        var v_buttonclass = "";
                        var v_iclass ="";
                        var v_popular = "";
                        if(row.popular == 1){
                            v_text = "下架";
                            v_buttonclass = "btn btn-warning";
                            v_iclass ="glyphicon glyphicon-chevron-down";
                            v_popular = 0;
                        }else{
                            v_text = "上架";
                            v_buttonclass = "btn btn-info";
                            v_iclass ="glyphicon glyphicon-chevron-up";
                            v_popular = 1;
                        }

                        return "<div class=\"btn-group\" role=\"group\" >\n" +
                            "  <button type=\"button\" onclick='deleteProductById(\""+data+"\")' class=\"btn btn-danger\"><i class='glyphicon glyphicon-trash'></i>删除</button>\n" +
                            "  <button type=\"button\" onclick='updateProduct(\""+data+"\")' class=\"btn btn-primary\"><i class='glyphicon glyphicon-pencil'></i>修改</button>\n" +
                            "  <button type=\"button\" onclick='updateProductPopular(\""+data+"\",\""+v_popular+"\")' class=\""+v_buttonclass+"\"><i class='"+v_iclass+"'></i>"+v_text+"</button>\n" +

                            "</div>";
                    } },
            ],
            "lengthMenu": [ 5, 10, 20, 40, 100 ],
            "searching":false,

        });

    }


//动态下拉框  三级联动
    function findClassByPid(prefix,obj){
        var id = 0;
        //判断当前传进来obj 是否存在  区分是否是第一次调用  设置查询的pid
        if(obj){
            id = obj.val();
            //解决下拉框展示全部展示  点击第一个下拉框重新赋值 后面下拉框不消失问题
            //解决办法  获取当前元素的父元素 获取父元素的同级元素  将同级元素后面的全部删掉
            obj.parent().nextAll().remove();
        }
        $.ajax({
            url:"/class/findListById.jhtml",
            data:{"id":id},
            dataType:"json",
            async:false,
            type:"post",
            success:function(result){
                if(result.code== 200){
                    var dataArr = result.data;
                    //判断传进来的data是否为空  为空不进行拼接字符串 直接返回
                        if(dataArr.length == 0){
                            return ;
                        }

                    //拼接前缀
                    var str = '<div class="col-sm-2">' +
                        '<select name ="'+prefix+'_selectClass" class="form-control"  onchange="findClassByPid(\''+prefix+'\',$(this))" >' +
                        '<option value="-1">==请选择==</option>';

                    //拼接 动态部分
                    for(var i = dataArr.length-1 ; i>=0 ; i--){
                        str += '<option value ="'+dataArr[i].id+'">'+dataArr[i].name+'</option>';
                    }

                    //拼接后缀
                    str += '</select>' +
                        '</div>';

                    //将数据拼接到div中
                    $("#"+prefix+"_selectClass").append(str);
                }
            }
        });
    }



//更改上下架状态
    function updateProductPopular(id,popular){

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
                        url:"/product/updateProductPopular.jhtml",
                        type:"post",
                        data:{"id":id,"popular":popular},
                        dataType:"json",
                        success:function(data){
                            if(data.code==200){
                                searcher();
                            }


                        }

                    });
                }
            }
        });

    }


//新增商品
    var productbootbox;
    function addProduct(){

        productbootbox =  bootbox.dialog({
            message: productAddFormData,
            title:"新增商品",
            size:'large',
            buttons: {
                confirm: {
                    label: '<i class="glyphicon glyphicon-ok"></iclass>确定',
                    className: 'btn-success',
                    callback:function(){
                        //获取输入框的值
                        var v_productName = $("#add_productName").val();
                        var v_price = $("#add_price").val();
                        var v_imgURL = $("#add_ImagePath").val();
                        var v_productDate = $("#add_productDate").val();
                        var v_add_select = $("#add_select").val();

                        var v_classId1 = $($("select[name='add_selectClass']")[0]).val();
                        var v_classId2 = $($("select[name='add_selectClass']")[1]).val();
                        var v_classId3 = $($("select[name='add_selectClass']")[2]).val();

                        //定义json对象
                        var param = {};
                        param.productName = v_productName;
                        param.price =v_price;
                        param.imgURL = v_imgURL;
                        param.productDate = v_productDate;
                        param.brandId = v_add_select;
                        param.classId1 = v_classId1;
                        param.classId2 = v_classId2;
                        param.classId3 = v_classId3;
                        $.ajax({
                            url:"/product/addProduct.jhtml",
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
        findClassByPid("add");
        initdatedate("add_productDate");
        findAllBrand("add");
        fileinput5("add","");
    }


//删除单个
    function deleteProductById(id){

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
                        url:"/product/deleteProductById.jhtml",
                        type:"post",
                        data:{"id":id},
                        dataType:"json",
                        success:function(data){
                            if(data.code==200){
                                searcher();
                            }


                        }

                    });
                }
            }
        });
    }


//时间插件调用  新增修改
    function initdatedate(initid) {
        $('#'+initid).datetimepicker({
            format: 'YYYY-MM-DD HH:mm:ss',
            locale: moment.locale('zh-CN'),
            showClear:true,
        });
    }

//修改类别
    function update_ClassStyle(obj){

        if($(obj).val() == 1){
            $(obj).val(2)
            $("#update_ClassButton").html("<i class='glyphicon glyphicon-pencil'></i>退出编辑")
            $("#update_ClassSpan").hide();
            findClassByPid("update");
            return ;
        }

        $(obj).val(1)
        $("#update_ClassButton").html("<i class='glyphicon glyphicon-pencil'></i>编辑")
        $("#update_ClassSpan").show();
        $("select[name='update_selectClass']").parent().remove();

    }


//修改 商品
 function updateProduct(id){
//回显
     $.ajax({
         url:"/product/findProductById.jhtml",
         type:"post",
         data:{"id":id},
         dataType:"json",
         success:function(data){

             if(data.code == 200){


                 $("#productUpdateFormData").html(productUpdateFormData);
                 findAllBrand("update");
                 $("#update_productName").val(data.data.productName);
                 $("#update_price").val(data.data.price);

                 $("#update_oldImagePath").val(data.data.imgURL);
                 $("#update_productDate").val(data.data.productDate);
                 $("#update_id").val(data.data.id);
                 $("#update_select").val(data.data.brandId);
                 $("#update_ClassSpan").html(data.data.categoryName);

                 var productUpdatebootbox =  bootbox.dialog({
                     message: $("#productUpdateFormData form"),
                     title:"修改商品",
                     size:'large',
                     buttons: {
                         confirm: {
                             label: '<i class="glyphicon glyphicon-ok"></iclass>确定',
                             className: 'btn-success',
                             callback:function(){
                                 //获取输入框的值
                                 var v_productName = $("#update_productName").val();
                                 var v_price = $("#update_price").val();
                                 var v_imgURL = $("#update_ImagePath").val();
                                 var v_oldimgURL = $("#update_oldImagePath").val();
                                 var v_productDate = $("#update_productDate").val();
                                 var v_id = $("#update_id").val();
                                 var v_update_select = $("#update_select").val();

                                 var c1 ;
                                 var c2 ;
                                 var c3 ;

                                 if($("select[name='update_selectClass']").length == 3){
                                     c1 = $($("select[name='update_selectClass']")[0]).val();
                                     c2 = $($("select[name='update_selectClass']")[1]).val();
                                     c3 = $($("select[name='update_selectClass']")[2]).val();
                                 }else{
                                     c1 = data.data.classId1;
                                     c2 = data.data.classId2;
                                     c3 = data.data.classId3;
                                 }

                                 //定义json对象
                                 var param = {};
                                 param.productName = v_productName;
                                 param.price =v_price;
                                 param.imgURL = v_imgURL;
                                 param.oldImgURL = v_oldimgURL;
                                 param.productDate = v_productDate;
                                 param.id = v_id;
                                 param.brandId = v_update_select;
                                 param.classId1 =c1;
                                 param.classId2 =c2;
                                 param.classId3 =c3;

                                 $.ajax({
                                     url:"/product/updateProductById.jhtml",
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
                 initdatedate("update_productDate");
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

//清除缓存
    function cleanCache(){
        $.ajax({
            url:"/cache/cleanUPProductCache.jhtml",
            type:"post",
            dataType:"json",
            async:true,
            data:{},
            success:function(result){
                if(result.code == 200){
                    bootbox.alert({
                        title:"提示信息",
                        message:"<i class='glyphicon glyphicon-exclamation-sign'></i>清除缓存成功",
                        size: 'small',
                    });
                }
            }

        });
    }


 //查询所有品牌 生成 动态下拉框
    function findAllBrand(id){

        $.ajax({
            url:"/brand/findAllBrand.jhtml",
            data:{},
            dataType:"json",
            async:false,
            type:"json",
            success:function(result){
                if(result.code== 200){
                    var dataArr = result.data;
                    var str = '<select id ="'+id+'_select" name="brandId" class="form-control" ><option value="0">===请选择===</option>';
                    for(var i = dataArr.length-1 ; i>=0 ; i--){
                        str += '<option value ="'+dataArr[i].id+'">'+dataArr[i].brandName+'</option>';
                    }
                    str += '</select>';
                    $("#select_div_"+id).html(str)
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

    //excel导出
    function excelDownLoad(){
        var form = $("#find_form")
        form.attr("action","/product/excelDownLoad.jhtml")
        form.attr("method","post")
        var v_classId1 = $($("select[name='find_selectClass']")[0]).val();
        var v_classId2 = $($("select[name='find_selectClass']")[1]).val();
        var v_classId3 = $($("select[name='find_selectClass']")[2]).val();
        if(!$("#excelDownClassId1").val()){
            var classId1=$("<input type='hidden' id='excelDownClassId1' name='classId1'/>");
            var classId2=$("<input type='hidden' id='excelDownClassId2' name='classId2'/>");
            var classId3=$("<input type='hidden' id='excelDownClassId3' name='classId3'/>");
            form.append(classId1);
            form.append(classId2);
            form.append(classId3);
        }
        $("#excelDownClassId1").val(v_classId1);
        $("#excelDownClassId2").val(v_classId2);
        $("#excelDownClassId3").val(v_classId3);
        form.submit();
    }


</script>




</body>
</html>
