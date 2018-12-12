package edu.xd.ridelab.foundationplatform.mapperInterface;

import edu.xd.ridelab.foundationplatform.model.PoliceOfficeModel;
import edu.xd.ridelab.foundationplatform.mysql.vo.PoliceOffice;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface PoliceOfficeMapper {
    int deleteByPrimaryKey(List<Long> list);

    int insert(PoliceOffice record);

    int insertSelective(PoliceOfficeModel record);

    Long selectFkDistrictIdByNoAndName(@Param("districtNo") String districtNo,@Param("districtName") String districtName);

    PoliceOfficeModel selectByPrimaryKey(Long policeOfficeId);

    int updateByPrimaryKeySelective(PoliceOfficeModel record);

    int updateByPrimaryKey(PoliceOffice record);

    List<PoliceOfficeModel> selectByPrimaryKeySelective(@Param("record") PoliceOfficeModel record, @Param("offset") int offset, @Param("pageSize") int pageSize);

    List<PoliceOffice> selectByDistrictId(@Param("fkDistrictId") Long districtId);

    long selectPoliceOfficeIdByName(@Param("policeOfficeName") String policeOfficeName);
}