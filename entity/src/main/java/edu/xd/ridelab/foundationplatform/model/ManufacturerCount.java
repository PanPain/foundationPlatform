package edu.xd.ridelab.foundationplatform.model;

/** 主要描述：安全厂商设备统计(厂商名称,厂商组织机构代码,设备总数,在线设备数量,离线设备数量)
 * @author :  zf
 * @date :  2018/5/14
 * @since :  Version 1.0
 */
public class ManufacturerCount {
    //厂商名称
    private String mfName;

    //厂商组织机构代码
    private String mfOrgNo;

    //设备总数
    private Integer total;

    //在线设备数量
    private Integer onlineCount;

    //离线设备数量
    private Integer offlineCount;

    public String getMfName() {
        return mfName;
    }

    public void setMfName(String mfName) {
        this.mfName = mfName;
    }

    public String getMfOrgNo() {
        return mfOrgNo;
    }

    public void setMfOrgNo(String mfOrgNo) {
        this.mfOrgNo = mfOrgNo;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
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
