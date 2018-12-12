package edu.xd.ridelab.foundationplatform.mapperInterface;



import edu.xd.ridelab.foundationplatform.model.PlacePropCount;
import edu.xd.ridelab.foundationplatform.model.PlaceTypeCount;
import edu.xd.ridelab.foundationplatform.model.PlaceInfoDistrictModel;
import edu.xd.ridelab.foundationplatform.mysql.vo.PlaceInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface PlaceInfoMapper {
    int deleteByPrimaryKey(Long placeId);

    int insert(PlaceInfo record);

    int insertSelective(PlaceInfo record);

    PlaceInfo selectByPrimaryKey(Long placeId);

    int updateByPrimaryKeySelective(PlaceInfo record);

    int updateByPrimaryKey(PlaceInfo record);

    List<PlaceInfoDistrictModel> selectBySomeCondition(@Param("placeName") String placeName, @Param("placeNo") String placeNo, @Param("address") String address, @Param("type") String type, @Param("property") String property, @Param("legalPerson") String legalPerson, @Param("orgCode") Integer orgCode , @Param("offset") int offset, @Param("pageNum") int pageNum);

    List<PlacePropCount> countPlaceByProp(@Param("districtId") Long districtId);

    List<PlaceTypeCount> countPlaceByType(@Param("districtId") Long districtId);

    Long selectPlaceIdByPlaceName(@Param("placeNo")String placeNo);

    List<PlaceInfoDistrictModel> selectAll(@Param("offset") int offset, @Param("pageNum") int pageNum);

    List<PlaceInfoDistrictModel> getPlaceInfoById(@Param("placeId")Long placeId);
//    List<PlaceInfo> selectBySomeCondition();
    List<PlaceInfo> selectAllPlaceInfo();
}