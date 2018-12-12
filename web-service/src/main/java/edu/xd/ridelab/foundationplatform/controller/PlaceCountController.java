package edu.xd.ridelab.foundationplatform.controller;

import edu.xd.ridelab.foundationplatform.controller.response.ResponseResult;
import edu.xd.ridelab.foundationplatform.model.PlacePropCountByDistrict;
import edu.xd.ridelab.foundationplatform.model.PlaceTypeCountByDistrict;
import edu.xd.ridelab.foundationplatform.mysql.vo.District;
import edu.xd.ridelab.foundationplatform.service.PlaceCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 翟佳豪
 * @date 2018/05/10
 * @since Version 1.0
 */

@Controller
public class  PlaceCountController {

    @Autowired
    private PlaceCountService placeCountService;

    @RequestMapping(value = "/placeCount/getAllDistrict",method=RequestMethod.GET)
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

    @RequestMapping(value="/placeCount/countByProp", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult countByProp(){

        ResponseResult responseResult = new ResponseResult();

        try{
            List<PlacePropCountByDistrict> result = placeCountService.countPlaceByProp();
            responseResult.setData(result);
            responseResult.setCode("success");
            responseResult.setSuccess(true);
            responseResult.setMessage("按经营性质统计区域场所数量成功");

        } catch (Exception e) {
            responseResult.setCode("failure");
            responseResult.setSuccess(false);
            responseResult.setMessage(e.getMessage());
            e.printStackTrace();
        }

        return responseResult;
    }

    @RequestMapping(value="/placeCount/countByType", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult countByType(){

        ResponseResult responseResult = new ResponseResult();

        try{
            List<PlaceTypeCountByDistrict> result = placeCountService.countPlaceByType();
            responseResult.setData(result);
            responseResult.setCode("success");
            responseResult.setSuccess(true);
            responseResult.setMessage("按服务类型统计区域场所数量成功");

        } catch (Exception e) {
            responseResult.setCode("failure");
            responseResult.setSuccess(false);
            responseResult.setMessage(e.getMessage());
            e.printStackTrace();
        }

        return responseResult;
    }
}
