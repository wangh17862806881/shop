package com.fh.shop.api.brand.biz;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.shop.api.brand.mapper.BrandMapper;
import com.fh.shop.api.brand.po.Brand;
import com.fh.shop.api.brand.vo.BrandVo;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service(value="brandService")
public class IBrandServiceImpl implements IBrandServerce{
    @Resource
    private BrandMapper brandMapper;


    @Override
    public ServerResponse findBrandList() {
        String hotBrandList = RedisUtil.get("hotBrandList");
        if(StringUtils.isNotEmpty(hotBrandList)){
            List<BrandVo> brandVos = JSONObject.parseArray(hotBrandList, BrandVo.class);
            return ServerResponse.success(brandVos);
        }
        QueryWrapper<Brand> brandQueryWrapper = new QueryWrapper<>();
        brandQueryWrapper.orderByDesc("sort");
        brandQueryWrapper.eq("popular","1");
        List<Brand> brands = brandMapper.selectList(brandQueryWrapper);
        List<BrandVo> voList = po2vo(brands);
        String s = JSONObject.toJSONString(voList);
        RedisUtil.set("hotBrandList",s);
        return ServerResponse.success(voList);
    }
//po转vo
    private List<BrandVo> po2vo(List<Brand> brands) {
        List<BrandVo> voList = new ArrayList<>();
        for (Brand po : brands) {
            BrandVo vo = new BrandVo();
            vo.setBrandName(po.getBrandName());
            vo.setImgURL(po.getImgURL());
            vo.setId(po.getId());
            voList.add(vo);
        }
        return voList;
    }
}
