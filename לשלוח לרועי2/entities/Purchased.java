package entities;

import java.sql.Timestamp;

public class Purchased {
	private int PurchasedID,UserID, BookID;
	private Timestamp TimePurchased;

	public Purchased( int userId,int bookId) {
		UserID=userId;
		BookID=bookId;
	}
	public Purchased( int purchasedId,int userId,int bookId,Timestamp time) {
		PurchasedID=purchasedId;
		UserID=userId;
		BookID=bookId;
		TimePurchased=time;
	}
	public int getPurchasedID() {
		return PurchasedID;
	}
	public void setPurchasedID(int purchasedID) {
		PurchasedID = purchasedID;
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
	
}
