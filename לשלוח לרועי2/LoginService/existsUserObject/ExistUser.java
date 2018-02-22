package existsUserObject;

public class ExistUser {
	private String UserID;
	private String Username;
	private String Password;
	private String Nickname;
	
	public ExistUser(String username,String password) {
		Username=username;
		Password=password;
		
	}
	// return nickname and crypto id. do nothing with username(just to prevent conflict with upper constructor)
	public ExistUser(String nickName,String id,String username) {
		Nickname=nickName;
		UserID=id;

	}
	
	public ExistUser( String userId) {// just from "logout" in FilterRegularUsers
		UserID=userId;
		
	}
	
	

	// set and get right below

	public String getUsername() {
		return Username;
	}


	public void setUsername(String username) {
		Username = username;
	}


	public String getPassword() {
		return Password;
	}


	public void setPassword(String password) {
		Password = password;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getNickname() {
		return Nickname;
	}
	public void setNickname(String nickname) {
		Nickname = nickname;
	}

	
}
