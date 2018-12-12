
package edu.xd.ridelab.foundationplatform.record;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 布控报警记录实体类
 *
 * @author 蔡依婷
 * @date 2018/5/7
 * @since 1.0
 */
@Data
public class MonitorAlarmRecord implements Serializable{
    private static final long serialVersionUID = 1L;
    /** 所属布控规则Id */
    private Long ruleId;

    /** 目标MAC */
    private String mac;

    /** 记录时间 */
    private LocalDateTime time;

    /** 采集设备MAC编号，引用MAC机具表的macMachineNo */
    private String devmac;

    /** 视频文件路径 */
    private String audioPath;
}
