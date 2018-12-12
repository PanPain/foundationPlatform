package edu.xd.ridelab.foundationplatform.service.impl;

import edu.xd.ridelab.foundationplatform.mapperInterface.ManufacturerMapper;
import edu.xd.ridelab.foundationplatform.mysql.vo.Manufacturer;
import edu.xd.ridelab.foundationplatform.service.ManufacturerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ChenXiang
 * @Date 2018/05/08,18:45
 */
@Service
public class ManufacturerImpl implements ManufacturerService{

    @Autowired
    private ManufacturerMapper manufacturerMapper;
    @Override
    public List<Manufacturer> getManufacturerList(String mfName, String mfOrgNo, String mfAddress, int offset, int pageNum) {
        System.out.println(mfName + mfOrgNo + mfAddress);

        return manufacturerMapper.selectBySomeConditions(mfName, mfOrgNo, mfAddress, offset, pageNum);

    }

    @Override
    public Manufacturer getManufacturer(@Param(value = "manufacturerId") Long manufacturerId) throws Exception {
        return manufacturerMapper.selectByPrimaryKey(manufacturerId);
    }

    @Override
    public int insertManufacturer(Manufacturer manufacturer) throws Exception {
        return manufacturerMapper.insert(manufacturer);
    }

    @Override
    public int deleteManufacturer(long manuFacturerId) throws Exception {
        return manufacturerMapper.deleteByPrimaryKey(manuFacturerId);
    }

    @Override
    public int updateManufacturerByManuId(Manufacturer manufacturer) throws Exception {

        return manufacturerMapper.updateByPrimaryKeySelective(manufacturer);
    }

    @Override
    public List<Manufacturer> getAllManu(int offset,int pageNum) throws Exception {
        return manufacturerMapper.getAllManu(offset,pageNum);
    }

}
