package com.fh.shop.admin.mapper.user;

import com.fh.shop.admin.param.user.UserLogin;
import com.fh.shop.admin.param.user.UserSearcherParam;
import com.fh.shop.admin.po.role.Role;
import com.fh.shop.admin.po.user.User;
import com.fh.shop.admin.po.user.User_Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserMapper {
    //新增方法
    void addUser(User user);
    //查询总条数
    Long findTotalCount(UserSearcherParam userSearcherParam);
    //查询用户列表
    List<User> findPageList(UserSearcherParam userSearcherParam);
//单个删除方法
    void deleteUserById(Long id);
//回显  回填  方法
    User findUserById(Long id);
//修改 方法
    void updateUserById(User user);
//查询用户对应的 角色名
    List<String> findRoleName(Long id);
//查询所有角色
    List<Role> findroleall();
//新增角色
    void addRole(User_Role user_role);

//根据用户id查询角色id集合
    List<Integer> findRoleIdByUserId(Long id);
//根据用户id  删除对应角色 在中间表
    void deleteRoleIdByUserId(Long id);
// 批量删除
void deletebatchids(List<Long> idarr);

//通过用户id集合 批量删除 中间表
    void deletebBatchUser_RoleByUserIdList(List<Long> idarr);
//登陆验证
    User loginCheck(String userName);
//把更新后的信息同步到数据库
    void updateUserLoginData(User user);
//更新登陆错误信息
    void updateLoginErrorData(@Param("id") Long id,@Param("loginErrorCount") Integer loginErrorCount);
//修改用户锁定状态
    void updateStatus(Long id);
//修改密码
    void updatePasswordById(@Param("password") String passsword,@Param("id") Long id);
//根据邮箱判断用户是否存在
    List<User> findUserByEmail(String email);

    List<User> findPageListNoCondi(UserSearcherParam userSearcherParam);
}
