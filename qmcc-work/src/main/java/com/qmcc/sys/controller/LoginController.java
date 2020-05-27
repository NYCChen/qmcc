package com.qmcc.sys.controller;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.qmcc.sys.domain.Loginfo;
import com.qmcc.sys.service.LoginfoService;
import com.qmcc.sys.vo.UserVo;
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

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录前端控制器
 */
@RestController
@RequestMapping("login")
public class LoginController {

	@Autowired
	private LoginfoService loginfoService;

	/*
	微信小程序无验证码登录
	 */
	@RequestMapping("loginNoMa")
	public Map<String, Object> loginNoMa(UserVo userVo, HttpSession session) {
		Subject subject = SecurityUtils.getSubject();
		AuthenticationToken token = new UsernamePasswordToken(userVo.getLoginname(),userVo.getPwd());

		Map<String, Object> loginMap = new HashMap<>();
		try {
			subject.login(token);
			ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
			WebUtils.getSession().setAttribute("user", activerUser.getUser());

			// 记录登录日志
			Loginfo loginfo = new Loginfo();
			loginfo.setLoginname(activerUser.getUser().getName() + "-" + activerUser.getUser().getLoginname());
			loginfo.setLoginip(WebUtils.getRequest().getRemoteAddr());
			loginfo.setLogintime(new Date());
			loginfoService.save(loginfo);

			//return ResultObj.LOGIN_SUCCESS;

			loginMap.put("code",200);
			loginMap.put("msg","登陆成功");
			loginMap.put("username",activerUser.getUser().getName());
			return loginMap;
		} catch (AuthenticationException e) {
			e.printStackTrace();
			//return ResultObj.LOGIN_ERROR_PASS;

			loginMap.put("code",-1);
			loginMap.put("msg","登陆失败,用户名或密码不正确");
			//loginMap.put("username",activerUser.getUser().getName());
			return loginMap;
		}

	}

	@RequestMapping("login")
	public ResultObj login(UserVo userVo, String code, HttpSession session) {
		// 获取存储在session中的图片验证码的数据
		String stringCode = (String) session.getAttribute("code");
		if (code != null && code.equals(stringCode)) {
			Subject subject = SecurityUtils.getSubject();
			AuthenticationToken token = new UsernamePasswordToken(userVo.getLoginname(),userVo.getPwd());
			try {
				subject.login(token);
				ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
				WebUtils.getSession().setAttribute("user", activerUser.getUser());

				// 记录登录日志
				Loginfo loginfo = new Loginfo();
				loginfo.setLoginname(activerUser.getUser().getName() + "-" + activerUser.getUser().getLoginname());
				loginfo.setLoginip(WebUtils.getRequest().getRemoteAddr());
				loginfo.setLogintime(new Date());
				loginfoService.save(loginfo);

				return ResultObj.LOGIN_SUCCESS;
			} catch (AuthenticationException e) {
				e.printStackTrace();
				return ResultObj.LOGIN_ERROR_PASS;
			}
		}else {
			return ResultObj.LOGIN_ERROR_CODE;
		}

	}

	/**
	 * 得到登陆验证码
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	@RequestMapping("getCode")
	public void getCode(HttpServletResponse response, HttpSession session) throws IOException {
		//定义图形验证码的长和宽
		LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(116, 36,4,5);
		//System.out.println(lineCaptcha.getCode());
		session.setAttribute("code",lineCaptcha.getCode());
		try {
			ServletOutputStream outputStream = response.getOutputStream();
			lineCaptcha.write(outputStream);
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

