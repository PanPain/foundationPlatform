package edu.xd.ridelab.foundationplatform.service.impl;

import edu.xd.ridelab.foundationplatform.mapperInterface.MacMachineMapper;
import edu.xd.ridelab.foundationplatform.mapperInterface.PoliceOfficeMapper;
import edu.xd.ridelab.foundationplatform.mapperInterface.RecordPerDayStatisticsMapper;
import edu.xd.ridelab.foundationplatform.model.MachineCollectCount;
import edu.xd.ridelab.foundationplatform.model.TotalCollectCount;
import edu.xd.ridelab.foundationplatform.model.PoliceOfficeStatistics;
import edu.xd.ridelab.foundationplatform.model.ViCountModel;
import edu.xd.ridelab.foundationplatform.service.RecordStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

/**
 *@author xuziheng
 *@date 2018/5/23
 *@since 1.0
 */
@Service
public class RecordStatisticServiceImpl implements RecordStatisticService{

	@Autowired
	RecordPerDayStatisticsMapper recordPerDayStatisticsMapper;


	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	PoliceOfficeMapper policeOfficeMapper;

	@Autowired
	MacMachineMapper macMachineMapper;

	@Override
	public BigDecimal countColumnByCompressionAndDate(String columnName, Integer compression, Date date) {
		return recordPerDayStatisticsMapper.countColumnByCompressionAndDate(columnName,compression,date==null?"":"'"+formatter.format(date)+"'");
	}

	@Override
	public List<TotalCollectCount> getMachineTotalCountByDates(Date startDate, Date endDate) {

		return recordPerDayStatisticsMapper.getTotalCountByDates(startDate,endDate);
	}

	@Override
	public List<MachineCollectCount> getLeastCollectMachine(Integer num) {
		return recordPerDayStatisticsMapper.getLeastCollectMachine(num);
	}

	@Override
	public List<PoliceOfficeStatistics> countPoliceOfficeStatisticsInfo(){
		List<PoliceOfficeStatistics> list = new ArrayList<PoliceOfficeStatistics>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String today = df.format(System.currentTimeMillis());
		String yesterday = df.format(System.currentTimeMillis() - 24 * 60 * 60 * 1000);

		List<Long> ids = policeOfficeMapper.getAllId();
		for(Long id : ids){
			PoliceOfficeStatistics pos = new PoliceOfficeStatistics();
			pos.setPoliceOfficeName(policeOfficeMapper.getPoliceOfficeNameById(id));
			pos.setMacCount(recordPerDayStatisticsMapper.selectMacCount(id));
			if(pos.getMacCount() == 0)
				continue;
			pos.setOfflineMacCount(recordPerDayStatisticsMapper.selectOfflineMacCount(id));
			pos.setOnlineMacCount(recordPerDayStatisticsMapper.selectOnlineMacCount(id));
			System.out.println("-----------");
			System.out.println("getMacCount : " + pos.getMacCount());
			System.out.println("getOfflineMacCount : " + pos.getOfflineMacCount());
			System.out.println("getOnlineMacCount : " + pos.getOnlineMacCount());
			System.out.println(pos.getOnlineMacCount() / pos.getMacCount());
			System.out.println("-----------");

			pos.setOnlineRate((double)(pos.getOnlineMacCount() / pos.getMacCount()) );
			List<String> macNos = macMachineMapper.getAllMacNoById(id);
			Integer sumToday = 0;
			Integer sumYesterday = 0;
			Integer sumTotal = 0;
			for(String macNo :macNos){
				if(recordPerDayStatisticsMapper.getSumMacCount(today,macNo) != null)
					sumToday += recordPerDayStatisticsMapper.getSumMacCount(today,macNo);
				if(recordPerDayStatisticsMapper.getSumMacCount(yesterday,macNo) != null)
					sumYesterday += recordPerDayStatisticsMapper.getSumMacCount(yesterday,macNo);
				if(recordPerDayStatisticsMapper.getSumMacCount(null,macNo) != null)
					sumTotal += recordPerDayStatisticsMapper.getSumMacCount(null,macNo);
			}
			pos.setTodayCollectCount(sumToday);
			pos.setYesterdayCollectCount(sumYesterday);
			pos.setTotalCollectCount(sumTotal);

			list.add(pos);
		}

		return list;
	}

	@Override
	public ViCountModel getViCountModel() {
		ViCountModel viCountModel = recordPerDayStatisticsMapper.countViRecord();
		long others = viCountModel.getViCount() - viCountModel.getQqChat() - viCountModel.getSinaWeiBo() - viCountModel.getWeiXinMobile();
		viCountModel.setOthers(others);

		return viCountModel;
	}
}
