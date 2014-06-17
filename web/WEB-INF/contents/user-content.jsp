<%-- 
    Document   : user-content
    Created on : 15 черв 2014, 23:41:17
    Author     : Nira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="axiom.entity.Post"%>


<%
String status = request.getAttribute("status") != null ?
     (String) request.getAttribute("status") : null;

String name = request.getAttribute("name") != null ?
     (String) request.getAttribute("name") : null;

String faculty = request.getAttribute("faculty") != null ?
     (String) request.getAttribute("faculty") : null;

String major = request.getAttribute("major") != null ?
     (String) request.getAttribute("major") : null;

boolean isOnline = request.getAttribute("isOnline") != null ?
     (Boolean) request.getAttribute("isOnline") : null;

boolean isValid = request.getAttribute("isValid") != null ?
     (Boolean) request.getAttribute("isValid") : null;
Map<Integer, Post> posts =
         request.getAttribute("posts") != null ?
     (Map<Integer, Post>) request.getAttribute("posts") : null;
%>

 <div class="whois">
        <table width="500px;" align="center">
            <tr>
                <td>
                    <img class="avatar" src="https://avatars2.githubusercontent.com/u/5579194?s=460" align="center"/>
                </td>
                <td style="padding-left: 15px;">
                    <% if (status != null) { %>
                    <blockquote>
                        <p><%=status%></p>
                    </blockquote>
                    <% } %>
                    <% if (faculty != null) { %>
                    <span class="label label-default"><%=faculty%></span>
                    <% } %>
                    <% if (major != null) { %>
                    <br><span class="label label-default"><%=major%></span>
                    <% } %>
                    <% if (isOnline) { %>
                    <br><span class="label label-success">online</span>
                    <br>
                    <% } %>
                    <% if (!isValid) { %>
                    <br><button type="button" class="danger"
                                action="UserController?=confirm">Підтвердити профіль</button>
                    <br>
                    <% } %>
                </td>
            </tr>
        </table>
        <h2 class="name" align="center"><%=name%></h2>
</div>

<div class="profile_content" width="675px">
<div class="tabs">
<ul class="tab-links">
    <li class="active"><a href="#tab1">Стіна</a></li>
    <li><a href="#tab2">Деталі профілю</a></li>
    <li><a href="#tab3">Налаштування сторінки</a></li>
</ul>

<div class="tab-content">
    <div id="tab1" class="tab active">
        <div class="wall">
<% if (posts != null && !posts.isEmpty()) {
  for (Integer key: posts.keySet()) {
      Post p = posts.get(key); %>
 
                <div class="panel panel-warning">
                <div class="panel-heading">
                    <h3 class="panel-title"><%=p.getName()%></h3>
                    <h3><%=p.getDate()%></h3>
                </div>
                <div class="panel-body"><%=p.getContent()%></div>
            </div>
            <br> 
        <% } %>
<% } %>
</div>