package edu.xd.ridelab.foundationplatform.service;


import edu.xd.ridelab.foundationplatform.model.MachineCollectCount;
import edu.xd.ridelab.foundationplatform.model.TotalCollectCount;

import edu.xd.ridelab.foundationplatform.model.PoliceOfficeStatistics;
import edu.xd.ridelab.foundationplatform.model.ViCountModel;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/5/23.
 */
public interface RecordStatisticService {

	BigDecimal countColumnByCompressionAndDate(String columnName, Integer compression, Date date);

	List<TotalCollectCount> getMachineTotalCountByDates(Date startDate,Date endDate);

	List<MachineCollectCount> getLeastCollectMachine(Integer num);

	List<PoliceOfficeStatistics> countPoliceOfficeStatisticsInfo();

	ViCountModel getViCountModel();
}
