package edu.xd.ridelab.foundationplatform.controller;/**
 * Created by Administrator on 2018/5/19.
 */

import edu.xd.ridelab.foundationplatform.controller.response.ResponseResult;
import edu.xd.ridelab.foundationplatform.mysql.vo.Role;
import edu.xd.ridelab.foundationplatform.mysql.vo.RolePermissionRelation;
import edu.xd.ridelab.foundationplatform.mysql.vo.Rule;
import edu.xd.ridelab.foundationplatform.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 角色管理
 *@author xuziheng
 *@date 2018/5/19
 *@since 1.0
 */
@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;

	/**根据条件查询角色
	 * @author xuziheng
	 * @date 2018/5/19
	 * @return edu.xd.ridelab.foundationplatform.controller.response.ResponseResult
	 * @throws
	 * @since 1.0
	 */
	@GetMapping(value = "/role/getRole")
	@ResponseBody
	public ResponseResult getRole(
			@RequestParam(required = false) Long roleId, @RequestParam(required = false) String roleName,
			@RequestParam(required = false) String roleDesc){

		ResponseResult responseResult = new ResponseResult();

		try {
			Role role = new Role();
			role.setRoleId(roleId);
			role.setRoleName(roleName);
			role.setRoleDesc(roleDesc);

			List<Role> roles= roleService.getRoles(role);
			responseResult.setData(roles);
			responseResult.setCode("success");
			responseResult.setSuccess(true);
			responseResult.setMessage("查询成功");
		}catch (Exception e){
			responseResult.setCode("failure");
			responseResult.setSuccess(false);
			responseResult.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return responseResult;
	}


	/**添加角色
	* @author xuziheng
	* @date 2018/5/19
	* @return edu.xd.ridelab.foundationplatform.controller.response.ResponseResult
	* @throws
	* @since 1.0
	*/
	@PostMapping(value = "/role/addRole")
	@ResponseBody
	public ResponseResult addRole(@RequestBody Role role){
		ResponseResult responseResult = new ResponseResult();
//		Role roleCopy= new Role();
//		roleCopy.setRoleName(role.getRoleName());
//		if(roleService.roleExist(roleCopy)){
//			responseResult.setCode("failure");
//			responseResult.setSuccess(false);
//			responseResult.setMessage("用户名已存在");
//			return responseResult;
//		}
		try {
			Date date = new Date(System.currentTimeMillis());
			role.setRoleId(null);
			role.setCreateTime(date);
			role.setModifyTime(date);
			int num = roleService.addRole(role);
			responseResult.setCode("success");
			responseResult.setSuccess(true);
			responseResult.setMessage("成功插入"+num+"条");
		}catch (Exception e){
			responseResult.setCode("failure");
			responseResult.setSuccess(false);
			responseResult.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return responseResult;
	}
	/**删除角色
	* @author xuziheng
	* @date 2018/5/19
	* @return edu.xd.ridelab.foundationplatform.controller.response.ResponseResult
	* @throws
	* @since 1.0
	*/
	@DeleteMapping(value = "/role/deleteRole")
	@ResponseBody
	public ResponseResult deleteRole(@RequestBody List<Long> roleIds){
		ResponseResult responseResult = new ResponseResult();
		try {
			int num = roleService.deleteRoles(roleIds);
			int num2 = roleService.deleteRolePermissionByRoleId(roleIds);
			responseResult.setCode("success");
			responseResult.setSuccess(true);
			responseResult.setMessage("成功删除"+num+"条角色信息,"+num2+"条角色权限关联信息");
		}catch (Exception e){
			responseResult.setCode("failure");
			responseResult.setSuccess(false);
			responseResult.setMessage(e.getMessage());
			e.printStackTrace();
		}

		return responseResult;
	}



	/** 更新角色
	* @author xuziheng
	* @date 2018/5/19
	* @return edu.xd.ridelab.foundationplatform.controller.response.ResponseResult
	* @throws
	* @since 1.0
	*/
	@PostMapping(value = "/role/updateRole")
	@ResponseBody
	public ResponseResult updateRole(@RequestBody Role role){
		ResponseResult responseResult = new ResponseResult();
		try {
			Date date = new Date(System.currentTimeMillis());
			role.setModifyTime(date);
			int num = roleService.updateRole(role);
			responseResult.setCode("success");
			responseResult.setSuccess(true);
			responseResult.setMessage("成功更新"+num+"条");
		}catch (Exception e){
			responseResult.setCode("failure");
			responseResult.setSuccess(false);
			responseResult.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return responseResult;
	}

	/** 获取角色的权限
	 * @author xuziheng
	 * @date 2018/5/19
	 * @return edu.xd.ridelab.foundationplatform.controller.response.ResponseResult
	 * @throws
	 * @since 1.0
	 */
	@GetMapping(value = "/role/getRolePermission")
	@ResponseBody
	public ResponseResult getRolePermission(@RequestParam Long roleId){
		ResponseResult responseResult = new ResponseResult();
		try {
			List<RolePermissionRelation> permissionList= roleService.getRolePermission(roleId);
			responseResult.setData(permissionList);
			responseResult.setCode("success");
			responseResult.setSuccess(true);
			responseResult.setMessage("成功查询"+permissionList.size()+"条");
		}catch (Exception e){
			responseResult.setCode("failure");
			responseResult.setSuccess(false);
			responseResult.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return responseResult;
	}

	/** 为指定角色添加权限
	* @author xuziheng
	* @date 2018/5/19
	* @return edu.xd.ridelab.foundationplatform.controller.response.ResponseResult
	* @throws
	* @since 1.0
	*/
	@PostMapping(value = "/role/addRolePermission")
	@ResponseBody
	public ResponseResult addRolePermission(@RequestBody RolePermissionRelation rolePermissionRelation){
		ResponseResult responseResult = new ResponseResult();
		try {
			Date date = new Date(System.currentTimeMillis());
			rolePermissionRelation.setCreateTime(date);
			int  num = roleService.addRolePermission(rolePermissionRelation);
			responseResult.setCode("success");
			responseResult.setSuccess(true);
			responseResult.setMessage("成功添加权限");
		}catch (Exception e){
			responseResult.setCode("failure");
			responseResult.setSuccess(false);
			responseResult.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return responseResult;
	}

	/**删除角色的权限
	 * @author xuziheng
	 * @date 2018/5/19
	 * @return edu.xd.ridelab.foundationplatform.controller.response.ResponseResult
	 * @throws
	 * @since 1.0
	 */
	@DeleteMapping(value = "/role/delRolePermission")
	@ResponseBody
	public ResponseResult deleteRolePermission(@RequestBody List<Long> relationId){
		ResponseResult responseResult = new ResponseResult();
		try {
			int  num = roleService.deleteRolePermissionByRelationId(relationId);
			responseResult.setCode("success");
			responseResult.setSuccess(true);
			responseResult.setMessage("成功删除"+num+"条权限");
		}catch (Exception e){
			responseResult.setCode("failure");
			responseResult.setSuccess(false);
			responseResult.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return responseResult;
	}

}
