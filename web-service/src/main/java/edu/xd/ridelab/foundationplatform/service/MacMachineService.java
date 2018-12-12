package edu.xd.ridelab.foundationplatform.service;

import edu.xd.ridelab.foundationplatform.model.MacInfoModel;
import edu.xd.ridelab.foundationplatform.mysql.vo.MacMachine;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 台恩
 * @date 2018/05/17
 * @since 1.0
 */


public interface MacMachineService {

    /**
     * @MethodName selectMacMachineInfo
     * @Description  根据特定条件查询设备信息
     * @author wh
     * @date 2018/5/18 16:58
     */
    public List<MacInfoModel> selectMacMachineInfo(String machineAddress,
                                             String macMachineNo,
                                             String machineName,
                                             String type,
                                             String status,
                                             String placeNo,
                                             String mfName,
                                             String policeOfficeName,
                                             int offset,
                                             int pageNum );

    /**
     * @MethodName selectMacMachineDetailInfo
     * @Description  根据设备id查询设备详细信息
     * @author wh
     * @date 2018/5/18 16:58
     */
    public MacMachine selectMacMachineDetailInfo(Long macMachineId);

    /**
     * @MethodName addMacMachineInfo
     * @Description 添加设备
     * @author wh
     * @date 2018/5/18 16:58
     */
    public int addMacMachineInfo(MacMachine record);

    /**
     * @MethodName deleteMacMachineInfo
     * @Description 根据设备id删除设备（后面会改为批量删除）
     * @author wh
     * @date 2018/5/18 16:58
     */
    public int deleteMacMachineInfo(Long macMachineId);

    /**
     * @MethodName updateMacMachineInfo
     * @Description 更新设备信息
     * @author wh
     * @date 2018/5/18 16:58
     */
    public int updateMacMachineInfo(MacMachine record);

    /**
     * @MethodName selectAll
     * @Description 获取数据库中所有设备信息
     * @author wh
     * @date 2018/5/18 16:58
     */
    public List<MacMachine> selectAll(int offset,int pageNum);

    public List<MacMachine> selectPoliceOfficeInfoByLongitudeAndLatitude(BigDecimal minLongitude, BigDecimal maxLongitude, BigDecimal minLatitude, BigDecimal maxLatitude, int offset, int pageSize);

}
