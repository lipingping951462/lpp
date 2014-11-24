<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="spring" uri="/spring" %>
<%@ taglib prefix="form" uri="/spring-form" %>
<%@ page import="forms.LoginForm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
</head>
<body>
<%
 LoginForm loginForm = new LoginForm();
 request.setAttribute("command",loginForm);
%>

<form:form commandName="command" id="form1" action="/user/login" method="post">
         <h1><spring:message code="login.page.title"/></h1><br>
         <spring:message code="login.page.username"/>
         <form:input path="username"/>
         <font color="red"><form:errors path="username"/></font><br/>
         <spring:message code="login.page.password"/><form:password path="password"/>
         <font color="red"><form:errors path="password"/></font><br/>
         <input type="submit" value="提交">
</form:form>
</body>
</html>