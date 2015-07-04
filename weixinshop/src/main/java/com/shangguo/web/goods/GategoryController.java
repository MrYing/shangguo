package com.shangguo.web.goods;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shangguo.dao.base.QueryResult;
import com.shangguo.model.goods.T_GOODS;
import com.shangguo.model.goods.T_goods_gategory;
import com.shangguo.service.goods.GategoryService;
import com.shangguo.service.goods.GoodsService;
import com.shangguo.util.DateUtil;
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
public class GategoryController {

	/**
	 * 查询方法
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/goodsGategory_search.do")
	public void search(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		// 设置请求的字符编码
		request.setCharacterEncoding("UTF-8");
		// 设置返回请求的字符编码
		response.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String drop = request.getParameter("drop");
		int p = MyUtil.isNotEmpty(page) ? Integer.parseInt(page) : 0;
		int r = MyUtil.isNotEmpty(rows) ? Integer.parseInt(rows) : 0;

		// 调用服务
		GategoryService service = new GategoryService();
		QueryResult<T_goods_gategory> gategoryResult = service
				.findBynameByPage(p, r, name);
		if ("true".equals(drop)) {
			String json = MyJsonUtil.toJson(gategoryResult.getList());
			System.out.println(json);
			response.getWriter().print(json);
		} else {
			String json = MyJsonUtil.QueryResulttoJson(gategoryResult);
			System.out.println(json);
			response.getWriter().print(json);
		}

	}

	/**
	 * 保存方法
	 * 
	 * @param request
	 * @param response
	 * @throws FileUploadException
	 * @throws IOException
	 */
	@RequestMapping(value = "/goodsGategory_save")
	public void save(HttpServletRequest request, HttpServletResponse response)
			throws FileUploadException, IOException {

		// 设置请求的字符编码
		request.setCharacterEncoding("UTF-8");
		// 设置返回请求的字符编码
		response.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String introduce = request.getParameter("introduce");

		T_goods_gategory gategory = new T_goods_gategory();
		gategory.setCreat_time(DateUtil.getDateTime());
		gategory.setIntroduce(introduce);
		gategory.setName(name);
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			// 调用服务
			GategoryService service = new GategoryService();
			service.save(gategory);
			result.put("success", true);
		} catch (Exception e) {
			result.put("error", e.toString());
		}

		//
		String json = JSONArray.fromObject(result).toString();
		response.getWriter().print(json);

	}

	/**
	 * 更新方法
	 * 
	 * @param request
	 * @param response
	 * @throws FileUploadException
	 * @throws IOException
	 */
	@RequestMapping(value = "/goodsGategory_update")
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws FileUploadException, IOException {

		// 设置请求的字符编码
		request.setCharacterEncoding("UTF-8");
		// 设置返回请求的字符编码
		response.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String introduce = request.getParameter("introduce");
		String gategory_id = request.getParameter("gategory_id");
		int id = MyUtil.isNotEmpty(gategory_id) ? Integer.parseInt(gategory_id)
				: 0;

		T_goods_gategory gategory = new T_goods_gategory();
		gategory.setGategory_id(id);
		gategory.setCreat_time(DateUtil.getDateTime());
		gategory.setIntroduce(introduce);
		gategory.setName(name);

		Map<String, Object> result = new HashMap<String, Object>();

		try {
			// 调用服务
			GategoryService service = new GategoryService();
			service.update(gategory);
			result.put("success", true);
		} catch (Exception e) {
			result.put("error", e.toString());
		}

		//
		String json = JSONArray.fromObject(result).toString();
		response.getWriter().print(json);

	}

	/**
	 * 删除方法
	 * 
	 * @param request
	 * @param response
	 * @throws FileUploadException
	 * @throws IOException
	 */
	@RequestMapping(value = "/goodsGategory_delete")
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws FileUploadException, IOException {

		// 设置请求的字符编码
		request.setCharacterEncoding("UTF-8");
		// 设置返回请求的字符编码
		response.setCharacterEncoding("UTF-8");
		String[] idArray = request.getParameter("ids").trim().split(",");
		int length = idArray.length;
		int[] ids = new int[length];
		for (int i = 0; i < length; i++) {
			ids[i] = Integer.parseInt(idArray[i]);
		}

		Map<String, Object> result = new HashMap<String, Object>();
		String existId = this.existGoods(ids);
		if (MyUtil.isNotEmpty(existId)) {
			result.put("exist", true);
			result.put("existId", existId);
		} else {
			try {
				// 调用服务
				GategoryService service = new GategoryService();
				service.batchDeleteById(ids);
				result.put("success", true);
			} catch (Exception e) {
				result.put("error", e.toString());
			}
		}
		String json = JSONArray.fromObject(result).toString();
		response.getWriter().print(json);
	}

	private String existGoods(int[] gategory_ids) {
		// 调用服务
		GoodsService service = new GoodsService();
		List<T_GOODS> goodsList = service.findByGategoryid(gategory_ids);
		int length = goodsList.size();
		StringBuilder existId = new StringBuilder();
		if (length > 0) {
			for (int i = 0; i < length; i++) {
				existId.append(goodsList.get(i).getGategory_id() + ",");
			}
			existId = existId.deleteCharAt(existId.length() - 1);

		}
		return existId.toString();
	}

}
