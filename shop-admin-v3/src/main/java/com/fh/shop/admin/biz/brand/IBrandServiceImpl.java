package com.fh.shop.admin.biz.brand;

import com.alibaba.fastjson.JSONObject;
import com.fh.shop.admin.common.DataTableResult;
import com.fh.shop.admin.common.ServerResponse;
import com.fh.shop.admin.common.page;
import com.fh.shop.admin.mapper.brand.IBrandMapper;
import com.fh.shop.admin.param.brand.BrandSearchParam;
import com.fh.shop.admin.po.brand.Brand;
import com.fh.shop.admin.util.FileUpload;
import com.fh.shop.admin.util.FileUtil;
import com.fh.shop.admin.util.RedisUtil;
import com.fh.shop.admin.vo.Brand.BrandVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value="brandService")
public class IBrandServiceImpl implements IBrandService{
    @Autowired
    private IBrandMapper brandMapper;



//新增品牌
    @Override
    public void addbrand(Brand brand) {
        RedisUtil.del("hotBrandList");
        RedisUtil.del("brandList");
        brandMapper.addbrand(brand);
    }
//回显
    @Override
    public BrandVo findBrandById(Long id) {
        Brand brandById = brandMapper.findBrandById(id);
        BrandVo brandVo = new BrandVo();
        brandVo.setId(brandById.getId());
        brandVo.setImgURL(brandById.getImgURL());
        brandVo.setBrandName(brandById.getBrandName());
        brandVo.setPopular(brandById.getPopular());
        return brandVo;
    }
//修改品牌
    @Override
    public void updateBrandById(Brand brand) {
        RedisUtil.del("hotBrandList");
        RedisUtil.del("brandList");
        if(StringUtils.isEmpty(brand.getImgURL())){
            brand.setImgURL(brand.getOldimgURL());
        } else{
            FileUpload.delete(brand.getOldimgURL());
        }
        brandMapper.updateBrandById(brand);
    }
// 删除 单个
    @Override
    public void deleteBrandById(Long id) {
        RedisUtil.del("hotBrandList");
        RedisUtil.del("brandList");
        brandMapper.deleteBrandById(id);
    }
//获取所有品牌集合
    @Override
    public DataTableResult findAllData(BrandSearchParam brandSearchParam) {

        //查询总条数
        Long totalcount = brandMapper.totalcount(brandSearchParam);
        //查询商品数据
        List<Brand> brandList = brandMapper.getBrandList(brandSearchParam);
        //po集合转vo集合
        List<BrandVo> brandVos1 = PoList2Volist(brandList);
        return new DataTableResult(totalcount,totalcount,brandSearchParam.getDraw(),brandVos1);
    }
//获取所有品牌    商品下拉框用
    @Override
    public ServerResponse findAllBrand() {
        String jsonBrandString = RedisUtil.get("brandList");
        List<BrandVo> brandVos = null;
        if(StringUtils.isEmpty(jsonBrandString)){
            List<Brand> brands = brandMapper.selectList(null);
             brandVos = PoList2Volist(brands);
             jsonBrandString = JSONObject.toJSONString(brandVos);
            RedisUtil.set("brandList",jsonBrandString);
        }else{
            brandVos = JSONObject.parseArray(jsonBrandString, BrandVo.class);
        }
        return ServerResponse.success(brandVos);
    }
//修改热销状态
    @Override
    public ServerResponse updateBrandPopular(Brand brand) {
        RedisUtil.del("hotBrandList");
        brandMapper.updateBrandById(brand);
        return ServerResponse.success();
    }

    @Override
    public ServerResponse updateBrandSort(Brand brand) {
        RedisUtil.del("hotBrandList");
        brandMapper.updateById(brand);
        return ServerResponse.success();
    }

    //po集合转vo集合
    private List<BrandVo> PoList2Volist(List<Brand> brandList) {
        List<BrandVo> brandVos = new ArrayList<>();
        for (Brand brand : brandList) {
            BrandVo brandVo = new BrandVo();
            brandVo.setId(brand.getId());
            brandVo.setBrandName(brand.getBrandName());
            brandVo.setImgURL(brand.getImgURL());
            brandVo.setSort(brand.getSort());
            brandVo.setPopular(brand.getPopular());
            brandVos.add(brandVo);
        }
        return brandVos;
    }


}
