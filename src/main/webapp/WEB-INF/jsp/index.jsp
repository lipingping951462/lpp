<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="entity.User"%>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<html>
<head>
<%    
String path = request.getContextPath();    
// 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:    
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";    
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<%=basePath %>js/jquery-1.9.1.js"></script>
<script src="<%=basePath %>js/bootstrap.min.js"></script>
<link href="<%=basePath %>/css/bootstrap.css" rel="stylesheet">

<script src="<%=basePath %>js/jquery-1.9.1.min.js" type="text/javascript" ></script> 
<script type="text/javascript">
function updateUser(id){
	var tr = $("tr[id=" + id + "]"); 
		var id = tr.find('input[ID$=id]').val();
		var name=tr.find('input[ID$=name]').val();
		var password=tr.find('input[ID$=password]').val();
		location.href = "/user/updateUser?id="+id+"&name="+name+"&password="+password;
	
	}
	
function deleteUser(id){
	var tr = $("tr[id=" + id + "]"); 
		var id = tr.find('input[ID$=id]').val();
		var name=tr.find('input[ID$=name]').val();
		location.href = "/user/deleteUser?id="+id+"&name="+name;
	}

</script>

<title>Insert title here</title>
</head>
<body>
<a href="<%=basePath %>logout">退出</a>
<form action="/user/searchuser">
<div class="form-group">
	name:<input class="form-controll"  type="text" name="name" maxlength="20"/> 
		id:<input  class="form-controll"  type="text" name="id"  maxlength="20"/> 
		<input class="btn btn-default" type="submit" value="搜索"/> 
</div>

	</form>
	
	<form action="<%=basePath %>user/saveUser">
	<div class="form-group">

		name:<input class="form-controll"  type="text" name="name"> 
	password:<input class="form-controll"  type="text" name="password">

<input class="btn btn-default"  type="submit" value="添加"/> 
	</div>
	</form>
	
				<label class="control-label">searchuser</label>：
	<table class="table table-condensed table-hover"  width="20" id="searchtable">
	<tbody>
	<tr>
	<td>id</td>
	<td>name</td>
	<td>password</td>
	<td>operate</td>
	</tr>

	<c:forEach items="${searchuser}" var="user"  step="1" varStatus="var">
        <tr  class="active" id='${user.id}'>
	<td><input   type="text"  class="form-controll"  id="userid"  name="userid" value="${user.id}" readonly></input></td>
	<td><input type="text"  id=" username"  name="username" value="${user.name}"></input> </td>
	<td><input type="text"  id="password" size="34"  name="password"   value="${user.password}"></input></td>
	<td><intput class="btn btn-default"   onclick="javascript:deleteUser('${user.id}')"  type="button">删除</intput> <input class="btn btn-default" type="button"  onclick="javascript:updateUser('${user.id}')"  value=
	"更新">  </td>
	</tr>
    </c:forEach>
   </tbody>
	</table>
  <jsp:include page="pager.jsp">
  <jsp:param value="/user/searchuser" name="url"/>
  </jsp:include>
</body>
	
</html>

