package com.fh.shop.admin.mapper.role;

import com.fh.shop.admin.common.page;
import com.fh.shop.admin.param.role.RoleSearcherParam;
import com.fh.shop.admin.po.role.Role;
import com.fh.shop.admin.po.role.Role_Resource;

import java.util.List;

public interface IRoleMapper {

//查询总条数
    Long totalcount(RoleSearcherParam roleSearcherParam);
//查询商品数据
    List<Role> getRoleList(RoleSearcherParam roleSearcherParam);
//新增
    void addRole(Role role);
//回显
    Role findRoleById(Long id);
//修改角色
    void updateRoleById(Role role);
// 单个删除
    void deleteRoleById(Long id);
//新增中间表
    void addRole_Resource(List<Role_Resource> role_resourceList);
//查询角色资源中间表
    List<Long> findRole_ResourceByid(Long id);
//删除角色对应资源 的中间表
    void deleteRole_ResourceByRoleId(Integer id);
//查询角色对应资源菜单名
    List<String> findResourcemenuNameByRoleId(Integer id);
}
