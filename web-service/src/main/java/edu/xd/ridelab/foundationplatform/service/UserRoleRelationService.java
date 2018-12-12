package edu.xd.ridelab.foundationplatform.service;

import edu.xd.ridelab.foundationplatform.mysql.vo.UserRoleRelation;

public interface UserRoleRelationService {
    UserRoleRelation selectByUserId(Long userId);

    int updateByPrimaryKeySelective(UserRoleRelation userRoleRelation);

    int addUserRoleRelation(UserRoleRelation userRoleRelation);

    int deleteByPrimaryKey(Long relationId);
}
