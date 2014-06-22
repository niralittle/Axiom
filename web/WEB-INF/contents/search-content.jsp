<%-- 
    Document   : search-content
    Created on : 15 черв 2014, 23:41:00
    Author     : Nira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.Map"%>
<%@page import="axiom.entity.User"%>
<%@page import="axiom.entity.Startup"%>
<%@page import="axiom.entity.Faculty"%>
<%@page import="axiom.entity.Major"%>
<%@page import="axiom.entity.ProjectType"%>
<%@page import="axiom.entity.StartupState"%>
<%@page import="axiom.entity.Skill"%>

<%
Map <Integer,User> users = (Map<Integer, User>) request.getAttribute("users");
Map <Integer,Startup> startups = (Map<Integer, Startup>) request.getAttribute("startups");
Map <Integer,Faculty> faculties = (Map<Integer, Faculty>) request.getAttribute("faculty");
Map <Integer,Major> majors = (Map<Integer, Major>) request.getAttribute("major");
Map <Integer,ProjectType> projectTypes = (Map<Integer, ProjectType>) request.getAttribute("startuptype");
Map <Integer,StartupState> startupstates = (Map<Integer, StartupState>) request.getAttribute("startupstate");
Map <Integer,Skill> skills = (Map<Integer, Skill>) request.getAttribute("skills");

boolean isUser=true;
%>
<div class="content">

    <form class="search-form" action="">
            <div class="input">
                    <input type="text" id="name" size="90%">
                    <input type="button" value = "Пошук" onmouseup = "Find(this);">
            </div>
        <%String name = (String)request.getAttribute("name");%>
            <div class="radio-button">
                    <input type="radio" checked name="kind" value="user" id="user" onclick="OnOff(this);"> User</input>
                    <input type="radio" name="kind" value="startup" id="startup" onclick = "OnOff(this);"> Startup</input>
            </div>
        <% if ("startup".equals(request.getAttribute("kind"))) isUser=false;%>
            <div class="between"></div>

            <div id="forUser">
                    <div class="select-faculty">
                    Факультет

                            <select name="faculty" id="faculty">
                                    <option selected value="0">Усі</option>
                                    <%if ((faculties!=null) && !(faculties.isEmpty()))
                                      for (Integer key: faculties.keySet()){
                                          Faculty f = faculties.get(key);%>
                                          <option value="<%=f.getId()%>"><%=f.getName()%></option>
                            </select>
                    </div>
                <%}String choosedfaculty = (String)request.getAttribute("faculty");%>
                    </br>
                    <div class="select-major">
                    Спеціальність
                            <select name="major" id="major">
                                    <option selected value="0">Усі</option>
                                    <%if ((majors!=null) && !(majors.isEmpty()))
                                      for (Integer key: majors.keySet()){
                                          Major m = majors.get(key);%>
                                          <option value="<%=m.getId()%>"><%=m.getName()%></option>
                            </select>
                    </div>
                    <%}String choosedmajor = (String)request.getAttribute("major");%>
                    </br>
                    <div class="skills">
                    Необхідні вміння:</br>
                    <table>
             
                            <%if (skills!=null && !skills.isEmpty())
                                for (Integer key = 0; key<skills.size(); key+=3){
                                    Skill s = skills.get(key);%>
                       <tr> <td width="33%"><input type="checkbox" id="<%=s.getId()%>" value="<%=s.getName()%>"><Br>
                            </td>
                            <%++key;
                              if (key<skills.size())s = skills.get(key);
                              else break;
                            %>
                            <td width="33%"><input type="checkbox" id="<%=s.getId()%>" value="<%=s.getName()%>"><Br>
                            </td>
                            <%++key;
                              if (key<skills.size())s = skills.get(key);
                              else break;
                            %>
                            <td width="33%"><input type="checkbox" id="<%=s.getId()%>" value="<%=s.getName()%>"><Br>
                            </td>
                       </tr><%}%>
                    </table>
                    </div>
            </div>

            <div id="forStartup">
                    <div class="select-type">
                    Тип проекту
                            <select name="startupType" id="startupType">
                                    <option selected value="0">Будь-який</option>
                                    <%if ((projectTypes!=null) && !(projectTypes.isEmpty()))
                                      for (Integer key: projectTypes.keySet()){
                                          ProjectType p = projectTypes.get(key);%>
                                          <option value="<%=p.getId()%>"><%=p.getDiscription()%></option>
                            </select>
                    </div>
                 <%}String choosedstartuptype = (String)request.getAttribute("startuptype");%>
                    </br>
                    <div class="select-state">
                    Стан проекту
                            <select name="startupState" id="startupState">
                                    <option selected value="0">Будь-який</option>
                                    <%if (startupstates!=null && !startupstates.isEmpty())
                                        for (Integer key: startupstates.keySet() ){
                                            StartupState ss = startupstates.get(key);%>
                                            <option value="<%=ss.getId()%>"><%=ss.getDescription()%></option>
                            </select>
                    </div>
                 <%}String choosedstartupstate = (String)request.getAttribute("startupstate");%>
            </div>


    </form>

    <div class="between"></div>

    <div id="Result" style="display: none;">
    <div style="padding-left: 10px;">Результати пошуку</div>
        <%if (isUser)
            { if (users!=null && !users.isEmpty()) {%>
              <ul class="result-links">
               <% for (Integer key: users.keySet())
                   { User u = users.get(key);
                     String userName = ((String)u.getFirstName()+" "+(String)u.getLastName());%>
                    <li> <div class="resultUser"><%=userName%></div> </br>
                            <a href="user.html">Детальніше...</a>
                    </li>
              </ul>
         <%}}}else {%>
          if (startups!=null && !startups.isEmpty())%>
              <ul class="result-links">
               <% for (Integer key: startups.keySet())
                   { Startup s = startups.get(key);%>
                    <li> <div class="resultUser"><%=s.getName()%></div> </br>
                        <%s.getDescription();}}%>
                            <a href="user.html">Детальніше...</a>
                    </li>


              </ul>
    </div>


</div>
