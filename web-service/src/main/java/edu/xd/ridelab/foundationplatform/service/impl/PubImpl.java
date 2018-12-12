package edu.xd.ridelab.foundationplatform.service.impl;

import edu.xd.ridelab.foundationplatform.mapperInterface.UserMapper;
import edu.xd.ridelab.foundationplatform.mysql.vo.User;
import edu.xd.ridelab.foundationplatform.service.PubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PubImpl implements PubService{

    @Autowired
    private UserMapper userMapper;

    /**
     * 验证登录
     * @param account
     * @param password
     * @return User
     */
    @Override
    public User userLoginCheck(String account , String password){
        System.out.println("account = " + account + ",password = " + password );
        return userMapper.selectByUserAccount(account);
    }
}
