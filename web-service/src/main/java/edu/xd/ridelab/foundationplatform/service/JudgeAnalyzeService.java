package edu.xd.ridelab.foundationplatform.service;

import edu.xd.ridelab.foundationplatform.record.StationMacRecord;

import java.time.LocalDateTime;
import java.util.List;

public interface JudgeAnalyzeService {
     List<StationMacRecord> trackQuery(String mac , LocalDateTime startTime , LocalDateTime stopTime);
}
