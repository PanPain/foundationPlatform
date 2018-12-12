package edu.xd.ridelab.foundationplatform.service.impl;

import edu.xd.ridelab.foundationplatform.mapperInterface.MacMachineMapper;
import edu.xd.ridelab.foundationplatform.model.MacInfoModel;
import edu.xd.ridelab.foundationplatform.mysql.vo.MacMachine;
import edu.xd.ridelab.foundationplatform.service.MacMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 台恩
 * @date 2018/05/17
 * @since 1.0
 */

/**主要描述：负责调用与数据库交互的mapper：
 * 查询设备信息
 * 设备信息明细
 * 增加设备信息
 * 删除设备信息
 * 修改设备信息
 * @author wh
 * @date 2018/05/014
 */
@Service
public class MacMachineImpl implements MacMachineService {

    @Autowired
    private MacMachineMapper macMachineMapper;

    /**
     * @param
     * @MethodName selectMacMachineInfo
     * @Description 根据特定条件查询设备信息
     * @author wh
     * @date 2018/5/18 16:58
     */
    @Override
    public List<MacInfoModel> selectMacMachineInfo(String machineAddress,
                                             String macMachineNo,
                                             String machineName,
                                             String type,
                                             String status,
                                             String placeNo,
                                             String mfName,
                                             String policeOfficeName,
                                             int offset,
                                             int pageNum ) {
        return macMachineMapper.selectBySomeCondition(machineAddress,macMachineNo,machineName,type,status,placeNo,mfName,policeOfficeName,offset,pageNum);
    }

    /**
     * @param macMachineId
     * @MethodName selectMacMachineDetailInfo
     * @Description 根据设备id查询设备详细信息
     * @author wh
     * @date 2018/5/18 16:58
     */
    @Override
    public MacMachine selectMacMachineDetailInfo(Long macMachineId) {
        return macMachineMapper.selectByPrimaryKey(macMachineId);
    }

    /**
     * @param record
     * @MethodName addMacMachineInfo
     * @Description 添加设备
     * @author wh
     * @date 2018/5/18 16:58
     */
    @Override
    public int addMacMachineInfo(MacMachine record) {
        return macMachineMapper.insert(record);
    }

    /**
     * @param macMachineId
     * @MethodName deleteMacMachineInfo
     * @Description 根据设备id删除设备（后面会改为批量删除）
     * @author wh
     * @date 2018/5/18 16:58
     */
    @Override
    public int deleteMacMachineInfo(Long macMachineId) {
        return macMachineMapper.deleteByPrimaryKey(macMachineId);
    }

    /**
     * @param record
     * @MethodName updateMacMachineInfo
     * @Description 更新设备信息
     * @author wh
     * @date 2018/5/18 16:58
     */
    @Override
    public int updateMacMachineInfo(MacMachine record) {
        return macMachineMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * @param
     * @MethodName selectAll
     * @Description 获取数据库中所有设备信息
     * @author wh
     * @date 2018/5/18 16:58
     */
    @Override
    public List<MacMachine> selectAll(int offset, int pageNum){
        return macMachineMapper.selectAll(offset,pageNum);
    }

    @Override
    public List<MacMachine> selectPoliceOfficeInfoByLongitudeAndLatitude(BigDecimal minLongitude, BigDecimal maxLongitude, BigDecimal minLatitude, BigDecimal maxLatitude, int offset, int pageSize){
        return macMachineMapper.selectMacInfoByLongitudeAndLatitude(minLongitude,maxLongitude,minLatitude,maxLatitude,offset,pageSize);
    }
}
