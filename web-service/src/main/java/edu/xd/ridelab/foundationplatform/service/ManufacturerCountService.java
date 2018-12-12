package edu.xd.ridelab.foundationplatform.service;

import edu.xd.ridelab.foundationplatform.model.ManufacturerCount;
import edu.xd.ridelab.foundationplatform.model.ManufacturerStatusCount;

import java.util.List;

/**
 * @author :  zf
 * @date :  2018/5/10
 * @since :  Version 1.0
 */
public interface ManufacturerCountService {

    /** 通过输入的厂商名模糊匹配对应厂商状态统计信息
     * @param: mfName
     * @param: offset
     * @param: pageNum
     * @return: edu.xd.ridelab.foundationplatform.module.ManufacturerStatusCount集合
     * @throws:
     * @since: version 1.0
     */
    List<ManufacturerStatusCount> getManufacturerStatusCountByMfName(String mfName, int offset, int pageNum);
    /** 通过输入的厂商名模糊匹配对应厂商设备统计信息
     * @param: mfName
     * @param: offset
     * @param: pageNum
     * @return: edu.xd.ridelab.foundationplatform.module.ManufacturerStatusCount集合
     * @throws:
     * @since: version 1.0
     */
    List<ManufacturerCount> getManufacturerCountByMfName(String mfName, int offset, int pageNum);


}
