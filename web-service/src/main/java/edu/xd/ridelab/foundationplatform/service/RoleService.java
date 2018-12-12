package edu.xd.ridelab.foundationplatform.service;/**
 * Created by Administrator on 2018/5/19.
 */

import edu.xd.ridelab.foundationplatform.mysql.vo.Role;
import edu.xd.ridelab.foundationplatform.mysql.vo.RolePermissionRelation;

import java.util.List;

/**
 *@author xuziheng
 *@date 2018/5/19
 *@since 1.0
 */
public interface RoleService {

	public boolean roleExist(Role role);

	public List<Role> getRoles(Role role);

	public int addRole(Role role);

	public int deleteRoles(List<Long> roleIds);

	public int updateRole(Role role);

	public List<RolePermissionRelation> getRolePermission(Long roleId);

	public int addRolePermission(RolePermissionRelation rolePermissionRelation);

	public int deleteRolePermissionByRelationId(List<Long> relationIds);

	public int deleteRolePermissionByRoleId(List<Long> roleIds);

	long selectRoleIdByRoleName(String roleName);
}
