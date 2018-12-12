package edu.xd.ridelab.foundationplatform.mapperInterface;

import edu.xd.ridelab.foundationplatform.mysql.vo.District;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface DistrictMapper {
    int deleteByPrimaryKey(Long districtId);

    int insert(District record);

    int insertSelective(District record);

    District selectByPrimaryKey(Long districtId);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);

    List<District> selectBySomeCondition(@Param("districtNo") String districtNo, @Param("districtName") String districtName, @Param("districtLevel") String districtLevel, @Param("offset") int offset, @Param("pageNum") int pageNum);

    List<District> selectAll(@Param("offset") int offset, @Param("pageNum") int pageNum);

    int deleteBatchByPrimaryKey(List<Long> list);

    List<District> selectAllDistrict();
}