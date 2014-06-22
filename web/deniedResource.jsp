<%-- 
    Document   : deniedResource
    Created on : 22.06.2014, 14:56:43
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/template.jsp">
	<jsp:param name="content" value="denied-content"/>
	<jsp:param name="title" value="Помилка доступу"/>
</jsp:include>
