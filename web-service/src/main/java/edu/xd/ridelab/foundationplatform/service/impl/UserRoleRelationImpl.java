package edu.xd.ridelab.foundationplatform.service.impl;

import edu.xd.ridelab.foundationplatform.mapperInterface.UserRoleRelationMapper;
import edu.xd.ridelab.foundationplatform.mysql.vo.UserRoleRelation;
import edu.xd.ridelab.foundationplatform.service.UserRoleRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleRelationImpl implements UserRoleRelationService{

    @Autowired
    UserRoleRelationMapper userRoleRelationMapper;


    @Override
    public UserRoleRelation selectByUserId(Long userId) {
        return userRoleRelationMapper.selectByUserId(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(UserRoleRelation userRoleRelation) {
        return userRoleRelationMapper.updateByPrimaryKeySelective(userRoleRelation);
    }

    @Override
    public int addUserRoleRelation(UserRoleRelation userRoleRelation) {
        return userRoleRelationMapper.insertSelective(userRoleRelation);
    }

    @Override
    public int deleteByPrimaryKey(Long relationId) {
        return userRoleRelationMapper.deleteByPrimaryKey(relationId);
    }
}
