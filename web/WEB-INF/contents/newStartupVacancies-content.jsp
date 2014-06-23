<%-- 
    Document   : newStartupVacancies-content
    Created on : 23.06.2014, 7:05:20
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
int numbOfVacansies = 1;
%>

<form name="newStartup" method="post" onsubmit="return validate()" action="NewStartupVacancy">
    <h4>Будь ласка, опишіть, яких учасників проекту ви шукаєте: </h4>

<%= request.getAttribute("errMessage") == null ? "" : request.getAttribute("errMessage")%>

    <table border="0" cellpadding="5" name = "VacancyTable" id = "VacancyTable">
        <tr id="Vacancy">
            <td>
                <div class="form-group" style="width: 300px;">
                    <label for="name">Назва: </label>
                    <input class="form-control" required type="text"
                           name="name1" id="name1"
                       value="<%=request.getParameter("name") == null ?
                           "" : request.getParameter("name")%>" />
                </div>
            </td>
            <td>
                <div class="form-group" style="width: 300px;">
                    <label for="description">Опис вакансії:</label>
                    <input class="form-control" required type="text" name="description1"
                           id="description1"
                       value="<%=request.getParameter("description") == null ? ""
                       : request.getParameter("description")%>" />
                </div>
            </td>
        </tr>


    </table>
    <button type="submit" class="btn btn-success" onclick="insertVacancy()" style="margin-top: 25px">Додати ще одну вакансію</button>
    </Br>
    </Br>
    <button type="submit" class="btn btn-success" style="margin-top: 25px">Зберігти вакансії</button>
</form>

