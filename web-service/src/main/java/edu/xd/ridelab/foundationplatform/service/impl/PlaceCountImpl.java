package edu.xd.ridelab.foundationplatform.service.impl;

import edu.xd.ridelab.foundationplatform.mapperInterface.DistrictMapper;
import edu.xd.ridelab.foundationplatform.mapperInterface.PlaceInfoMapper;
import edu.xd.ridelab.foundationplatform.model.PlacePropCountByDistrict;
import edu.xd.ridelab.foundationplatform.model.PlacePropCount;
import edu.xd.ridelab.foundationplatform.model.PlaceTypeCount;
import edu.xd.ridelab.foundationplatform.model.PlaceTypeCountByDistrict;
import edu.xd.ridelab.foundationplatform.mysql.vo.District;
import edu.xd.ridelab.foundationplatform.service.PlaceCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 翟佳豪
 * @date 2018/05/10
 * @since Version 1.0
 */
@Service
public class PlaceCountImpl implements PlaceCountService{

    @Autowired
    private DistrictMapper districtMapper;

    @Autowired
    private PlaceInfoMapper placeInfoMapper;

    @Override
    public List<PlacePropCountByDistrict> countPlaceByProp() throws Exception {

        List<PlacePropCountByDistrict> resultCount = new ArrayList<>();

        List<District> districtList = districtMapper.selectAllDistrict();
        for(District vo : districtList){
            PlacePropCountByDistrict placePropCountByDistrict = new PlacePropCountByDistrict();
            placePropCountByDistrict.setDistrictName(vo.getDistrictName());

            List<PlacePropCount> propList = placeInfoMapper.countPlaceByProp(vo.getDistrictId());

            placePropCountByDistrict.setCountByProp(propList);

            resultCount.add(placePropCountByDistrict);
        }
        return resultCount;
    }

    @Override
    public List<PlaceTypeCountByDistrict> countPlaceByType() throws Exception {
        List<PlaceTypeCountByDistrict> resultCount = new ArrayList<>();

        List<District> districtList = districtMapper.selectAllDistrict();
        for(District vo : districtList){
            PlaceTypeCountByDistrict placeTypeCountByDistrict = new PlaceTypeCountByDistrict();
            placeTypeCountByDistrict.setDistrictName(vo.getDistrictName());

            List<PlaceTypeCount> typeList = placeInfoMapper.countPlaceByType(vo.getDistrictId());
            placeTypeCountByDistrict.setCountByType(typeList);

            resultCount.add(placeTypeCountByDistrict);
        }

        return resultCount;
    }

    @Override
    public List<District> getAllDistrict() throws Exception {
        return districtMapper.selectAllDistrict();
    }
}
