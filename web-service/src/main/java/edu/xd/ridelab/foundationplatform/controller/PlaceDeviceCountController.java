package edu.xd.ridelab.foundationplatform.controller;

import edu.xd.ridelab.foundationplatform.controller.response.ResponseResult;
import edu.xd.ridelab.foundationplatform.model.MachineCountByPolice;
import edu.xd.ridelab.foundationplatform.mysql.vo.District;
import edu.xd.ridelab.foundationplatform.service.PlaceCountService;
import edu.xd.ridelab.foundationplatform.service.PlaceDeviceCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 翟佳豪
 * @date 2018/05/18
 * @since Version 1.0
 */
@Controller
public class PlaceDeviceCountController {

    @Autowired
    private PlaceDeviceCountService placeDeviceCountService;

    @Autowired
    private PlaceCountService placeCountService;

    @RequestMapping(value = "/deviceCount/getAllDistrict",method=RequestMethod.GET)
    @ResponseBody
    public ResponseResult getAllDistrict(){

        ResponseResult responseResult = new ResponseResult();

        try{
            List<District> districtList = placeCountService.getAllDistrict();
            responseResult.setData(districtList);
            responseResult.setSuccess(true);
            responseResult.setMessage("查询成功");

        }catch (Exception e) {
            responseResult.setCode("failure");
            responseResult.setSuccess(false);
            responseResult.setMessage(e.getMessage());
            e.printStackTrace();
        }

        return responseResult;
    }

    @RequestMapping(value = "/deviceCount/getDeviceCount", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getDeviceCount(@RequestParam Long districtId){

        ResponseResult responseResult = new ResponseResult();

        try{
            List<MachineCountByPolice> list = placeDeviceCountService.getMachineCountByPolice(districtId);

            responseResult.setData(list);
            responseResult.setSuccess(true);
            responseResult.setCode("xxx");
            responseResult.setMessage("查询成功");
        } catch (Exception e){
            responseResult.setSuccess(false);
            responseResult.setCode("failure");
            responseResult.setMessage(e.getMessage());
            e.printStackTrace();
        }

        return responseResult;
    }
}
