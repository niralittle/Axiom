<%-- 
    Document   : newStartup-content
    Created on : 23.06.2014, 2:42:11
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="axiom.entity.StartupState"%>
<%@page import="axiom.entity.ProjectType"%>

<script type="text/javascript" src="assets/newStartupValidator.js"></script>

<%
List<StartupState> startupStates = (List<StartupState>) request.getAttribute("startupStates");
List<ProjectType> projectTypes = (List<ProjectType>) request.getAttribute("projectTypes");
%>

<form name="newStartup" method="post" onsubmit="return validate()" action="newStartup">
    <h4>Будь ласка, заповніть усі поля нижче: </h4>

<%= request.getAttribute("errMessage") == null ? "" : request.getAttribute("errMessage")%>

    <table border="0" cellpadding="5">
         <tr>
            <td>
                <div class="form-group" style="width: 300px;">
                    <label for="fname">Назва: </label>
                    <input class="form-control" required type="text"
                           name="name" id="name"
                       value="<%=request.getParameter("name") == null ?
                           "" : request.getParameter("name")%>" />
                </div>
            </td>
            <td>
                <div id="nameMsg"></div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="form-group" style="width: 300px;">
                    <label for="login">Опис:</label>
                    <input class="form-control" required type="text" name="description"
                           id="description"
                       value="<%=request.getParameter("description") == null ? ""
                       : request.getParameter("description")%>" />
                </div>
            </td>
            <td>
                <div id="descriptionMsg"></div>
            <td>
        </tr>
        <% if (projectTypes != null && !projectTypes.isEmpty()) { %>
        <tr>
            <td>
                <div class="form-group" style="width: 300px;">
                    <label for="projectType">Тип проекту: </label>
                    <select name="projectType" id="projectType"
                       value="<%=request.getParameter("projectType") == null ?
                                "" : request.getParameter("projectType")%>">
                        <%
                        for (ProjectType pt: projectTypes) { %>
                        <option value ="<%=pt.getId()%>"><%=pt.getDiscription()%></option>
                    <% } %>
                    </select>
                </div>
            </td>
            <td>
                <div id="facMsg"></div>
            </td>
        </tr>
        <% }
           if (startupStates != null && !startupStates.isEmpty()) { %>
        <tr>
            <td>
                <div class="form-group" style="width: 300px;">
                    <label for="startupState">Стан проекту: </label>
                    <select name="startupState" id="startupState">
                    <%
                        for (StartupState ss : startupStates) {
                    %>
                        <option value ="<%=ss.getId()%>"><%=ss.getDescription()%></option>
                    <%  } %>
                    </select>
                </div>
            </td>
            <td>
                <div id="startupStateMsg"></div>
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
    <button type="submit" class="btn btn-success" style="margin-top: 25px">Створити проект</button>
</form>
