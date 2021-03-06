<%-- 
    Document   : search-content
    Created on : 15 черв 2014, 23:41:00
    Author     : Nira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="java.util.List" %>
<%@page import="axiom.entity.User" %>
<%@page import="axiom.entity.Startup" %>
<%@page import="axiom.entity.Skill" %>
<%@page import="axiom.entity.Vacancy" %>

<%
    List<User> users = (List<User>) request.getAttribute("users");
    List<Startup> startups = (List<Startup>) request.getAttribute("startups");
    List<Skill> skills = (List<Skill>) request.getAttribute("skills");
    List<Vacancy> vacancies = (List<Vacancy>) request.getAttribute("vacancies");

    boolean isStartup = "startup".equals(request.getParameter("subject"));
    boolean isVacancy = "vacancy".equals(request.getParameter("subject"));
    boolean isUser = !(isStartup || isVacancy);
%>
<div class="content">

        <%
            if (isUser) {
        %>
    <form class="search-form" method="post" action="SearchUser">
        <div class="input">
            <input type="text" name="firstName" placeholder="Ім'я" <%=
            request.getParameter("firstName") !=null ? "value='" + request.getParameter("firstName") + "'" : ""
            %> size="30%">
            <input type="text" name="lastName" placeholder="Прізвище" <%=
            request.getParameter("lastName") !=null ? "value='" + request.getParameter("lastName") + "'" : ""
            %> size="30%">
            <input type="submit" value="Пошук">
        </div>
    </form>
        <%
            } else if (isStartup) {
        %>
    <form class="search-form" method="post" action="SearchStartup">
        <div class="input">
            <input type="text" name="startup" placeholder="Назва стартапу"
                <%=request.getParameter("startup") !=null ? "value='" +
                request.getParameter("startup") + "'" : "" %> size="60%">
            <input type="submit" value="Знайти">
        </div>
    </form>
        <%  } else {  %>
    <form class="search-form" method="post" action="SearchVacancy">
        <div class="input">
            <input type="text" name="vacancy" placeholder="Введіть критерій. Наприклад: java" <%=
            request.getParameter("vacancy") !=null ? "value='" + request.getParameter("vacancy") + "'" : ""
            %> size="60%">
            <input type="submit" value="Шукати">
        </div>
    </form>
        <%  }  %>

    <div class="between"></div>

    <div id="Result" style="display: none;">
        <div style="padding-left: 10px;">Результати пошуку</div>
        <%
            if (isUser) {
                if (users != null && !users.isEmpty()) {
        %>
        <ul class="result-links">
                <% for (User u : users) {
                    String userName = ((String) u.getFirstName() + " " + (String) u.getLastName());%>
            <li>
                <div class="result"><%=userName%>
                </div>
                </br>
                <a href="User?id=<%=u.getId()%>">Детальніше...</a>
            </li>
                <%  }  %>
       </ul>
    <%           }
           } else  if (startups!=null && !startups.isEmpty()) { %>
         <ul class="result-links">
            <% for (Startup v : startups) {%>
            <li>
                <div class="result"><%=v.getName()%>
                </div>
                </br>
                <%=v.getDescription()%>
                <a href="Startup?id=<%=v.getId()%>">Детальніше...</a>
            </li>
           <%  } %>
            </ul>
        <% } else  if (vacancies!=null && !vacancies.isEmpty()) { %>
         <ul class="result-links">
            <% for (Vacancy v : vacancies) {%>
            <li>
                <div class="result"><%=v.getName()%>
                </div>
                </br>
                <%=v.getDescription()%>
                <a href="Vacancy?id=<%=v.getID()%>">Детальніше...</a>
            </li>
    <%         }
           } %>
         </ul>
    </div>
</div>
