package com.qmcc.sys.controller;


import com.qmcc.sys.domain.Loginfo;
import com.qmcc.sys.service.LoginfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qmcc.sys.common.ActiverUser;
import com.qmcc.sys.common.ResultObj;
import com.qmcc.sys.common.WebUtils;

import java.util.Date;

/**
 * 登录前端控制器
 */
@RestController
@RequestMapping("login")
public class LoginController {

	@Autowired
	private LoginfoService loginfoService;

	@RequestMapping("login")
	public ResultObj login(String loginname,String pwd) {
		Subject subject = SecurityUtils.getSubject();
		AuthenticationToken token=new UsernamePasswordToken(loginname, pwd);
		try {
			subject.login(token);
			ActiverUser activerUser=(ActiverUser) subject.getPrincipal();
			WebUtils.getSession().setAttribute("user", activerUser.getUser());

			// 记录登录日志
			Loginfo loginfo = new Loginfo();
			loginfo.setLoginname(activerUser.getUser().getName()+"-"+activerUser.getUser().getLoginname());
			loginfo.setLoginip(WebUtils.getRequest().getRemoteAddr());
			loginfo.setLogintime(new Date());
			loginfoService.save(loginfo);

			return ResultObj.LOGIN_SUCCESS;
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return ResultObj.LOGIN_ERROR_PASS;
		}
	}
}

