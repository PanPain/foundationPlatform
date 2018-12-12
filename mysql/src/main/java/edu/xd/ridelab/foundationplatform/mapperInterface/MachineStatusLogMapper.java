package edu.xd.ridelab.foundationplatform.mapperInterface;

import edu.xd.ridelab.foundationplatform.mysql.vo.MachineStatusLog;
import edu.xd.ridelab.foundationplatform.model.MachineStatusLogModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MachineStatusLogMapper {
    int deleteByPrimaryKey(Long onoffId);

    int insert(MachineStatusLog record);

    int insertSelective(MachineStatusLog record);

    MachineStatusLog selectByPrimaryKey(Long onoffId);

    int updateByPrimaryKeySelective(MachineStatusLog record);

    int updateByPrimaryKey(MachineStatusLog record);

    List<MachineStatusLogModel> selectMachineStatusLogInfo(@Param(value = "macMachineNo") String macMachineNo,
                                                           @Param(value = "machineAddress") String machineAddress,
                                                           @Param(value = "policeOfficeName") String policeOfficeName,
                                                           @Param(value = "minOnlineTime") String minOnlineTime,
                                                           @Param(value = "maxOnlineTime") String maxOnlineTime,
                                                           @Param(value = "offset") int offset,
                                                           @Param(value = "pageSize") int pageSize
                                                           );
}