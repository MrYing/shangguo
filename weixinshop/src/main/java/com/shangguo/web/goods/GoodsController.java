package com.shangguo.web.goods;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangguo.dao.base.QueryResult;
import com.shangguo.model.goods.T_GOODS;
import com.shangguo.service.goods.GoodsService;
import com.shangguo.util.MyJsonUtil;
import com.shangguo.util.MyUtil;

@Controller
@RequestMapping(value = "/admin")
public class GoodsController {
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "/goods_search")
	public String helloWorld(HttpServletRequest request,
			HttpServletResponse Response) {
		Response.setCharacterEncoding("UTF-8");
		String goods_name = request.getParameter("goods_name");
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");

		int p = MyUtil.isNotEmpty(page) ? Integer.parseInt(page) : 0;
		int r = MyUtil.isNotEmpty(rows) ? Integer.parseInt(rows) : 0;

		GoodsService service = new GoodsService();
		QueryResult<T_GOODS> goodsResult = service.findBynameByPage(p, r,
				goods_name);
		MyJsonUtil jUtil = new MyJsonUtil();
		String dd = jUtil.QueryResulttoJson(goodsResult);
		System.out.println(dd);

		return dd;

	}
	/*
	 * public String showForm(HttpServletRequest request) { String a =
	 * request.getParameter("goods_name"); System.out.println("入参goods_name：" +
	 * a); GoodsService service = new GoodsService();
	 * System.out.println("===============GET"); List<T_GOODS> ll =
	 * service.getall(); // String[] arr = {"asd","dfgd","asd","234"}; JSONArray
	 * jsonarray = JSONArray.fromObject(ll); System.out.println(jsonarray);
	 * 
	 * // return ll; String ss = " { \"total\":22," + " \"rows\":[   " +
	 * " {\"goods_name\":\"001\",\"goods_id\":\"111\"},    " +
	 * "  {\"goods_name\":\"022\",\"goods_id\":\"221\"} ]   }   ";
	 * System.out.println(ss);
	 * 
	 * return ss;
	 * 
	 * }
	 */
}
