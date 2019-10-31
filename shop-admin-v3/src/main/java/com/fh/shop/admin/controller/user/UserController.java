package com.fh.shop.admin.controller.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fh.shop.admin.biz.resource.IResourceService;
import com.fh.shop.admin.biz.user.IUserService;
import com.fh.shop.admin.common.DataTableResult;
import com.fh.shop.admin.common.Log;
import com.fh.shop.admin.common.ResponseEnum;
import com.fh.shop.admin.common.ServerResponse;
import com.fh.shop.admin.param.user.UserPasswordParam;
import com.fh.shop.admin.param.user.UserSearcherParam;
import com.fh.shop.admin.po.role.Role;
import com.fh.shop.admin.po.user.User;
import com.fh.shop.admin.util.*;
import com.fh.shop.admin.vo.Resource.ResourceVo;
import com.fh.shop.admin.vo.user.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value="/user")
public class UserController {
    //注入service
@Resource(name="userService")
private IUserService userService;
@Autowired(required = false)
private HttpServletRequest request;
@Autowired
private IResourceService resourceService;
@Resource
private HttpServletResponse response;

private Logger LOGGER = LoggerFactory.getLogger("`");

//登陆验证
    @RequestMapping(value="/loginCheck")
    @ResponseBody
    public ServerResponse loginCheck(User userLogin){
        String sessionId = DistributeSession.getSessionId(response, request);
        String userName = userLogin.getUserName();
         String password = userLogin.getPassword();
        String imgCode = userLogin.getImgCode();
        //判断用户名密码验证码是否为空
        if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(password) || StringUtils.isEmpty(imgCode)){
            return ServerResponse.error(ResponseEnum.USERNAME_PASSWORD_IS_EMPTY);
        }
        //判断验证码是否为正确
        // String checkcode = (String) request.getSession().getAttribute("checkcode");
        String checkcode = RedisUtil.get(CookKey.buildImgCodeId(sessionId));
        if(!imgCode.equalsIgnoreCase(checkcode)){
            return ServerResponse.error(ResponseEnum.IMGCODE_IS_ERROR);
        }

        //查找数据库根据 用户名
        User user =  userService.loginCheck(userLogin);
      //判断用户是否存在
      if(null == user ) {
          return ServerResponse.error(ResponseEnum.USERNAME_IS_ERROR);
      }
      //判断当前用户是否被锁定
        if(DateUtil.date2str(new Date(),DateUtil.Y_M_D).equals(DateUtil.date2str(user.getLoginErrorDate(),DateUtil.Y_M_D)) && user.getLoginErrorCount()>=3 ){
          return ServerResponse.error(ResponseEnum.USER_IS_LOCK);
        }
      //判断用户密码是否正确
        if(!user.getPassword().equals(Md5Util.md5(Md5Util.md5(userLogin.getPassword()+user.getSalt())))){
            //密码错误
            //判断上次登陆错误时间和当前时间是否是同一天
            if(DateUtil.date2str(new Date(),DateUtil.Y_M_D).equals(DateUtil.date2str(user.getLoginErrorDate(),DateUtil.Y_M_D))){
                //是同一天  将登陆错误次数加一 并登陆错误时间更新为当前时间 错误登陆时间在sql写
                user.setLoginErrorCount(user.getLoginErrorCount()+1);
            }else{
                //不是同一天  将登陆错误次数设置为一 并将登陆错误时间设置为当前时间 错误登陆时间在sql写
                user.setLoginErrorCount(1);
            }
            //更新登陆错误信息
            userService.updateLoginErrorData(user.getId(),user.getLoginErrorCount());
            return ServerResponse.error(ResponseEnum.PASSWORD_IS_ERROR);
        }
        //判断登陆时间和当前时间是否是同一天
        if(DateUtil.date2str(new Date(),DateUtil.Y_M_D).equals(DateUtil.date2str(user.getLoginDate(),DateUtil.Y_M_D))){
                user.setLoginCount(user.getLoginCount()+1);
        }else{
            user.setLoginCount(1);
        }
        //把更新后的信息同步到数据库
        userService.updateUserLoginData(user);
        //把登陆验证成功的信息放到session中
        //request.getSession().setAttribute(SystemConst.CURRENT_USER,user);
        //把登陆验证成功的信息放到redis中
        String userJsonStr = JSONObject.toJSONString(user);
        RedisUtil.setEx(CookKey.buildUserId(sessionId),SystemConst.CODE_EXPIRE,userJsonStr);
        //查询动态导航条需要数据 菜单
        List<ResourceVo> resourceList = resourceService.findAllResourceByUserId(user.getId());
        //查询所有权限
        List<com.fh.shop.admin.po.Resource.Resource> urlList =  resourceService.findUserResourceList();
        //查询用户对应所有权限
        List<String> userResourceUrl = resourceService.findUserResourceList(user.getId());
        //将查到的权限放到session中
      /*  request.getSession().setAttribute(SystemConst.USER_RESOURCE_MENU,resourceList);
        request.getSession().setAttribute(SystemConst.RESOURCE_ALL_LIST,urlList);
        request.getSession().setAttribute(SystemConst.USER_RESOURCE_ALL_URL_LIST,userResourceUrl);*/

