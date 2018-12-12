package edu.xd.ridelab.foundationplatform.model;

/**
 * @author 翟佳豪
 * @date 2018/05/18
 * @since Version 1.0
 */
public class MachineCountByPolice {

    //派出所名称
    private String policeName;

    //在线设备数量
    private Integer onlineCount;

    //离线设备数量
    private Integer offlineCount;

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }

    public Integer getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(Integer onlineCount) {
        this.onlineCount = onlineCount;
    }

    public Integer getOfflineCount() {
        return offlineCount;
    }

    public void setOfflineCount(Integer offlineCount) {
        this.offlineCount = offlineCount;
    }
}