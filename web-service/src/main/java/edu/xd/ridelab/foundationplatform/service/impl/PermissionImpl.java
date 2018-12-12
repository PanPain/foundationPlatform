package edu.xd.ridelab.foundationplatform.service.impl;/**
 * Created by Administrator on 2018/5/20.
 */

import edu.xd.ridelab.foundationplatform.mapperInterface.PermissionMapper;
import edu.xd.ridelab.foundationplatform.mysql.vo.Permission;
import edu.xd.ridelab.foundationplatform.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *@author xuziheng
 *@date 2018/5/20
 *@since 1.0
 */
@Service
public class PermissionImpl implements PermissionService{

	@Autowired
	PermissionMapper permissionMapper;

	@Override
	public List<Permission> searchPermission(Permission permission) {

		return permissionMapper.selectByConds(permission);
	}

	@Override
	public int addPermission(Permission permission) {
		return permissionMapper.insert(permission);
	}

	@Override
	public int deletePermissionByPermissionId(List<Long> permissionIds) {
		return permissionMapper.deleteByPermissionIds(permissionIds);
	}

	@Override
	public int updatePermission(Permission permission) {
		return permissionMapper.updateByPrimaryKey(permission);
	}
}
