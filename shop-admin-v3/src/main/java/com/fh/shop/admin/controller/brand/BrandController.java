package com.fh.shop.admin.controller.brand;

import com.fh.shop.admin.biz.brand.IBrandService;
import com.fh.shop.admin.common.DataTableResult;
import com.fh.shop.admin.common.Log;
import com.fh.shop.admin.common.ServerResponse;
import com.fh.shop.admin.param.brand.BrandSearchParam;
import com.fh.shop.admin.po.brand.Brand;
import com.fh.shop.admin.vo.Brand.BrandVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="/brand")
public class BrandController {
@Resource(name="brandService")
    private IBrandService brandService;

//获取所有品牌集合 下拉框使用
    @RequestMapping(value="findAllBrand")
    @ResponseBody
    public ServerResponse findAllBrand(){
        return brandService.findAllBrand();
    }

//获取所有品牌集合  条件 分页
    @RequestMapping(value="/getBrandList")
    @ResponseBody
    public ServerResponse getBrandList(BrandSearchParam brandSearchParam){
        DataTableResult dataTableResult =  brandService.findAllData(brandSearchParam);
        return ServerResponse.success(dataTableResult);
    }

//新增品牌
    @RequestMapping(value="/addbrand")
    @ResponseBody
    @Log("新增品牌")
    public ServerResponse addbrand(Brand brand){


            brandService.addbrand(brand);
        return    ServerResponse.success();

    }


//更改热销状态
@RequestMapping(value="/updateBrandPopular")
@ResponseBody
    public ServerResponse updateBrandPopular(Brand brand){
        return brandService.updateBrandPopular(brand);
    }


//更改排序字段
@RequestMapping(value="/updateBrandSort")
@ResponseBody
public ServerResponse updateBrandSort(Brand brand){
        return brandService.updateBrandSort(brand);
}


// 删除 单个
    @RequestMapping(value="/deleteBrandById")
    @ResponseBody
    @Log("删除 单个品牌")
    public ServerResponse deleteBrandById(Long id){
            brandService.deleteBrandById(id);
           return ServerResponse.success();
    }



//修改品牌
    @RequestMapping(value="/updateBrandById")
    @ResponseBody
    @Log("修改品牌")
    public ServerResponse updateBrandById(Brand brand){
            brandService.updateBrandById(brand);
           return ServerResponse.success();
    }


//回显
    @RequestMapping(value="/findBrandById")
    @ResponseBody
public ServerResponse findBrandById(Long id){
            BrandVo brandVo = brandService.findBrandById(id);
          return ServerResponse.success(brandVo);
}


 // 跳转展示商品页面
    @RequestMapping(value="/toBrandList")
    public  String toshowList(){
        return "brand/showList";
    }
}
