package edu.xd.ridelab.foundationplatform.service.impl;

import edu.xd.ridelab.foundationplatform.mapperInterface.UserMapper;
import edu.xd.ridelab.foundationplatform.model.UserInfoModel;
import edu.xd.ridelab.foundationplatform.mysql.vo.User;
import edu.xd.ridelab.foundationplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserInfoModel> selectAllUserInfo() {
        List<UserInfoModel> result = userMapper.selectAllUserInfo();
        return result;
    }

    @Override
    public User getUserDetailInfo(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public int deleteUserByPrimaryKey(Long userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public User getUserByUserAccount(String userAccount) {
        return userMapper.selectByUserAccount(userAccount);
    }
}
