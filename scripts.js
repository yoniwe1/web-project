/**
 * 
 */

/*function viewDate() {
    var date = new Date();
    document.getElementById("a").innerHTML = date;
}*/

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
	    cityPattern = /[a-zA-Z ]/;
	    phonenumber = document.getElementById("phonenumber");
	    cellphonenumber = document.getElementById("cellphonenumber");
	    psw = document.getElementById("psw");
	    psw_repeat = document.getElementById("psw-repeat");
	    
	    if (!username.value){
	    	alert("Username is a required field");
	    	message.innerHTML = "Username is a required field";
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
    var message, email, emailPattern,zipcode, city, zip_phonePattern, cityPattern, creditCard, ccNumber, month, year, currentMonth, currentYear, cvv;
    message = document.getElementById("message");
    message.innerHTML = "";
    email = document.getElementById("email");
    emailPattern = /[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/;
    zip_Pattern = /[0-9]{7}/;
    zipcode=document.getElementById("zipcode");
    city = document.getElementById("city");
    cityPattern = /[a-zA-Z ]/;
    creditCard = document.getElementById("credit card").value;
    ccNumber = document.getElementById("cc-number");
    month = document.getElementById("month");
    year = document.getElementById("year");
    currentMonth = new Date().getMonth();
    currentYear = new Date().getFullYear().toString().substr(2,2);
    cvv = document.getElementById("cc-cvv");
    
    if (!email.value){
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
    else if (!zipcode.value){
    	alert("Zipcode is a required field");
    	message.innerHTML = "Zipcode is a required field";
    	return false;
    }
    else if ((zip_Pattern.test(zipcode.value) == false) && (zipcode.value)){
    	alert("Zipcode must contain exactly 7 digits");
    	message.innerHTML = "Zipcode must contain exactly 7 digits";
    	return false;
    }
    else if (!ccNumber.value){
    	alert("Credit card number is a required field");
    	message.innerHTML = "Credit card number is a required field";
    	return false;
    }
    else if (creditCard == "visa"){
    	if (((ccNumber.value.length != 13) || (ccNumber.value.length != 16) || (ccNumber.value.length != 19)) || (ccNumber.value.charAt(0) != '4')){
    		alert("Credit card format does not apply");
        	message.innerHTML = "Credit card format does not apply";
        	return false;
    	}
    }
    else if (creditCard == "mastercard"){
    	if (ccNumber.value.length != 16) {
    		alert("Credit card format does not apply");
        	message.innerHTML = "Credit card format does not apply";
        	return false;
    	}
    }
    else if (creditCard == "amex"){
    	if ((ccNumber.value.length != 15) || (ccNumber.value.charAt(0) != '3') || ((ccNumber.value.charAt(1) != '4') || (ccNumber.value.charAt(1) != '7'))){
    		alert("Credit card format does not apply");
        	message.innerHTML = "Credit card format does not apply";
        	return false;
    	}
    }
    else if (!month.value){
    	alert("Expiration date is a required field");
    	message.innerHTML = "Expiration date is a required field";
    	return false;
    }
    else if (!year.value){
    	alert("Expiration date is a required field");
    	message.innerHTML = "Expiration date is a required field";
    	return false;
    }
    else if ((Number(month.value) < 1) || (Number(month.value) > 12) || (Number(month.value) < currentMonth) || (Number(year.value) < Number(currentYear))){
    	alert("Expiration date is not correct");
    	message.innerHTML = "Expiration date is not correct";
    	return false;
    }
    else if (!cvv.value){
    	alert("CVV is a required field");
    	message.innerHTML = "CVV is a required field";
    	return false;
    }
    else if (cvv.value.length != 3){
    	alert("CVV is not of correct length");
    	message.innerHTML = "CVV is not of correct length";
    	return false;
    }	
    return true;
}



