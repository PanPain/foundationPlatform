package edu.xd.ridelab.foundationplatform.mapperInterface;


import edu.xd.ridelab.foundationplatform.mysql.vo.RolePermissionRelation;

import java.util.List;


public interface RolePermissionRelationMapper {
    int deleteByPrimaryKey(Long relationId);

    int insert(RolePermissionRelation record);

    int insertSelective(RolePermissionRelation record);

    RolePermissionRelation selectByPrimaryKey(Long relationId);

    int updateByPrimaryKeySelective(RolePermissionRelation record);

    int updateByPrimaryKey(RolePermissionRelation record);

    List<RolePermissionRelation> selectByRoleId(Long roleId);

    int batchDeleteByRoleId(List<Long> roleIds);

    int batchDeleteByRelationIds(List<Long> realtionIds);
}