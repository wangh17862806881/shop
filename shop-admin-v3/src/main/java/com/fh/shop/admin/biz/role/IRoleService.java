package com.fh.shop.admin.biz.role;

import com.fh.shop.admin.common.DataTableResult;
import com.fh.shop.admin.common.page;
import com.fh.shop.admin.param.role.RoleSearcherParam;
import com.fh.shop.admin.po.role.Role;
import com.fh.shop.admin.vo.role.RoleVo;

import java.util.List;

public interface IRoleService {

//新增
    void addRole(Role role, List<Long> idarr);
//回显
    RoleVo findRoleById(Long id);
//修改角色
    void updateRoleById(Role role, List<Long> idarr);
// 单个删除
    void deleteRoleById(Long id);
//查询所有角色数据
    DataTableResult findRoleAllData(RoleSearcherParam roleSearcherParam);
}
