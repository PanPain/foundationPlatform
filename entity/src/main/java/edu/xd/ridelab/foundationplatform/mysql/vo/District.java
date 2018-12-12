package edu.xd.ridelab.foundationplatform.mysql.vo;

/** 主要描述：区县表实体类
 * @author 台恩
 * @date 2018/05/07
 * @since 1.0
 */
public class District {
    //主键
    private Long districtId;

    //区县编码
    private String districtNo;

    //区县名称
    private String districtName;

    //区县级别（1：一级，2：二级）
    private Short districtLevel;

    //区县范围
    private String districtArea;

    //备注
    private String districtRemark;

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getDistrictNo() {
        return districtNo;
    }

    public void setDistrictNo(String districtNo) {
        this.districtNo = districtNo == null ? null : districtNo.trim();
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName == null ? null : districtName.trim();
    }

    public Short getDistrictLevel() {
        return districtLevel;
    }

    public void setDistrictLevel(Short districtLevel) {
        this.districtLevel = districtLevel;
    }

    public String getDistrictArea() {
        return districtArea;
    }

    public void setDistrictArea(String districtArea) {
        this.districtArea = districtArea == null ? null : districtArea.trim();
    }

    public String getDistrictRemark() {
        return districtRemark;
    }

    public void setDistrictRemark(String districtRemark) {
        this.districtRemark = districtRemark == null ? null : districtRemark.trim();
    }
}