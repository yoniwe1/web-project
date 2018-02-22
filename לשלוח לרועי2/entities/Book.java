package entities;
import java.util.LinkedList;
import java.util.List;
public class Book {

	private int BookID,SumLike;
    private String Title,Url,Price,Description,Image;
    private boolean IsLike,Isbuy;
    
/*    private List<String> Categories;
    private List<String> Tags;
    */
	public Book( String title,String url ,String price,String description,String image) {
	
		Title = title;
		Url = url;
		Price = price;
		Description = description;
		Image = image;
		
	}
	public Book( int bookID,String title,String url ,String price,String description,String image) {
		BookID=bookID;
		Title = title;
		Url = url;
		Price = price;
		Description = description;
		Image = image;
		
	}
	public Book( int bookID,String title,String url ,String price,String description,String image,int sumLike,boolean islike,boolean isbuy) {
		BookID=bookID;
		Title = title;
		Url = url;
		Price = price;
		Description = description;
		Image = image;
		SumLike=sumLike;
		IsLike=islike;
		Isbuy=isbuy;
	}

	public int getBookID() {
		return BookID;
	}

	public void setBookID(int bookID) {
		BookID = bookID;
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

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
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
	public int getSumLike() {
		return SumLike;
	}
	public void setSumLike(int sumLike) {
		SumLike = sumLike;
	}
	public boolean isIsLike() {
		return IsLike;
	}
	public void setIsLike(boolean isLike) {
		IsLike = isLike;
	}
	public boolean isIsbuy() {
		return Isbuy;
	}
	public void setIsbuy(boolean isbuy) {
		Isbuy = isbuy;
	}


	

}
