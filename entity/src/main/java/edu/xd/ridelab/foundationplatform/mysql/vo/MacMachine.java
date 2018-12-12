package edu.xd.ridelab.foundationplatform.mysql.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 主要描述：设备信息表实体类
 * @author 台恩
 * @date 2018/05/11
 * @since 1.0
 */
public class MacMachine implements Serializable{
    private static final long serialVersionUID = 1L;
    //主键
    public Long macMachineId;

    //机具编号
    public String macMachineNo;

    //状态 00 - 离线，01 - 在线
    public String status;

    //场所ID
    public Long fkPlaceId;

    //采集设备名称
    public String machineName;

    //经度
    public BigDecimal longitude;

    //纬度
    public BigDecimal latitude;

    //设备地址
    public String machineAddress;

    /*设备类型
    01 - 固定采集设备
    02 - 移动车载采集设备
    03 - 单兵采集设备
    09 - 其他
    */
    public String type;

    //厂商ID
    public Long fkMfId;

    //数据上传时间间隔(S)
    public Long timeInterval;

    //采集半径，单位（M）
    public Long radius;

    //车牌号码
    public String bikeNo;

    //地铁线路信息
    public String metroLine;

    //地铁车辆信息
    public String metroVehicle;

    //地铁车厢信息
    public String carriageNo;

    //站台信息
    public String platform;

    //IP地址
    public String ip;

    //上次连接时间 (YYYY-MM-DD HH:MM:SS)
    public Date lastConnectionTime;

    //外键，单位ID
    public Long fkPoliceofficeId;

    private String orgNum;

    private String placeNo;

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
        this.macMachineNo = macMachineNo == null ? null : macMachineNo.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getPlaceNo() {
        return placeNo;
    }

    public void setPlaceNo(String placeNo) {
        this.placeNo = placeNo;
    }

    public Long getFkPlaceId() {
        return fkPlaceId;
    }

    public void setFkPlaceId(Long fkPlaceId) {
        this.fkPlaceId = fkPlaceId;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName == null ? null : machineName.trim();
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getMachineAddress() {
        return machineAddress;
    }

    public void setMachineAddress(String machineAddress) {
        this.machineAddress = machineAddress == null ? null : machineAddress.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getOrgNum() {
        return orgNum;
    }

    public void setOrgNum(String orgNum) {
        this.orgNum = orgNum;
    }

    public Long getFkMfId() {
        return fkMfId;
    }

    public void setFkMfId(Long fkMfId) {
        this.fkMfId = fkMfId;
    }

    public Long getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(Long timeInterval) {
        this.timeInterval = timeInterval;
    }

    public Long getRadius() {
        return radius;
    }

    public void setRadius(Long radius) {
        this.radius = radius;
    }

    public String getBikeNo() {
        return bikeNo;
    }

    public void setBikeNo(String bikeNo) {
        this.bikeNo = bikeNo == null ? null : bikeNo.trim();
    }

    public String getMetroLine() {
        return metroLine;
    }

    public void setMetroLine(String metroLine) {
        this.metroLine = metroLine == null ? null : metroLine.trim();
    }

    public String getMetroVehicle() {
        return metroVehicle;
    }

    public void setMetroVehicle(String metroVehicle) {
        this.metroVehicle = metroVehicle == null ? null : metroVehicle.trim();
    }

    public String getCarriageNo() {
        return carriageNo;
    }

    public void setCarriageNo(String carriageNo) {
        this.carriageNo = carriageNo == null ? null : carriageNo.trim();
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getLastConnectionTime() {
        return lastConnectionTime;
    }

    public void setLastConnectionTime(Date lastConnectionTime) {
        this.lastConnectionTime = lastConnectionTime;
    }

    public Long getFkPoliceofficeId() {
        return fkPoliceofficeId;
    }

    public void setFkPoliceofficeId(Long fkPoliceofficeId) {
        this.fkPoliceofficeId = fkPoliceofficeId;
    }

}

