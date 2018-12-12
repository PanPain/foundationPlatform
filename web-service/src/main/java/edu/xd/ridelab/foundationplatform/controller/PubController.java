package edu.xd.ridelab.foundationplatform.controller;

import edu.xd.ridelab.foundationplatform.controller.response.ResponseResult;
import edu.xd.ridelab.foundationplatform.mysql.vo.User;
import edu.xd.ridelab.foundationplatform.service.PubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PubController {

    @Autowired
    private PubService pubService;

    @ResponseBody
    @RequestMapping(value = "/login" , method = RequestMethod.GET)
    public ResponseResult signin(@RequestParam(required = true) String userAccount ,@RequestParam String userPassword ,HttpSession httpSession ){
        User user = pubService.userLoginCheck(userAccount,userPassword);
        if(user != null && user.getUserPassword().endsWith(userPassword)){
            httpSession.setAttribute("",user.getUserId());
            httpSession.setAttribute("",user.getUserAccount());

            Map<String,String> data = new HashMap<>();
            data.put("userId" , user.getUserId() + "");
            data.put("userAccount" , user.getUserAccount());
            return new ResponseResult(true , "success" , "登录成功" , data);
        }

        return new ResponseResult(false , "failure" , "用户名或密码错误" ,null);
    }
}
