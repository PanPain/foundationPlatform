package edu.xd.ridelab.foundationplatform.mysqlAlone;

import edu.xd.ridelab.foundationplatform.mapperInterface.*;
import edu.xd.ridelab.foundationplatform.mysql.vo.*;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;


/**
 * Mysql数据库操作基本类
 * @author cwz
 * @date 2018/05/10
 */
public class SqlUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(SqlUtils.class);

    /**
     * 获取所有报警规则
     *
     * @return
     */
    public static List<Rule> getRules() {
        SqlSession session = SqlManager.getSession();
        RuleMapper ruleMapper = session.getMapper(RuleMapper.class);
        List<Rule> rules = ruleMapper.selectAllRules(0, Integer.MAX_VALUE);
        session.close();
        return rules;
    }

    /**
     * 更新表中对应地点信息，如果没有则新建
     *
     * @param placeInfo
     */
    public static void writePlaceToOracle(PlaceInfo placeInfo) {
        LOGGER.info("place : {}", placeInfo.toString());
        SqlSession session = SqlManager.getSession();
        PlaceInfoMapper placeInfoMapper = session.getMapper(PlaceInfoMapper.class);
        Long placeId = placeInfoMapper.selectPlaceIdByPlaceName(placeInfo.getPlaceNo());

        if (placeId != null) {
            placeInfo.setPlaceId(placeId);
            placeInfoMapper.updateByPrimaryKeySelective(placeInfo);
        } else {
            placeInfo.setCheckStatus(Byte.valueOf("0"));
            placeInfoMapper.insert(placeInfo);
        }
        session.close();
    }

    /**
     * 更新表中对应厂商信息，如果没有则新建
     *
     * @param manufacturer
     */
    public static void writeManufactureToOracle(Manufacturer manufacturer) {
        LOGGER.info("manufacturer : {}", manufacturer.toString());
        SqlSession session = SqlManager.getSession();
        ManufacturerMapper manufacturerMapper = session.getMapper(ManufacturerMapper.class);
        Long manufacturerId = manufacturerMapper.selectMfIdByMfOrgNo(manufacturer.getMfOrgNo());
        if (manufacturerId != null) {
            manufacturer.setMfId(manufacturerId);
            manufacturerMapper.updateByPrimaryKeySelective(manufacturer);
        } else {
            manufacturerMapper.insert(manufacturer);
        }

        session.close();

    }

    /**
     * 更新表中对应机具信息，如果没有新建
     *
     * @param macMachine
     */
    public static void writeMacMachineBasicToOracle(MacMachine macMachine) {
        LOGGER.info("macMachine : {}", macMachine.toString());
        SqlSession session = SqlManager.getSession();
        PlaceInfoMapper placeInfoMapper = session.getMapper(PlaceInfoMapper.class);
        ManufacturerMapper manufacturerMapper = session.getMapper(ManufacturerMapper.class);
        MacMachineMapper macMachineMapper = session.getMapper(MacMachineMapper.class);

        Long macMachineId = macMachineMapper.selectMacIdByMacNo(macMachine.getMacMachineNo());
        Long placeId = placeInfoMapper.selectPlaceIdByPlaceName(macMachine.getPlaceNo());
        Long manufacturerId = manufacturerMapper.selectMfIdByMfOrgNo(macMachine.getOrgNum());
        if (placeId == null || manufacturerId == null) {
            LOGGER.error("no place or manufacturer found for {}", macMachine);
        } else {
            PlaceInfo placeInfo = placeInfoMapper.selectByPrimaryKey(placeId);
            Long policeOfficeId = placeInfo.getFkPoliceOfficeId();
            if (policeOfficeId != null) {
                macMachine.setFkPoliceofficeId(policeOfficeId);
            }
            macMachine.setFkPlaceId(placeId);
            macMachine.setFkMfId(manufacturerId);

            if (macMachineId != null) {
                macMachine.setMacMachineId(macMachineId);
                macMachineMapper.updateByPrimaryKeySelective(macMachine);
            } else {
                macMachineMapper.insert(macMachine);
            }
        }

        session.close();
    }

    public static void insertMachineStatusLog(MacMachineHeartbeat heartbeat) {
        SqlSession session = SqlManager.getSession();
        MacMachineMapper macMachineMapper = session.getMapper(MacMachineMapper.class);
        MachineStatusLogMapper machineStatusLogMapper = session.getMapper(MachineStatusLogMapper.class);
        Long macMachineId = macMachineMapper.selectMacIdByMacNo(heartbeat.getMacMachineNo());

        MachineStatusLog log = new MachineStatusLog();
        log.setOnlineTime(Date.from(heartbeat.getHeartbeatTime()));
        log.setFkMacMachineId(macMachineId);

        machineStatusLogMapper.insert(log);
    }

    public static void updateRecordPerDayStatisticsToMysql(RecordPerDayStatistics recordPerDayStatistics) {
        SqlSession session = SqlManager.getSession();
        RecordPerDayStatisticsMapper recordPerDayStatisticsMapper = session.getMapper(RecordPerDayStatisticsMapper.class);
        recordPerDayStatisticsMapper.updateByPrimaryKey(recordPerDayStatistics);
        session.close();
    }

    public static RecordPerDayStatistics queryRecordPerDayStatisticsFromMysql(RecordPerDayStatisticsKey recordPerDayStatisticsKey) {
        SqlSession session = SqlManager.getSession();
        RecordPerDayStatisticsMapper recordPerDayStatisticsMapper = session.getMapper(RecordPerDayStatisticsMapper.class);
        RecordPerDayStatistics recordPerDayStatistics = recordPerDayStatisticsMapper.selectByPrimaryKey(recordPerDayStatisticsKey);
        session.close();
        return recordPerDayStatistics;
    }

    public static void insertRecordPerDayStatisticsToMysql(RecordPerDayStatistics recordPerDayStatistics) {
        SqlSession session = SqlManager.getSession();
        RecordPerDayStatisticsMapper recordPerDayStatisticsMapper = session.getMapper(RecordPerDayStatisticsMapper.class);
        recordPerDayStatisticsMapper.insertSelective(recordPerDayStatistics);
        session.close();
    }

    public static List<Manufacturer> getManufacturerFromMysql() {
        SqlSession session = SqlManager.getSession();
        ManufacturerMapper manufacturerMapper = session.getMapper(ManufacturerMapper.class);

        return manufacturerMapper.getAllManu(0, Integer.MAX_VALUE);
    }

    public static List<PlaceInfo> getPlaceInfoFromMysql() {
        SqlSession session = SqlManager.getSession();
        PlaceInfoMapper placeInfoMapper = session.getMapper(PlaceInfoMapper.class);

        return placeInfoMapper.selectAllPlaceInfo();
    }

    public static List<MacMachine> getMacMachineFromMysql() {
        SqlSession session = SqlManager.getSession();
        MacMachineMapper macMachineMapper = session.getMapper(MacMachineMapper.class);

        return macMachineMapper.getAllMacMachine();
    }
}
