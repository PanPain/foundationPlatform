package edu.xd.ridelab.foundationplatform.service.impl;/**
 * Created by Administrator on 2018/5/19.
 */

import edu.xd.ridelab.foundationplatform.mapperInterface.RoleMapper;
import edu.xd.ridelab.foundationplatform.mapperInterface.RolePermissionRelationMapper;
import edu.xd.ridelab.foundationplatform.mysql.vo.Role;
import edu.xd.ridelab.foundationplatform.mysql.vo.RolePermissionRelation;
import edu.xd.ridelab.foundationplatform.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *@author xuziheng
 *@date 2018/5/19
 *@since 1.0
 */
@Service
public class RoleImpl implements RoleService{

	@Autowired
	RoleMapper roleMapper;

	@Autowired
	RolePermissionRelationMapper rolePermissionRelationMapper;

	@Override
	public boolean roleExist(Role role) {

		return roleMapper.selectByConds(role).size()==0?false:true;
	}

	@Override
	public List<Role> getRoles(Role role) {

		return roleMapper.selectByConds(role);
	}

	@Override
	public int addRole(Role role) {

		return roleMapper.insert(role);
	}

	@Override
	public int deleteRoles(List<Long> roleIds) {

		return roleMapper.batchDeleteByPrimaryKey(roleIds);
	}

	@Override
	public int updateRole(Role role) {

		return roleMapper.updateByPrimaryKeySelective(role);
	}

	@Override
	public List<RolePermissionRelation> getRolePermission(Long roleId) {
		return rolePermissionRelationMapper.selectByRoleId(roleId);
	}

	@Override
	public int addRolePermission(RolePermissionRelation rolePermissionRelation) {

		return rolePermissionRelationMapper.insert(rolePermissionRelation);
	}

	@Override
	public int deleteRolePermissionByRelationId(List<Long> relationIds) {
		return rolePermissionRelationMapper.batchDeleteByRelationIds(relationIds);
	}

	@Override
	public int deleteRolePermissionByRoleId(List<Long> roleIds) {
		return rolePermissionRelationMapper.batchDeleteByRoleId(roleIds);
	}

	@Override
	public long selectRoleIdByRoleName(String roleName) {
		return roleMapper.selectRoleIdByRoleName(roleName);
	}
}
