package com.fh.shop.admin.controller.product;

import com.fh.shop.admin.biz.product.IProductService;
import com.fh.shop.admin.common.DataTableResult;
import com.fh.shop.admin.common.Log;
import com.fh.shop.admin.common.ServerResponse;
import com.fh.shop.admin.param.product.ProductSeracherParam;
import com.fh.shop.admin.po.product.Product;
import com.fh.shop.admin.util.ExcelUtil;
import com.fh.shop.admin.util.FileUtil;
import com.fh.shop.admin.vo.product.ProductVo;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value="/product")
public class ProductController {
    @Resource
    private IProductService productService;


//查询商品 条件  分页
@RequestMapping(value="/getProductList")
@ResponseBody
public ServerResponse getProductList(ProductSeracherParam seracherParam){
    DataTableResult dataTableResult = productService.findAllproductData(seracherParam);
    return ServerResponse.success(dataTableResult);
}

//新增商品
    @RequestMapping(value="/addProduct")
    @ResponseBody
    @Log("新增商品")
    public ServerResponse addProduct(Product product){


            productService.addProduct(product);
           return ServerResponse.success();

    }

    @RequestMapping(value="/updateProductPopular")
    @ResponseBody
    public ServerResponse updateProductPopular(Product product){
        return productService.updateProductPopular(product);
    }

//删除 单个 通过 id
    @RequestMapping(value="/deleteProductById")
    @ResponseBody
    @Log("通过 id删除 单个 商品")
    public ServerResponse deleteProductById(Long id){
            productService.deleteProductById(id);
            return ServerResponse.success();

    }


//excel 导出
    @RequestMapping(value="/excelDownLoad")
    public void excelDownLoad(ProductSeracherParam productSeracherParam, HttpServletResponse response){
        //获取数据
        List<Product> list =  productService.findALLProductNoCondi(productSeracherParam);
        //创建数据
        String[] headNames = {"id","商品名","商品价格","生产日期","分类名","品牌名"};
        String[] props ={"id","productName","price","productDate","categoryName","brandName"};

        //获取workbook 构建
        XSSFWorkbook workbook = ExcelUtil.excelDownLoad(list, "商品列表", headNames, props, Product.class);
        //响应下载
        FileUtil.excelDownload(workbook,response);

    }


//修改商品方法
    @RequestMapping(value="/updateProductById")
    @ResponseBody
    @Log("修改商品方法")
    public ServerResponse updateProductById(Product product){
           productService.updateProductById(product);
          return ServerResponse.success();
    }


//回显
    @RequestMapping(value="/findProductById")
    @ResponseBody
    public ServerResponse findProductById(Long id){

            ProductVo productVo =  productService.findProductById(id);
            return ServerResponse.success(productVo);

    }


//跳转商品展示页面
    @RequestMapping(value="/toProductList")
    public String toProductList(){
    return "product/showList";
    }


}
