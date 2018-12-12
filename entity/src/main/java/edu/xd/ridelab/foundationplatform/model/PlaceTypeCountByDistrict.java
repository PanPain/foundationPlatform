package edu.xd.ridelab.foundationplatform.model;

import java.util.List;

/**
 * @author 翟佳豪
 * @date 2018/05/18
 * @since Version 1.0
 */
public class PlaceTypeCountByDistrict {
    //区域名称
    private String districtName;

    //该区域内不用服务类型的场所数量
    private List<PlaceTypeCount> countByType;

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public List<PlaceTypeCount> getCountByType() {
        return countByType;
    }

    public void setCountByType(List<PlaceTypeCount> countByType) {
        this.countByType = countByType;
    }
}
