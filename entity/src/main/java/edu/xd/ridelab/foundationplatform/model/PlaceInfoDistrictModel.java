package edu.xd.ridelab.foundationplatform.model;

/**
 * @ClassName PlaceInfoDistrictModel
 * @Author：WW
 * @Description
 * @Date: 16:08$ 2018/5/18$
 **/
public class PlaceInfoDistrictModel {
    //主键
    private Long placeId;

    //场所编号
    private String placeNo;

    //场所名称
    private String placeName;

    //场所地址
    private String address;

    //场所服务类型
    private String type;

    //场所经营性质
    private String property;

    //法人姓名
    private String legalPerson;

    //设备厂商组织机构代码
    private Integer orgCode;

    //区县编码
    private String districtNo;

    //区县名称
    private String districtName;

    //经度
    private String longitude;

    //纬度
    private String latitude;

    //联系电话
    private String tel;

    private String fkPoliceOfficeId;

    public String getFkPoliceOfficeId() {
        return fkPoliceOfficeId;
    }

    public void setFkPoliceOfficeId(String fkPoliceOfficeId) {
        this.fkPoliceOfficeId = fkPoliceOfficeId;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

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
        this.placeNo = placeNo;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public Integer getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(Integer orgCode) {
        this.orgCode = orgCode;
    }

    public String getDistrictNo() {
        return districtNo;
    }

    public void setDistrictNo(String districtNo) {
        this.districtNo = districtNo;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
