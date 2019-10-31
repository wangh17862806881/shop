package com.fh.shop.admin.biz.user;

import com.fh.shop.admin.common.DataTableResult;
import com.fh.shop.admin.common.ServerResponse;
import com.fh.shop.admin.param.user.UserPasswordParam;
import com.fh.shop.admin.param.user.UserSearcherParam;
import com.fh.shop.admin.po.role.Role;
import com.fh.shop.admin.po.user.User;
import com.fh.shop.admin.vo.user.UserVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface IUserService {
//新增方法
    void addUser(User user);


//单个删除方法
    void deleteUserById(Long id);
//回显  回填  方法
UserVo findUserById(Long id);
//修改 方法
    void updateUserById(User user);
//查询所有角色
    List<Role> findroleall();
//上传图片
    String imgFileInputupload(MultipartFile img);
// 批量删除
void deletebatchids(List<Long> idarr);
//查询用户列表
    DataTableResult findAllData(UserSearcherParam userSearcherParam);
//登陆验证
    User loginCheck(User userLogin);
//把更新后的信息同步到数据库
    void updateUserLoginData(User user);
//更新登陆错误信息
    void updateLoginErrorData(Long id, Integer loginErrorCount);
//修改用户锁定状态
    void updateStatus(Long id);
//修改个人密码
    ServerResponse updatePasswordByid(UserPasswordParam userPasswordParam);
//重置密码
    ServerResponse resetPasswordById(Long id);
//验证用户是否存在
    ServerResponse verifyUserIsExist(String verifyUser);
//验证 找回密码 是否是非法登陆
    ServerResponse toForgetPassword();
//找回密码
    ServerResponse forgetPassword(User user);

    Boolean toUserUpdateOneSelfPassword();

    ServerResponse updatePasswordByName(UserPasswordParam userPasswordParam);


    List<User> findPageListNoCondi(UserSearcherParam userSearcherParam);
}
