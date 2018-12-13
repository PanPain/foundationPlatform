package edu.xd.ridelab.foundationplatform.model;

import java.math.BigDecimal;

public class PoliceOfficeStatistics {

    private String policeOfficeName;

    public String getPoliceOfficeName() {
        return policeOfficeName;
    }

    public void setPoliceOfficeName(String policeOfficeName) {
        this.policeOfficeName = policeOfficeName;
    }

    private Integer macCount;

    private Integer offlineMacCount;

    private Integer onlineMacCount;

    private Double onlineRate;

    private Integer todayCollectCount;

    private Integer yesterdayCollectCount;

    public Integer getMacCount() {
        return macCount;
    }

    public void setMacCount(Integer macCount) {
        this.macCount = macCount;
    }

    public Integer getOfflineMacCount() {
        return offlineMacCount;
    }

    public void setOfflineMacCount(Integer offlineMacCount) {
        this.offlineMacCount = offlineMacCount;
    }

    public Integer getOnlineMacCount() {
        return onlineMacCount;
    }

    public void setOnlineMacCount(Integer onlineMacCount) {
        this.onlineMacCount = onlineMacCount;
    }

    public Double getOnlineRate() {
        return onlineRate;
    }

    public void setOnlineRate(Double onlineRate) {
        this.onlineRate = onlineRate;
    }

    public Integer getTodayCollectCount() {
        return todayCollectCount;
    }

    public void setTodayCollectCount(Integer todayCollectCount) {
        this.todayCollectCount = todayCollectCount;
    }

    public Integer getYesterdayCollectCount() {
        return yesterdayCollectCount;
    }

    public void setYesterdayCollectCount(Integer yesterdayCollectCount) {
        this.yesterdayCollectCount = yesterdayCollectCount;
    }

    public Integer getTotalCollectCount() {
        return totalCollectCount;
    }

    public void setTotalCollectCount(Integer totalCollectCount) {
        this.totalCollectCount = totalCollectCount;
    }

    private Integer totalCollectCount;
}
