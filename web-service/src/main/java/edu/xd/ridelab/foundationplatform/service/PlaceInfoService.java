package edu.xd.ridelab.foundationplatform.service;

import edu.xd.ridelab.foundationplatform.model.PlaceInfoDistrictModel;
import edu.xd.ridelab.foundationplatform.mysql.vo.PlaceInfo;

import java.util.List;

/**
 * @Author：WW
 * @Description
 * @Date: 16:27 2018/5/5
 **/

public interface PlaceInfoService {
    /**
     * @author VV
     * @Description
     * @Date 11:58 2018/5/6
     * @Param [placeName, placeNo, address, type, property, legalPerson, orgcode, offset, pageNum]
     * @return java.util.List<edu.xd.ridelab.foundationplatform.mysql.vo.PlaceInfo>
     */
    List<PlaceInfoDistrictModel> getPlaceInfoList (String placeName, String placeNo, String address, String type, String property, String legalPerson, Integer orgCode, int offset, int pageNum) throws Exception;
    /**
     * @author VV
     * @Description
     * @Date 11:58 2018/5/6
     * @Param [placeId]
     * @return edu.xd.ridelab.foundationplatform.mysql.vo.PlaceInfo
     */
    PlaceInfo getPlaceInfo(Long placeId) throws Exception;
    /**
     * @author VV
     * @Description
     * @Date 11:58 2018/5/6
     * @Param [placeInfo]
     * @return int
     */
    int insertPlaceInfo(PlaceInfo placeInfo) throws Exception;
    /**
     * @author VV
     * @Description
     * @Date 11:58 2018/5/6
     * @Param [placeId]
     * @return int
     */

    int deletePlaceInfo(Long placeId) throws Exception;
    /**
     * @author VV
     * @Description
     * @Date 11:58 2018/5/6
     * @Param [placeInfo]
     * @return int
     */
    int modifyPlaceInfoByPlaceId(PlaceInfo placeInfo) throws Exception;

    List<PlaceInfoDistrictModel> getAllPlaceInfo(int offset, int pageNum) throws Exception;

    List<PlaceInfoDistrictModel> getPlaceInfoById(Long placeId);
}
