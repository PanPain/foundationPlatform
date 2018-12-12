package edu.xd.ridelab.foundationplatform.mapperInterface;



import edu.xd.ridelab.foundationplatform.model.SystemLogInfo;
import edu.xd.ridelab.foundationplatform.mysql.vo.SystemLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface SystemLogMapper {
    int deleteByPrimaryKey(Long logId);

    int insert(SystemLog record);

    int insertSelective(SystemLog record);

    SystemLog selectByPrimaryKey(Long logId);

    int updateByPrimaryKeySelective(SystemLog record);

    int updateByPrimaryKey(SystemLog record);

    List<SystemLogInfo> selectAllLog(@Param("offset")int offset, @Param("pageSize")int pageSize);

    List<SystemLogInfo> selectBySomeConditions(@Param("logType") Integer logType, @Param("module") String module, @Param("userName") String userName, @Param("startTime") Date startTime,
                                               @Param("endTime") Date endTime, @Param("message") String message, @Param("offset") int offset, @Param("pageSize") int pageSize);
}