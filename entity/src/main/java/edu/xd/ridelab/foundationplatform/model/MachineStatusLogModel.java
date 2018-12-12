package edu.xd.ridelab.foundationplatform.model;

import edu.xd.ridelab.foundationplatform.mysql.vo.MachineStatusLog;

import java.util.Date;

public class MachineStatusLogModel{
    private Long macMachineId;

    private String macMachineNo;

    private String machineAddress;

    private String policeOfficeName;

    private Long onoffId;

    private Long fkMacMachineId;

    private Date onlineTime;

    public Long getMacMachineId() {
        return macMachineId;
    }

    public void setMacMachineId(Long macMachineId) {
        this.macMachineId = macMachineId;
    }

    public String getMacMachineNo() {
        return macMachineNo;
    }

    public void setMacMachineNo(String macMachineNo) {
        this.macMachineNo = macMachineNo;
    }

    public String getMachineAddress() {
        return machineAddress;
    }

    public void setMachineAddress(String machineAddress) {
        this.machineAddress = machineAddress;
    }

    public String getPoliceOfficeName() {
        return policeOfficeName;
    }

    public void setPoliceOfficeName(String policeOfficeName) {
        this.policeOfficeName = policeOfficeName;
    }

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

    private Date offlineTime;


}
