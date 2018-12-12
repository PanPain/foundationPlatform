package edu.xd.ridelab.foundationplatform.controller;


import edu.xd.ridelab.foundationplatform.controller.response.ResponseResult;
import edu.xd.ridelab.foundationplatform.model.PlaceInfoDistrictModel;
import edu.xd.ridelab.foundationplatform.mysql.vo.PlaceInfo;
import edu.xd.ridelab.foundationplatform.service.PlaceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author：WW
 * @Description
 * @Date: 17:11 2018/5/5
 **/
@Controller

public class PlaceInfoController {

    @Autowired
    private PlaceInfoService placeInfoService;

    @RequestMapping(value = "/place/searchPlaceInfo" ,method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult searchPlaceInfo(@RequestParam(required = false) String placeName, @RequestParam(required = false) String placeNo,
                                          @RequestParam(required = false) String address, @RequestParam(required = false) String type,
                                          @RequestParam(required = false) String property, @RequestParam(required = false) String legalPerson,
                                          @RequestParam(required = false) Integer orgCode ,
                                          @RequestParam(defaultValue = "1") int curPage , @RequestParam(defaultValue = "1") int pageNum)
    {
        int offset=(curPage-1)*pageNum;
        ResponseResult responseResult=new ResponseResult();
        List<PlaceInfoDistrictModel> placeInfoList = null;
        try {
            placeInfoList = placeInfoService.getPlaceInfoList( placeName,   placeNo,  address,  type,  property, legalPerson,  orgCode,  offset,  pageNum);
            responseResult.setData(placeInfoList);
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


    @RequestMapping(value = "/place/getPlaceInfoById" ,method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult searchPlaceInfo(@RequestParam(required = false) Long placeId)
    {
        ResponseResult responseResult=new ResponseResult();
        List<PlaceInfoDistrictModel> placeInfoList = null;
        try {
            placeInfoList = placeInfoService.getPlaceInfoById(placeId);
            responseResult.setData(placeInfoList);
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


    @RequestMapping(value = "place/getAllPlaceInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getAllPlaceInfo(@RequestParam(defaultValue = "1") int curPage, @RequestParam(defaultValue = "10") int pageNum) {
        int offset = ( curPage - 1 ) * pageNum;
        ResponseResult responseResult= new ResponseResult();
        List<PlaceInfoDistrictModel> placeInfoList = null;
        try {
            placeInfoList = placeInfoService.getAllPlaceInfo(offset, pageNum);
            responseResult.setData(placeInfoList);
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

    @RequestMapping(value = "/place/getPlaceDetailInfo" ,method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getPlaceDetailInfo(@RequestParam(defaultValue = "1") Long placeId){
        ResponseResult responseResult=new ResponseResult();

        PlaceInfo placeInfo = null;
        try {
            placeInfo = placeInfoService.getPlaceInfo(placeId);
            responseResult.setData(placeInfo);
            responseResult.setCode("success");
            responseResult.setSuccess(true);
            responseResult.setMessage("查询成功");
        } catch (Exception e) {
            responseResult.setCode("failure");
            responseResult.setSuccess(false);
            responseResult.setMessage(e.getMessage());
            e.printStackTrace();
        }

        return  responseResult;
    }
    @RequestMapping(value = "/place/deletePlaceInfo" ,method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult deletePlaceInfo(@RequestParam Long placeId){
        ResponseResult responseResult=new ResponseResult();

        try {
            int number=placeInfoService.deletePlaceInfo(placeId);
            responseResult.setCode("success");
            responseResult.setSuccess(true);
            responseResult.setMessage("成功删除"+number+"条");
        } catch (Exception e) {
            responseResult.setCode("failure");
            responseResult.setSuccess(false);
            responseResult.setMessage(e.getMessage());
            e.printStackTrace();
        }

        return responseResult;
    }

    @RequestMapping(value ="/place/updatePlaceInfo" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult updatePlaceInfo(@RequestBody PlaceInfo placeInfo){
        ResponseResult responseResult=new ResponseResult();

        try {
            int number=placeInfoService.modifyPlaceInfoByPlaceId(placeInfo);
            responseResult.setCode("success");
            responseResult.setSuccess(true);
            responseResult.setMessage("更新成功"+number+"条");
        } catch (Exception e) {
            responseResult.setCode("failure");
            responseResult.setSuccess(false);
            responseResult.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return responseResult;
    }

    @RequestMapping(value ="/place/addPlaceInfo" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult addPlaceInfo(@RequestBody PlaceInfo placeInfo){
        ResponseResult responseResult=new ResponseResult();
        try {
            int number=placeInfoService.insertPlaceInfo(placeInfo);
            responseResult.setCode("success");
            responseResult.setSuccess(true);
            responseResult.setMessage("更新成功"+number+"条");
        } catch (Exception e) {
            responseResult.setCode("failure");
            responseResult.setSuccess(false);
            responseResult.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return responseResult;
    }

}
