package entities;

public class User {// 8 fields 
	private int Id;
	private String Username,EncryUserID;
	private String Password, Email,Address,Telephone,Nickname,Description,PhotoUrl;//customer "schema"
	
	public User(int id,String username, String password,String email ,String address, String telephone,String nickname,String description,String photoUrl) {
		Id=id;
		Username = username;
		Password = password;
		Email = email;
		Address = address;
		Telephone = telephone;
		Nickname = nickname;
		Description = description;
		PhotoUrl = photoUrl;
	
	}
	public User(String username, String password,String email ,String address, String telephone,String nickname,String description,String photoUrl) {
	
		Username = username;
		Password = password;
		Email = email;
		Address = address;
		Telephone = telephone;
		Nickname = nickname;
		Description = description;
		PhotoUrl = photoUrl;
	
	}
	//for delete by admin
	public User(String requestBtId,int id)
	{
		EncryUserID=requestBtId;
		Id=id;
	}
	
	public String getEncryUserID() {
		return EncryUserID;
	}
	public void setEncryUserID(String encryUserID) {
		EncryUserID = encryUserID;
	}
	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getTelephone() {
		return Telephone;
	}

	public void setTelephone(String telephone) {
		Telephone = telephone;
	}

	public String getNickname() {
		return Nickname;
	}

	public void setNickname(String nickname) {
		Nickname = nickname;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPhotoUrl() {
		return PhotoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		PhotoUrl = photoUrl;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getUsername() {
		return Username;
	}

	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}



	
}
