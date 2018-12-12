package edu.xd.ridelab.foundationplatform.service.impl;


import edu.xd.ridelab.foundationplatform.mapperInterface.MacMachineMapper;
import edu.xd.ridelab.foundationplatform.model.MachineCountByDistrict;
import edu.xd.ridelab.foundationplatform.service.MachineStatusCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author :  zf
 * @date :  2018/5/15
 * @since :  Version 1.0
 */
@Service
public class MachineStatusCountImpl implements MachineStatusCountService{

    @Autowired
    private MacMachineMapper macMachineMapper;

    @Override
    public List<MachineCountByDistrict> getMachineStatusCountByDistrict() throws Exception {
        return macMachineMapper.getMachineStatusCountByDistrict();
    }

    @Override
    public Map<String, Integer> getMachineStatusCount() throws Exception {

        List<MachineCountByDistrict> list = macMachineMapper.getMachineStatusCountByDistrict();

        int onlineCount = 0, offlineCount = 0;
        Map<String, Integer> statusAndCount = new HashMap<>();

        for(MachineCountByDistrict vo : list){
            onlineCount += vo.getOnlineCount();
            offlineCount += vo.getOfflineCount();
        }

        statusAndCount.put("online", onlineCount);
        statusAndCount.put("offline", offlineCount);

        return statusAndCount;
    }
}
