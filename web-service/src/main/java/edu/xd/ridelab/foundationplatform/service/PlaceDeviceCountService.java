package edu.xd.ridelab.foundationplatform.service;

import edu.xd.ridelab.foundationplatform.model.MachineCountByPolice;

import java.util.List;

/**
 * @author 翟佳豪
 * @date 2018/05/18
 * @since Version 1.0
 */
public interface PlaceDeviceCountService {

    List<MachineCountByPolice> getMachineCountByPolice(Long districtId) throws Exception;
}
