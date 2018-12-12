package edu.xd.ridelab.foundationplatform.controller;/**
 * Created by Administrator on 2018/5/3.
 *
 * @
 */

import edu.xd.ridelab.foundationplatform.controller.response.ResponseResult;
import edu.xd.ridelab.foundationplatform.model.MachineStatusLogModel;
import edu.xd.ridelab.foundationplatform.service.MachineStatusLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 台恩
 * @date 2018/05/17
 * @since 1.0
 */
@Controller
public class MachineStatusLogController {

	@Autowired
	private MachineStatusLogService machineStatusLogService;

	@ResponseBody
	@RequestMapping(value = "/machineStatusLog/getMachineStatusLogInfo",method = RequestMethod.GET)
	public ResponseResult getMacMachineOnlineInfo(@RequestParam(required = false) String macMachineNo,
												  @RequestParam(required = false) String machineAddress,
												  @RequestParam(required = false) String policeOfficeName,
												  @RequestParam(required = false) String minOnlineTime,
												  @RequestParam(required = false) String maxOnlineTime,
												  @RequestParam(defaultValue = "1") int curPage,
												  @RequestParam(defaultValue = "10") int pageSize
												  ){
		ResponseResult responseResult = new ResponseResult();
		int offset = ( curPage - 1 ) * pageSize;
		List<MachineStatusLogModel> machineStatusLogModelList = null;
		try {

			machineStatusLogModelList = machineStatusLogService.getMachineStatusLogInfo(macMachineNo, machineAddress, policeOfficeName, minOnlineTime, maxOnlineTime,offset,pageSize);

			responseResult.setData(machineStatusLogModelList);
			responseResult.setSuccess(true);
			responseResult.setCode("xxx");
			responseResult.setMessage("查询设备上下线日志信息成功");
		}catch (Exception e){
			responseResult.setSuccess(false);
			responseResult.setCode("xxx");
			responseResult.setMessage("查询设备上下线日志信息失败" + e.getMessage());
		}


		return responseResult;
	}
}
