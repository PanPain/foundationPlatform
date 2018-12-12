package edu.xd.ridelab.foundationplatform.record;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 记录传输接口
 *
 * @author 蔡依婷
 * @date 2018/5/4
 * @since 1.0
 */
public interface TransferRecord extends Serializable{

    /** 获取MAC地址
     * @return MAC地址
     */
    String getMac();

    /** 获取采集设备MAC编号
     * @return 采集设备MAC编号
     */
    String getDevmac();

    /** 获取记录开始时间
     * @return 记录开始时间
     */
    LocalDateTime getFirstTime();


    /** 获取记录结束时间
     * @return 记录结束时间
     */
    LocalDateTime getLastTime();

    /**
     * 增加并返回计数，用于统计压缩合并的采集记录数量
     * @return 采集计数
     */
    Integer addAndGetCount();

    /**
     * 修改记录开始时间，用于压缩处理
     */
    void setFirstTime(LocalDateTime firstTime);

    /**
     * 修改记录结束时间，用于压缩处理
     */
    void setLastTime(LocalDateTime lastTime);

}

