package edu.xd.ridelab.foundationplatform.model;

import edu.xd.ridelab.foundationplatform.mysql.vo.PoliceOffice;

public class PoliceOfficeModel extends PoliceOffice {
    public String districtNo;

    public String districtName;

    public String getDistrictNo() {
        return districtNo;
    }

    public void setDistrictNo(String districtNo) {
        this.districtNo = districtNo;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

}
