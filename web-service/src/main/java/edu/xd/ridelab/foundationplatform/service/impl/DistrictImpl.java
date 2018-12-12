package edu.xd.ridelab.foundationplatform.service.impl;

import edu.xd.ridelab.foundationplatform.mapperInterface.DistrictMapper;
import edu.xd.ridelab.foundationplatform.mysql.vo.District;
import edu.xd.ridelab.foundationplatform.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author :  zf
 * @description :
 * @date :  2018/5/7
 */
@Service
public class DistrictImpl implements DistrictService {

    @Autowired
    private DistrictMapper districtMapper;

    public District getDistrict(Long districtId) throws Exception {
        return districtMapper.selectByPrimaryKey(districtId);
    }

    public int insertDistrict(District district) throws Exception {
        return districtMapper.insert(district);
    }

    public int deleteDistrict(Long districtId) throws Exception {
        return districtMapper.deleteByPrimaryKey(districtId);
    }

    public int deleteBatchByPrimaryKey(List<Long> list) throws Exception {
        return districtMapper.deleteBatchByPrimaryKey(list);
    }

    public int modifyDistrictByDistrictId(District district) throws Exception {
        return districtMapper.updateByPrimaryKeySelective(district);
    }

    public List<District> getDistrictList(String districtNo, String districtName, String districtLevel, int offset, int pageNum) throws Exception {
        return districtMapper.selectBySomeCondition(districtNo,districtName,districtLevel,offset,pageNum);
    }

    public List<District> getAllDistrict(int offset, int pageNum) throws Exception{
        return districtMapper.selectAll(offset,pageNum);
    }
}
