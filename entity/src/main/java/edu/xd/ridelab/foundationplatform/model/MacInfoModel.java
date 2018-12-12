package edu.xd.ridelab.foundationplatform.model;

import edu.xd.ridelab.foundationplatform.mysql.vo.MacMachine;

public class MacInfoModel extends MacMachine {
    private String placeNo;

    private String mfName;

    private String policeOfficeName;

    private String property;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getPlaceNo() {
        return placeNo;
    }

    public void setPlaceNo(String placeNo) {
        this.placeNo = placeNo;
    }

    public String getMfName() {
        return mfName;
    }

    public void setMfName(String mfName) {
        this.mfName = mfName;
    }

    public String getPoliceOfficeName() {
        return policeOfficeName;
    }

    public void setPoliceOfficeName(String policeOfficeName) {
        this.policeOfficeName = policeOfficeName;
    }
}
