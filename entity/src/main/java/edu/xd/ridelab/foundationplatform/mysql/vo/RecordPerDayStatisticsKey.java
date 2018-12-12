package edu.xd.ridelab.foundationplatform.mysql.vo;

import java.util.Date;

public class RecordPerDayStatisticsKey {
    private Date recordDate;

    private String macMachineNo;

    private Boolean compression;

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getMacMachineNo() {
        return macMachineNo;
    }

    public void setMacMachineNo(String macMachineNo) {
        this.macMachineNo = macMachineNo == null ? null : macMachineNo.trim();
    }

    public Boolean getCompression() {
        return compression;
    }

    public void setCompression(Boolean compression) {
        this.compression = compression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecordPerDayStatisticsKey)) return false;

        RecordPerDayStatisticsKey that = (RecordPerDayStatisticsKey) o;

        if (getRecordDate() != null ? !getRecordDate().equals(that.getRecordDate()) : that.getRecordDate() != null)
            return false;
        if (getMacMachineNo() != null ? !getMacMachineNo().equals(that.getMacMachineNo()) : that.getMacMachineNo() != null)
            return false;
        return getCompression() != null ? getCompression().equals(that.getCompression()) : that.getCompression() == null;
    }

    @Override
    public int hashCode() {
        int result = getRecordDate() != null ? getRecordDate().hashCode() : 0;
        result = 31 * result + (getMacMachineNo() != null ? getMacMachineNo().hashCode() : 0);
        result = 31 * result + (getCompression() != null ? getCompression().hashCode() : 0);
        return result;
    }

    public RecordPerDayStatisticsKey() {
    }

    public RecordPerDayStatisticsKey(RecordPerDayStatistics recordPerDayStatistics) {
        this.setRecordDate(recordPerDayStatistics.getRecordDate());
        this.setCompression(recordPerDayStatistics.getCompression());
        this.setMacMachineNo(recordPerDayStatistics.getMacMachineNo());
    }
}