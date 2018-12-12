package edu.xd.ridelab.foundationplatform.RedisUtils;

/**
 * @Author ChenXiang
 * @Date 2018/05/17,17:57
 */
public class DataTypeUtils {
    public static Boolean getBooleanFromString(String string){
        return string.equals("compress")?true:false;
    }
}