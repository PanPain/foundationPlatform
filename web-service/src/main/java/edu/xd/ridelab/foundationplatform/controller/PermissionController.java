package edu.xd.ridelab.foundationplatform.controller;/**
 * Created by Administrator on 2018/5/20.
 */

import edu.xd.ridelab.foundationplatform.controller.response.ResponseResult;
import edu.xd.ridelab.foundationplatform.mysql.vo.Permission;
import edu.xd.ridelab.foundationplatform.mysql.vo.RolePermissionRelation;
import edu.xd.ridelab.foundationplatform.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 *@author xuziheng
 *@date 2018/5/20
 *@since 1.0
 */
@Controller
public class PermissionController {

	@Autowired
	PermissionService permissionService;

	/** 多条件查询权限
	* @author xuziheng
	* @date 2018/5/20
	* @return edu.xd.ridelab.foundationplatform.controller.response.ResponseResult
	* @throws
	* @since 1.0
	*/
	@GetMapping(value = "/permission/searchPermission")
	@ResponseBody
	public ResponseResult searchPermission(
			@RequestParam(required = false) Long permissionId, @RequestParam(required = false) String permissionUrl,
			@RequestParam(required = false) String permissionDesc){
		ResponseResult responseResult = new ResponseResult();
		try {
			Permission permission = new Permission();
			permission.setPermissionId(permissionId);
			permission.setPermissionUrl(permissionUrl);
			permission.setPermissionDesc(permissionDesc);
			List<Permission> permissionList = permissionService.searchPermission(permission);
			responseResult.setData(permissionList);
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


	/**向权限表添加权限
	* @author xuziheng
	* @date 2018/5/20
	* @return edu.xd.ridelab.foundationplatform.controller.response.ResponseResult
	* @throws
	* @since 1.0
	*/
	@PostMapping(value = "/permission/addPermission")
	@ResponseBody
	public ResponseResult addPermission(@RequestBody Permission permission){
		ResponseResult responseResult = new ResponseResult();
		try {
			Date date = new Date(System.currentTimeMillis());
			permission.setCreateTime(date);
			permission.setModifyTime(date);
			int num  = permissionService.addPermission(permission);
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


	/** 删除权限表中的权限
	* @author xuziheng
	* @date 2018/5/20
	* @return edu.xd.ridelab.foundationplatform.controller.response.ResponseResult
	* @throws
	* @since 1.0
	*/
	@DeleteMapping(value = "/permission/delPermission")
	@ResponseBody
	public ResponseResult deletePermission(@RequestBody List<Long> permissionIds){
		ResponseResult responseResult = new ResponseResult();
		try {
			int num  = permissionService.deletePermissionByPermissionId(permissionIds);
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


	/**更新权限表,单条
	* @author xuziheng
	* @date 2018/5/20
	* @return edu.xd.ridelab.foundationplatform.controller.response.ResponseResult
	* @throws
	* @since 1.0
	*/
	@PostMapping(value = "/permission/updatePermission")
	@ResponseBody
	public ResponseResult updatePermission(@RequestBody Permission permission){
		ResponseResult responseResult = new ResponseResult();
		try {
			Date date = new Date(System.currentTimeMillis());
			permission.setModifyTime(date);
			int num  = permissionService.updatePermission(permission);
			responseResult.setCode("success");
			responseResult.setSuccess(true);
			responseResult.setMessage("成功更新"+num+"条权限");
		}catch (Exception e){
			responseResult.setCode("failure");
			responseResult.setSuccess(false);
			responseResult.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return responseResult;
	}
}
