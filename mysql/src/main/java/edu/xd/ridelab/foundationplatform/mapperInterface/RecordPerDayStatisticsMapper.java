package edu.xd.ridelab.foundationplatform.mapperInterface;


import edu.xd.ridelab.foundationplatform.model.ViCountModel;
import edu.xd.ridelab.foundationplatform.model.MachineCollectCount;
import edu.xd.ridelab.foundationplatform.model.TotalCollectCount;
import edu.xd.ridelab.foundationplatform.mysql.vo.RecordPerDayStatistics;
import edu.xd.ridelab.foundationplatform.mysql.vo.RecordPerDayStatisticsKey;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface RecordPerDayStatisticsMapper {
    int deleteByPrimaryKey(RecordPerDayStatisticsKey key);

    int insert(RecordPerDayStatistics record);

    int insertSelective(RecordPerDayStatistics record);

    RecordPerDayStatistics selectByPrimaryKey(RecordPerDayStatisticsKey key);

    int updateByPrimaryKeySelective(RecordPerDayStatistics record);

    int updateByPrimaryKey(RecordPerDayStatistics record);

    ViCountModel countViRecord();

    /**
     * 统计指定列,指定日期,指定压缩的采集量(终端\热点\虚拟身份)
     * @param col
     * @param compression
     * @param date
     * @return
     */
    BigDecimal countColumnByCompressionAndDate(@Param("column") String col, @Param("compression") Integer compression, @Param("recordDate") String date);

    /**
     * 统计起始日期内的所有机具的终端(MAC_COUNT)采集量
     * @param startDate
     * @param endDate
     * @return
     */
    List<TotalCollectCount> getTotalCountByDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 返回采集量倒数前num位的机具和采集量
     * @param num
     * @return
     */
    List<MachineCollectCount> getLeastCollectMachine(@Param("num") Integer num);
    Integer selectMacCount(Long id);

    Integer selectOfflineMacCount(Long id);

    Integer selectOnlineMacCount(Long id);

    Integer getSumMacCount(@Param("date") String date,@Param("macNo") String macNo);
}