
package edu.xd.ridelab.foundationplatform.record;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 虚拟身份记录实体类
 *
 * @author 蔡依婷
 * @date 2018/5/4
 * @since 1.0
 * @see TransferRecord
 */
@Data
public class VIRecord implements TransferRecord {
    private static final long serialVersionUID = 1L;
    // 记录采集原生属性
    /** 所属移动设备的MAC地址 */
    private String mac;

    /** 采集时间 */
    private LocalDateTime firstTime;

    /** 移动设备国际身份码 */
    private String imei;

    /** 国际移动用户识别码 */
    private String imsi;

    /** 手机号 */
    private String phonenum;

    /** 位置名称 */
    private String location;

    /** 品牌 */
    private String brand;

    /** 操作系统 */
    private String OS;

    /** 协议名称 */
    private String protocol;

    /** 账户 */
    private String account;

    /** URL */
    private String url;

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
