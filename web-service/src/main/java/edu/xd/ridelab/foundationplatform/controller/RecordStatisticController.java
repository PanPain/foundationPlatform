package edu.xd.ridelab.foundationplatform.controller;

import edu.xd.ridelab.foundationplatform.controller.response.ResponseResult;

import edu.xd.ridelab.foundationplatform.model.MachineCollectCount;
import edu.xd.ridelab.foundationplatform.model.PoliceOfficeStatistics;
import edu.xd.ridelab.foundationplatform.model.TotalCollectCount;
import edu.xd.ridelab.foundationplatform.model.ViCountModel;
import edu.xd.ridelab.foundationplatform.service.RecordStatisticService;
import edu.xd.ridelab.foundationplatform.service.impl.RecordStatisticServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 运维统计
 *@author xuziheng
 *@date 2018/5/23
 *@since 1.0
 */
@Controller
public class RecordStatisticController {

	@Autowired
	RecordStatisticService recordStatisticService;


	/**终端采集、热点采集、虚拟身份采集
	* @author xuziheng
	* @date 2018/5/24
	* @return edu.xd.ridelab.foundationplatform.controller.response.ResponseResult
	* @throws
	* @since 1.0
	*/
	@GetMapping(value = "/recordStatistic/getTotalByConds")
	@ResponseBody
	public ResponseResult getRecordStatistic(@RequestParam String column,@RequestParam Integer compression,@RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd")  Date date){
		ResponseResult responseResult = new ResponseResult();
		try {
			BigDecimal total = recordStatisticService.countColumnByCompressionAndDate(column,compression,date);
			responseResult.setData(total);
			responseResult.setCode("success");
			responseResult.setSuccess(true);
			responseResult.setMessage("查询成功");
		}catch (Exception e){
			responseResult.setCode("failure");
			responseResult.setSuccess(false);
			responseResult.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return responseResult;
	}



	/**根据日期,查询指定日期的终端采集量
	* @author xuziheng
	* @date 2018/5/24
	* @return edu.xd.ridelab.foundationplatform.controller.response.ResponseResult
	* @throws
	* @since 1.0
	*/
	@GetMapping(value = "/recordStatistic/getTotalByDates")
	@ResponseBody
	public ResponseResult getRecordStatistic(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate){
		ResponseResult responseResult = new ResponseResult();
		try {
			List<TotalCollectCount> list = recordStatisticService.getMachineTotalCountByDates(startDate,endDate);
			responseResult.setData(list);
			responseResult.setCode("success");
			responseResult.setSuccess(true);
			responseResult.setMessage("查询成功");
		}catch (Exception e){
			responseResult.setCode("failure");
			responseResult.setSuccess(false);
			responseResult.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return responseResult;
	}

	@GetMapping(value = "/recordStatistic/getLeastCollectMachine")
	@ResponseBody
	/**输出采集量较少的前num位的设备和采集量
	* @author xuziheng
	* @date 2018/5/24
	* @return edu.xd.ridelab.foundationplatform.controller.response.ResponseResult
	* @throws
	* @since 1.0
	*/
	public ResponseResult getRecordStatistic(@RequestParam( defaultValue = "5") Integer num){
		ResponseResult responseResult = new ResponseResult();
		try {
			List<MachineCollectCount> list = recordStatisticService.getLeastCollectMachine(num);
			responseResult.setData(list);
			responseResult.setCode("success");
			responseResult.setSuccess(true);
			responseResult.setMessage("查询成功");
		}catch (Exception e){
			responseResult.setCode("failure");
			responseResult.setSuccess(false);
			responseResult.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return responseResult;
	}


	@GetMapping(value = "/recordStatistic/getMacStatusInfo")
	@ResponseBody
	public ResponseResult getMacStatusInfo(){
		ResponseResult responseResult = new ResponseResult();
		try {
			List<PoliceOfficeStatistics> list = recordStatisticService.countPoliceOfficeStatisticsInfo();
			responseResult.setData(list);
			responseResult.setCode("success");
			responseResult.setSuccess(true);
			responseResult.setMessage("查询成功");
		}catch (Exception e){
			responseResult.setCode("failure");
			responseResult.setSuccess(false);
			responseResult.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return responseResult;
	}

	/**统计虚拟身份：微信、QQ、微博及其他的采集总数
	 * @param
	 * @return edu.xd.ridelab.foundationplatform.model.ViCountModel
	 * @throws
	 * @since 1.0
	 */
	@GetMapping(value = "/recordStatistic/countViModel")
	@ResponseBody
	public ResponseResult countViModel(){
		ResponseResult responseResult = new ResponseResult();
		try {
			ViCountModel viCountModel = recordStatisticService.getViCountModel();
			responseResult.setData(viCountModel);
			responseResult.setCode("success");
			responseResult.setSuccess(true);
			responseResult.setMessage("查询成功");
		}catch (Exception e){
			responseResult.setCode("failure");
			responseResult.setSuccess(false);
			responseResult.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return responseResult;
	}
}
