package com.shangguo.web.user;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shangguo.dao.base.QueryResult;
import com.shangguo.model.user.T_user;
import com.shangguo.service.user.UserInfoService;
import com.shangguo.util.MyJsonUtil;
import com.shangguo.util.MyUtil;


/**
 * 用户增删改查
 * @author JabnMai
 * @date 2015年7月24日
 * 
 */
@Controller
@RequestMapping(value = "/backadmin/admin")
public class UserInfoController {
	/**
	 * 显示用户信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/user_show")
    public void userInfoShow(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 设置请求的字符编码
				request.setCharacterEncoding("UTF-8");
				// 设置返回请求的字符编码
				response.setCharacterEncoding("UTF-8");
				String page = request.getParameter("page");
				String rows = request.getParameter("rows");
				int p = MyUtil.isNotEmpty(page) ? Integer.parseInt(page) : 0;
				int r = MyUtil.isNotEmpty(rows) ? Integer.parseInt(rows) : 0;
				//调用服务
				UserInfoService userInfoService = new UserInfoService();
				QueryResult<T_user> userResult = userInfoService.queryUserInfo(p, r);
				String result = MyJsonUtil.QueryResulttoJson(userResult);
				int time = (Integer)MyJsonUtil.getValue(result, "rows", "join_time", "time");
				System.out.println("time################################"+time);
				System.out.println(result);
				response.getWriter().print(result);
				
				
    }
}
