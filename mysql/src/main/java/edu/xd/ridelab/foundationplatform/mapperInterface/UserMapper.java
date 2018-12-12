package edu.xd.ridelab.foundationplatform.mapperInterface;

import edu.xd.ridelab.foundationplatform.model.UserInfoModel;
import edu.xd.ridelab.foundationplatform.mysql.vo.User;

import java.util.List;


public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserAccount(String userAccount);

    List<UserInfoModel> selectAllUserInfo();

}