        String menuShowJsonStr = JSONObject.toJSONString(resourceList);
        String allResourceJsonStr = JSONObject.toJSONString(urlList);
        String userAllResourceByidJsonStr = JSONObject.toJSONString(userResourceUrl);
        //将查到的信息放到redis中
        RedisUtil.setEx(CookKey.buildmenuShowId(sessionId),SystemConst.CODE_EXPIRE,menuShowJsonStr);
        RedisUtil.setEx(CookKey.buildallResourceId(sessionId),SystemConst.CODE_EXPIRE,allResourceJsonStr);
        RedisUtil.setEx(CookKey.builduserAllResource(sessionId),SystemConst.CODE_EXPIRE,userAllResourceByidJsonStr);
        //清空验证码
        RedisUtil.del(CookKey.buildImgCodeId(sessionId));

        return ServerResponse.success();

    }


//退出登陆  并且清空session中信息  退回登陆页面
    @RequestMapping(value="/loginOut")
    public String loginOut(){
        String sessionId = DistributeSession.getSessionId(response, request);
        RedisUtil.del(CookKey.buildUserId(sessionId),CookKey.buildallResourceId(sessionId),CookKey.buildmenuShowId(sessionId),CookKey.builduserAllResource(sessionId),CookKey.buildImgCodeId(sessionId));
        return "redirect:/";
    }

//上传图片
    @RequestMapping(value="/imgFileInputupload")
    @ResponseBody
    public ServerResponse imgFileInputupload(MultipartFile myfile){

           String url = userService.imgFileInputupload(myfile);
           return ServerResponse.success(url);

    }



//修改 方法
@Log("修改用户 方法")
@RequestMapping(value="/updateUserById")
@ResponseBody
    public ServerResponse updateUserById(User user){


        userService.updateUserById(user);
        return ServerResponse.success();

}

//回显  回填  方法
    @RequestMapping(value="/findUserById")
    @ResponseBody
    public ServerResponse findUserById(Long id){


            UserVo user =  userService.findUserById(id);
           return ServerResponse.success(user);


    }


//单个删除方法
@Log("单个删除用户 方法")
    @RequestMapping(value="/deleteUserById")
    @ResponseBody
    public ServerResponse deleteUserById(Long id){


            userService.deleteUserById(id);
          return   ServerResponse.success();

    }

    @Log("修改用户锁定状态")
    @RequestMapping("/updateStatus")
    @ResponseBody
    public ServerResponse updateStatus(Long id){
        userService.updateStatus(id);
        return ServerResponse.success();
    }


//新增方法
    @Log("新增用户方法")
    @RequestMapping(value="/add")
    @ResponseBody
    public ServerResponse add(User user){

            userService.addUser(user);
          return ServerResponse.success();

    }

// 批量删除
    @Log("批量删除用户方法")
    @RequestMapping(value="/deletebatchids")
    @ResponseBody
