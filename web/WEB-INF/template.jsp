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
    <link rel="stylesheet" href="assets/bootstrap.min.css">
    <link rel="stylesheet" href="assets/main.css">
    <script type="text/javascript" src="assets/jquery.js"></script>
</head>

<body>
<div id="page">
    <div class="header">
    <%  if (request.getUserPrincipal() == null) { %>
        <div class="auth">
            <form method="POST" action="Auth" class="navbar-form navbar-right" role="form">
                <div class="form-group">
                    <input required type="text" name="j_username"  placeholder="Логін" class="form-control">
                </div>
                <div class="form-group">
                    <input required type="password" name="j_password" placeholder="Пароль" class="form-control">
                </div>
                <button type="submit" class="btn btn-primary" value="Login">Увійти</button>
            </form>
        </div>
    <%  } else { %>
        <button type="submit" action="Logout" class="btn btn-primary" value="Logout">Вийти</button>
    <%  } %>
         <div class="pages_list">
            <ul>
                <% if (request.getUserPrincipal() != null) { %>
                <li><a href="UserController">Моя сторінка</a></li>
                <% } %>
                <li><a href="search.jsp?subject=startup">Стартапи</a></li>
                <li><a href="search.jsp?subject=name">Люди</a></li>
                <li><a href="search.jsp?subject=vacancy">Пошук вакансій</a></li>
            </ul>
        </div>
        <div style="background-color: #58CAD9; height: 8px;"></div>
    </div>
    <h2 id="title"><%=request.getParameter("title")%></h2>  
    <div class="content">
        <br>
        <% pageContext.include("/WEB-INF/contents/" + request.getParameter("content") + ".jsp");%>
        <br>
    </div>

    <div class="footer">
        <div style="background: url(assets/footer.jpg) no-repeat #E9F2FF; height: 334px; margin: -40px 0;"></div>
    </div>
</div>

</body>
</html>
