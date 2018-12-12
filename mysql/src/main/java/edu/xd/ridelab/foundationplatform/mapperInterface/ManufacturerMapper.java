package edu.xd.ridelab.foundationplatform.mapperInterface;


import edu.xd.ridelab.foundationplatform.model.ManufacturerCount;
import edu.xd.ridelab.foundationplatform.model.ManufacturerStatusCount;
import edu.xd.ridelab.foundationplatform.mysql.vo.Manufacturer;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ManufacturerMapper {
    int deleteByPrimaryKey(Long mfId);

    int insert(Manufacturer record);

    int insertSelective(Manufacturer record);

    Manufacturer selectByPrimaryKey(Long mfId);

    int updateByPrimaryKeySelective(Manufacturer record);

    int updateByPrimaryKey(Manufacturer record);

    List<Manufacturer> getAllManu(@Param(value = "offset") int offset,@Param(value = "pageNum")int pageNum);

    List<Manufacturer> selectBySomeConditions(@Param(value = "mfName") String mfName,@Param(value = "mfOrgNo") String mfOrgNo,@Param(value = "mfAddress") String mfAddress,@Param(value = "offset") int offset,@Param(value = "pageNum") int pageNum);

    Long selectMfIdByMfOrgNo(String mfOrgNo);

    List<ManufacturerStatusCount> getManufacturerStatusCountByMfName(@Param(value = "mfName")String mfName, @Param(value = "offset") int offset, @Param(value = "pageNum") int pageNum);

    List<ManufacturerCount> getManufacturerCountByMfName(@Param(value = "mfName")String mfName, @Param(value = "offset") int offset, @Param(value = "pageNum") int pageNum);
}