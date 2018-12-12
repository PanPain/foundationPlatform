package edu.xd.ridelab.foundationplatform.controller;

import edu.xd.ridelab.foundationplatform.controller.response.ResponseResult;
import edu.xd.ridelab.foundationplatform.model.MachineCountByDistrict;
import edu.xd.ridelab.foundationplatform.service.MachineStatusCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author :  zf
 * @date :  2018/5/15
 * @since :  Version 1.0
 */
@Controller
public class MachineStatusCountController {
    @Autowired
    private MachineStatusCountService machineStatusCountService;

    @RequestMapping(value = "machineCount/getMachineStatusCountByDistrict" ,method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getMachineStatusCountByDistrict() {
            ResponseResult responseResult = new ResponseResult();
            List<MachineCountByDistrict> list = null;
            try {
                list = machineStatusCountService.getMachineStatusCountByDistrict();
                responseResult.setData(list);
                responseResult.setCode("success");
                responseResult.setSuccess(true);
                responseResult.setMessage("查询成功");
            } catch (Exception e) {
                responseResult.setCode("failure");
                responseResult.setSuccess(false);
                responseResult.setMessage(e.getMessage());
                e.printStackTrace();
            }
            return responseResult;
    }

    @RequestMapping(value = "machineCount/getMachineStatusCount" ,method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getMachineStatusCount() {
        ResponseResult responseResult = new ResponseResult();
        Map<String, Integer> map = null;
        try {
            map = machineStatusCountService.getMachineStatusCount();
            responseResult.setData(map);
            responseResult.setCode("success");
            responseResult.setSuccess(true);
            responseResult.setMessage("查询成功");
        } catch (Exception e) {
            responseResult.setCode("failure");
            responseResult.setSuccess(false);
            responseResult.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return responseResult;
    }
}
