package edu.xd.ridelab.foundationplatform.controller;

import edu.xd.ridelab.foundationplatform.controller.response.ResponseResult;
import edu.xd.ridelab.foundationplatform.model.PoliceOfficeModel;
import edu.xd.ridelab.foundationplatform.mysql.vo.PoliceOffice;
import edu.xd.ridelab.foundationplatform.service.PoliceOfficeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** 主要描述：主要用于管理单位信息，包括以下功能：
 * 查询单位信息
 * 单位信息明细
 * 增加单位信息
 * 删除单位信息
 * 修改单位信息
 *
 * @author 台恩
 * @date 2018/05/07
 * @since 1.0
 */
@Controller
public class PoliceOfficeController {
    @Autowired
    private PoliceOfficeInfoService policeOfficeInfoService;


    /** 主要描述：根据传入条件参数查询数据库中对应单位记录
     * @param record 单位信息实体类，用于包装前端传入查询条件
     * @return 返回对象包括查询到的实体类，以及查询状态等信息
     * @throws Exception
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "/policeOffice/getPoliceOfficeInfo",method = RequestMethod.POST)
    public ResponseResult getPoliceOfficeInfo(@RequestBody PoliceOfficeModel record, @RequestParam(defaultValue = "1") int curPage, @RequestParam(defaultValue = "10") int pageSize){
        ResponseResult responseResult = new ResponseResult();
        int offset = ( curPage - 1 ) * pageSize;
        System.out.println(record.getPoliceOfficeNo());
        System.out.println(record.getPoliceOfficeName());
        System.out.println(record.getDistrictNo());
        List<PoliceOfficeModel> policeOfficeModelList = null;
        try {
            policeOfficeModelList = policeOfficeInfoService.selectPoliceOfficeInfo(record, offset, pageSize);

            responseResult.setData(policeOfficeModelList);
            responseResult.setSuccess(true);
            responseResult.setCode("xxx");
            responseResult.setMessage("查询单位信息成功");
        }catch (Exception e){
            responseResult.setSuccess(false);
            responseResult.setCode("xxx");
            responseResult.setMessage("查询单位信息失败" + e.getMessage());
        }


        return responseResult;
    }

    /** 主要描述：根据传入的记录主键查询数据库中对应单位明细记录
     * @param  policeOfficeId 记录主键
     * @return 返回对象包括查询到的实体类，以及查询状态等信息
     * @throws Exception
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "/policeOffice/getPoliceOfficeDetailInfo",method = RequestMethod.GET)
    public ResponseResult getPoliceOfficeDetailInfo(@RequestParam Long policeOfficeId){
        ResponseResult responseResult = new ResponseResult();
        try {
            PoliceOfficeModel policeOfficeModel = policeOfficeInfoService.selectPoliceOfficeDetailInfo(policeOfficeId);

            responseResult.setData(policeOfficeModel);
            responseResult.setSuccess(true);
            responseResult.setCode("xxx");
            responseResult.setMessage("查询单位详细信息成功");
        }catch (Exception e){
            responseResult.setSuccess(false);
            responseResult.setCode("xxx");
            responseResult.setMessage("查询单位详细信息失败" + e.getMessage());
        }


        return responseResult;
    }

    /** 主要描述：根据传入条件参数把实体类添加到数据库中
     * @param record 单位信息实体类，用于包装前端传入参数数据
     * @return 返回对象包括插入状态等信息
     * @throws Exception
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "/policeOffice/addPoliceOfficeInfo",method = RequestMethod.POST)
    public ResponseResult addPoliceOfficeInfo(@RequestBody PoliceOfficeModel record){
        ResponseResult responseResult = new ResponseResult();
        try {
            int num = policeOfficeInfoService.addPoliceOfficeInfo(record);

            responseResult.setData(num);
            responseResult.setSuccess(true);
            responseResult.setCode("xxx");
            responseResult.setMessage("添加单位信息成功");
        }catch (Exception e){
            responseResult.setSuccess(false);
            responseResult.setCode("xxx");
            responseResult.setMessage("添加单位信息失败" + e.getMessage());
        }


        return responseResult;
    }

    /** 主要描述：根据传入的记录主键删除数据库中对应记录
     * @param  list 主键列表
     * @return 返回对象删除状态等信息
     * @throws Exception
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "/policeOffice/deletePoliceOfficeInfo",method = RequestMethod.POST)
    public ResponseResult deletePoliceOfficeInfo(@RequestBody List<Long> list){
        ResponseResult responseResult = new ResponseResult();
        for(Long l : list)
            System.out.println(l);
        try {
            int num = policeOfficeInfoService.deletePoliceOfficeInfo(list);

            responseResult.setData(num);
            responseResult.setSuccess(true);
            responseResult.setCode("xxx");
            responseResult.setMessage("删除单位信息成功");
        }catch (Exception e){
            responseResult.setSuccess(false);
            responseResult.setCode("xxx");
            responseResult.setMessage("删除单位信息失败" + e.getMessage());
        }


        return responseResult;
    }

    /** 主要描述：根据传入条件参数把实体类更新到数据库对应记录
     * @param record 单位信息实体类，用于包装前端传入参数数据
     * @return 返回对象包括更新状态等信息
     * @throws Exception
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "/policeOffice/updatePoliceOfficeInfo",method = RequestMethod.POST)
    public ResponseResult updatePoliceOfficeInfo(@RequestBody PoliceOfficeModel record){
        ResponseResult responseResult = new ResponseResult();
        try {
            int num = policeOfficeInfoService.updatePoliceOfficeInfo(record);

            responseResult.setData(num);
            responseResult.setSuccess(true);
            responseResult.setCode("xxx");
            responseResult.setMessage("更新单位信息成功");
        }catch (Exception e){
            responseResult.setSuccess(false);
            responseResult.setCode("xxx");
            responseResult.setMessage("更新单位信息失败" + e.getMessage());
        }
        return responseResult;
    }
}
