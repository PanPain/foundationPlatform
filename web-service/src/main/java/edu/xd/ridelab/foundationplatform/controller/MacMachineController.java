package edu.xd.ridelab.foundationplatform.controller;

import edu.xd.ridelab.foundationplatform.controller.response.ResponseResult;
import edu.xd.ridelab.foundationplatform.model.MacInfoModel;
import edu.xd.ridelab.foundationplatform.mysql.vo.MacMachine;
import edu.xd.ridelab.foundationplatform.service.MacMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 台恩
 * @date 2018/05/17
 * @since 1.0
 */
@Controller
public class MacMachineController {
    @Autowired
    private MacMachineService macMachineService;

    @ResponseBody
    @RequestMapping(value = "/macMachine/getMacMachineInfoByLongitudeAndLatitude",method = RequestMethod.GET)
    public ResponseResult getMacMachineInfoByLongitudeAndLatitude(@RequestParam(required=false) BigDecimal minLongitude,
                                                                  @RequestParam(required=false) BigDecimal maxLongitude,
                                                                  @RequestParam(required=false) BigDecimal minLatitude,
                                                                  @RequestParam(required=false) BigDecimal maxLatitude,
                                                                  @RequestParam(defaultValue = "1") int curPage,
                                                                  @RequestParam(defaultValue = "10") int pageSize) {
        ResponseResult responseResult = new ResponseResult();
        int offset = ( curPage - 1 ) * pageSize;
        List<MacMachine> macMachineList = null;
        try {
            macMachineList = macMachineService.selectPoliceOfficeInfoByLongitudeAndLatitude(minLongitude,maxLongitude,minLatitude,maxLatitude,offset,pageSize);
            responseResult.setData(macMachineList);
            responseResult.setSuccess(true);
            responseResult.setCode("xxx");
            responseResult.setMessage("查询设备分布信息成功");
        }catch (Exception e){
            responseResult.setSuccess(false);
            responseResult.setCode("xxx");
            responseResult.setMessage("查询设备分布信息失败" + e.getMessage());
        }


        return responseResult;
    }

