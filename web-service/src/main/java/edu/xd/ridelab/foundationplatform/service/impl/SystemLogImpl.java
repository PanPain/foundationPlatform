package edu.xd.ridelab.foundationplatform.service.impl;

import edu.xd.ridelab.foundationplatform.mapperInterface.SystemLogMapper;
import edu.xd.ridelab.foundationplatform.model.SystemLogInfo;
import edu.xd.ridelab.foundationplatform.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 翟佳豪
 * @date 2018/05/22
 * @since Version 1.0
 */
@Service
public class SystemLogImpl implements SystemLogService {

    @Autowired
    private SystemLogMapper systemLogMapper;

    @Override
    public List<SystemLogInfo> getAllLogInfo(int offset, int pageSize) throws Exception {
        return systemLogMapper.selectAllLog(offset, pageSize);
    }

    @Override
    public List<SystemLogInfo> getLogByConditions(Integer logType, String module, String userName, Date startTime, Date endTime, String message, int offset, int pageSize) throws Exception {
        return systemLogMapper.selectBySomeConditions(logType, module, userName, startTime, endTime, message, offset, pageSize);
    }
}
