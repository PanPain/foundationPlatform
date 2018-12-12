package edu.xd.ridelab.foundationplatform.service.impl;

import edu.xd.ridelab.foundationplatform.mapperInterface.ManufacturerMapper;
import edu.xd.ridelab.foundationplatform.model.ManufacturerCount;
import edu.xd.ridelab.foundationplatform.model.ManufacturerStatusCount;
import edu.xd.ridelab.foundationplatform.service.ManufacturerCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author :  zf
 * @date :  2018/5/10
 * @since :  Version 1.0
 */
@Service
public class ManufacturerCountImpl implements ManufacturerCountService {


    @Autowired
    private ManufacturerMapper manufacturerMapper;

    public List<ManufacturerStatusCount> getManufacturerStatusCountByMfName(String mfName, int offset, int pageNum) {

        return manufacturerMapper.getManufacturerStatusCountByMfName( mfName, offset, pageNum);
    }

    public List<ManufacturerCount> getManufacturerCountByMfName(String mfName, int offset, int pageNum) {
        return manufacturerMapper.getManufacturerCountByMfName(mfName,offset,pageNum);
    }
}
