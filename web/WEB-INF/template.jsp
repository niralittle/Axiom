<%-- 
    Document   : header
    Created on : 14 черв 2014, 19:30:18
    Author     : Nira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="content-type" content="text/html; charset=windows-1251"/>
    <title><%=request.getParameter("title")%> | Axiom</title>
    <link rel="icon" href="assets/favicon.ico"/>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="main.css">
    <script type="text/javascript" src="jquery.js"></script>
</head>

<body>
<div id="page">
    <div class="header">
        <div style="background-color: #CAD958; height: 20px; margin: 0 0 3px 0;"></div>
        <div style="background-color: #58CAD9; height: 10px;"></div>
    </div>
    <h2 id="title"><%=request.getParameter("title")%></h2>

    <div class="content">
        <% pageContext.include("/WEB-INF/contents/" + request.getParameter("content") + ".jsp");%>
    </div>

    <div class="footer">
        <div style="background-color: #CAD958; height: 20px; margin: 0 0 3px 0;"></div>
        <div style="background-color: #58CAD9; height: 10px;"></div>
    </div>
</div>

</body>
</html>
