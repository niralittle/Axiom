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
