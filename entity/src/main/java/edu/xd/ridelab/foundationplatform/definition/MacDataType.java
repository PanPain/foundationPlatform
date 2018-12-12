package edu.xd.ridelab.foundationplatform.definition;

import java.io.Serializable;

/**
 * @author PanTeng
 * @Description
 * @file MacDataType.java
 * @date 2018/5/10
 * @attention Copyright (C),2004-2018,RIDELab, SEI, XiDian University
 */
public enum MacDataType implements Serializable{
    /**
     * @Fields STATION_MAC : 终端特征信息，数据类型代码1001
     */
    STATION_MAC(1001),
    /**
     * @Fields AP_MAC : 被采集热点信息，数据类型代码1002
     */
    AP_MAC(1002),
    /**
     * @Fields STATION_RECEIVER_TRAJECTORY : 终端特征移动采集设备轨迹信息，数据类型代码1003
     */
    MOBILE_RECEIVER_TRAJECTORY(1003),
    /**
     * @Fields RECEIVER_BASIC : 终端特征采集设备基础信息，数据类型代码1004
     */
    RECEIVER_BASIC(1004),
    /**
     * @Fields PLACE_BASIC : 场所基础信息，数据类型代码1005
     */
    PLACE_BASIC(1005),
    /**
     * @Fields MANUFACTURER_BASIC : 安全厂商基础信息，数据类型代码1006
     */
    MANUFACTURER_BASIC(1006),
    /**
     * @Fields RECEIVER_STATUS : 采集前端设备状态，数据类型代码1007
     */
    RECEIVER_STATUS(1007),
    /**
     * @Fields RECEIVER_REMOTE_CONTROL : 前台设备远程控制管理，数据类型代码2001
     */
    RECEIVER_REMOTE_CONTROL(2001),
    /**
     * @Fields UPDATE_STATUS : 升级状态上报，数据类型代码2002
     */
    UPDATE_STATUS(2002),
    /**
     * @Fields VIRTUAL_IDENTITY : 虚拟身份上报，数据类型代码2003
     */
    VIRTUAL_IDENTITY(2003),

    /**
     * @Fields ERROR_TYPE : 错误类型，不包含以上类型
     */
    ERROR_TYPE(9999);

    private int codeValue = 9999;

    /**
     * @description 根据value返回对应的MacDataType的值
     * @author PanTeng
     * @date 下午7:24  2018/5/10
     * @param value
     * @return
     */
    public static MacDataType getType(int value){
        switch(value){
            case 1001:
                return STATION_MAC;
            case 1002:
                return AP_MAC;
            case 1003:
                return MOBILE_RECEIVER_TRAJECTORY;
            case 1004:
                return RECEIVER_BASIC;
            case 1005:
                return PLACE_BASIC;
            case 1006:
                return MANUFACTURER_BASIC;
            case 1007:
                return RECEIVER_STATUS;
            case 2001:
                return RECEIVER_REMOTE_CONTROL;
            case 2002:
                return UPDATE_STATUS;
            case 2003:
                return VIRTUAL_IDENTITY;
            default:
                return ERROR_TYPE;
        }
    }
    private MacDataType(int codeValue) {
        // TODO Auto-generated constructor stub
        this.codeValue=codeValue;
    }
    public int value(){
        return codeValue;
    }
    public String code(){
        return String.valueOf(codeValue);
    }

    public static void main(String[] args) {
        System.out.println(MacDataType.MANUFACTURER_BASIC.code());
    }
}
