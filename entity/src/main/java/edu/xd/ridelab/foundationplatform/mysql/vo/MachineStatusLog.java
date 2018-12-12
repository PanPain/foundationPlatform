package edu.xd.ridelab.foundationplatform.mysql.vo;

import java.util.Date;

public class MachineStatusLog {
    private Long onoffId;

    private Long fkMacMachineId;

    private Date onlineTime;

    private Date offlineTime;

    public Long getOnoffId() {
        return onoffId;
    }

    public void setOnoffId(Long onoffId) {
        this.onoffId = onoffId;
    }

    public Long getFkMacMachineId() {
        return fkMacMachineId;
    }

    public void setFkMacMachineId(Long fkMacMachineId) {
        this.fkMacMachineId = fkMacMachineId;
    }

    public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Date getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(Date offlineTime) {
        this.offlineTime = offlineTime;
    }
}