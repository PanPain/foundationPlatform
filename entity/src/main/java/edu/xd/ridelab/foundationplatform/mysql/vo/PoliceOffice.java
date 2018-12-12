package edu.xd.ridelab.foundationplatform.mysql.vo;

/** 主要描述：单位信息（公安局，派出所）表实体类
 * @author 台恩
 * @date 2018/05/07
 * @since 1.0
 */
public class PoliceOffice {
    //主键
    public Long policeOfficeId;

    //公安局编码
    public String policeOfficeNo;

    //公安局名称
    public String policeOfficeName;

    //上级公安局编码
    public String superiorPoliceOffice;

    //公安局地址
    public String address;

    //公安局电话
    public String phone;

    //备注
    public String remark;

    //外键，区县ID
    public Long fkDistrictId;

    public Long getPoliceOfficeId() {
        return policeOfficeId;
    }

    public void setPoliceOfficeId(Long policeOfficeId) {
        this.policeOfficeId = policeOfficeId;
    }

    public String getPoliceOfficeNo() {
        return policeOfficeNo;
    }

    public void setPoliceOfficeNo(String policeOfficeNo) {
        this.policeOfficeNo = policeOfficeNo == null ? null : policeOfficeNo.trim();
    }

    public String getPoliceOfficeName() {
        return policeOfficeName;
    }

    public void setPoliceOfficeName(String policeOfficeName) {
        this.policeOfficeName = policeOfficeName == null ? null : policeOfficeName.trim();
    }

    public String getSuperiorPoliceOffice() {
        return superiorPoliceOffice;
    }

    public void setSuperiorPoliceOffice(String superiorPoliceOffice) {
        this.superiorPoliceOffice = superiorPoliceOffice == null ? null : superiorPoliceOffice.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getFkDistrictId() {
        return fkDistrictId;
    }

    public void setFkDistrictId(Long fkDistrictId) {
        this.fkDistrictId = fkDistrictId;
    }
}