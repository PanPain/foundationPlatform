package edu.xd.ridelab.foundationplatform.service.impl;

import edu.xd.ridelab.foundationplatform.operation.TrackBack;
import edu.xd.ridelab.foundationplatform.record.StationMacRecord;
import edu.xd.ridelab.foundationplatform.service.JudgeAnalyzeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class JudgeAnalyzeImpl implements JudgeAnalyzeService{
    @Override
    public List<StationMacRecord> trackQuery(String mac, LocalDateTime startTime, LocalDateTime endTime) {
        TrackBack trackBack = new TrackBack();
        List<StationMacRecord> records = trackBack.getTrackBackList(mac,startTime,endTime);

        //按照进入时间进行排序
        Collections.sort(records, (o1, o2) -> {
            if(o1.getFirstTime().isBefore(o2.getFirstTime()))
            {
                return 1;
            }else{
                return -1;
            }
        });
        return records;
    }
}
