package com.fh.shop.admin.biz.user;

import com.fh.shop.admin.common.DataTableResult;
import com.fh.shop.admin.common.ResponseEnum;
import com.fh.shop.admin.common.ServerResponse;
import com.fh.shop.admin.common.WebContext;
import com.fh.shop.admin.mapper.user.IUserMapper;
import com.fh.shop.admin.param.user.UserPasswordParam;
import com.fh.shop.admin.param.user.UserSearcherParam;
import com.fh.shop.admin.po.product.Product;
import com.fh.shop.admin.po.role.Role;
import com.fh.shop.admin.po.user.User;
import com.fh.shop.admin.po.user.User_Role;
import com.fh.shop.admin.util.*;
import com.fh.shop.admin.vo.user.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service(value="userService")
public class IUserServiceImpl implements IUserService {
@Autowired
private IUserMapper userMapper;
@Autowired(required = false)
private HttpServletRequest request;





//新增方法
    @Override
    public void addUser(User user) {
        String salt = UUID.randomUUID().toString();
        user.setSalt(salt);
        user.setPassword(Md5Util.encodePassword(user.getPassword(), user.getSalt()));
        userMapper.addUser(user);
        addRoleinT_user_role(user);
    }
//根据用户 新增 角色  中间表操作
    private void addRoleinT_user_role(User user) {
        String roleIds = user.getRoleIds();
        if(StringUtils.isNotBlank(roleIds)){
            String[] rolearr = roleIds.split(",");
            for (String roleid : rolearr) {
                //新增角色
                User_Role user_role = new User_Role();
                user_role.setRoleId(Long.parseLong(roleid));
                user_role.setUserId(user.getId());
                userMapper.addRole(user_role);
            }
        }
    }


//po对象转vo
    private UserVo buildUserVo(User user1) {
        UserVo userVo = new UserVo();
        userVo.setUserName(user1.getUserName());
        userVo.setAge(user1.getAge());
        userVo.setRealName(user1.getRealName());
        userVo.setId(user1.getId());
        userVo.setEmail(user1.getEmail());
        userVo.setPhone(user1.getPhone());
        userVo.setSex(user1.getSex());
        userVo.setSalary(user1.getSalary().toString());
        userVo.setImgURL(user1.getImgURL());
        userVo.setEntryTime(DateUtil.date2str(user1.getEntryTime(),DateUtil.Y_M_D));
        userVo.setStatus(SystemConst.LOGIN_ERROR_COUNT.equals(user1.getLoginErrorCount()));
        userVo.setAreaId1(user1.getAreaId1());
        userVo.setAreaId2(user1.getAreaId2());
        userVo.setAreaId3(user1.getAreaId3());
        userVo.setAreaNames(user1.getAreaNames());
        return userVo;
    }


//单个删除方法
    @Override
    public void deleteUserById(Long id) {
        userMapper.deleteUserById(id);
        //通过用户id删除中间表
        userMapper.deleteRoleIdByUserId(id);
    }
//回显  回填  方法
    @Override
    public UserVo findUserById(Long id) {
        User  user= userMapper.findUserById(id);
        //根据用户id查询角色id集合
       List<Integer> roleidarr = userMapper.findRoleIdByUserId(id);

         UserVo userVo = buildUserVo(user);
        userVo.setPassword(user.getPassword());
        userVo.setRoleIdlist(roleidarr);
        return userVo;
    }
//修改 方法
    @Override
    public void updateUserById(User user) {
        userMapper.updateUserById(user);
        userMapper.deleteRoleIdByUserId(user.getId());
        if(user.getRoleIds()!=null && user.getRoleIds()!=""){
            addRoleinT_user_role(user);
        }
    }
//查询所有角色
    @Override
    public List<Role> findroleall() {
        List<Role> findroleall = userMapper.findroleall();
        return findroleall;
    }
//上传图片
    @Override
    public String imgFileInputupload(MultipartFile img) {
        String filename = img.getOriginalFilename();
        //32位新名字
        String uuidname = UUID.randomUUID() + filename.substring(filename.lastIndexOf("."));
//创建ftp
        FTPClient ftp = new FTPClient();
//连接ftp
        try {
            ftp.connect("192.168.150.128");
            ftp.login("wang","wang");
            ftp.changeWorkingDirectory("img");
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftp.storeFile(uuidname,img.getInputStream());
            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/img/"+uuidname;
    }
// 批量删除
    @Override
    public void deletebatchids(List<Long> idarr) {
         userMapper.deletebatchids(idarr);
         //通过用户id集合 批量删除 中间表
        userMapper.deletebBatchUser_RoleByUserIdList(idarr);

    }


//查询用户列表
    @Override
    public DataTableResult findAllData(UserSearcherParam userSearcherParam) {

        //查询总条数
        Long totalCount = userMapper.findTotalCount(userSearcherParam);
        //查询当前页数据
        List<User> userList = userMapper.findPageList(userSearcherParam);
        //po集合转vo集合
        List<UserVo> userVos1 = PoList2ViList(userList);
        return new DataTableResult(totalCount,totalCount,userSearcherParam.getDraw(),userVos1);
    }
//登陆验证
    @Override
    public User loginCheck(User userLogin) {
        return userMapper.loginCheck(userLogin.getUserName());
    }
//把更新后的信息同步到数据库
    @Override
    public void updateUserLoginData(User user) {
        userMapper.updateUserLoginData(user);
    }
//更新登陆错误信息
    @Override
    public void updateLoginErrorData(Long id, Integer loginErrorCount) {
        userMapper.updateLoginErrorData(id,loginErrorCount);
    }
//修改用户锁定状态
    @Override
    public void updateStatus(Long id) {
        userMapper.updateStatus(id);
    }
//修改个人密码
    @Override
    public ServerResponse updatePasswordByid(UserPasswordParam userPasswordParam) {
        String confirmPassword = userPasswordParam.getConfirmPassword();
        String newPassword = userPasswordParam.getNewPassword();
        String oldPassword = userPasswordParam.getOldPassword();
        //判断非空
        if(StringUtils.isEmpty(confirmPassword)
                || StringUtils.isEmpty(newPassword)
                ||StringUtils.isEmpty(oldPassword)){
            return ServerResponse.error(ResponseEnum.PASSWORD_IS_EMPTY);
        }
        //判断两次新密码是否一致
        if(!newPassword.equals(confirmPassword)){
            return ServerResponse.error(ResponseEnum.PASSWORD_IS_DIFFERENT);
        }
        //查询数据库获取数据
        User user = userMapper.findUserById(userPasswordParam.getUserId());
        //盐
        String salt = user.getSalt();
        //判断数据库密码和原密码是否相同
        if(!user.getPassword().equals(Md5Util.encodePassword(oldPassword, salt))){
            return ServerResponse.error(ResponseEnum.OLDPASSWORD_IS_ERROR);
        }
       String passsword = Md5Util.encodePassword(newPassword,salt);
        //修改密码
       userMapper.updatePasswordById(passsword,user.getId());
        return ServerResponse.success();
    }
//重置密码
    @Override
    public ServerResponse resetPasswordById(Long id) {
        //获取当前id对应用户
        User user = userMapper.findUserById(id);
        //判断非空
        if(user == null ){
            return ServerResponse.error(ResponseEnum.USER_IS_EMPTY);
        }
        String salt = user.getSalt();
        String resetPassword = Md5Util.encodePassword(SystemConst.RESET_DEFAULT_PASSWORD,salt);
        //重置密码
        userMapper.updatePasswordById(resetPassword,user.getId());
        return ServerResponse.success();
    }
//验证用户是否存在
    @Override
    public ServerResponse verifyUserIsExist(String verifyUser) {
        //判断输入的用户名或邮箱是否为空
        if(StringUtils.isEmpty(verifyUser)){
            return ServerResponse.error(ResponseEnum.USERNAME_EMAIL_IS_EMPTY);
        }
        User user = null;
        List<User> list  = new ArrayList<>();
        //判断输入的是邮箱还是用户名
        //邮箱
        if(verifyUser.contains("@")) {
             list  = userMapper.findUserByEmail(verifyUser);
        } else{
            //用户名
             user = userMapper.loginCheck(verifyUser);
        }
        //判断用户是否存在
        if(user == null &&list.size() ==0 ){
            ServerResponse.error(ResponseEnum.USER_IS_EMPTY);
        }
        //用户存在  放入session中标志位 用来发送邮件时判断
        request.getSession().setAttribute(SystemConst.USER_FORGET_PASSWORD,SystemConst.USER_IS_EXIST);
        return ServerResponse.success();
    }

    @Override
    public ServerResponse toForgetPassword() {
        Integer flag = (Integer) request.getSession().getAttribute(SystemConst.USER_FORGET_PASSWORD);
        if(flag != SystemConst.USER_IS_EXIST){
            return ServerResponse.error(ResponseEnum.USER_IS_NOT_LOGIN);
        }
        return ServerResponse.success();
    }
//找回密码
    @Override
    public ServerResponse forgetPassword(User user) {
        //判断非法登陆
        Integer flag = (Integer) request.getSession().getAttribute(SystemConst.USER_FORGET_PASSWORD);
        if(flag != SystemConst.USER_IS_EXIST){
            return ServerResponse.error(ResponseEnum.USER_IS_NOT_LOGIN);
        }
        //判断输入的用户名或邮箱是否为空
        // 1689cd4047c87ed6479a8bcffdd9f7c3
        // 3aa8bfab-2563-4d11-a3d2-9f84de55a99f
        if(StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getEmail())){
            return ServerResponse.error(ResponseEnum.USERNAME_EMAIL_IS_EMPTY);
        }
        //根据用户名查找用户信息
        User user1 = userMapper.loginCheck(user.getUserName());
        if(null == user1){
            return ServerResponse.error(ResponseEnum.USER_IS_EMPTY);
        }
        //判断邮件是否正确
        if(!user.getEmail().equals(user1.getEmail())){
            return ServerResponse.error(ResponseEnum.EMAIL_is_defferent);
        }
        //发送邮件
        EmailUtil.postEmail(user.getEmail(),"<h2>请点击下面地址进行更改密码，有效期30分钟</h2><br/><a href='http://localhost:8080/user/toUserUpdateOneSelfPassword.jhtml'>点击此处进行修改</a>","找回密码");

        //session中数据更新标志位
        request.getSession().setAttribute(SystemConst.CURRENT_USER,user.getUserName());

        return ServerResponse.success();
    }

