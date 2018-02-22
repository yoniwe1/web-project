package viewModelReviews;

public class LikeServer {

	private int BookID,UserID;
	private String EncryUserID;
	private boolean  Islike;
	
	public LikeServer(int bookId,String userId,boolean islike) {
		 BookID=bookId;
		 EncryUserID=userId;
		 Islike=islike;
	
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

	public boolean isIslike() {
		return Islike;
	}

	public void setIslike(boolean islike) {
		Islike = islike;
	}
	
	
}
