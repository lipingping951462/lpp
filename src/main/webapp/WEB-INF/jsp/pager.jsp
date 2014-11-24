<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function selectpagesize(feild){
	var url=window.location.href;
	if(-1==url.indexOf("?")){
		url=url+"?a=a";
	}
	if(-1==url.indexOf("pagesize")){
		url=url.concat("&pagesize=" + feild.value);
	}else{
		url=url.replace("pagesize="+<%=request.getParameter("pagesize")%>+"","pagesize=" + feild.value) ;
	}
	
	if(-1==url.indexOf("pager.offset")){
		url=url.concat("&pager.offset="+0);
	}else{
		url=url.replace("pager.offset="+<%=request.getParameter("pager.offset")%>+"","pager.offset=" +0) ;
	}
	
	window.location=url;
}


</script>
</head>
<body>
<%
int pagesize=10;
if(null!=request.getParameter("pagesize") ){
	pagesize=Integer.parseInt(request.getParameter("pagesize"));
}
%>

<pg:pager items="${count}"  maxPageItems="<%= pagesize%>"  maxIndexPages="10"  url="${param.url}"  export="currentPageNumber=pageNumber"> 
    <pg:param name="parentId"/>  
    <pg:param name="pagesize" value="<%= String.valueOf(pagesize)%>"/>
    <pg:first>  
        <a href="${pageUrl}" id="firstPage">首页</a>  
    </pg:first>  
    <pg:prev>  
        <a href="${pageUrl}">前页</a>  
    </pg:prev>  
    <pg:pages>  
        <c:choose>  
            <c:when test="${currentPageNumber eq pageNumber}">  
                <font color="red">${pageNumber }</font>  
            </c:when>  
            <c:otherwise>   
                <a href="${pageUrl}">${pageNumber }</a>  
            </c:otherwise>  
        </c:choose>  
    </pg:pages>  
    <pg:next>  
        <a href="${pageUrl}">后页</a>  
    </pg:next>  
    <pg:last>  
        <a href="${pageUrl}">尾页</a>  
    </pg:last>  
    <select name="pagesize" onchange="selectpagesize(this)">
   <% for(int i=5;i<=50;i=i+5){
    if (i==pagesize) {%>
    
    	 <option value="<%=i %>"  selected ><%=i %></option>
    <%}else{%>
    	 <option value="<%=i %>" ><%=i %></option>
    <%}
    }
    %>
       

</select>
</pg:pager>  
</body>
</html>