package edu.xd.ridelab.foundationplatform.mysql.vo;

import java.io.Serializable;
import java.time.Instant;

/**
 * @author PanTeng
 * @Description
 * @file MacMachineHeartbeat.java
 * @date 2018/5/19
 * @attention Copyright (C),2004-2018,RIDELab, SEI, XiDian University
 */
public class MacMachineHeartbeat implements Serializable {
    private static final long serialVersionUID = 1L;

    //场所代码
    String placeNo;
    //设备代码
    String macMachineNo;
    //状态代码   01 - 在线 ，99 - 其他
    String status;
    //心跳时间
    Instant heartbeatTime;

    public String getPlaceNo() {
        return placeNo;
    }

    public void setPlaceNo(String placeNo) {
        this.placeNo = placeNo;
    }

    public String getMacMachineNo() {
        return macMachineNo;
    }

    public void setMacMachineNo(String macMachineNo) {
        this.macMachineNo = macMachineNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getHeartbeatTime() {
        return heartbeatTime;
    }

    public void setHeartbeatTime(Instant heartbeatTime) {
        this.heartbeatTime = heartbeatTime;
    }
}
