package edu.xd.ridelab.foundationplatform.service.impl;

import edu.xd.ridelab.foundationplatform.mapperInterface.PlaceInfoMapper;
import edu.xd.ridelab.foundationplatform.model.PlaceInfoDistrictModel;
import edu.xd.ridelab.foundationplatform.mysql.vo.PlaceInfo;
import edu.xd.ridelab.foundationplatform.service.PlaceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @Author：WW
 * @Description
 * @Date: 21:52 2018/5/5
 **/
@Service
public class PlaceInfoImpl implements PlaceInfoService {

    @Autowired
    private PlaceInfoMapper placeInfoMapper;

    public List<PlaceInfoDistrictModel> getPlaceInfoList(String placeName, String placeNo, String address, String type, String property, String legalPerson, Integer orgCode , int offset, int pageNum) throws Exception{
        //List<PlaceInfo> placeInfoList=
        System.out.println(placeName+placeNo+address+type+property+legalPerson+orgCode);
//        PlaceInfo placeInfo=new PlaceInfo();
//        placeInfo.setPlaceName(placeName);
//        placeInfo.setPlaceNo(placeNo);
//        placeInfo.setAddress(address);
//        placeInfo.setType(type);
//        placeInfo.setProperty(property);
//        placeInfo.setLegalPerson(legalPerson);
//        placeInfo.setOrgCode(orgCode);
        return placeInfoMapper.selectBySomeCondition(placeName, placeNo,address,type,property,legalPerson,orgCode, offset, pageNum);
//        return placeInfoMapper.selectBySomeCondition();
    }

    public PlaceInfo getPlaceInfo(Long placeId) throws Exception{
        return placeInfoMapper.selectByPrimaryKey(placeId);
    }

    public int insertPlaceInfo(PlaceInfo placeInfo) throws Exception{
        return placeInfoMapper.insert(placeInfo);
    }
    public int deletePlaceInfo(Long placeId) throws Exception{
        return placeInfoMapper.deleteByPrimaryKey(placeId);
    }
    public int modifyPlaceInfoByPlaceId(PlaceInfo placeInfo) throws Exception{
        return placeInfoMapper.updateByPrimaryKeySelective(placeInfo);
    }

    public List<PlaceInfoDistrictModel> getAllPlaceInfo(int offset, int pageNum) throws Exception {
        return placeInfoMapper.selectAll(offset,pageNum);
    }

    @Override
    public List<PlaceInfoDistrictModel> getPlaceInfoById(Long placeId) {
        return placeInfoMapper.getPlaceInfoById(placeId);
    }
}
