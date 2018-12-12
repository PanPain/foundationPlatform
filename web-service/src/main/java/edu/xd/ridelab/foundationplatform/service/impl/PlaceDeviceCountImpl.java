package edu.xd.ridelab.foundationplatform.service.impl;

import edu.xd.ridelab.foundationplatform.mapperInterface.MacMachineMapper;
import edu.xd.ridelab.foundationplatform.model.MachineCountByPolice;
import edu.xd.ridelab.foundationplatform.service.PlaceDeviceCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 翟佳豪
 * @date 2018/05/18
 * @since Version 1.0
 */
@Service
public class PlaceDeviceCountImpl implements PlaceDeviceCountService {

    @Autowired
    private MacMachineMapper macMachineMapper;

    @Override
    public List<MachineCountByPolice> getMachineCountByPolice(Long districtId) throws Exception {
        return macMachineMapper.getMachineStatusCountByPolice(districtId);
    }
}
