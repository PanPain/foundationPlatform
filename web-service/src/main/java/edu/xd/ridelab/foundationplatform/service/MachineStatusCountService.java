package edu.xd.ridelab.foundationplatform.service;

import edu.xd.ridelab.foundationplatform.model.MachineCountByDistrict;

import java.util.List;
import java.util.Map;

/**
 * @author :  zf
 * @date :  2018/5/15
 * @since :  Version 1.0
 */
public interface MachineStatusCountService {
    /** 按区域统计设备状态
     * @return: 返回edu.xd.ridelab.foundationplatform.module.MachineCountByDistrict的集合类
     * @throws:
     * @since: version 1.0
     */
    List<MachineCountByDistrict> getMachineStatusCountByDistrict() throws Exception;

    Map<String, Integer> getMachineStatusCount() throws Exception;
}
