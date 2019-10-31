package com.fh.shop.admin.biz.role;

import com.fh.shop.admin.common.DataTableResult;
import com.fh.shop.admin.common.page;
import com.fh.shop.admin.mapper.role.IRoleMapper;
import com.fh.shop.admin.param.role.RoleSearcherParam;
import com.fh.shop.admin.po.role.Role;
import com.fh.shop.admin.po.role.Role_Resource;
import com.fh.shop.admin.vo.role.RoleVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value="roleService")
public class IRoleServiceImpl implements IRoleService{
    @Autowired
    private IRoleMapper roleMapper;


//新增
    @Override
    public void addRole(Role role,List<Long> idarr)  {

        //        //新增角色
        roleMapper.addRole(role);
        //非空判断
        if(null != idarr && idarr.size()>0) {
            //获取 新增中间表对象集合、、
            List<Role_Resource> role_resourcesList = getRole_resourcesList(idarr, role.getId());
            //批量新增中间表
            roleMapper.addRole_Resource(role_resourcesList);

        }

    }
//回显
    @Override
    public RoleVo findRoleById(Long id) {
        //查询role角色表
        Role role = roleMapper.findRoleById(id);
        //po对象转vo对象
        RoleVo roleVo = getRoleVo(role);
        //查询角色资源中间表
        List<Long> idList =  roleMapper.findRole_ResourceByid(id);
        roleVo.setResourceIdList(idList);
        return roleVo;
    }
//po对象转vo对象
    private RoleVo getRoleVo(Role role) {
        RoleVo roleVo = new RoleVo();
        //查询角色对应资源菜单名
        List<String> menuNames =  roleMapper.findResourcemenuNameByRoleId(role.getId());
        roleVo.setMenuNames(StringUtils.join(menuNames,","));
        roleVo.setId(role.getId());
        roleVo.setRoleName(role.getRoleName());
        return roleVo;
    }

    //修改角色
    @Override
    public void updateRoleById(Role role,List<Long> idarr) {
        roleMapper.updateRoleById(role);
        //删除角色对应资源 的中间表
        roleMapper.deleteRole_ResourceByRoleId(role.getId());
        if(null != idarr && idarr.size()>0){
            //获取泛型是中间表的List集合
            List<Role_Resource> list = getRole_resourcesList(idarr, role.getId());
            //批量新增中间表
            roleMapper.addRole_Resource(list);
        }


    }
// 获取中间表数据集合
    private List<Role_Resource> getRole_resourcesList(List<Long> idarr, Integer id) {
        List<Role_Resource> list = new ArrayList<>();

        //新增角色对应id
        for (Long aLong : idarr) {
            Role_Resource role_resource = new Role_Resource();
            role_resource.setRoleId(id);
            role_resource.setResourceId(aLong);
            list.add(role_resource);
        }
        return list;
    }

// 单个删除
    @Override
    public void deleteRoleById(Long id) {
        roleMapper.deleteRoleById(id);
        roleMapper.deleteRole_ResourceByRoleId(id.intValue());
    }
//查询所有角色数据
    @Override
    public DataTableResult findRoleAllData(RoleSearcherParam roleSearcherParam) {
        //查询总条数
        Long totalcount = roleMapper.totalcount(roleSearcherParam);
        //查询数据
            List<Role> roleList = roleMapper.getRoleList(roleSearcherParam);
        //po集合转vo集合
            List<RoleVo> roleVoList1 = PoList2VoList(roleList);
        return new DataTableResult(totalcount,totalcount,roleSearcherParam.getDraw(),roleVoList1);
    }

    private List<RoleVo> PoList2VoList(List<Role> roleList) {
        List<RoleVo> roleVoList = new ArrayList<>();
        for (Role role : roleList) {
            RoleVo roleVo = getRoleVo(role);
            roleVoList.add(roleVo);
        }
        return roleVoList;
    }
}
