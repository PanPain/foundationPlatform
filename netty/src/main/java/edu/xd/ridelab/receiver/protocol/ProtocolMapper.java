package edu.xd.ridelab.receiver.protocol;

import edu.xd.ridelab.foundationplatform.mysql.vo.MacMachine;
import edu.xd.ridelab.foundationplatform.mysql.vo.MacMachineHeartbeat;
import edu.xd.ridelab.foundationplatform.mysql.vo.Manufacturer;
import edu.xd.ridelab.foundationplatform.mysql.vo.PlaceInfo;
import edu.xd.ridelab.foundationplatform.record.APMacRecord;
import edu.xd.ridelab.foundationplatform.record.StationMacRecord;
import edu.xd.ridelab.foundationplatform.record.VIRecord;
import edu.xd.ridelab.receiver.util.ByteUtils;
import edu.xd.ridelab.receiver.util.DateUtils;
import edu.xd.ridelab.receiver.util.PropertiesUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * @author PanTeng
 * @Description 将协议中的实际内容字节数组转换成对应的实体类实例
 * @file ProtocolMapper.java
 * @date 2018/5/10
 * @attention Copyright (C),2004-2018,RIDELab, SEI, XiDian University
 */
@Slf4j
public class ProtocolMapper {
    String ip;
    static final String encoding = "UTF-8";
    String lineRegex = PropertiesUtils.getProperty("lineRegex");       //多条数据间的分隔符
    String fieldrRegex = PropertiesUtils.getProperty("fieldrRegex");   //协议中字段间分隔符
    String ssidRegex = PropertiesUtils.getProperty("ssidRegex");       //子数据字段分隔符
    DateUtils format;

    public ProtocolMapper(String ip) {
        format = new DateUtils();
        this.ip = ip;
    }

