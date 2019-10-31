package com.fh.shop.admin.biz.resource;

import com.fh.shop.admin.po.Resource.Resource;
import com.fh.shop.admin.vo.Resource.ResourceVo;

import java.util.List;

public interface IResourceService {

    //查询所有资源 菜单
    List<ResourceVo> findAllResourceList();
    //删除选择 的 所有
    void deleteResource(Long[] idarr);
    //新增
    void addResource(Resource resource);
    //修改
    void updateResource(Resource resource);
    //查询用户对应权限
    List<ResourceVo> findAllResourceByUserId(Long id);

    //查询所有权限
    List<Resource> findUserResourceList();
    //查询用户对应所有权限
    List<String> findUserResourceList(Long id);

}
