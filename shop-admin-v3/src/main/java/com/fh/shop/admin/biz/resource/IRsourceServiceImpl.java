package com.fh.shop.admin.biz.resource;

import com.fh.shop.admin.mapper.Resource.IResourceMapper;
import com.fh.shop.admin.po.Resource.Resource;
import com.fh.shop.admin.vo.Resource.ResourceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value="resourceService")
public class IRsourceServiceImpl implements IResourceService{
    @Autowired
    private IResourceMapper resourceMapper;
//查询所有资源 菜单
    @Override
    public List<ResourceVo> findAllResourceList() {
        List<Resource> allResourceList = resourceMapper.findAllResourceList();
        List<ResourceVo> resourceVoList = new ArrayList<>();
        for (Resource resource : allResourceList) {
            ResourceVo resourceVo = new ResourceVo();
            resourceVo.setId(resource.getId());
            resourceVo.setName(resource.getMenuName());
            resourceVo.setpId(resource.getFatherId());
            resourceVo.setType(resource.getType());
            resourceVo.setMenuUrl(resource.getUrl());
            resourceVoList.add(resourceVo);
        }
        return resourceVoList;
    }
//删除选择 的 所有
    @Override
    public void deleteResource(Long[] idarr) {
        resourceMapper.deleteResource(idarr);
    }
//新增
    @Override
    public void addResource(Resource resource) {
        resourceMapper.addResource(resource);
    }
//修改
    @Override
    public void updateResource(Resource resource) {
        resourceMapper.updateResource(resource);
    }
//查询用户对应权限
    @Override
    public List<ResourceVo> findAllResourceByUserId(Long id) {
        return resourceMapper.findAllResourceByUserId(id);
    }
    //查询所有权限
    @Override
    public List<Resource> findUserResourceList() {
        List<Resource> allResourceList = resourceMapper.findAllResourceList();
        return allResourceList;
    }
    //查询用户对应所有权限
    @Override
    public List<String> findUserResourceList(Long id) {
        List<String> allUser_Resource_Url = resourceMapper.findUserResourceUrlByUserId(id);
        return allUser_Resource_Url;
    }


}
