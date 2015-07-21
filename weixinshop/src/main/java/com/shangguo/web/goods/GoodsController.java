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
import com.shangguo.service.goods.GoodsService;
import com.shangguo.util.MyJsonUtil;
import com.shangguo.util.MyUploadUtil;
import com.shangguo.util.MyUploadUtil.State;
import com.shangguo.util.MyUtil;
import com.shangguo.util.UploadType;

/**
 * 商品的增删改查处理器
 * 
 * @author JasonLin
 * 
 */
@Controller
@RequestMapping(value = "/backadmin/admin")
public class GoodsController {
	/**
	 * 查询方法
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/goods_search")
	public void goods_search(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		// 设置请求的字符编码
		request.setCharacterEncoding("UTF-8");
		// 设置返回请求的字符编码
		response.setCharacterEncoding("UTF-8");
		String goods_name = request.getParameter("goods_name");
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		int p = MyUtil.isNotEmpty(page) ? Integer.parseInt(page) : 0;
		int r = MyUtil.isNotEmpty(rows) ? Integer.parseInt(rows) : 0;

		// 调用服务
		GoodsService service = new GoodsService();
		QueryResult<T_GOODS> goodsResult = service.findBynameByPage(p, r,
				goods_name);

		String dd = MyJsonUtil.QueryResulttoJson(goodsResult);
		System.out.println(dd);

		response.getWriter().print(dd);

	}

	/**
	 * 保存方法
	 * 
	 * @param request
	 * @param response
	 * @throws FileUploadException
	 * @throws IOException
	 */
	@RequestMapping(value = "/goods_save")
	public void goods_save(HttpServletRequest request,
			HttpServletResponse response) throws FileUploadException,
			IOException {

		// 设置请求的字符编码
		request.setCharacterEncoding("UTF-8");
		// 设置返回请求的字符编码
		response.setCharacterEncoding("UTF-8");
		MyUploadUtil uploadUtil = new MyUploadUtil();
		UploadType uploadType = uploadUtil.doUpload(request, new T_GOODS());
		State state = uploadType.getState();
		List<T_GOODS> goodsList = uploadType.getList();
		Map<String, Object> result = new HashMap<String, Object>();
		if (state.getCode() != 200) {
			// 上传文件错误
			String error = state.getMessage();
			result.put("error", error);
		} else {
			// 调用服务
			GoodsService service = new GoodsService();
			service.save(goodsList.get(0));

			result.put("success", true);
		}
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
	@RequestMapping(value = "/goods_update")
	public void goods_update(HttpServletRequest request,
			HttpServletResponse response) throws FileUploadException,
			IOException {

		// 设置请求的字符编码
		request.setCharacterEncoding("UTF-8");
		// 设置返回请求的字符编码
		response.setCharacterEncoding("UTF-8");
		MyUploadUtil uploadUtil = new MyUploadUtil();
		UploadType uploadType = uploadUtil.doUpload(request, new T_GOODS());
		State state = uploadType.getState();
		List<T_GOODS> goodsList = uploadType.getList();
		Map<String, Object> result = new HashMap<String, Object>();
		if (state.getCode() != 200) {
			// 上传文件错误
			String error = state.getMessage();
			result.put("error", error);
		} else {
			// 调用服务
			GoodsService service = new GoodsService();
			service.update(goodsList.get(0));

			result.put("success", true);
		}
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
	@RequestMapping(value = "/goods_delete")
	public void goods_delete(HttpServletRequest request,
			HttpServletResponse response) throws FileUploadException,
			IOException {

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

		try {
			// 调用服务
			GoodsService service = new GoodsService();
			service.batchDeleteById(ids);
			result.put("success", true);
		} catch (Exception e) {
			result.put("error", e.toString());
		}
		String json = JSONArray.fromObject(result).toString();
		response.getWriter().print(json);

	}
}
