package edu.xd.ridelab.foundationplatform.service;

import edu.xd.ridelab.foundationplatform.model.PoliceOfficeModel;
import edu.xd.ridelab.foundationplatform.mysql.vo.PoliceOffice;

import java.util.List;

/**
 * @author 台恩
 * @date 2018/05/07
 * @since 1.0
 */
public interface PoliceOfficeInfoService {

    public List<PoliceOfficeModel> selectPoliceOfficeInfo(PoliceOfficeModel record, int offset, int pageSize);

    public PoliceOfficeModel selectPoliceOfficeDetailInfo(Long policeOfficeId);

    public int addPoliceOfficeInfo(PoliceOfficeModel record);

    public int deletePoliceOfficeInfo(List<Long> list);

    public int updatePoliceOfficeInfo(PoliceOfficeModel record);

    long selectPoliceOfficeIdByName(String policeOfficeName);
}
