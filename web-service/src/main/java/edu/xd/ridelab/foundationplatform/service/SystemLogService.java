package edu.xd.ridelab.foundationplatform.service;

import edu.xd.ridelab.foundationplatform.model.SystemLogInfo;

import java.util.Date;
import java.util.List;

/**
 * @author 翟佳豪
 * @date 2018/05/22
 * @since Version 1.0
 */
public interface SystemLogService {

    List<SystemLogInfo> getAllLogInfo(int offset, int pageSize) throws Exception;

    List<SystemLogInfo> getLogByConditions(Integer logType, String module, String userName, Date startTime,
                                           Date endTime, String message, int offset, int pageSize) throws Exception;
}
