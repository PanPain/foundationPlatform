package edu.xd.ridelab.foundationplatform.model;

/**
 * @author 翟佳豪
 * @date 2018/05/18
 * @since Version 1.0
 */
public class PlaceTypeCount {

    //场所的服务类型
    private String type;

    //该服务类型的场所数量
    private int count;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
