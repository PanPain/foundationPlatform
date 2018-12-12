package edu.xd.ridelab.foundationplatform.service;

import edu.xd.ridelab.foundationplatform.model.PlacePropCountByDistrict;
import edu.xd.ridelab.foundationplatform.model.PlaceTypeCountByDistrict;
import edu.xd.ridelab.foundationplatform.mysql.vo.District;

import java.util.List;

/**
 * @author 翟佳豪
 * @date 2018/05/10
 * @since Version 1.0
 */
public interface PlaceCountService {
    List<PlacePropCountByDistrict> countPlaceByProp()throws Exception;

    List<PlaceTypeCountByDistrict> countPlaceByType()throws Exception;

    List<District> getAllDistrict() throws Exception;
}
