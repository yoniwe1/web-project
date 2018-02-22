package purchasesViewAdmin;

import java.sql.Timestamp;

public class EbookObject {
	  private String Title,Url,Description,Image;
		private Timestamp TimePurchased;
		private int BookID,SumLikes;
		private boolean IsLike;
		public EbookObject( int bookID,String title,String url ,String description,String image,Timestamp timePurchased,int sumLikes,boolean isLike) {
			BookID=bookID;
			Title = title;
			Url = url;
			Description = description;
			Image = image;
			TimePurchased=timePurchased;
			SumLikes=sumLikes;
			IsLike=isLike;
		}
		public String getTitle() {
			return Title;
		}
		public void setTitle(String title) {
			Title = title;
		}
		public String getUrl() {
			return Url;
		}
		public void setUrl(String url) {
			Url = url;
		}
		public String getDescription() {
			return Description;
		}
		public void setDescription(String description) {
			Description = description;
		}
		public String getImage() {
			return Image;
		}
		public void setImage(String image) {
			Image = image;
		}
		public Timestamp getTimePurchased() {
			return TimePurchased;
		}
		public void setTimePurchased(Timestamp timePurchased) {
			TimePurchased = timePurchased;
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
			this.SumLikes = sumLikes;
		}
		public boolean isIsLike() {
			return IsLike;
		}
		public void setIsLike(boolean isLike) {
			IsLike = isLike;
		}

	  
}
