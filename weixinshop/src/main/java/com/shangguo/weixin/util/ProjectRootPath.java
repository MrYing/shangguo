package com.shangguo.weixin.util;

import javax.servlet.http.HttpServletRequest;

public class ProjectRootPath {
	
    public static String getRootPath(HttpServletRequest request){
    	String relativelyPath=request.getSession().getServletContext().getRealPath("/");
		return relativelyPath;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
