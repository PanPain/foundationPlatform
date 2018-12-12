package edu.xd.ridelab.foundationplatform.record;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 热点Mac记录实体类
 *
 * @author 蔡依婷
 * @date 2018/5/4
 * @since 1.0
 * @see TransferRecord
 */
@Data
public class APMacRecord implements TransferRecord {
    private static final long serialVersionUID = 1L;
    // 记录采集原生属性
    /** 热点设备的MAC地址 */
    private String mac;

    /** 热点设备的SSID名称 */
    private String ssid;

    /** 热点设备的工作信道 */
    private Integer chan;

    /** 加密方式 */
    private String encryption;

    /** 记录开始时间 */
    private LocalDateTime firstTime;

    /** 信号强度指示值 */
    private Integer rssi;

    /** 经度 */
    private Double x;

    /** 纬度 */
    private Double y;

    /** 场所编号 */
    private String placeNo;

    /** 采集设备MAC编号，引用MAC机具表的macMachineNo */
    private String devmac;

    /** 采集设备经度 */
    private Double longitude;

    /** 采集设备纬度 */
    private Double latitude;

    // 记录处理辅助属性
    /** 记录结束时间 */
    private LocalDateTime lastTime;

    /** 采集计数 */
    private Integer count = 1;

    @Override
    public Integer addAndGetCount() {
        return ++this.count;
    }
}

