package edu.xd.ridelab.foundationplatform.model;

/**
 * @author :  zf
 * @date :  2018/5/15
 * @since :  Version 1.0
 */
public class MachineCountByDistrict {

    //区县名称
    private String districtName;

    //在线设备数量
    private Integer onlineCount;

    //离线设备数量
    private Integer offlineCount;

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
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
