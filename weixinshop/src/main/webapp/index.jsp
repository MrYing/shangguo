<%@ page language="java" %> 
<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ page import="java.util.*" %> 
<%@ page isELIgnored="false" %> 

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<% 
String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%> 

<html> 
<head> 
<base href="<%=basePath%>" /> 
<link rel=stylesheet type="text/css" href="css/style.css"/> 
<script type="text/javascript" src="js/jquery-1.5.1.min.js"></script> 

<script type="text/javascript"> 
$(document).ready(function() { 
alert("hello, jquery"); 
}); 
</script> 

<title>登录</title> 
</head> 

<body> 
<p class="error"><%=basePath%></p> <br>
<%=path%><br>
${pageContext.request.contextPath}
</body> 
</html> 