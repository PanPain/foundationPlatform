package edu.xd.ridelab.foundationplatform.controller;

import edu.xd.ridelab.foundationplatform.controller.response.ResponseResult;
import edu.xd.ridelab.foundationplatform.model.SystemLogInfo;
import edu.xd.ridelab.foundationplatform.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @author 翟佳豪
 * @date 2018/05/22
 * @since Version 1.0
 */
@Controller
public class SystemLogController {

    @Autowired
    private SystemLogService systemLogService;

    @RequestMapping(value = "systemLog/searchAllLogs" , method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getAllLogInfo(@RequestParam(defaultValue = "1") int curPage, @RequestParam(defaultValue = "10") int pageSize){

        ResponseResult responseResult = new ResponseResult();
        int offset = (curPage - 1) * pageSize;

        try{
            List<SystemLogInfo> list = systemLogService.getAllLogInfo(offset, pageSize);
            responseResult.setData(list);
            responseResult.setMessage("查询成功");
            responseResult.setSuccess(true);
            responseResult.setCode("success");

        }catch (Exception e){
            responseResult.setMessage(e.getMessage());
            responseResult.setSuccess(false);
            responseResult.setCode("failure");
        }

        return responseResult;
    }

    @RequestMapping(value = "systemLog/searchByConditions" , method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult searchByConditions(@RequestParam(required = false) Integer logType, @RequestParam(required = false) String module,
                                             @RequestParam(required = false) String userName, @RequestParam(required = false) Date startTime,
                                             @RequestParam(required = false) Date endTime, @RequestParam(required = false) String message,
                                             @RequestParam(defaultValue = "1") int curPage, @RequestParam(defaultValue = "10") int pageSize){

        ResponseResult responseResult = new ResponseResult();
        int offset = (curPage - 1) * pageSize;

        try{
            List<SystemLogInfo> list = systemLogService.getLogByConditions(logType, module, userName, startTime, endTime, message, offset, pageSize);
            responseResult.setData(list);
            responseResult.setMessage("查询成功");
            responseResult.setSuccess(true);
            responseResult.setCode("success");

        }catch (Exception e){
            responseResult.setMessage(e.getMessage());
            responseResult.setSuccess(false);
            responseResult.setCode("failure");
        }

        return responseResult;
    }
}
