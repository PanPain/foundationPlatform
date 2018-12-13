package edu.xd.ridelab.foundationplatform.mysql.vo;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalTime;

/** 主要描述：场所信息表实体类
 * @author 台恩
 * @date 2018/05/07
 * @since 1.0
 */
public class PlaceInfo implements Serializable{
    private static final long serialVersionUID = 1L;
    //主键
    private Long placeId;

    //场所编号
    private String placeNo;

    //场所名称
    private String placeName;

    //场所地址
    private String address;

    //经度
    private String longitude;

    //纬度
    private String latitude;

    //场所服务类型
    private String type;

    //场所经营性质
    private String property;

    //场所所属单位ID
    private Long fkPoliceOfficeId;

    //法人姓名
    private String legalPerson;

    /*证件类型
    00 - 身份证
    01 - 户口本
    02 - 居住证
    03 - 签证
    04 - 护照
    05 - 军人证
    06 - 港澳通行证
    99 - 其他
    */
    private String certType;

    //证件号码
    private String certNo;

    //联系电话
    private String tel;

    //营业开始时间（HH:MM:SS）
//    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime beginTime;

    //营业结束时间（HH:MM:SS）
//    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;


    //设备厂商组织机构代码
    private Integer orgCode;


    //审核状态
    private Byte checkStatus;

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public String getPlaceNo() {
        return placeNo;
    }

    public void setPlaceNo(String placeNo) {
        this.placeNo = placeNo == null ? null : placeNo.trim();
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName == null ? null : placeName.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property == null ? null : property.trim();
    }

    public Long getFkPoliceOfficeId() {
        return fkPoliceOfficeId;
    }

    public void setFkPoliceOfficeId(Long fkPoliceOfficeId) {
        this.fkPoliceOfficeId = fkPoliceOfficeId;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson == null ? null : legalPerson.trim();
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType == null ? null : certType.trim();
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo == null ? null : certNo.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public LocalTime getBeginTime() {
        return beginTime;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "GMT+8")
    public void setBeginTime(LocalTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "GMT+8")
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Integer getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(Integer orgCode) {
        this.orgCode = orgCode;
    }

    public Byte getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Byte checkStatus) {
        this.checkStatus = checkStatus;
    }

    @Override
    public String toString() {
        return "PlaceInfo{" +
                "placeId=" + placeId +
                ", placeNo='" + placeNo + '\'' +
                ", placeName='" + placeName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
