package edu.xd.ridelab.foundationplatform.controller;

import edu.xd.ridelab.foundationplatform.controller.response.ResponseResult;
import edu.xd.ridelab.foundationplatform.model.UserInfoModel;
import edu.xd.ridelab.foundationplatform.mysql.vo.User;
import edu.xd.ridelab.foundationplatform.mysql.vo.UserRoleRelation;
import edu.xd.ridelab.foundationplatform.service.PoliceOfficeInfoService;
import edu.xd.ridelab.foundationplatform.service.RoleService;
import edu.xd.ridelab.foundationplatform.service.UserRoleRelationService;
import edu.xd.ridelab.foundationplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PoliceOfficeInfoService policeOfficeInfoService;

    @Autowired
    UserRoleRelationService userRoleRelationService;

    @ResponseBody
    @RequestMapping(value = "/user/getAllUserInfo" , method = RequestMethod.GET)
    public ResponseResult getAllUserInfo(){
        ResponseResult responseResult = new ResponseResult();
        List<UserInfoModel> userInfoModels = null;
        try{
            userInfoModels = userService.selectAllUserInfo();
            responseResult.setData(userInfoModels);
            responseResult.setCode("success");
            responseResult.setSuccess(true);
            responseResult.setMessage("查询成功");
        }catch(Exception e){
            responseResult.setCode("failure");
            responseResult.setSuccess(false);
            responseResult.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return responseResult;
    }


    @Transactional
    @ResponseBody
    @RequestMapping(value = "/user/modifyUserInfo" , method = RequestMethod.POST)
    public ResponseResult modifyUserInfo(@RequestBody UserInfoModel userInfoModel){
        ResponseResult responseResult = new ResponseResult();
        try{
            Long roleId = null;
            if(userInfoModel.getRoleName() != null)
                roleId = roleService.selectRoleIdByRoleName(userInfoModel.getRoleName());

            Long policeOfficeId = null;
            if(userInfoModel.getPoliceOfficeName() != null)
                policeOfficeId = policeOfficeInfoService.selectPoliceOfficeIdByName(userInfoModel.getPoliceOfficeName());

            User user = new User();
            user.setUserId(userInfoModel.getUserId());
            user.setUserAccount(userInfoModel.getUserAccount());
            user.setUserPhone(userInfoModel.getUserPhone());
            user.setUserRealName(userInfoModel.getUserRealName());
            user.setFkPoliceOfficeId(policeOfficeId);

            if(userInfoModel.getUserId() != null) {
                Long userRoleRelationId = userRoleRelationService.selectByUserId(userInfoModel.getUserId()).getRelationId();
                UserRoleRelation userRoleRelation = new UserRoleRelation();
                userRoleRelation.setRelationId(userRoleRelationId);
                userRoleRelation.setFkRoleId(roleId);
                userRoleRelation.setFkUserId(userInfoModel.getUserId());
                userRoleRelation.setModifyTime(new Date(System.currentTimeMillis()));
                userRoleRelationService.updateByPrimaryKeySelective(userRoleRelation);
            }

            userService.updateByPrimaryKeySelective(user);

            responseResult.setSuccess(true);
            responseResult.setMessage("修改成功");
            responseResult.setCode("success");

        }catch(Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            responseResult.setMessage(e.getMessage());
            responseResult.setSuccess(false);
            responseResult.setCode("failure");
            e.printStackTrace();
        }
        return responseResult;
    }

    @ResponseBody
    @Transactional
    @RequestMapping(value = "/user/addUser" , method = RequestMethod.POST)
    public ResponseResult addUser(@RequestBody UserInfoModel userInfoModel){
        ResponseResult responseResult = new ResponseResult();
        //1.判定需要的字段是否都存在
        if(userInfoModel.getUserAccount() == null || userInfoModel.getUserPassword() == null ||
                userInfoModel.getPoliceOfficeName() == null || userInfoModel.getRoleName() == null){
            responseResult.setSuccess(false);
            responseResult.setCode("failure");
            responseResult.setMessage("参数不足");
            return responseResult;
        }
        //执行插入操作
        try {
            Long roleId = roleService.selectRoleIdByRoleName(userInfoModel.getRoleName());
            Long policeOfficeId = policeOfficeInfoService.selectPoliceOfficeIdByName(userInfoModel.getPoliceOfficeName());

            User user = new User();
            user.setUserAccount(userInfoModel.getUserAccount());
            user.setUserPassword(userInfoModel.getUserPassword());
            user.setFkPoliceOfficeId(policeOfficeId);
            user.setUserRealName(userInfoModel.getUserRealName());
            user.setUserPhone(userInfoModel.getUserPhone());

            userService.addUser(user);

            UserRoleRelation userRoleRelation = new UserRoleRelation();
            userRoleRelation.setModifyTime(new Date(System.currentTimeMillis()));
            userRoleRelation.setFkUserId(userService.getUserByUserAccount(user.getUserAccount()).getUserId());
            userRoleRelation.setFkRoleId(roleId);

            userRoleRelationService.addUserRoleRelation(userRoleRelation);

            responseResult.setSuccess(true);
            responseResult.setMessage("添加成功");
            responseResult.setCode("success");

        }catch(Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            responseResult.setMessage(e.getMessage());
            responseResult.setSuccess(false);
            responseResult.setCode("failure");
            e.printStackTrace();
        }

        return responseResult;
    }

    @ResponseBody
    @Transactional
    @RequestMapping(value = "/user/deleteUser" , method = RequestMethod.GET)
    public ResponseResult addUser(@RequestParam Long userId){
        ResponseResult responseResult = new ResponseResult();
        try{
            UserRoleRelation userRoleRelation = userRoleRelationService.selectByUserId(userId);

            //删除操作
            userRoleRelationService.deleteByPrimaryKey(userRoleRelation.getRelationId());
            userService.deleteUserByPrimaryKey(userId);

            responseResult.setSuccess(true);
            responseResult.setMessage("删除成功");
            responseResult.setCode("success");

        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            responseResult.setMessage(e.getMessage());
            responseResult.setSuccess(false);
            responseResult.setCode("failure");
            e.printStackTrace();
        }
        return responseResult;
    }

}
