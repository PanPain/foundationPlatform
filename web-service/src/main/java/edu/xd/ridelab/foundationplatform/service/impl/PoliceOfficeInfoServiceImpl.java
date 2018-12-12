package edu.xd.ridelab.foundationplatform.service.impl;

import edu.xd.ridelab.foundationplatform.mapperInterface.PoliceOfficeMapper;
import edu.xd.ridelab.foundationplatform.model.PoliceOfficeModel;
import edu.xd.ridelab.foundationplatform.mysql.vo.PoliceOffice;
import edu.xd.ridelab.foundationplatform.service.PoliceOfficeInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.ibatis.annotations.Param;



import java.util.List;

/** 主要描述：负责调用与数据库交互的mapper：
 * 查询单位信息
 * 单位信息明细
 * 增加单位信息
 * 删除单位信息
 * 修改单位信息
 *
 * @author 台恩
 * @date 2018/05/07
 * @since 1.0
 */
@Service
public class PoliceOfficeInfoServiceImpl implements PoliceOfficeInfoService {
    @Autowired
    private PoliceOfficeMapper policeOfficeMapper;

    /** 主要描述：根据传入条件参数查询数据库对应记录
     * @param record 单位信息实体类
     * @return 查询到的实体类结果
     * @since 1.0
     */
    @Override
    public List<PoliceOfficeModel> selectPoliceOfficeInfo(@Param(value = "record") PoliceOfficeModel record,@Param(value = "offset") int offset,@Param(value = "pageSize") int pageSize) {

        return policeOfficeMapper.selectByPrimaryKeySelective(record, offset, pageSize);
    }

    /** 主要描述：根据传入记录主键查询数据库对应明细记录
     * @param policeOfficeId 记录主键
     * @return 查询到的实体类结果
     * @since 1.0
     */
    @Override
    public PoliceOfficeModel selectPoliceOfficeDetailInfo(Long policeOfficeId){
        return policeOfficeMapper.selectByPrimaryKey(policeOfficeId);
    }

    /** 主要描述：根据传入条件参数添加记录到数据库
     * @param record 单位信息实体类
     * @return 添加状态
     * @since 1.0
     */
    @Override
    public int addPoliceOfficeInfo(PoliceOfficeModel record){
        if(record.getDistrictNo() != null || record.getDistrictName() != null){
            record.setFkDistrictId(policeOfficeMapper.selectFkDistrictIdByNoAndName(record.getDistrictNo(),record.getDistrictName()));
            return policeOfficeMapper.insertSelective(record);
        }
        else {
            record.setFkDistrictId(null);
            return policeOfficeMapper.insertSelective(record);
        }

    }

    /** 主要描述：根据传入记录主键删除数据库记录
     * @param list 主键列表
     * @return 删除状态
     * @since 1.0
     */
    @Override
    public int deletePoliceOfficeInfo(List<Long> list){
        return  policeOfficeMapper.deleteByPrimaryKey(list);
    }

    /** 主要描述：根据传入条件参数更新数据库对应记录
     * @param record 单位信息实体类
     * @return 更新状态
     * @since 1.0
     */
    @Override
    public int updatePoliceOfficeInfo(PoliceOfficeModel record){
        if(record.getDistrictNo() != null || record.getDistrictName() != null){
            record.setFkDistrictId(policeOfficeMapper.selectFkDistrictIdByNoAndName(record.getDistrictNo(),record.getDistrictName()));
            return  policeOfficeMapper.updateByPrimaryKeySelective(record);
        }
        else {
            record.setFkDistrictId(null);
            return  policeOfficeMapper.updateByPrimaryKeySelective(record);
        }
    }

    /** 主要描述：根据单位名称获取单位Id
     * @param policeOfficeName 单位名称
     * @return 更新状态
     * @since 1.0
     */
    public long selectPoliceOfficeIdByName(String policeOfficeName){
        return policeOfficeMapper.selectPoliceOfficeIdByName(policeOfficeName);
    }
}
