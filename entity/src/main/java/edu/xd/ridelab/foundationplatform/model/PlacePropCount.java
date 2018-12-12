package edu.xd.ridelab.foundationplatform.model;

/**
 * @author 翟佳豪
 * @date 2018/05/18
 * @since Version 1.0
 */
public class PlacePropCount {

    //场所的经营性质
    private String prop;

    //该经营性质对应场所数量
    private int count;

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
