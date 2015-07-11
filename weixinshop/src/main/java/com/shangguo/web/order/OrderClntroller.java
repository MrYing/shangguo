package com.shangguo.web.order;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shangguo.dao.base.QueryResult;
import com.shangguo.model.goods.T_goods_gategory;
import com.shangguo.model.order.T_order;
import com.shangguo.service.goods.GategoryService;
import com.shangguo.service.order.OrderService;
import com.shangguo.util.MyJsonUtil;
import com.shangguo.util.MyUtil;

/**
 * 商品类别的增删改查处理器
 * 
 * @author JasonLin
 * 
 */
@Controller
@RequestMapping(value = "/admin")
public class OrderClntroller {
	@RequestMapping(value = "/order_search.do")
	public void search(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		// 设置请求的字符编码
		request.setCharacterEncoding("UTF-8");
		// 设置返回请求的字符编码
		response.setCharacterEncoding("UTF-8");

		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		int p = MyUtil.isNotEmpty(page) ? Integer.parseInt(page) : 0;
		int r = MyUtil.isNotEmpty(rows) ? Integer.parseInt(rows) : 0;

		String order_id = request.getParameter("order_id");
		String user_id = request.getParameter("user_id");

		int orderId = MyUtil.isNotEmpty(order_id) ? Integer.parseInt(order_id)
				: -999;
		int userId = MyUtil.isNotEmpty(user_id) ? Integer.parseInt(user_id)
				: -999;

		// 调用服务
		OrderService service = new OrderService();
		QueryResult<T_order> result = service.findBynameByPage(p, r, orderId,
				userId);

		String json = MyJsonUtil.QueryResulttoJson(result);
		System.out.println(json);
		response.getWriter().print(json);

	}

	@RequestMapping(value = "/order_findListByOrderId.do")
	public void findListByOrderId(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		// 设置请求的字符编码
		request.setCharacterEncoding("UTF-8");
		// 设置返回请求的字符编码
		response.setCharacterEncoding("UTF-8");

		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		int p = MyUtil.isNotEmpty(page) ? Integer.parseInt(page) : 0;
		int r = MyUtil.isNotEmpty(rows) ? Integer.parseInt(rows) : 0;

		String order_id = request.getParameter("order_id");
		String user_id = request.getParameter("user_id");

		int orderId = MyUtil.isNotEmpty(order_id) ? Integer.parseInt(order_id)
				: -999;
		int userId = MyUtil.isNotEmpty(user_id) ? Integer.parseInt(user_id)
				: -999;

		// 调用服务
		OrderService service = new OrderService();
		QueryResult<T_order> result = service.findBynameByPage(p, r, orderId,
				userId);

		String json = MyJsonUtil.QueryResulttoJson(result);
		System.out.println(json);
		response.getWriter().print(json);

	}
}
