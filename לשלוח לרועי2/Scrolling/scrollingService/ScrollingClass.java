package scrollingService;

public class ScrollingClass {
	private int ID,BookID;
	private String EncryUserID,Location;


//for INSERT or UPDATE
public  ScrollingClass (int bookId, String userId,String location) {

	BookID=bookId;
	EncryUserID=userId;
	Location=location;
}

//for GET
public ScrollingClass (String location) {
	Location=location;
}

public String getEncryUserID() {
	return EncryUserID;
}

public void setEncryUserID(String encryUserID) {
	EncryUserID = encryUserID;
}

public int getID() {
	return ID;
}



public void setID(int iD) {
	ID = iD;
}



public int getBookID() {
	return BookID;
}



public void setBookID(int bookID) {
	BookID = bookID;
}

public String getLocation() {
	return Location;
}



public void setLocation(String location) {
	Location = location;
}




}