    /**
     * @param
     * @return
     * @description 协议中的数据内容 转换成 移动终端Mac记录实体类数组（不包含协议头）
     * @author PanTeng
     * @date 下午7:26  2018/5/10
     */
    public ArrayList toStationMacRecord(byte[] bytes) {
        ArrayList<StationMacRecord> macList = new ArrayList<>();
        try {
            String content = DES.decode(bytes);
            String[] macMessages = content.split(lineRegex);
            for (int i = 0; i < macMessages.length; i++) {
                String[] fields = macMessages[i].split(fieldrRegex);
                if (fields.length != 17) {
                    continue;                // 机具发来的数据包以"ABCD"结尾，扔掉"ABCD"
                }
                val entity = new StationMacRecord();
                entity.setMac(fields[0]);
                entity.setBrand(fields[1]);
                entity.setSsidList(fields[2]);
                entity.setFirstTime(format.getTimestampBySecondString(fields[3]).toLocalDateTime());
                if (fields[4].equals("")) {
                    //TODO 该字段为null
                } else if (checkStrToInt(fields[4], macMessages[i])) {
                    entity.setRssi(Integer.parseInt(fields[4]));
                } else continue;
                if (!fields[5].equals("")) {
                    entity.setIdType(fields[5]);
                }
                entity.setIdContent(fields[6]);
                entity.setSsid(fields[7]);
                entity.setBssid(fields[8].toUpperCase());
                if (fields[9].equals("")) {
                    //TODO 该字段为null
                } else if (checkStrToInt(fields[9], macMessages[i])) {
                    entity.setChan(Integer.parseInt(fields[9]));
                } else continue;
                entity.setEncryption(fields[10]);
                if (fields[11].equals("")) {
                    //TODO 该字段为null
                } else if (checkStrToDouble(fields[11], macMessages[i])) {
                    entity.setX(Double.parseDouble(fields[11]));
                } else continue;
                if (fields[12].equals("")) {
                    //TODO 该字段为null
                } else if (checkStrToDouble(fields[12], macMessages[i])) {
                    entity.setY(Double.parseDouble(fields[12]));
                } else continue;
                entity.setPlaceNo(fields[13]);
                entity.setDevmac(fields[14]);
                if (fields[15].equals("")) {
                    //TODO 该字段为null
                } else if (checkStrToDouble(fields[15], macMessages[i])) {
                    entity.setLongitude(Double.parseDouble(fields[15]));
                } else continue;
                if (fields[16].equals("")) {
                    //TODO 该字段为null
                } else if (checkStrToDouble(fields[16], macMessages[i])) {
                    entity.setLatitude(Double.parseDouble(fields[16]));
                } else continue;
                entity.setLastTime(format.getTimestampBySecondString(fields[3]).toLocalDateTime());
                entity.setCount(1);
                macList.add(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return macList;
    }

    /**
     * @param
     * @return
     * @description 协议中的数据内容 转换成 热点Mac记录实体类数组 （不包含协议头）
     * @author PanTeng
     * @date 下午9:45  2018/5/10
     */
    public ArrayList toAPMacRecord(byte[] bytes) {
        ArrayList<APMacRecord> macList = new ArrayList<>();
        try {
            String content = DES.decode(bytes);
            String[] macMessages = content.split(lineRegex);
            for (int i = 0; i < macMessages.length; i++) {
                String[] fields = macMessages[i].split(fieldrRegex);
                if (fields.length != 12) {
                    continue;                // 机具发来的数据包以"ABCD"结尾，扔掉"ABCD"
                }
                val entity = new APMacRecord();
                entity.setMac(fields[0]);
                entity.setSsid(fields[1]);
                if (fields[2].equals("")) {
                    //TODO 该字段为null
                } else if (checkStrToInt(fields[2], macMessages[i])) {
                    entity.setChan(Integer.parseInt(fields[2]));
                } else continue;
                entity.setEncryption(fields[3]);
                entity.setFirstTime(format.getTimestampBySecondString(fields[4]).toLocalDateTime());
                if (fields[5].equals("")) {
                    //TODO 该字段为null
                } else if (checkStrToInt(fields[5], macMessages[i])) {
                    entity.setRssi(Integer.parseInt(fields[5]));
                } else continue;
                if (fields[6].equals("")) {
                    //TODO 该字段为null
                } else if (checkStrToDouble(fields[6], macMessages[i])) {
                    entity.setX(Double.parseDouble(fields[6]));
                } else continue;
                if (fields[7].equals("")) {
                    //TODO 该字段为null
                } else if (checkStrToDouble(fields[7], macMessages[i])) {
                    entity.setY(Double.parseDouble(fields[7]));
                } else continue;
                entity.setPlaceNo(fields[8]);
                entity.setDevmac(fields[9]);
                if (fields[10].equals("")) {
                    //TODO 该字段为null
                } else if (checkStrToDouble(fields[10], macMessages[i])) {
                    entity.setLongitude(Double.parseDouble(fields[10]));
                } else continue;
                if (fields[11].equals("")) {
                    //TODO 该字段为null
                } else if (checkStrToDouble(fields[11], macMessages[i])) {
                    entity.setLatitude(Double.parseDouble(fields[11]));
                } else continue;
                entity.setLastTime(format.getTimestampBySecondString(fields[4]).toLocalDateTime());

                entity.setCount(1);
                macList.add(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return macList;
    }

    /**
     * @param
     * @return
     * @description 协议中的数据内容 转换成 虚拟身份记录实体类数组（不包含协议头）
     * @author PanTeng
     * @date 上午9:38  2018/5/11
     */
    public ArrayList toVIRecord(byte[] bytes) {
        ArrayList<VIRecord> macList = new ArrayList<>();
        try {
            String content = DES.decode(bytes);
            String[] macMessages = content.split(lineRegex);
            for (int i = 0; i < macMessages.length; i++) {
                String[] fields = macMessages[i].split(fieldrRegex);
                if (fields.length != 17) {
                    continue;                // 机具发来的数据包以"ABCD"结尾，扔掉"ABCD"
                }
                val entity = new VIRecord();
                entity.setMac(fields[0]);
                entity.setFirstTime(format.getTimestampBySecondString(fields[1]).toLocalDateTime());
                entity.setImei(fields[2]);
                entity.setImsi(fields[3]);
                entity.setPhonenum(fields[4]);
                entity.setLocation(fields[5]);
                entity.setBrand(fields[6]);
                entity.setOS(fields[7]);
                entity.setProtocol(fields[8]);
                entity.setAccount(fields[9]);
                entity.setUrl(fields[10]);
                if (fields[11].equals("")) {
                    //TODO 该字段为null
                } else if (checkStrToDouble(fields[11], macMessages[i])) {
                    entity.setX(Double.parseDouble(fields[11]));
                } else continue;
                if (fields[12].equals("")) {
                    //TODO 该字段为null
                } else if (checkStrToDouble(fields[12], macMessages[i])) {
                    entity.setY(Double.parseDouble(fields[12]));
                } else continue;
                entity.setPlaceNo(fields[13]);
                entity.setDevmac(fields[14]);
                if (fields[15].equals("")) {
                    //TODO 该字段为null
                } else if (checkStrToDouble(fields[15], macMessages[i])) {
                    entity.setLongitude(Double.parseDouble(fields[15]));
                } else continue;
                if (fields[16].equals("")) {
                    //TODO 该字段为null
                } else if (checkStrToDouble(fields[16], macMessages[i])) {
                    entity.setLatitude(Double.parseDouble(fields[16]));
                } else continue;
                entity.setLastTime(format.getTimestampBySecondString(fields[1]).toLocalDateTime());
                entity.setCount(1);
                macList.add(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return macList;
    }

    /**
     * @param
     * @return
     * @description 协议中的数据内容 转换成 终端特征采集设备基础信息实体类数组（不包含协议头）
     * @author PanTeng
     * @date 上午11:21  2018/5/11
     */
    public ArrayList toMacMachine(byte[] bytes) {
        ArrayList<MacMachine> macList = new ArrayList<>();
        try {
            String content = DES.decode(bytes);
            String[] macMessages = content.split(lineRegex);
            for (int i = 0; i < macMessages.length; i++) {
                String[] fields = macMessages[i].split(fieldrRegex);
                if (fields.length < 10) {
                    continue;                // 机具发来的数据包以"ABCD"结尾，扔掉"ABCD"
                }
                val entity = new MacMachine();
                entity.setPlaceNo(fields[0]);
                entity.setMacMachineNo(fields[1]);
                entity.setMachineName(fields[2]);
                entity.setMachineAddress(fields[3]);
                if (!fields[4].equals("")) {
                    entity.setType(fields[4]);
                }
                entity.setOrgNum(fields[5]);
                if (fields[6].equals("")) {
                    //TODO 该字段为null
                } else if (checkStrToDouble(fields[6], macMessages[i])) {
                    entity.setLongitude(BigDecimal.valueOf(Double.parseDouble(fields[6])));
                } else continue;
                if (fields[7].equals("")) {
                    //TODO 该字段为null
                } else if (checkStrToDouble(fields[7], macMessages[i])) {
                    entity.setLatitude(BigDecimal.valueOf(Double.parseDouble(fields[7])));
                } else continue;
                if (fields[8].equals("")) {
                    //TODO 该字段为null
                } else if (checkStrToLong(fields[8], macMessages[i])) {
                    entity.setTimeInterval(Long.parseLong(fields[8]));
                } else continue;
                if (fields[9].equals("")) {
                    //TODO 该字段为null
                } else if (checkStrToLong(fields[9], macMessages[i])) {
                    entity.setRadius(Long.parseLong(fields[9]));
                } else continue;
                switch (fields.length) {
                    case 15:
                        entity.setPlatform(fields[14]);
                    case 14:
                        entity.setCarriageNo(fields[13]);
                    case 13:
                        entity.setMetroVehicle(fields[12]);
                    case 12:
                        entity.setMetroLine(fields[11]);
                    case 11:
                        entity.setBikeNo(fields[10]);
                    default:
                }
                entity.setIp(ip);
                entity.setLastConnectionTime(format.getTimestamp());
                macList.add(entity);
            }
            logger.debug("macList = {}", macList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return macList;
    }

    /**
     * @param
     * @return
     * @description 协议中的数据内容 转换成 场所基础信息实体类数组（不包含协议头）
     * @author PanTeng
     * @date 上午11:56  2018/5/11
     */
    public ArrayList toPlaceInfo(byte[] bytes) {
        ArrayList<PlaceInfo> macList = new ArrayList<>();
        try {
            String content = DES.decode(bytes);
            String[] macMessages = content.split(lineRegex);
            format.setDateFormat("HH:mm:ss");
            for (int i = 0; i < macMessages.length; i++) {
                String[] fields = macMessages[i].split(fieldrRegex);
                if (fields.length != 14) {
                    continue;                // 机具发来的数据包以"ABCD"结尾，扔掉"ABCD"
                }
                val entity = new PlaceInfo();
                entity.setPlaceNo(fields[0]);
                entity.setPlaceName(fields[1]);
                entity.setAddress(fields[2]);
                entity.setLongitude(fields[3]);
                entity.setLatitude(fields[4]);
                entity.setType(fields[5]);
                entity.setProperty(fields[6]);
                entity.setLegalPerson(fields[7]);
                entity.setCertType(fields[8]);
                entity.setCertNo(fields[9]);
                entity.setTel(fields[10]);
                if (fields[11].equals("")) {
                    //TODO 该字段为null
                } else if (checkStrToLocalTime(fields[11])) {
                    entity.setBeginTime(LocalTime.parse(fields[11]));
                } else continue;
                if (fields[12].equals("")) {
                    //TODO 该字段为null
                } else if (checkStrToLocalTime(fields[12])) {
                    entity.setEndTime(LocalTime.parse(fields[12]));
                } else continue;
                if (fields[13].equals("")) {
                    //TODO 该字段为null
                } else if (checkStrToInt(fields[13], macMessages[i])) {
                    entity.setOrgCode(Integer.parseInt(fields[13]));
                } else continue;
                macList.add(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return macList;
    }

    /**
     * @param
     * @return
     * @description 协议中的数据内容 转换成 安全厂商基础信息实体类数组（不包含协议头）
     * @author PanTeng
     * @date 下午1:03  2018/5/11
     */
    public ArrayList toManufacturer(byte[] bytes) {
        ArrayList<Manufacturer> macList = new ArrayList<>();
        try {
            String content = DES.decode(bytes);
            String[] macMessages = content.split(lineRegex);
            for (int i = 0; i < macMessages.length; i++) {
                String[] fields = macMessages[i].split(fieldrRegex);
                if (fields.length != 6) {
                    continue;                // 机具发来的数据包以"ABCD"结尾，扔掉"ABCD"
                }
                val entity = new Manufacturer();
                entity.setMfName(fields[0]);
                entity.setMfOrgNo(fields[1]);
                entity.setMfAddress(fields[2]);
                entity.setMfContact(fields[3]);
                entity.setPhone(fields[4]);
                entity.setEmail(fields[5]);
                macList.add(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return macList;
    }

    /**
     * @param
     * @return
     * @description 协议中的数据内容 转换成 前端设备状态（心跳）实体类
     * @author PanTeng
     * @date 下午4:26  2018/5/19
     */
    public ArrayList toMacMachineHeartBeat(byte[] bytes) {
        ArrayList<MacMachineHeartbeat> maclist = new ArrayList<>();
        String content = ByteUtils.bytesToString(bytes);
        String[] fields = content.split(fieldrRegex);
        val entity = new MacMachineHeartbeat();
        if (fields.length != 3) {
            logger.error("心跳数据格式错误！ content：", content);
            throw new IllegalArgumentException();
        }
        entity.setPlaceNo(fields[0]);
        entity.setMacMachineNo(fields[1]);
        entity.setStatus(fields[2]);
        entity.setHeartbeatTime(Instant.now());

        maclist.add(entity);
        return maclist;
    }

    /**
     * @param
     * @return
     * @description 检查String是否能转换成int
     * @author PanTeng
     * @date 下午9:27  2018/5/10
     */
    private boolean checkStrToInt(String str, String message) {
        try {
            if (str.equals(""))
                return false;
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error("type cast {}", message);
            return false;
        }
    }

    /**
     * @param
     * @return
     * @description 检查String是否能转换成double
     * @author PanTeng
     * @date 下午9:34  2018/5/10
     */
    private boolean checkStrToDouble(String str, String message) {
        try {
            if (str.equals(""))
                return false;
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error("type cast {}", message);
            return false;
        }
    }

    /**
     * @param
     * @return
     * @description 检查String是否能转换成long
     * @author PanTeng
     * @date 上午11:42  2018/5/11
     */
    private boolean checkStrToLong(String str, String message) {
        try {
            if (str.equals(""))
                return false;
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error("type cast {}", message);
            return false;
        }
    }

    /**
     * @param
     * @return
     * @description 检查String是否能转换成LocalTime
     * @author PanTeng
     * @date 下午4:38  2018/5/15
     */
    private boolean checkStrToLocalTime(String str) {
        try {
            LocalTime.parse(str);
            return true;
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            logger.error("type cast {}", str);
            return false;
        }
    }

}
