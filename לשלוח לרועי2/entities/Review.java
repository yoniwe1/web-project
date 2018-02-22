package entities;

public class Review {// class  for initial DB in start ( with json file) and update from admin

	private int RequestedById,ReviewID,UserID,BookID,Approved;
	private String Review,EncryUserID;
	private boolean IsLike;
	
	//for insert
	public Review(int userId,int bookId,String review,int approved,boolean isLike) {
		UserID=userId;
		BookID=bookId;
		Review= review;
		Approved=approved;
		IsLike=isLike;
	}
	//for approve or decline by admin
	public Review(int id,String requestedBy ) {
		ReviewID=id;
		EncryUserID=requestedBy;
		
	}
	//for select query
	public Review(int reviewId,int userId,int bookId,String review,int approved,boolean isLike) {
		ReviewID=reviewId;
		UserID=userId;
		BookID=bookId;
		Review= review;
		Approved=approved;
		IsLike=isLike;
	}
	
	public String getEncryUserID() {
		return EncryUserID;
	}
	public void setEncryUserID(String encryUserID) {
		EncryUserID = encryUserID;
	}
	public int getReviewID() {
		return ReviewID;
	}
	public void setReviewID(int reviewID) {
		ReviewID = reviewID;
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
	public String getReview() {
		return Review;
	}
	public void setReview(String review) {
		Review = review;
	}
	public int getApproved() {
		return Approved;
	}
	public void setApproved(int approved) {
		Approved = approved;
	}
	public boolean getIsLike() {
		return IsLike;
	}
	public void setIsLike(boolean isLike) {
		IsLike = isLike;
	}
	public int getRequestedById() {
		return RequestedById;
	}
	public void setRequestedById(int requestedById) {
		RequestedById = requestedById;
	}
	
}
