package purchasesViewAdmin;

import java.sql.Timestamp;

public class PurchaseObject {
	// USERS.USERNAME, USERS.TELEPHONE ,BOOKS.TITLE ,BOOKS.ID,BOOKS.PRICE
	private String Username,Telephone,Title,Price;
	private int UserId,BookId;
	private Timestamp TimePurchased;
	
// info for admin
	public PurchaseObject (String username,String telephone,String title,int bookID,String price,Timestamp timePurchased) {
		Username=username;
		Telephone=telephone;
		Title=title;
		BookId=bookID	;	
		Price=price;
		TimePurchased=timePurchased;
		
	}
	



	public String getUsername() {
		return Username;
	}


	public void setUsername(String username) {
		Username = username;
	}


	public String getTelephone() {
		return Telephone;
	}


	public void setTelephone(String telephone) {
		Telephone = telephone;
	}


	public String getTitle() {
		return Title;
	}


	public void setTitle(String title) {
		Title = title;
	}


	public String getPrice() {
		return Price;
	}


	public void setPrice(String price) {
		Price = price;
	}


	public int getBookId() {
		return BookId;
	}


	public void setBookId(int bookId) {
		BookId = bookId;
	}


	public Timestamp getTimePurchased() {
		return TimePurchased;
	}


	public void setTimePurchased(Timestamp timePurchased) {
		TimePurchased = timePurchased;
	}
	

}
