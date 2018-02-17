/*

function validateForm() {
	    var message, email, emailPattern,zipcode, city, phonenumber, zip_phonePattern, cellphonenumber, cityPattern, username, nickname, psw, psw_repeat;
	    message = document.getElementById("message");
	    message.innerHTML = "";
	    username = document.getElementById("username");
	    nickname = document.getElementById("nickname");
	    email = document.getElementById("email");
	    emailPattern = /[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/;
	    zip_phonePattern = /[0-9]{7}/;
	    zipcode=document.getElementById("zipcode");
	    city = document.getElementById("city");
	    cityPattern = /^([a-zA-Z]|\s)*$/;
	    phonenumber = document.getElementById("phonenumber");
	    cellphonenumber = document.getElementById("cellphonenumber");
	    psw = document.getElementById("psw");
	    psw_repeat = document.getElementById("psw-repeat");
	    
	    if (!username.value){
	    	alert("Username is a required field");
	    	message.innerHTML = "Username is a required field";
	    	return false;
	    }
	    else if (cityPattern.test(username.value) == false){
	    	alert("User name format is incorrect");
	    	message.innerHTML = "User name format is incorrect";
	    	return false;
	    }
	    else if (!email.value){
	    	alert("Email is a required field");
	    	message.innerHTML = "Email is a required field";
	    	return false;
	    }
	    else if (emailPattern.test(email.value) == false){
	    	email.setCustomValidity('Yoni is gay');
	   // 	alert("Email must be of the format: user@domain");
	    //	message.innerHTML = "Email must be of the format: user@domain";
	    	return false;
	    }
	    else if (!zipcode.value){
	    	alert("Zipcode is a required field");
	    	message.innerHTML = "Zipcode is a required field";
	    	return false;
	    }
	    else if ((zip_phonePattern.test(zipcode.value) == false) && (zipcode.value)){
	    	alert("Zipcode must contain exactly 7 digits");
	    	message.innerHTML = "Zipcode must contain exactly 7 digits";
	    	return false;
	    }
	    else if (!city.value){
	    	alert("City is a required field");
	    	message.innerHTML = "City is a required field";
	    	return false;
	    }
	    else if ((cityPattern.test(city.value) == false) && (city.value)){
	    	alert("City must contain only uppercase and lowercase letters");
	    	message.innerHTML = "City must contain only uppercase and lowercase letters";
	    	return false;
	    }
	    else if (city.value.length < 3){
	    	alert("City must contain 3 or more characters");
	    	message.innerHTML = "City must contain 3 or more characters";
	    	return false;
	    }
	    else if (!phonenumber.value){
	    	alert("Phone number is a required field");
	    	message.innerHTML = "Phone number is a required field";
	    	return false;
	    }
	    else if ((zip_phonePattern.test(phonenumber.value) == false) && (phonenumber.value)){
	    	alert("Phone number must contain exactly 7 digits");
	    	message.innerHTML = "Phone number must contain exactly 7 digits";
	    	return false;
	    }
	    else if (!cellphonenumber.value){
	    	alert("Cell phone number is a required field");
	    	message.innerHTML = "Cell phone number is a required field";
	    	return false;
	    }
	    else if ((zip_phonePattern.test(cellphonenumber.value) == false) && (cellphonenumber.value)){
	    	alert("Cell Phone number must contain exactly 7 digits");
	    	message.innerHTML = "Cell Phone number must contain exactly 7 digits";
	    	return false;
	    }
	    else if (!nickname.value){
	    	alert("Nickname is a required field");
	    	message.innerHTML = "Nickname is a required field";
	    	return false;
	    }
	    else if (!psw.value){
	    	alert("Password is a required field");
	    	message.innerHTML = "Password is a required field";
	    	return false;
	    }
	    else if (psw_repeat.value !== psw.value){
	    	alert("Password not reapeated correctly");
	    	message.innerHTML = "Password not reapeated correctly";
	    	return false;
	    }
	    return true;
}

function validatePurchase() {
    var message, email, emailPattern,zipcode, city, zip_phonePattern, cityPattern, ccNumber, month, year, currentMonth, currentYear, cvv, firstname, lastname, address, visa, master, amex, cardName, visaPattern, masterPattern, amexPattern;
    message = document.getElementById("message");
    message.innerHTML = "";
    firstname = document.getElementById("firstName");
    lastname = document.getElementById("lastName");
    email = document.getElementById("email");
    emailPattern = /[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/;
    address = document.getElementById("address")
    zip_Pattern = /[0-9]{7}/;
    zipcode=document.getElementById("zip");
    city = document.getElementById("city");
    cityPattern = /^([a-zA-Z]|\s)*$/;
    visa = document.getElementById("visa");
    master = document.getElementById("mastercard");
    amex = document.getElementById("amex");
    ccNumber = document.getElementById("cc-number");
    visaPattern = /^(?:4[0-9]{12}(?:[0-9]{3})?)$/;
    amexPattern = /^(?:3[47][0-9]{13})$/;
    masterPattern = /^(?:5[1-5][0-9]{14})$/;
    cardName = document.getElementById("cc-name");
    month = document.getElementById("month");
    year = document.getElementById("year");
    currentMonth = new Date().getMonth();
    currentYear = new Date().getFullYear().toString().substr(2,2);
    cvv = document.getElementById("cc-cvv");
    
    if (!firstname.value){
    	alert("First name is a required field");
    	message.innerHTML = "First name is a required field";
    	return false;
    }
    else if (cityPattern.test(firstname.value) == false){
    	alert("First name format is incorrect");
    	message.innerHTML = "First name format is incorrect";
    	return false;
    }
    else if (!lastname.value){
    	alert("Last name is a required field");
    	message.innerHTML = "Last name is a required field";
    	return false;
    }
    else if (cityPattern.test(lastname.value) == false){
    	alert("Last name format is incorrect");
    	message.innerHTML = "Last name format is incorrect";
    	return false;
    }
    else if (!email.value){
    	alert("Email is a required field");
    	message.innerHTML = "Email is a required field";
    	return false;
    }
    else if ((emailPattern.test(email.value) == false) && (email.value)){
    	alert("Email must be of the format: user@domain");
    	message.innerHTML = "Email must be of the format: user@domain";
    	return false;
    }
    else if (!city.value){
    	alert("City is a required field");
    	message.innerHTML = "City is a required field";
    	return false;
    }
    else if (cityPattern.test(city.value) == false){
    	alert("City must contain only uppercase and lowercase letters");
    	message.innerHTML = "City must contain only uppercase and lowercase letters";
    	return false;
    }
    else if (city.value.length < 3){
    	alert("City must contain 3 or more characters");
    	message.innerHTML = "City must contain 3 or more characters";
    	return false;
    }
    else if (!zipcode.value){
    	alert("Zipcode is a required field");
    	message.innerHTML = "Zipcode is a required field";
    	return false;
    }
    else if (zip_Pattern.test(zipcode.value) == false){
    	alert("Zipcode must contain exactly 7 digits");
    	message.innerHTML = "Zipcode must contain exactly 7 digits";
    	return false;
    }
    else if (!cardName.value){
    	alert("Credit card name is a required field");
    	message.innerHTML = "Credit card name is a required field";
    	return false;
    }
    else if (cityPattern.test(cardname.value) == false){
    	alert("Card name format is incorrect");
    	message.innerHTML = "Card name format is incorrect";
    	return false;
    }
    else if (!ccNumber.value){
    	alert("Credit card number is a required field");
    	message.innerHTML = "Credit card number is a required field";
    	return false;
    }
    else{
    	if (visa.checked){
    		if (!ccNumber.value.match(visaPattern)){
    			alert("Credit card format does not apply");
    			message.innerHTML = "Credit card format does not apply";
    			return false;
    		}
    	}
    	else if (master.checked){
    		if (!ccNumber.value.match(masterPattern)){
    			alert("Credit card format does not apply");
    			message.innerHTML = "Credit card format does not apply";
    			return false;
    		}
        }
    	else if (amex.checked){
    		if (!ccNumber.value.match(amexPattern)){
    			alert("Credit card format does not apply");
    			message.innerHTML = "Credit card format does not apply";
    			return false;
    		}
        }
    }	 
    if (!month.value){
    	alert("Expiration date is a required field");
       	message.innerHTML = "Expiration date is a required field";
       	return false;
    }
    else if (!year.value){
   		alert("Expiration date is a required field");
   		message.innerHTML = "Expiration date is a required field";
   		return false;
   	}
    else if ((!month.value.match(/^(0[1-9]|1[0-2])$/)) || (!year.value.match(/\d{2}$/)) || ((Number(year.value) == Number(currentYear)) && (Number(month.value) < (currentMonth+1))) || (Number(year.value) < Number(currentYear))){
   		alert("Expiration date is not correct");
   		message.innerHTML = "Expiration date is not correct";
   		return false;
   	}
   	else if (!cvv.value){
   		alert("CVV is a required field");
   		message.innerHTML = "CVV is a required field";
   		return false;
   	}
   	else if (!cvv.value.match(/\d{3}$/)){
   		alert("CVV is not of correct format");
   		message.innerHTML = "CVV is not of correct format";
   		return false;
   	}
   	else{
   		return true;
   	}
}




*/