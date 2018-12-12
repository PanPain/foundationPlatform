package edu.xd.ridelab.foundationplatform.service.impl;

import edu.xd.ridelab.foundationplatform.mapperInterface.MachineStatusLogMapper;
import edu.xd.ridelab.foundationplatform.model.MachineStatusLogModel;
import edu.xd.ridelab.foundationplatform.mysql.vo.MachineStatusLog;
import edu.xd.ridelab.foundationplatform.service.MachineStatusLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 台恩
 * @date 2018/05/17
 * @since 1.0
 */
@Service
public class MachineStatusLogImpl implements MachineStatusLogService{

	@Autowired
	private MachineStatusLogMapper machineStatusLogMapper;

	@Override
	public MachineStatusLog getMachineStatusLogByOnOffID(long onOffID) {

		return machineStatusLogMapper.selectByPrimaryKey(onOffID);

	}

	@Override
	public List<MachineStatusLogModel> getMachineStatusLogInfo(String macMachineNo,
															   String machineAddress,
															   String policeOfficeName,
															   String minOnlineTime,
															   String maxOnlineTime,
															   int offset,
															   int pageSize){
		return machineStatusLogMapper.selectMachineStatusLogInfo(macMachineNo, machineAddress, policeOfficeName, minOnlineTime, maxOnlineTime,offset,pageSize);
	}
}
