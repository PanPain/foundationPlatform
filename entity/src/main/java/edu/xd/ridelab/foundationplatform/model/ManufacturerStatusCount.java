package edu.xd.ridelab.foundationplatform.model;

/** 主要描述：安全厂商设备在线离线统计(厂商名称，厂商组织机构代码，设备数量，在线/离线)
 * @author :  zf
 * @date :  2018/5/10
 * @since :  Version 1.0
 */
public class ManufacturerStatusCount {

    //厂商名称
    private String mfName;

    //厂商组织机构代码
    private String mfOrgNo;

    //设备数
    private Integer count;

    //状态 00 - 离线，01 - 在线
    private String status;


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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
