package viewModelReviews;

public class UserReviewViewModel {
	private String EncryUserID;
	private int ReviewID, BookID,UserID;
	private String Nickname,Review;
	
	// for 'select FOR admin':
	public UserReviewViewModel(int reviewId,int bookId,int userId,String nickname,String review) {
		ReviewID=reviewId;
		BookID=bookId;
		 UserID=userId;
		 Nickname=nickname;
		 Review=review;
	}
	
	
	// for 'select':
	public UserReviewViewModel(String nickname,String review) {
		 Nickname=nickname;
		 Review=review;
	}
	// for 'insert':
	public UserReviewViewModel(int bookId,int userId,String review) {
		 BookID=bookId;
		 UserID=userId;
		 Review=review;
	}
	
	
	//for filter and  decryption
	public UserReviewViewModel(int bookId,String userId,String review) {
		 BookID=bookId;
		 EncryUserID=userId;
		 Review=review;
	}

	public String getEncryUserID() {
		return EncryUserID;
	}


	public void setEncryUserID(String encryUserID) {
		EncryUserID = encryUserID;
	}


	public int getBookID() {
		return BookID;
	}

	public void setBookID(int bookID) {
		BookID = bookID;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public String getNickname() {
		return Nickname;
	}

	public void setNickname(String nickname) {
		Nickname = nickname;
	}

	public String getReview() {
		return Review;
	}

	public void setReview(String review) {
		Review = review;
	}

}
