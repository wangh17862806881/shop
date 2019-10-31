package com.fh.shop.admin.mapper.brand;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.shop.admin.param.brand.BrandSearchParam;
import com.fh.shop.admin.po.brand.Brand;

import java.util.List;

public interface IBrandMapper extends BaseMapper<Brand> {
//查询总条数
    Long totalcount(BrandSearchParam brandSearchParam);
//查询商品数据
    List<Brand> getBrandList(BrandSearchParam brandSearchParam);
//新增品牌
    void addbrand(Brand brand);
//回显
    Brand findBrandById(Long id);
//修改品牌
    void updateBrandById(Brand brand);
// 删除 单个
    void deleteBrandById(Long id);
}
