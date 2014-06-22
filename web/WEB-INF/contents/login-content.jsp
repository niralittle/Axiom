<%--
    Document   : main-content
    Created on : 22 черв 2014, 17:07:16
    Author     : Nira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form method="POST" action="Auth">
    <div class="form-group" style="width: 300px;">
        <label for="exampleInputEmail1">Логін:</label>
        <input required="required" type="text" name="j_username"
               class="form-control" id="exampleInputEmail1">
    </div>
    <div class="form-group" style="width: 300px;">
        <label for="exampleInputPassword1">Пароль:</label>
        <input required="required" type="password" name="j_password"
               class="form-control" id="exampleInputPassword1">
    </div>
    <button type="submit" class="btn btn-primary">Увійти</button>
</form>