public ServerResponse deletebatchids(@RequestParam("idarr[]") List<Long> idarr){
          userService.deletebatchids(idarr);
     return ServerResponse.success();
}

    @Log("修改个人密码")
    @RequestMapping("updatePasswordByid")
    @ResponseBody
    public ServerResponse updatePasswordByid(UserPasswordParam userPasswordParam){
        ServerResponse serverResponse =  userService.updatePasswordByid(userPasswordParam);
        return serverResponse;
    }


    @RequestMapping("forgetUpdatePasswordByName")
    @ResponseBody
    public ServerResponse forgetUpdatePasswordByName(UserPasswordParam userPasswordParam){
        ServerResponse serverResponse =  userService.updatePasswordByName(userPasswordParam);
        return serverResponse;
    }




//跳转用户找回密码时修改自己密码页面
    @RequestMapping("toUserUpdateOneSelfPassword")

    public String toUserUpdateOneSelfPassword(){
        Boolean b =  userService.toUserUpdateOneSelfPassword();
        if(b){
            return "UserUpdateOneSelfPassword";
        }
        return "redirect:/error.jsp";
    }

//查询所有角色
    @RequestMapping(value="/findroleall")
    @ResponseBody
    public ServerResponse findroleall(){

            List<Role> roleList =  userService.findroleall();
          return ServerResponse.success(roleList);
    }

@Log("重置密码")
@RequestMapping("resetPasswordById")
@ResponseBody
public ServerResponse resetPasswordById(Long id){
    ServerResponse serverResponse =     userService.resetPasswordById(id);
        return serverResponse;
}

//查询用户列表
    @RequestMapping(value="/getUserList")
    @ResponseBody
    public ServerResponse getUserList(UserSearcherParam userSearcherParam){

        //查询用户列表
        DataTableResult dataTableResult =  userService.findAllData(userSearcherParam);
        return ServerResponse.success(dataTableResult);
    }

//找回密码
  @RequestMapping("forgetPassword")
  @ResponseBody
  public ServerResponse forgetPassword(User user){
      ServerResponse serverResponse =  userService.forgetPassword(user);
      return serverResponse;
  }

//查询用户信息
  @RequestMapping("findUserInfo")
  @ResponseBody
  public ServerResponse findUserInfo(){
      String sessionId = DistributeSession.getSessionId(response, request);
      String s = RedisUtil.get(CookKey.buildUserId(sessionId));
      User user =  JSON.parseObject(s,User.class);
      user.setLoginDateStr(DateUtil.date2str(user.getLoginDate(),DateUtil.full_year));
      return ServerResponse.success(user);
  }



//验证用户是否存在
    @RequestMapping("verifyUserIsExist")
    @ResponseBody
    public ServerResponse verifyUserIsExist(String verifyUser){
        ServerResponse serverResponse = userService.verifyUserIsExist(verifyUser);
        return serverResponse;
    }

//excel导出
    @RequestMapping("excelDownLoad")
    public void excelDownLoad(UserSearcherParam userSearcherParam,HttpServletResponse servletResponse){
        //获取导出数据集合
        List<User> userList =  userService.findPageListNoCondi(userSearcherParam);
        //创建导出需要数组
        String[] props = {"id","userName","age","salary","entryTime"};
        String[] headNames = {"id","用户名","年龄","薪资","入职时间"};
        //调用excel工具包 自动生成workbook
        XSSFWorkbook workbook = ExcelUtil.excelDownLoad(userList, "用户列表", headNames, props, User.class);
        //调用excel下载方法
        FileUtil.excelDownload(workbook,servletResponse);
    }





    //跳转发送邮件页面
    @RequestMapping("toForgetPassword")
    public String toForgetPassword(){
        ServerResponse serverResponse =  userService.toForgetPassword();
        if(serverResponse.getCode() == 200){
            return "forgetPassword";
        }
        return "redirect:/error.jsp";
    }


//跳转到展示页面
    @RequestMapping(value="/toList")
    public String toList(){
        return "/user/list";
    }

//跳转修改密码 页面
    @RequestMapping(value="/toUpdatePassword")
    public String toUpdatePassword(){
        return "/updatePassword";
    }

}
