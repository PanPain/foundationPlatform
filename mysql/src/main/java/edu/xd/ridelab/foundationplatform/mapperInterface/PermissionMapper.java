package edu.xd.ridelab.foundationplatform.mapperInterface;


import edu.xd.ridelab.foundationplatform.mysql.vo.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface PermissionMapper {
    int deleteByPrimaryKey(Long permissionId);

    int deleteByPermissionIds(List<Long> permissionId);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Long permissionId);

    List<Permission> selectByConds(Permission permission);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    Permission checkUserHasPermission(@Param("userId") Long userId ,@Param("permissionUrl") String requestURL);

    Long getPermissionIDByPermissionName(@Param("permissionName") String permissionName);
}