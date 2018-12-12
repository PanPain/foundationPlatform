package edu.xd.ridelab.receiver.protocol;

import edu.xd.ridelab.foundationplatform.definition.MacDataType;
import edu.xd.ridelab.receiver.util.ByteUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

/**
 * @author PanTeng
 * @Description 协议为： 数据类型（4字节，大端序） + 内容长度（4字节，大端序） + 内容（变长）
 * @file MacProtocol.java
 * @date 2018/5/10
 * @attention Copyright (C),2004-2018,RIDELab, SEI, XiDian University
 */
@Slf4j
public class MacProtocol {
    /**
     * "数据类型" 在协议中的存储长度为4字节
     */
    private static final int macDataTypeLength = 4;
    /**
     * 数据类型
     */
    MacDataType macDataType;
    /**
     * "实际数据内容长度" 在协议中存储长度为4字节
     */
    private static final int contentLengthLength = 4;
    /**
     * 实际数据内容长度
     */
    int contentLength;
    /**
     * 实际数据内容（变长）
     */
    byte[] content;
    /**
     * 发送方ip地址
     */
    final String ip;
    /**
     * 用来将 实际数据内容 转换成 对应实体
     */
    ProtocolMapper mapper;

    public MacDataType getMacDataType() {
        return macDataType;
    }

    public int getContentLength() {
        return contentLength;
    }

    public byte[] getContent() {
        return content;
    }

    public String getIp() {
        return ip;
    }

    public MacProtocol(byte[] buf, String ip){
        this.ip = ip;
        macDataType = MacDataType.getType(ByteUtils.bytesToInt(buf, 0));
        contentLength = ByteUtils.bytesToInt(buf, macDataTypeLength);
        if (macDataType != MacDataType.ERROR_TYPE){
            content = splitContentFromBuf(buf);
            mapper = new ProtocolMapper(ip);
        }
    }

    @Override
    public String toString() {
        return "MacProtocol{" +
            "Type = " + macDataType +
            '}';
    }

    /**
     * @description 将实际数据内容从协议缓存中分离出来
     * @author PanTeng
     * @date 下午4:09  2018/5/10
     * @param buf
     */
    private byte[] splitContentFromBuf(byte[] buf) {
        int offset = macDataTypeLength + contentLengthLength;  // 8

        //2018-5-24 注释此段代码，疑似可有可无
/*        if (macDataType == MacDataType.RECEIVER_STATUS){
            // 设备状态（心跳）从协议中解析出来的内容字节长度为29，与实际40不符合，因此将内容长度变成40
            contentLength = 40;
        }*/

        byte[] acturalContent = new byte[contentLength];
        for (int i = 0; i < contentLength; i++){
            acturalContent[i] = buf[offset + i];
        }
        return acturalContent;
    }

    /**
     * @description 将实际数据内容转换为对应的实体类数组（一条协议数据包中，可能包含多个数据（相同类型））
     * @author PanTeng
     * @date 下午5:22  2018/5/10
     * @param
     */
    public List parserContent2VOList(){
        switch (macDataType){
            case STATION_MAC:
                return mapper.toStationMacRecord(content);
            case AP_MAC:
                return mapper.toAPMacRecord(content);
            case VIRTUAL_IDENTITY:
                return mapper.toVIRecord(content);
            case RECEIVER_BASIC:
                return mapper.toMacMachine(content);
            case PLACE_BASIC:
                return mapper.toPlaceInfo(content);
            case MANUFACTURER_BASIC:
                return mapper.toManufacturer(content);
            case RECEIVER_STATUS:
                return mapper.toMacMachineHeartBeat(content);
                //TODO  其他类型信息转换为实体类
            default:
                logger.warn("unsupported type {}", macDataType);
                return Collections.EMPTY_LIST;
        }
    }
}
