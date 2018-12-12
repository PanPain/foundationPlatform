package edu.xd.ridelab.foundationplatform.controller;

import edu.xd.ridelab.foundationplatform.controller.response.ResponseResult;
import edu.xd.ridelab.foundationplatform.mysql.vo.Manufacturer;
import edu.xd.ridelab.foundationplatform.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * @Author ChenXiang
 * @Date 2018/05/08,16:51
 */
@Controller
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;
    @RequestMapping(value = "/manufacturer/addManu", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult addManu(@RequestBody  Manufacturer manufacturer) {
        ResponseResult responseResult = new ResponseResult();
        try {
            int num = manufacturerService.insertManufacturer(manufacturer);
            responseResult.setData(manufacturer);
            responseResult.setCode("success");
            responseResult.setSuccess(true);

            responseResult.setMessage("成功添加" + num + "条");

        } catch (Exception e) {
            responseResult.setCode("failure");
            responseResult.setSuccess(false);
            responseResult.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return responseResult;
    }

    @RequestMapping(value = "/manufacturer/queryManuByCond", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseResult queryManuByCond(@RequestParam(required = false) String mfName, @RequestParam(required = false) String mfOrgNo,
                                          @RequestParam(required = false) String mfAddress,
                                          @RequestParam(defaultValue = "1") int curPage, @RequestParam(defaultValue = "1") int pageNum) {

        int offset = (curPage - 1) * pageNum;
        ResponseResult responseResult = new ResponseResult();

        List<Manufacturer> manufacturerList = null;
        try {
            manufacturerList = manufacturerService.getManufacturerList(mfName, mfOrgNo, mfAddress, offset, pageNum);
            responseResult.setData(manufacturerList);
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

    @RequestMapping(value = "/manufacturer/modifyManufacturer", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult modifyManufacturer(@RequestBody Manufacturer manufacturer) {
        ResponseResult responseResult = new ResponseResult();
        try {
            int num = manufacturerService.updateManufacturerByManuId(manufacturer);
            responseResult.setCode("success");
            responseResult.setSuccess(true);
            responseResult.setMessage("成功更新" + num + "条");

        } catch (Exception e) {
            responseResult.setCode("failure");
            responseResult.setSuccess(false);
            responseResult.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return responseResult;
    }

    @RequestMapping(value = "/manufacturer/getManuDetai", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getManuDetail(@RequestParam(defaultValue = "1") Long manuId) {
        ResponseResult responseResult = new ResponseResult();
        Manufacturer manufacturer = null;

        try {
            manufacturer = manufacturerService.getManufacturer(manuId);
            responseResult.setData(manufacturer);
            responseResult.setSuccess(true);
            responseResult.setCode("success");

            responseResult.setMessage("查询成功");
        } catch (Exception e) {
            responseResult.setCode("failure");
            responseResult.setSuccess(false);
            responseResult.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return responseResult;
    }

    @RequestMapping(value = "/manufacturer/deleteManu", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseResult deleteManu(@RequestParam Long manuId) {
        ResponseResult responseResult = new ResponseResult();

        try {
            int num = manufacturerService.deleteManufacturer(manuId);
            responseResult.setCode("success");
            responseResult.setSuccess(true);
            responseResult.setMessage("删除成功" + num + "条");

        } catch (Exception e) {
            responseResult.setCode("failure");
            responseResult.setSuccess(false);
            responseResult.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return responseResult;
    }


    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult selectAll(@RequestParam(defaultValue = "1") int curPage, @RequestParam(defaultValue = "1") int pageNum) {
        ResponseResult responseResult = new ResponseResult();
        List<Manufacturer> manufacturerList = null;
        int offset = (curPage - 1) * pageNum;
        try {
            manufacturerList = manufacturerService.getAllManu(offset, pageNum);
            responseResult.setData(manufacturerList);
            responseResult.setCode("success");
            responseResult.setSuccess(true);
            responseResult.setMessage("成功获取所有厂商");

        } catch (Exception e) {
            responseResult.setCode("failure");
            responseResult.setSuccess(false);
            responseResult.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return responseResult;
    }

}