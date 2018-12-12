package edu.xd.ridelab.foundationplatform.mapperInterface;


import edu.xd.ridelab.foundationplatform.mysql.vo.UserRoleRelation;
import org.apache.ibatis.annotations.Param;


public interface UserRoleRelationMapper {
    int deleteByPrimaryKey(Long relationId);

    int insert(UserRoleRelation record);

    int insertSelective(UserRoleRelation record);

    UserRoleRelation selectByPrimaryKey(Long relationId);

    int updateByPrimaryKeySelective(UserRoleRelation record);

    int updateByPrimaryKey(UserRoleRelation record);

    UserRoleRelation selectByUserId(@Param("userId") Long userId);
}