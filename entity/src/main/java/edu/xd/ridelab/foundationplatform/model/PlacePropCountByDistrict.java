package edu.xd.ridelab.foundationplatform.model;

import java.util.List;

/**
 * @author 翟佳豪
 * @date 2018/05/17
 * @since Version 1.0
 */
public class PlacePropCountByDistrict {

    //区域名称
    private String districtName;

    //该区域内不同经营性质的场所数量
    private List<PlacePropCount> countByProp;

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public List<PlacePropCount> getCountByProp() {
        return countByProp;
    }

    public void setCountByProp(List<PlacePropCount> countByProp) {
        this.countByProp = countByProp;
    }
}
