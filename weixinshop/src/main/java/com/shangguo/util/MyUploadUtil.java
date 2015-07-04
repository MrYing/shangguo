package com.shangguo.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.NoPermissionException;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyUploadUtil {
	/** 日志对象 */
	private Log logger = LogFactory.getLog(this.getClass());

	/** 上传目录名 */
	private static final String uploadFolderName = "shangguoUploadFiles";

	/** 使用临时文件的临界值（为5M */
	private static final int tempFileMaxSize = 5242880;

	/** 上传临时文件存储目录 */
	private static final String tempFolderName = "shangguoTempFiles";

	/** 上传文件最大为30M */
	private static final Long fileMaxSize = 30000000L;

	/** 允许上传的扩展名 */
	private static final String[] extensionPermit = { "jpg", "png" };

	/** 统一的编码格式 */
	private static final String encode = "UTF-8";

	@SuppressWarnings("deprecation")
	public <T> UploadType doUpload(HttpServletRequest request, T data)
			throws FileUploadException, UnsupportedEncodingException {

		logger.info("UploadFileServlet#doPost() start");
		/** 返回状态 */
		State state = State.OK;
		List dataList = new ArrayList();
		try {

			String curProjectPath = request.getRealPath("/");
			String SSS = request.getRequestURL().toString();
			String saveDirectoryPath = curProjectPath + uploadFolderName;
			String tempDirectoryPath = curProjectPath + tempFolderName;
			File saveDirectory = new File(saveDirectoryPath);
			File tempDirectory = new File(tempDirectoryPath);
			// 上传临时文件的默认目录为java.io.tmpdir中保存的路径，根据操作系统的不同会有区别
			if (!tempDirectory.exists()) {
				tempDirectory.mkdir();
			}
			if (!saveDirectory.exists()) {
				saveDirectory.mkdir();
			}
			logger.debug("Project real path ["
					+ saveDirectory.getAbsolutePath() + "]");
			// 上传时产生的临时文件的默认保存目录
			logger.debug("Temp files default save path ["
					+ System.getProperty("java.io.tmpdir") + "]");

			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 当上传文件太大时，因为虚拟机能使用的内存是有限的，所以此时要通过临时文件来实现上传文件的保存
			// 此方法是设置是否使用临时文件的临界值（单位：字节）
			factory.setSizeThreshold(tempFileMaxSize);
			// 与上一个结合使用，设置临时文件的路径（绝对路径）
			factory.setRepository(tempDirectory);
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 设置上传内容的大小限制（单位：字节）
			upload.setSizeMax(fileMaxSize);

			// 设置文件上传的头编码，如果需要正确接收中文文件路径或者文件名
			// 这里需要设置对应的字符编码，为了通用这里设置为UTF-8
			upload.setHeaderEncoding(encode);

			// 解析请求
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();
			Map<String, String> jsonMap = new HashMap<String, String>();

			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				String fieldName = item.getFieldName();
				String name = item.getName();
				if (item.isFormField()) {
					// 如果是普通表单字段
					String value = item.getString("UTF-8");
					jsonMap.put(fieldName, value);

				} else {
					// 如果是文件字段
					logger.debug("fieldName[" + fieldName + "] fileName["
							+ name + "] ");
					System.out.println("保存文件：位置" + saveDirectoryPath
							+ "fieldName[" + fieldName + "] fileName[" + name
							+ "] ");
					if (item.getSize() > 0) {
						String fileExtension = FilenameUtils.getExtension(name);
						if (!ArrayUtils
								.contains(extensionPermit, fileExtension)) {
							throw new NoPermissionException("不支持的文件类型");
						}
						String fileName = FilenameUtils.getName(name);
						FileUtils.copyInputStreamToFile(item.getInputStream(),
								new File(saveDirectory, fileName));
						jsonMap.put(fieldName, uploadFolderName + "/" + name);

					}

				}

			}
			JSONArray dataJsonArray = JSONArray.fromObject(jsonMap);
			dataList = JSONArray.toList(dataJsonArray, data.getClass());

		} catch (FileSizeLimitExceededException e) {
			logger.error(e.getMessage(), e);
			state = State.OVER_FILE_LIMIT;
		} catch (NoPermissionException e) {
			logger.error(e.getMessage(), e);
			state = State.NO_SUPPORT_EXTENSION;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			state = State.ERROR;
		} finally {
			// 清除上传进度信息
			request.getSession().removeAttribute("fileUploadProcess");
		}
		logger.info("UploadFileServlet#doPost() end");
		return new UploadType(state, dataList);
	}

	public enum State {
		OK(200, "上传成功"), ERROR(500, "上传失败"), OVER_FILE_LIMIT(501, "超过上传大小限制"), NO_SUPPORT_EXTENSION(
				502, "不支持的扩展名");

		private int code;
		private String message;

		private State(int code, String message) {
			this.code = code;
			this.message = message;
		}

		public int getCode() {
			return code;
		}

		public String getMessage() {
			return message;
		}

	}
}