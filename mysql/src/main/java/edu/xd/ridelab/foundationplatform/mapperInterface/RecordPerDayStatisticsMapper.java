package edu.xd.ridelab.foundationplatform.mapperInterface;


import edu.xd.ridelab.foundationplatform.mysql.vo.RecordPerDayStatistics;
import edu.xd.ridelab.foundationplatform.mysql.vo.RecordPerDayStatisticsKey;

public interface RecordPerDayStatisticsMapper {
    int deleteByPrimaryKey(RecordPerDayStatisticsKey key);

    int insert(RecordPerDayStatistics record);

    int insertSelective(RecordPerDayStatistics record);

    RecordPerDayStatistics selectByPrimaryKey(RecordPerDayStatisticsKey key);

    int updateByPrimaryKeySelective(RecordPerDayStatistics record);

    int updateByPrimaryKey(RecordPerDayStatistics record);
}