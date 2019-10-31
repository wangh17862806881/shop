package com.fh.shop.admin.mapper.Resource;

import com.fh.shop.admin.po.Resource.Resource;
import com.fh.shop.admin.vo.Resource.ResourceVo;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface IResourceMapper {
//查询所有资源 菜单
    List<Resource> findAllResourceList();
//删除选择 的 所有
    void deleteResource(Long[] idarr);
//新增
    void addResource(Resource resource);
//修改
    void updateResource(Resource resource);
//查询用户对应权限
    List<ResourceVo> findAllResourceByUserId(Long id);
//查询用户对应按钮权限url
    List<String> findUserResourceUrlByUserId(Long id);

}
