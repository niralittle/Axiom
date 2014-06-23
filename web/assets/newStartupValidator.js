 function validate() {
	// getting inputed values in fields
	var name = document.getElementById("name");
	var descr = document.getElementById("description");

	// Regex for validation inputed data
        var name_check =/^[А-яА-я]{3,40}$/;
	var description_check = /^[A-Za-z0-9!@#$%^&*()_]{6,40}$/;

	// value to return. If all data valid - all OK!
	var valid = true;



	if (!name_check.test(name.value)) {
		document.getElementById("nameMsg").innerHTML = "Змініть назву проекту.";
		valid = false;
	} else {
		document.getElementById("nameMsg").innerHTML = " ";
	}

        if (!description_check.test(descr.value)) {
		document.getElementById("descriptionMsg").innerHTML = "Щось не так з описом проекту";
		valid = false;
	} else {
		document.getElementById("descriptionMsg").innerHTML = " ";
	}
	// send page to server side only if inputed data is correct
	return valid;
};

function insertVacancy(){
    var numb = document.getElementsByName("numbOfVacansies")
    var x=document.getElementById("VacancyTable").insertRow(++numb);
    var y=x.insertCell(0)
    var z=x.insertCell(1)
    y.innerHTML="<div class=\"form-group\" style=\"width: 300px;\">" +
                    "<label for=\"name\">Назва: </label>" +
                    "<input class=\"form-control\" required type=\"text\"" +
                           "name=\"name\" id=\"name\"" +
                      " value=\"<%=request.getParameter(\"name\") == null ?" +
                           "\"\" : request.getParameter(\"name\")%>\" />" +
                "</div>";
    z.innerHTML="<div class=\"form-group\" style=\"width: 300px;\">" +
                   " <label for=\"description\">Опис вакансії:</label>" +
                   " <input class=\"form-control\" required type=\"text\" name=\"description\"" +
                         "id=\"description\"" +
                       "value=\"<%=request.getParameter(\"description\") == null ? \"\"" +
                       ": request.getParameter(\"description\")%>\" />" +
                "</div>";

};
