package edu.xd.ridelab.foundationplatform.service;/**
 * Created by Administrator on 2018/5/4.
 *
 * @
 */

import edu.xd.ridelab.foundationplatform.model.MachineStatusLogModel;
import edu.xd.ridelab.foundationplatform.mysql.vo.MachineStatusLog;

import java.util.List;

/**
 * @author 台恩
 * @date 2018/05/17
 * @since 1.0
 */

public interface MachineStatusLogService {

	MachineStatusLog getMachineStatusLogByOnOffID(long onOffID);

	List<MachineStatusLogModel> getMachineStatusLogInfo(String macMachineNo, String machineAddress, String policeOfficeName, String minOnlineTime, String maxOnlineTime, int offset, int pageSize);
}
