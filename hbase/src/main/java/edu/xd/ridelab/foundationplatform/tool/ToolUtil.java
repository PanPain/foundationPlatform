package edu.xd.ridelab.foundationplatform.tool;


/**
 * @Author zhangxin
 * @Date 2018/5/11 10:52
 * @Description 获取tool对象工具类
 * @Since
 **/
public class ToolUtil {

    /**FindColumnFamily实例  */
    private static FindColumnFamily FINDCOLUMNFAMILY = new FindColumnFamily();

    /**FindRowKey实例 */
    private static FindRowKey FINDROKEY = new FindRowKey();

    /** 获取FindColumnFamily实例
     * @return FindColumnFamily实例
     */
    public static FindColumnFamily getFindColumnFamily(){
        return FINDCOLUMNFAMILY;
    }

    /** 获取FindRowKey实例
     * @return FindRowKey实例
     */
    public static FindRowKey getFindRowKey(){
        return FINDROKEY;
    }
}
