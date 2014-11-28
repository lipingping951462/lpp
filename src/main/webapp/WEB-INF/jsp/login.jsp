<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c"   
                 uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ page import="forms.LoginForm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%    
String path = request.getContextPath();    
// 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:    
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";    
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<link href="<%=basePath %>/css/bootstrap.css" rel="stylesheet">
</head>
<body>

管理员登录：
<br/>
${errormessage }
<spring:form action="/login"  method="post" modelAttribute="admin">
	<div class="form-group">
姓名：<spring:input class="form-controll"  path="name"/><spring:errors path="name"/><br/>
密码：<spring:input class="form-controll"   path="password"/><spring:errors path="password"/><br/>
<input  class="btn btn-default" type="submit" value="提交">
</div>
</spring:form>



</body>
</html>