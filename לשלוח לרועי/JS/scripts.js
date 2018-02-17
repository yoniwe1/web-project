/**
 * 
 */

/*function viewDate() {
    var date = new Date();
    document.getElementById("a").innerHTML = date;
}*/

function validateForm() {
	    var email, emailPattern,address, zipcode, city, phonenumber, zip_phonePattern, cellphonenumber, cityPattern, username, nickname, psw, psw_repeat;
	    username = document.getElementById("username");
	    nickname = document.getElementById("nickname");
	    email = document.getElementById("email");
	    emailPattern = /[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/;
	    address = document.getElementById("address");
	    zip_phonePattern = /[0-9]{7}/;
	    zipcode=document.getElementById("zipcode");
	    city = document.getElementById("city");
	    cityPattern = /^([a-zA-Z]|\s)*$/;
	    phonenumber = document.getElementById("phonenumber");
	    cellphonenumber = document.getElementById("cellphonenumber");
	    psw = document.getElementById("psw");
	    psw_repeat = document.getElementById("psw-repeat");
	    username.setCustomValidity('');
	    email.setCustomValidity('');
	    address.setCustomValidity('');
	    zipcode.setCustomValidity('');
	    city.setCustomValidity('');
	    phonenumber.setCustomValidity('');
	    cellphonenumber.setCustomValidity('');
	    nickname.setCustomValidity('');
	    psw.setCustomValidity('');
	    psw_repeat.setCustomValidity('');
	    
	    
	    if (!username.value){
	    	username.setCustomValidity('User name is a required field');
	    	return false;
	    }
	    else if (cityPattern.test(username.value) == false){
	    	username.setCustomValidity('User name format is incorrect');
	    	return false;
	    }
	    else if (!email.value){
	    	email.setCustomValidity('Email is a required field');
	    	return false;
	    }
	    else if (emailPattern.test(email.value) == false){
	    	email.setCustomValidity('Email must be of the format: user@domain');
	    	return false;
	    }
	    if (!address.value){
	    	address.setCustomValidity('Address is a required field');
	    	return false;
	    }
	    else if (!zipcode.value){
	    	zipcode.setCustomValidity('Zipcode is a required field');
	    	return false;
	    }
	    else if ((zip_phonePattern.test(zipcode.value) == false) && (zipcode.value)){
	    	zipcode.setCustomValidity('Zipcode must contain exactly 7 digits');
	    	return false;
	    }
	    else if (!city.value){
	    	city.setCustomValidity('City is a required field');
	    	return false;
	    }
	    else if ((cityPattern.test(city.value) == false) && (city.value)){
	    	city.setCustomValidity('City must contain only uppercase and lowercase letters');
	    	return false;
	    }
	    else if (city.value.length < 3){
	    	city.setCustomValidity('City must contain 3 or more characters');
	    	return false;
	    }
	    else if (!phonenumber.value){
	    	phonenumber.setCustomValidity('Phone number is a required field');
	    	return false;
	    }
	    else if ((zip_phonePattern.test(phonenumber.value) == false) && (phonenumber.value)){
	    	phonenumber.setCustomValidity('Phone number must contain exactly 7 digits');
	    	return false;
	    }
	    else if (!cellphonenumber.value){
	    	cellphonenumber.setCustomValidity('Cell phone number is a required field');
	    	return false;
	    }
	    else if ((zip_phonePattern.test(cellphonenumber.value) == false) && (cellphonenumber.value)){
	    	cellphonenumber.setCustomValidity('Cell Phone number must contain exactly 7 digits');
	    	return false;
	    }
	    else if (!nickname.value){
	    	nickname.setCustomValidity('Nickname is a required field');
	    	return false;
	    }
	    else if (!psw.value){
	    	psw.setCustomValidity('Password is a required field');
	    	return false;
	    }
	    else if (psw_repeat.value !== psw.value){
	    	psw_repeat.setCustomValidity('Password not reapeated correctly');
	    	return false;
	    }
	    return true;
}

function validatePurchase() {
    var email, emailPattern,zipcode, city, zip_phonePattern, cityPattern;
    var ccNumber, month, year, currentMonth, currentYear, cvv, firstname, lastname, address;
    var visa, master, amex, cardName, visaPattern, masterPattern, amexPattern;
    firstname = document.getElementById("firstName");
    lastname = document.getElementById("lastName");
    email = document.getElementById("email");
    emailPattern = /[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/;
    address = document.getElementById("address");
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
   
    firstname.setCustomValidity('');
    lastname.setCustomValidity('');
    email.setCustomValidity('');
    address.setCustomValidity('');
    city.setCustomValidity('');
    zipcode.setCustomValidity('');
    cardName.setCustomValidity('');
    ccNumber.setCustomValidity('');
    month.setCustomValidity('');
    year.setCustomValidity('');
    cvv.setCustomValidity('');    
    
    if (!firstname.value){
    	firstname.setCustomValidity('First name is a required field');
    	return false;
    }
    else if (cityPattern.test(firstname.value) == false){
    	firstname.setCustomValidity('First name format is incorrect');
    	return false;
    }
    else if (!lastname.value){
    	lastname.setCustomValidity('Last name is a required field');
    	return false;
    }
    else if (cityPattern.test(lastname.value) == false){
    	lastname.setCustomValidity('Last name format is incorrect');
    	return false;
    }
    else if (!email.value){
    	email.setCustomValidity('Email is a required field');
    	return false;
    }
    else if ((emailPattern.test(email.value) == false) && (email.value)){
    	email.setCustomValidity('Email must be of the format: user@domain');
    	return false;
    }
    else if (!address.value){
    	address.setCustomValidity('Address is a required field');
    	return false;
    }
    else if (!city.value){
    	city.setCustomValidity('City is a required field');
    	return false;
    }
    else if (cityPattern.test(city.value) == false){
    	city.setCustomValidity('City must contain only uppercase and lowercase letters');
    	return false;
    }
    else if (city.value.length < 3){
    	city.setCustomValidity('City must contain 3 or more characters');
    	return false;
    }
    else if (!zipcode.value){
    	zipcode.setCustomValidity('Zipcode is a required field');
    	return false;
    }
    else if (zip_Pattern.test(zipcode.value) == false){
    	zipcode.setCustomValidity('Zipcode must contain exactly 7 digits');
    	return false;
    }
    else if (!cardName.value){
    	cardName.setCustomValidity('Credit card name is a required field');
    	return false;
    }
    else if (cityPattern.test(cardName.value) == false){
    	cardName.setCustomValidity('Card name format is incorrect');
    	return false;
    }
    else if (!ccNumber.value){
    	ccNumber.setCustomValidity('Credit card number is a required field');
    	return false;
    }
    else{
    	if (visa.checked){
    		if (!ccNumber.value.match(visaPattern)){
    	    	ccNumber.setCustomValidity('Credit card format does not apply');
    			return false;
    		}
    	}
    	else if (master.checked){
    		if (!ccNumber.value.match(masterPattern)){
    	    	ccNumber.setCustomValidity('Credit card format does not apply');
    			return false;
    		}
        }
    	else if (amex.checked){
    		if (!ccNumber.value.match(amexPattern)){
    	    	ccNumber.setCustomValidity('Credit card format does not apply');
    			return false;
    		}
        }
    }	 
    if (!month.value){
    	month.setCustomValidity('Month is a required field');
       	return false;
    }
    else if (!year.value){
    	year.setCustomValidity('Year is a required field');
   		return false;
   	}
    else if ((!month.value.match(/^(0[1-9]|1[0-2])$/)) || (!year.value.match(/\d{2}$/)) || ((Number(year.value) == Number(currentYear)) && (Number(month.value) < (currentMonth+1))) || (Number(year.value) < Number(currentYear))){
    	year.setCustomValidity('Expiration date is not correct');
   		return false;
   	}
   	else if (!cvv.value){
    	cvv.setCustomValidity('CVV is a required field');
   		return false;
   	}
   	else if (!cvv.value.match(/\d{3}$/)){
    	cvv.setCustomValidity('CVV is not of correct format');
   		return false;
   	}
   	else{
   		return true;
   	}
}



