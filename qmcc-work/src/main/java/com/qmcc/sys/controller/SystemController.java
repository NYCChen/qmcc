package com.qmcc.sys.controller;

import com.qmcc.sys.common.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sys")
public class SystemController {

	/**
	 * 跳转到登陆页面
	 */
	@RequestMapping("toLogin")
	public String toLogin() {
		return "system/index/login";
	}

	/**
	 * 跳转到首页
	 */
	@RequestMapping("index")
	public String index() {
		return "system/index/index";
	}

	/**
	 * 退出然后跳转到登陆页面
	 * @return
	 */
	@RequestMapping("toSignOut")
	public String toSignOut(){
		//销毁session
		WebUtils.getSession().removeAttribute("user");
		return "system/index/login";
	}

	/**
	 * 跳转到个人资料页面
	 * @return
	 */
	@RequestMapping("toUserInfo")
	public String toUserInfo(){
		return "system/user/userInfo";
	}

	/**
	 * 跳转到修改密码页面
	 * @return
	 */
	@RequestMapping("toChangePassword")
	public String toChangePassword(){
		return "system/user/changePassword";
	}



	/**
	 * 跳转到工作台
	 */
	@RequestMapping("toDeskManager")
	public String toDeskManager(){
		return "system/index/deskManager";
	}

	/**
	 * 跳转到日志管理
	 */
	@RequestMapping("toLoginfoManager")
	public String toLoginfoManager(){
		return "system/loginfo/loginfoManager";
	}

	/**
	 * 跳转到公告管理
	 */
	@RequestMapping("toNoticeManager")
	public String toNoticeManager(){
		return "system/notice/noticeManager";
	}


/***********部门管理开始************/
	/**
	 * 跳转到部门管理
	 */
	@RequestMapping("toDeptManager")
	public String toDeptManager() {
		return "system/dept/deptManager";
	}

	/**
	 * 跳转到部门管理-left
	 */
	@RequestMapping("toDeptLeft")
	public String toDeptLeft() {
		return "system/dept/deptLeft";
	}


	/**
	 * 跳转到部门管理--right
	 */
	@RequestMapping("toDeptRight")
	public String toDeptRight() {
		return "system/dept/deptRight";
	}

	/***********部门管理结束************/


	/***********菜单管理开始************/
	/**
	 * 跳转到菜单管理
	 */
	@RequestMapping("toMenuManager")
	public String toMenuManager() {
		return "system/menu/menuManager";
	}

	/**
	 * 跳转到菜单管理-left
	 */
	@RequestMapping("toMenuLeft")
	public String toMenuLeft() {
		return "system/menu/menuLeft";
	}


	/**
	 * 跳转到菜单管理--right
	 */
	@RequestMapping("toMenuRight")
	public String toMenuRight() {
		return "system/menu/menuRight";
	}

	/***********菜单管理结束************/

	/***********权限管理开始************/
	/**
	 * 跳转到权限管理
	 */
	@RequestMapping("toPermissionManager")
	public String toPermissionManager() {
		return "system/permission/permissionManager";
	}

	/**
	 * 跳转到权限管理-left
	 */
	@RequestMapping("toPermissionLeft")
	public String toPermissionLeft() {
		return "system/permission/permissionLeft";
	}


	/**
	 * 跳转到权限管理--right
	 */
	@RequestMapping("toPermissionRight")
	public String toPermissionRight() {
		return "system/permission/permissionRight";
	}

	/***********权限管理结束************/

	/***********角色管理开始************/
	@RequestMapping("toRoleManager")
	public String toRoleManager(){
		return "system/role/roleManager";
	}
	/***********角色管理结束************/

	/***********用户管理开始************/
	@RequestMapping("toUserManager")
	public String toUserManager(){
		return "system/user/userManager";
	}
	/***********用户管理结束************/

	/***********缓存管理开始************/
	@RequestMapping("toCacheManager")
	public String toCacheManager(){
		return "system/cache/cacheManager";
	}
	/***********缓存管理结束************/
}
