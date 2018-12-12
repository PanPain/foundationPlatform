package edu.xd.ridelab.foundationplatform.service;/**
 * Created by Administrator on 2018/5/20.
 */

import edu.xd.ridelab.foundationplatform.mysql.vo.Permission;

import java.util.List;

/**
 *@author xuziheng
 *@date 2018/5/20
 *@since 1.0
 */
public interface PermissionService {
	List<Permission> searchPermission(Permission permission);

	int  addPermission(Permission permission);

	int deletePermissionByPermissionId(List<Long> permissionIds);

	int updatePermission(Permission permission);
}
