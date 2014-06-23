<%-- 
    Document   : startup-content
    Created on : 23.06.2014, 9:10:19
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="axiom.entity.Vacancy"%>
<%@page import="axiom.entity.User"%>


<%
String name = request.getAttribute("name") != null ?
     (String) request.getAttribute("name") : null;

String description = request.getAttribute("description") != null ?
     (String) request.getAttribute("description") : null;

String projectType = request.getAttribute("projectType") != null ?
     (String) request.getAttribute("projectType") : null;

String startupState = request.getAttribute("startupState") != null ?
     (String) request.getAttribute("startupState") : null;

ArrayList <Vacancy> vacancies = request.getAttribute("vacancies") != null ?
     (ArrayList <Vacancy>) request.getAttribute("vacancies") : null;

ArrayList <User> team = request.getAttribute("team") != null ?
     (ArrayList <User>) request.getAttribute("team") : null;
%>

<form class="startup-form" method="post" action="StartupServlet">
 <div class="whois">
        <table width="500px;" align="center">
            <tr>
                <td style="padding-left: 15px;">
                    <% if (name != null) { %>
                    <blockquote>
                        <p><%=name%></p>
                    </blockquote>
                    <% } %>
                    <% if (projectType != null) { %>
                    <span class="label label-default"><%=projectType%></span>
                    <% } %>
                    <% if (startupState != null) { %>
                    <br><span class="label label-default"><%=startupState%></span>
                    <% } %>
                    <% if (description != null ) { %>
                    <br><span class="label label-success"><%=description%></span>
                    <br>
                    <% } %>
                </td>
            </tr>
        </table>
</div>

<div class="profile_content" width="675px">
<div class="tabs">
<ul class="tab-links">
    <li class="active"><a href="#tab1">Учасники</a></li>
    <li><a href="#tab2">Вакансії</a></li>
</ul>

<div class="tab-content">
    <div id="tab1" class="tab active">
        <div class="team">
<% if (team != null && !team.isEmpty()) {
  for (User u: team) { %>

            <div class="panel panel-warning">
                <div class="panel-heading">
                    <h5 class="panel-title"><%=u.getFirstName()+" "+u.getLastName()%></h5>
                    <a href="User?id=<%=u.getId()%>">Детальніше...</a>
                </div>
            </div>
            <br>
        <% } %>
<% } %>
</div>

 <div id="tab2">
        <div class="vacancies">
<% if (vacancies != null && !vacancies.isEmpty()) {
  for (Vacancy v: vacancies) { %>

            <div class="panel panel-warning">
                <div class="panel-heading">
                    <h5 class="panel-title"><%=v.getName()%></h5>
                    <h6><%=v.getDescription()%></h6>
                </div>
            </div>
            <br>
        <% } %>
<% } %>
</div>
</div>
</form>