    /** 主要描述：根据传入条件参数查询数据库中对应单位记录
     * @param   machineAddress 等,设备信息条件，用于包装前端传入查询条件
     * @return 返回对象包括查询到的实体类，以及查询状态等信息
     * @throws Exception
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "/macMachine/getMacMachineInfo",method = RequestMethod.POST)
    public ResponseResult getMacMachineInfo(@RequestParam(required = false) String machineAddress,
                                            @RequestParam(required = false) String macMachineNo,
                                            @RequestParam(required = false) String machineName,
                                            @RequestParam(required = false) String type,
                                            @RequestParam(required = false) String status,
                                            @RequestParam(required = false) String placeNo,
                                            @RequestParam(required = false) String mfName,
                                            @RequestParam(required = false) String policeOfficeName,
                                            @RequestParam(defaultValue = "1") int curPage,
                                            @RequestParam(defaultValue = "10") int tagNum ){
        int offset = (curPage-1)*tagNum;
        ResponseResult responseResult = new ResponseResult();
        List<MacInfoModel> macInfoModels = null;
        try {
            macInfoModels = macMachineService.selectMacMachineInfo(machineAddress, macMachineNo,machineName,type,status,placeNo,mfName,policeOfficeName,offset,tagNum);
            responseResult.setData(macInfoModels);
            responseResult.setSuccess(true);
            responseResult.setCode("xxx");
            responseResult.setMessage("查询设备信息成功");
        }catch (Exception e){
            responseResult.setSuccess(false);
            responseResult.setCode("xxx");
            responseResult.setMessage("查询设备信息失败"+e.getMessage());
            }
        return responseResult;
    }

    /** 主要描述：根据传入条件参数查询数据库中对应设备详细记录
     * @param macMachineId 设备主键，用于包装前端传入查询条件
     * @return 返回对象包括查询到的实体类，以及查询状态等信息
     * @throws Exception
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "/macMachine/getMacMachineDetailInfo", method = RequestMethod.GET)
    public ResponseResult getMacMachineDetailInfo(@RequestParam Long macMachineId){
        ResponseResult responseResult = new ResponseResult();

        try{
            MacMachine macMachine = macMachineService.selectMacMachineDetailInfo(macMachineId);
            responseResult.setData(macMachine);
            responseResult.setSuccess(true);
            responseResult.setCode("xxx");
            responseResult.setMessage("查询设备详细信息成功");
        } catch (Exception e){
            responseResult.setSuccess(false);
            responseResult.setCode("xxx");
            responseResult.setMessage("查询设备详细信息失败"+e.getMessage());
        }
        return responseResult;
    }


    /** 主要描述：根据传入条件参数在数据库中添加响应的设备信息
     * @param macMachine 设备信息实体类，用于包装前端传入查询条件
     * @return 返回对象为添加的状态等信息
     * @throws Exception
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "/macMachine/addMacMachineInfo",method = RequestMethod.POST)
    public ResponseResult addMacMachineInfo(@RequestBody MacMachine macMachine){
        ResponseResult responseResult = new ResponseResult();
        try{
            int i = macMachineService.addMacMachineInfo(macMachine);
            responseResult.setData(i);
            responseResult.setSuccess(true);
            responseResult.setCode("xxx");
            responseResult.setMessage("添加设备信息成功");
        }catch (Exception e){
            responseResult.setSuccess(false);
            responseResult.setCode("xxx");
            responseResult.setMessage("添加设备信息失败"+e.getMessage());
        }
        return responseResult;
    }

    /** 主要描述：根据传入条件参数在数据库中添加相应的设备信息
     * @param macMachineId 设备主键，用于包装前端传入查询条件
     * @return 返回对象为添加的状态等信息
     * @throws Exception
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "/macMachine/deleteMacMachineInfo",method = RequestMethod.GET)
    public ResponseResult deleteMacMachineInfo(@RequestParam Long macMachineId){
        ResponseResult responseResult = new ResponseResult();
        try{
            int i = macMachineService.deleteMacMachineInfo(macMachineId);
            responseResult.setData(i);
            responseResult.setSuccess(true);
            responseResult.setCode("xxx");
            responseResult.setMessage("删除设备信息成功");
        }catch (Exception e){
            responseResult.setSuccess(false);
            responseResult.setCode("xxx");
            responseResult.setMessage("删除设备信息失败"+e.getMessage());
        }
        return responseResult;
    }

    /** 主要描述：根据传入条件参数在数据库中修改相应的设备信息
     * @param macMachine 设备信息实体类，用于包装前端传入查询条件
     * @return 返回对象为更新的状态等信息
     * @throws Exception
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "/macMachine/updateMacMachineInfo",method = RequestMethod.POST)
    public ResponseResult updateMacMachineInfo(@RequestBody MacMachine macMachine){
        ResponseResult responseResult = new ResponseResult();
        try{
            int i = macMachineService.updateMacMachineInfo(macMachine);
            responseResult.setData(i);
            responseResult.setSuccess(true);
            responseResult.setCode("xxx");
            responseResult.setMessage("更新设备信息成功");
        }catch (Exception e){
            responseResult.setSuccess(false);
            responseResult.setCode("xxx");
            responseResult.setMessage("更新设备信息失败"+e.getMessage());
        }
        return responseResult;
    }

    /** 主要描述：根据传入条件参数在数据库中修改相应的设备信息
     * @param  curPage，tagNum
     * @return 返回对象为查询的状态等信息
     * @throws Exception
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "/macMachine/getAllMachineInfo",method = RequestMethod.GET)
    public ResponseResult getAllMachineInfo(@RequestParam(defaultValue = "1") int curPage, @RequestParam(defaultValue = "10") int tagNum){
        ResponseResult responseResult = new ResponseResult();
        int offset = ( curPage - 1 ) * tagNum;
        List<MacMachine> lists = null;
        try{
            lists = macMachineService.selectAll(offset,tagNum);
            responseResult.setData(lists);
            responseResult.setSuccess(true);
            responseResult.setCode("xxx");
            responseResult.setMessage("查询所有设备信息成功");
        }catch (Exception e){
            responseResult.setSuccess(false);
            responseResult.setCode("xxx");
            responseResult.setMessage("查询所有设备信息失败"+e.getMessage());
        }
        return responseResult;
    }

}
