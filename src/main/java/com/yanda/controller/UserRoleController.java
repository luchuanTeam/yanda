package com.yanda.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yanda.entity.JsonResult;
import com.yanda.entity.generated.RoleInfo;
import com.yanda.service.UserRoleService;

@RestController
@RequestMapping(value = "/role")
public class UserRoleController extends BaseController {
	
	@Autowired
	private UserRoleService userRoleService;
	
	/**
	 * 根据用户ID获取用户角色列表
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public JsonResult getRoleByUserId(HttpServletRequest request, @PathVariable int id) {
		if (id == 0)
			return result(-1, "用户id为空");
		try {
			List<RoleInfo> roles = userRoleService.findUserRoleByUserId(id);
			return result(200, "", roles);
		} catch (Exception e) {
			return result(-1, "查询失败");
		}
	}
	
	/**
	 * 获取角色列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JsonResult getRoleByUserId(HttpServletRequest request) {

		try {
			List<RoleInfo> roles = userRoleService.list();
			return result(200, "", roles);
		} catch (Exception e) {
			return result(-1, "查询失败");
		}
	}
	
	/**
	 * 根据用户ID，角色ID列表，更新用户角色
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/user/update/{userId}", method = RequestMethod.POST)
	public JsonResult updateUserRole(HttpServletRequest request, @PathVariable int userId) {
		if (userId == 0)
			return result(-1, "用户id为空");
		String roleIds = getNotEmptyValue(request, "roleIds");
		try {
			userRoleService.updateUserRoles(userId, roleIds);
			return result(200, "");
		} catch (Exception e) {
			return result(-1, "更新失败");
		}
	}
	
}
