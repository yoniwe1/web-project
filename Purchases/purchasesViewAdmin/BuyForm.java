package purchasesViewAdmin;

public class BuyForm {
	private String EncryUserID;
	private String Firstname,Lastname,Address,City,Country,Zip,Email;
	private String CardName,CcNum,Month,Year,Cvv;
	private int UserID,BookID;
	private int BuyID;

 
	
	public BuyForm(int bookID,String userID,String firstname,String lastname, String email
			,String address,String city,String country,String zip,String cardName,String ccNum,
			String month,String year,String cvv) {
		BookID=bookID;
		EncryUserID=userID;
		Firstname=firstname;
		Lastname=lastname;
		Email=email;
		Address=address;
		City=city;
		Country=country;
		Zip=zip;
		CardName=cardName;
		CcNum=ccNum;
		Month=month;
		Year=year;
		Cvv=cvv;
	}
	
	//check if was bought already in request
	public BuyForm(int userId,int bookId) {
		UserID=userId;
		BookID=bookId;
	
	}
	// for checking "was already bought?" in response
	public BuyForm(int buyID) {
		BuyID=buyID;
	
	
	}
	
	public String getEncryUserID() {
		return EncryUserID;
	}

	public void setEncryUserID(String encryUserID) {
		EncryUserID = encryUserID;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getZip() {
		return Zip;
	}

	public void setZip(String zip) {
		Zip = zip;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getCardName() {
		return CardName;
	}

	public void setCardName(String cardName) {
		CardName = cardName;
	}

	public String getCcNum() {
		return CcNum;
	}

	public void setCcNum(String ccNum) {
		CcNum = ccNum;
	}

	public String getMonth() {
		return Month;
	}

	public void setMonth(String month) {
		Month = month;
	}

	public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		Year = year;
	}

	public String getCvv() {
		return Cvv;
	}

	public void setCvv(String cvv) {
		Cvv = cvv;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public int getBookID() {
		return BookID;
	}

	public void setBookID(int bookID) {
		BookID = bookID;
	}

	public String getFirstname() {
		return Firstname;
	}

	public void setFirstname(String firstname) {
		Firstname = firstname;
	}

	public String getLastname() {
		return Lastname;
	}

	public void setLastname(String lastname) {
		Lastname = lastname;
	}

	public int getBuyID() {
		return BuyID;
	}
	public void setBuyID(int buyID) {
		BuyID = buyID;
	}

}
