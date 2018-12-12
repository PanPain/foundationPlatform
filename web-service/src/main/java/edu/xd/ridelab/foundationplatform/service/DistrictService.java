package edu.xd.ridelab.foundationplatform.service;

import edu.xd.ridelab.foundationplatform.mysql.vo.District;

import java.util.List;

/**
 * @author :  zf
 * @date :  2018/5/7
 * @since :  Version 1.0
 */
public interface DistrictService {

    /** 通过区域ID获取对应区域
     * @param: districtId 区域ID
     * @return: edu.xd.ridelab.foundationplatform.mysql.vo.District
     * @throws:
     * @since: version 1.0
     */
    District getDistrict(Long districtId) throws Exception;

    /** 添加新区域
     * @param: edu.xd.ridelab.foundationplatform.mysql.vo.District
     * @return: 添加成功返回 添加成功个数
     * @throws: 
     * @since: version 1.0
     */
    int insertDistrict(District district) throws Exception;

    /** 通过区域ID删除对应区域
     * @param: districtId 区域ID
     * @return: 删除成功返回 删除成功个数
     * @throws: 
     * @since: version 1.0
     */
    int deleteDistrict(Long districtId) throws Exception;

    /** 通过区域ID修改对应区域
     * @param: edu.xd.ridelab.foundationplatform.mysql.vo.District
     * @return: 修改成功返回 修改成功个数
     * @throws:
     * @since: version 1.0
     */
    int modifyDistrictByDistrictId(District district) throws Exception;

    /** 多条件选择查询区域
     * @param: districtNo 区域编号
     * @param: districtName 区域名称
     * @param: districtLevel 区域级别
     * @param: offset
     * @param: pageNum
     * @return: 返回查询到的区域集合
     * @throws:
     * @since: version 1.0
     */
    List<District> getDistrictList (String districtNo, String districtName, String districtLevel, int offset, int pageNum) throws Exception;

    /** 通过区域ID集合批量删除对应区域
     * @param:  区域ID集合
     * @return: 删除成功返回 删除成功个数
     * @throws:
     * @since: version 1.0
     */
    int deleteBatchByPrimaryKey(List<Long> list) throws Exception;

    /** 获取全部区域信息
     * @param: offset
     * @param: pageNum
     * @return: 返回全部区域集合
     * @throws:
     * @since: version 1.0
     */
    List<District> getAllDistrict(int offset, int pageNum) throws Exception;
}
