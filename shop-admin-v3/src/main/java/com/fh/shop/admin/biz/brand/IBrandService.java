package com.fh.shop.admin.biz.brand;

import com.fh.shop.admin.common.DataTableResult;
import com.fh.shop.admin.common.ServerResponse;
import com.fh.shop.admin.common.page;
import com.fh.shop.admin.param.brand.BrandSearchParam;
import com.fh.shop.admin.po.brand.Brand;
import com.fh.shop.admin.vo.Brand.BrandVo;
import com.fh.shop.admin.vo.role.RoleVo;

import java.util.List;

public interface IBrandService {

//新增品牌
    void addbrand(Brand brand);
//回显
    BrandVo findBrandById(Long id);
//修改品牌
    void updateBrandById(Brand brand);
// 删除 单个
    void deleteBrandById(Long id);
//获取所有品牌集合
    DataTableResult findAllData(BrandSearchParam brandSearchParam);

    ServerResponse findAllBrand();

    ServerResponse updateBrandPopular(Brand brand);

    ServerResponse updateBrandSort(Brand brand);
}
