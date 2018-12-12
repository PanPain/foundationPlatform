package edu.xd.ridelab.foundationplatform.mapperInterface;

import edu.xd.ridelab.foundationplatform.model.MachineCountByDistrict;

import edu.xd.ridelab.foundationplatform.model.MacInfoModel;

import edu.xd.ridelab.foundationplatform.model.MachineCountByPolice;

import edu.xd.ridelab.foundationplatform.mysql.vo.MacMachine;
import org.apache.ibatis.annotations.Param;

import javax.crypto.Mac;
import java.math.BigDecimal;
import java.util.List;

public interface MacMachineMapper {
    int deleteByPrimaryKey(Long macMachineId);

    int insert(MacMachine record);

    int insertSelective(MacMachine record);

    MacMachine selectByPrimaryKey(Long macMachineId);

    int updateByPrimaryKeySelective(MacMachine record);

    int updateByPrimaryKey(MacMachine record);

    Long selectMacIdByMacNo(String macMachineNo);

    List<MacInfoModel> selectBySomeCondition(@Param("machineAddress") String machineAddress,
                                             @Param("macMachineNo") String macMachineNo,
                                             @Param("machineName") String machineName,
                                             @Param("type") String type,
                                             @Param("status") String status,
                                             @Param("placeNo") String placeNo,
                                             @Param("mfName") String mfName,
                                             @Param("policeOfficeName") String policeOfficeName,
                                             @Param("offset") int offset,
                                             @Param("pageNum") int pageNum
                                            );

    List<MacMachine> selectMacInfoByLongitudeAndLatitude(@Param(value="minLongitude") BigDecimal minLongitude,
                                                         @Param(value="maxLongitude") BigDecimal maxLongitude,
                                                         @Param(value="minLatitude") BigDecimal minLatitude,
                                                         @Param(value="maxLatitude") BigDecimal maxLatitude,
                                                         @Param("offset") int offset,
                                                         @Param("pageSize") int pageSize
                                                         );

    List<MacMachine> selectAll(@Param("offset") int offset, @Param("pageNum") int pageNum);

    List<MachineCountByDistrict> getMachineStatusCountByDistrict();

    List<MachineCountByPolice> getMachineStatusCountByPolice(@Param(value="districtId") Long districtId);

    List<MacMachine> getAllMacMachine();
}