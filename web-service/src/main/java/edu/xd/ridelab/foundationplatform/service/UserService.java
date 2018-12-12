package edu.xd.ridelab.foundationplatform.service;

import edu.xd.ridelab.foundationplatform.model.UserInfoModel;
import edu.xd.ridelab.foundationplatform.mysql.vo.User;

import java.util.List;

public interface UserService {
    List<UserInfoModel> selectAllUserInfo();

    User getUserDetailInfo(Long userId);

    int updateByPrimaryKeySelective(User user);

    int addUser(User user);

    int deleteUserByPrimaryKey(Long userId);

    User getUserByUserAccount(String userAccount);
}
