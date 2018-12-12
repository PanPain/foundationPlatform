package edu.xd.ridelab.foundationplatform.controller;

import edu.xd.ridelab.foundationplatform.controller.response.ResponseResult;
import edu.xd.ridelab.foundationplatform.mysql.vo.District;
import edu.xd.ridelab.foundationplatform.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author :  zf
 * @date :  2018/5/7
 * @since :  Version 1.0
 */
@Controller
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @RequestMapping(value = "/district/searchDistrict", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult searchDistrict(@RequestParam(required = false) String districtNo,
                                         @RequestParam(required = false) String districtName, @RequestParam(required = false) String districtLevel,
                                         @RequestParam(defaultValue = "1") int curPage, @RequestParam(defaultValue = "10") int pageNum) {
        int offset = ( curPage - 1 ) * pageNum;
        ResponseResult responseResult= new ResponseResult();
        List<District> districtList = null;
        try {
            districtList = districtService.getDistrictList(districtNo, districtName, districtLevel, offset, pageNum);
            responseResult.setData(districtList);
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

    @RequestMapping(value = "/district/getAllDistrict", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getAllDistrict(@RequestParam(defaultValue = "1") int curPage, @RequestParam(defaultValue = "10") int pageNum) {
        int offset = ( curPage - 1 ) * pageNum;
        ResponseResult responseResult= new ResponseResult();
        List<District> districtList = null;
        try {
            districtList = districtService.getAllDistrict(offset, pageNum);
            responseResult.setData(districtList);
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

    @RequestMapping(value = "/district/getDistrictDetailInfo" ,method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getDistrictDetailInfo(@RequestParam Long districtId) {
        ResponseResult responseResult=new ResponseResult();
        District district = null;
        try {
            district = districtService.getDistrict(districtId);
            responseResult.setData(district);
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

    @RequestMapping(value = "/district/addDistrict", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult addDistrict(@RequestBody District district) {
        ResponseResult responseResult = new ResponseResult();
        try {
            int number = districtService.insertDistrict(district);
            responseResult.setCode("success");
            responseResult.setSuccess(true);
            responseResult.setMessage("成功添加" + number + "条");
        } catch (Exception e) {
            responseResult.setCode("failure");
            responseResult.setSuccess(false);
            responseResult.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return responseResult;
    }

    @RequestMapping(value = "/district/deleteDistrict", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseResult deleteDistrict(@RequestParam Long districtId) {
        ResponseResult responseResult = new ResponseResult();
        try {
            int number = districtService.deleteDistrict(districtId);
            responseResult.setCode("success");
            responseResult.setSuccess(true);
            responseResult.setMessage("成功删除" + number + "条");
        } catch (Exception e) {
            responseResult.setCode("failure");
            responseResult.setSuccess(false);
            responseResult.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return responseResult;
    }

    @RequestMapping(value = "/district/deleteBatchDistrict", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult deleteBatchDistrict(@RequestBody List<Long> list) {
        System.out.println(list);
        ResponseResult responseResult = new ResponseResult();
        try {
            int number = districtService.deleteBatchByPrimaryKey(list);
            responseResult.setCode("success");
            responseResult.setSuccess(true);
            responseResult.setMessage("成功删除" + number + "条");
        } catch (Exception e) {
            responseResult.setCode("failure");
            responseResult.setSuccess(false);
            responseResult.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return responseResult;
    }

    @RequestMapping(value = "/district/updateDistrict", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult updateDistrict(@RequestBody District district) {
        ResponseResult responseResult = new ResponseResult();
        try {
            int number = districtService.modifyDistrictByDistrictId(district);
            responseResult.setCode("success");
            responseResult.setSuccess(true);
            responseResult.setMessage("成功更新" + number + "条");
        } catch (Exception e) {
            responseResult.setCode("failure");
            responseResult.setSuccess(false);
            responseResult.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return responseResult;
    }
}
