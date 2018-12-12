package edu.xd.ridelab.foundationplatform.controller;

import edu.xd.ridelab.foundationplatform.controller.response.ResponseResult;
import edu.xd.ridelab.foundationplatform.model.ManufacturerCount;
import edu.xd.ridelab.foundationplatform.model.ManufacturerStatusCount;
import edu.xd.ridelab.foundationplatform.service.ManufacturerCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author :  zf
 * @date :  2018/5/10
 * @since :  Version 1.0
 */
@Controller
public class ManufacturerCountController {

    @Autowired
    private ManufacturerCountService manufacturerCountService;

    @RequestMapping(value = "manufacturerCount/getManufacturerStatusCountByMfName", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getManufacturerStatusCountByMfName(@RequestParam(required = false) String mfName, @RequestParam(defaultValue = "1") int curPage, @RequestParam(defaultValue = "1") int pageNum) {
        ResponseResult responseResult = new ResponseResult();
        int offset = (curPage - 1) * pageNum;
        List<ManufacturerStatusCount> list = null;
        try {
            list = manufacturerCountService.getManufacturerStatusCountByMfName(mfName,offset,pageNum);
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

    @RequestMapping(value = "manufacturerCount/getManufacturerCountByMfName", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getManufacturerCountByMfName(@RequestParam(required = false) String mfName, @RequestParam(defaultValue = "1") int curPage, @RequestParam(defaultValue = "10") int pageNum) {
        ResponseResult responseResult = new ResponseResult();
        int offset = (curPage - 1) * pageNum;
        List<ManufacturerCount> list = null;
        try {
            list = manufacturerCountService.getManufacturerCountByMfName(mfName,offset,pageNum);
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

}
