package com.shangguo.web.systemadmin;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shangguo.service.systemadmin.LoginInterceptor;

/**
 * @author JabnMai 2015-6-14
 */
@Controller
@RequestMapping("adminlogin")
public class LoginController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(String redirectURL, HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		// 把拦截前路径存下来，以便登入成功可以直接请求到登录前的页面
		view.addObject("redirectURL", redirectURL);
		view.setViewName("/login");
		System.out.println("login:" +redirectURL);
		return view;
	}
	
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submit(String userName, String password, String redirectURL,
            HttpServletRequest request) {
        //模拟登陆成功 用户admin 密码admin的用户

       /* if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)
                && userName.equals("admin") && password.equals("admin")) {*/
		
		if(userName.equals("admin") && password.equals("admin")){
            //当登陆成功是，将用户信息存放到session中去
			System.out.println("密码正确！");
            HttpSession session = request.getSession();
            session.setAttribute("isLogin", true);
            if (StringUtils.isNotBlank(redirectURL)) {
            	System.out.println(URLDecoder.decode(redirectURL));
                return "redirect:" + URLDecoder.decode(redirectURL);
            }
            return "/admin/main";
        } else {
            if (StringUtils.isNotBlank(redirectURL)) {
                return "redirect:/login.jsp" + URLDecoder.decode(redirectURL);
            }
            return "redirect:/login.jsp";
        }
    }
	
}
