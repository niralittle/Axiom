<%-- 
Document   : registration-content
Created on : 14 черв 2014, 19:29:52
Author     : Nira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="axiom.entity.Faculty"%>
<%@page import="axiom.entity.Major"%>

<script type="text/javascript" src="assets/registerValidator.js"></script>

<%
List<Faculty> faculties = (List<Faculty>) request.getAttribute("faculties");
List<Major> majors = (List<Major>) request.getAttribute("majors");
%>

<form name="Register" method="post" onsubmit="return validate()" action="Register">
    <h4>Будь ласка, заповніть усі поля нижче: </h4>

<%= request.getAttribute("errMessage") == null ? "" : request.getAttribute("errMessage")%>

    <table border="0" cellpadding="5">
         <tr>
            <td>
                <div class="form-group" style="width: 300px;">
                    <label for="fname">Ім'я: </label>
                    <input class="form-control" required type="text"
                           name="firstName" id="fname"
                       value="<%=request.getParameter("firstName") == null ?
                           "" : request.getParameter("firstName")%>" />
                </div>
            </td>
            <td>
                <div id="fnameMsg"></div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="form-group" style="width: 300px;">
                    <label for="lname">Прізвище: </label>
                    <input class="form-control" required type="text"
                           name="lastName" id="lname"
                       value="<%=request.getParameter("lastName") == null ?
                           "" : request.getParameter("lastName")%>" />
                </div>
            </td>
            <td>
                <div id="lnameMsg"></div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="form-group" style="width: 300px;">
                    <label for="login">Логін:</label>
                    <input class="form-control" required type="text" name="login"
                           id="login" oninput="loginValidate()"
                       value="<%=request.getParameter("login") == null ? ""
                       : request.getParameter("login")%>" />
                </div>
            </td>
            <td>
                <div id="loginMsg"></div>
            <td>
        </tr>
        <tr>
            <td>
                <div class="form-group" style="width: 300px;">
                    <label for="pass">Пароль (мінімум 6 символів):</label>
                    <input class="form-control" required type="password"
                           name="password" id="pass"/>
                </div>
            </td>
            <td>
                <div id="passMsg"></div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="form-group" style="width: 300px;">
                    <label for="pass2">Повторіть пароль:</label>
                    <input class="form-control" required type="password"
                           name="passwordConf" id="pass2"/>
                </div>
            </td>
            <td>
                <div id="passMsg2"></div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="form-group" style="width: 300px;">
                    <label for="email">Емейл:</label>
                    <input class="form-control" required type="text" name="email"
                           oninput="emailValidate()" id="email"
                           value="<%=request.getParameter("email") == null ?
                               "" : request.getParameter("email")%>" />
                </div>
            </td>
            <td>
                <div id="emailMsg"></div>
            </td>
        </tr>
        <% if (faculties != null && !faculties.isEmpty()) { %>
        <tr>
            <td>
                <div class="form-group" style="width: 300px;">
                    <label for="faculty">Факультет: </label>
                    <select name="faculty" id="fac">
                        <%
                        for (Faculty f: faculties) { %>
                        <option value="<%=f.getId()%>"><%=f.getName()%></option>
                    <% } %>
                    </select>
                </div>
            </td>
            <td>
                <div id="facMsg"></div>
            </td>
        </tr>
        <% }
           if (majors != null && !majors.isEmpty()) { %>
        <tr>
            <td>
                <div class="form-group" style="width: 300px;">
                    <label for="major">Спеціальність: </label>
                    <select name="major" id="maj">
                    <%
                        String val = request.getParameter("major");
                        if (val != null) {
                    %>
                         <option selected value="<%=val%>"><%=val%></option>

                    <%
                        }
                        for (Major m : majors) {
                    %>
                        <option value ="<%=m.getId()%>"><%=m.getName()%></option>
                    <%  } %>
                    </select>
                </div>
            </td>
            <td>
                <div id="majMsg"></div>
            </td>
        </tr>
        <% } %>
        <tr>
            <td>
                <div class="form-group" style="width: 300px;">
                    <label for="code">Введіть текст зображений на картинці: </label>
                    <input class="form-control" type="text" name="code" id="code"/>
                </div>
            </td>
        </tr>
        <tr>
            <td>
            	<img src="Captcha">
                <button name="action" value="Refresh" type="submit"
                        class="btn btn-success btn-sm">Оновити
                </button>
            </td>
        </tr>
    </table>
    <button type="submit" class="btn btn-success" style="margin-top: 25px">Стати частиною Axiom</button>
</form>