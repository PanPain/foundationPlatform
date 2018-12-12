package edu.xd.ridelab.foundationplatform.controller;

import edu.xd.ridelab.foundationplatform.controller.response.ResponseResult;
import edu.xd.ridelab.foundationplatform.record.StationMacRecord;
import edu.xd.ridelab.foundationplatform.service.JudgeAnalyzeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class JudgeAnalyzeController {

    @Autowired
    JudgeAnalyzeService judgeAnalyzeService;

    @ResponseBody
    @RequestMapping(value = "/judgeAnalyze/trackQuery" , method = RequestMethod.GET)
    public ResponseResult trackQuery(@RequestParam String mac , @RequestParam String startTime ,
                                     @RequestParam String endTime){
        System.out.println(mac + "    " + startTime + "     " + endTime);
        ResponseResult responseResult = new ResponseResult();
        try{
            List<StationMacRecord> records = judgeAnalyzeService.trackQuery(mac , LocalDateTime.parse(startTime), LocalDateTime.parse(endTime));
            responseResult.setData(records);
            responseResult.setCode("success");
            responseResult.setMessage("操作成功");
            responseResult.setSuccess(true);
        }catch (Exception e){
            responseResult.setCode("failure");
            responseResult.setMessage(e.getMessage());
            responseResult.setSuccess(false);
        }
        return responseResult;
    }
}
