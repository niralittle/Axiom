<%-- 
    Document   : search-content
    Created on : 15 черв 2014, 23:41:00
    Author     : Nira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="java.util.List" %>
<%@page import="axiom.entity.User" %>
<%@page import="axiom.entity.Startup" %>
<%@page import="axiom.entity.Faculty" %>
<%@page import="axiom.entity.Major" %>
<%@page import="axiom.entity.ProjectType" %>
<%@page import="axiom.entity.StartupState" %>
<%@page import="axiom.entity.Skill" %>

<%
    List<User> users = (List<User>) request.getAttribute("users");
    List<Startup> startups = (List<Startup>) request.getAttribute("startups");
    List<Faculty> faculties = (List<Faculty>) request.getAttribute("faculty");
    List<Major> majors = (List<Major>) request.getAttribute("major");
    List<ProjectType> projectTypes = (List<ProjectType>) request.getAttribute("startuptype");
    List<StartupState> startupstates = (List<StartupState>) request.getAttribute("startupstate");
    List<Skill> skills = (List<Skill>) request.getAttribute("skills");

    boolean isStartup = "startup".equals(request.getParameter("subject"));
    boolean isVacancy = "vacancy".equals(request.getParameter("subject"));
    boolean isUser = !(isStartup || isVacancy);
%>
<div class="content">

    <form class="search-form" action="">
        <div class="input">
            <input type="text" id="name" size="90%">
            <input type="button" value="Пошук" onmouseup="Find(this);">
        </div>
        <%String name = (String) request.getAttribute("name");%>
        <div class="between"></div>

        <%
            if (isUser) {
        %>
        <div id="forUser">
            <div class="select-faculty">
                Факультет
                <select name="faculty" id="faculty">
                    <option selected value="0">Усі</option>
                    <%
                        if ((faculties != null) && !(faculties.isEmpty())) {
                            for (Faculty f : faculties) {
                    %>
                    <option value="<%=f.getId()%>"><%=f.getName()%>
                    </option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>
            <%String choosedfaculty = (String) request.getAttribute("faculty");%>
            </br>
            <div class="select-major">
                Спеціальність
                <select name="major" id="major">
                    <option selected value="0">Усі</option>
                    <%
                        if ((majors != null) && !(majors.isEmpty())) {
                            for (Major m : majors) {
                    %>
                    <option value="<%=m.getId()%>"><%=m.getName()%>
                    </option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>
            <%String choosedmajor = (String) request.getAttribute("major");%>
            </br>
            <div class="skills">
                Необхідні вміння:</br>
                <table>
                    <%
                        if (skills != null && !skills.isEmpty()) {
                            for (int key = 0; key < skills.size(); key += 3) {
                                Skill s = skills.get(key);
                    %>
                    <tr>
                        <td width="33%"><input type="checkbox" id="<%=s.getId()%>" value="<%=s.getName()%>"><Br>
                        </td>
                        <%
                            ++key;
                            if (key < skills.size()) s = skills.get(key);
                            else break;
                        %>
                        <td width="33%"><input type="checkbox" id="<%=s.getId()%>" value="<%=s.getName()%>"><Br>
                        </td>
                        <%
                            ++key;
                            if (key < skills.size()) s = skills.get(key);
                            else break;
                        %>
                        <td width="33%"><input type="checkbox" id="<%=s.getId()%>" value="<%=s.getName()%>"><Br>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </table>
            </div>
        </div>
            <%
                }
            if (isStartup) {%>
        <div id="forStartup">
            <div class="select-type">
                Тип проекту
                <select name="startupType" id="startupType">
                    <option selected value="0">Будь-який</option>
                    <%
                        if ((projectTypes != null) && !(projectTypes.isEmpty())) {
                            for (ProjectType p : projectTypes) {
                    %>
                    <option value="<%=p.getId()%>"><%=p.getDiscription()%>
                    </option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>
            <%String choosedstartuptype = (String) request.getAttribute("startuptype");%>
            </br>
            <div class="select-state">
                Стан проекту
                <select name="startupState" id="startupState">
                    <option selected value="0">Будь-який</option>
                    <%
                        if (startupstates != null && !startupstates.isEmpty()) {
                            for (StartupState ss : startupstates) {
                    %>
                    <option value="<%=ss.getId()%>"><%=ss.getDescription()%>
                    </option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>
            <%String choosedstartupstate = (String) request.getAttribute("startupstate");%>
        </div>
    </form>
    <%
        }
    %>

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
                <div class="resultUser"><%=userName%>
                </div>
                </br>
                <a href="user.html">Детальніше...</a>
            </li>
        </ul>
        <%
                }
            }
        } else  if (startups!=null && !startups.isEmpty()) {%>
        <ul class="result-links">
            <% for (Startup s : startups) {%>
            <li>
                <div class="resultUser"><%=s.getName()%>
                </div>
                </br>
                <%=s.getDescription()%>
                <a href="user.html">Детальніше...</a>
            </li>
            <%
                    }
        }
            %>
        </ul>
    </div>
</div>
