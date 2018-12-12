package edu.xd.ridelab.foundationplatform.operation;

import edu.xd.ridelab.foundationplatform.operation.TableCreate;
import edu.xd.ridelab.foundationplatform.operation.TableInsert;

/**
 * @Author zhangxin
 * @Date 2018/5/11 10:46
 * @Description 获取operation对象工具类
 * @Since
 **/
public class OperationUtil {
    /** TableCreate实例 */
    private static TableCreate TABLECREATE = new TableCreate();

    /** TableInsert实例 */
    private static TableInsert TABLEINSERT = new TableInsert();

    /** 获取TableCreate实例
     * @return TableCreate实例
     */
    public static TableCreate getTableCreate() {
        return TABLECREATE;
    }

    /** 获取TableInsert实例
     * @return TableInsert实例
     */
    public static TableInsert getTableInsert(){
        return TABLEINSERT;
    }

}