    @Override
    public Boolean toUserUpdateOneSelfPassword() {
        Boolean flag = true;
        String userName = (String) request.getSession().getAttribute(SystemConst.CURRENT_USER);
        if(userName == null){
            flag = false;
        }
        return flag;
    }



    @Override
    public ServerResponse updatePasswordByName(UserPasswordParam userPasswordParam) {
        String userName = (String) request.getSession().getAttribute(SystemConst.CURRENT_USER);
        if(userName == null){
            return ServerResponse.error(ResponseEnum.POST_IS_ERROR);
        }
        String confirmPassword = userPasswordParam.getConfirmPassword();
        String newPassword = userPasswordParam.getNewPassword();
        //判断非空
        if(StringUtils.isEmpty(confirmPassword)
                || StringUtils.isEmpty(newPassword)){
            return ServerResponse.error(ResponseEnum.PASSWORD_IS_EMPTY);
        }
        //判断两次新密码是否一致
        if(!newPassword.equals(confirmPassword)){
            return ServerResponse.error(ResponseEnum.PASSWORD_IS_DIFFERENT);
        }
        //更新密码
        User user = userMapper.loginCheck(userName);
        String password = Md5Util.encodePassword(newPassword,user.getSalt());
        //更新密码
        userMapper.updatePasswordById(password,user.getId());
        return ServerResponse.success();
    }

    @Override
    public List<User> findPageListNoCondi(UserSearcherParam userSearcherParam) {
        List<User> pageListNoCondi = userMapper.findPageListNoCondi(userSearcherParam);
        return pageListNoCondi;
    }

    //po集合转vo集合
 private List<UserVo> PoList2ViList(List<User> userList) {
        List<UserVo> userVos = new ArrayList<>();
        for (User user1 : userList) {
            UserVo userVo = buildUserVo(user1);
            List<String> roleNames= userMapper.findRoleName(user1.getId());
            if(roleNames.size()>0){
                String join = StringUtils.join(roleNames, ",");
                userVo.setRoleNames(join);
            }else{
                userVo.setRoleNames("无");
            }
            userVos.add(userVo);

        }
        return userVos;
    }

}
