package viewModelReviews;

public class LikesViewModel {

	private int BookID,SumLikes;
	private int UserID;
	private String Nickname;
	
	
	
	// for 'select': sum of likes
	public LikesViewModel(int bookId,int sumLikes) {
		 BookID=bookId;
		 SumLikes=sumLikes;
	
	}
	public LikesViewModel(int bookId ) {
		 BookID=bookId;
	
	
	}
	
	// to get nicknames who like of book the user ask for
	public LikesViewModel(int bookId,int userId,String nickname) {
		 BookID=bookId;
		 UserID=userId;
		 Nickname=nickname;
	
	}

	public int getBookID() {
		return BookID;
	}

	public void setBookID(int bookID) {
		BookID = bookID;
	}

	public int getSumLikes() {
		return SumLikes;
	}

	public void setSumLikes(int sumLikes) {
		SumLikes = sumLikes;
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
	
	
}
