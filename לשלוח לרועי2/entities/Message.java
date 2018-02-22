package entities;

import java.sql.Timestamp;

public class Message  {
	private String EncryUserID;
	private int MessageID,FromUserID,ToUserID,UserID;
	private String Message;
	private boolean IsRead;
	private Timestamp CreatedDate;
	
	//for get
	public Message(int messageID,int fromUserId,int toUserId,String message,boolean isRead,Timestamp createdDate) {
		MessageID=messageID;
		FromUserID=fromUserId;
		ToUserID=toUserId;
		Message=message;
		IsRead=isRead;
		CreatedDate=createdDate;
	}
	
	
	//for initials
	public Message(int fromUserId,int toUserId,String message,boolean isRead) {
		FromUserID=fromUserId;
		ToUserID=toUserId;
		Message=message;
		IsRead=isRead;
	
	}
	//for post
/*	public Message(int messageID,int userId) {
		MessageID=messageID;
		UserID=userId;
		
	}*/
	public Message(int messageID,String encryUserId) {
		MessageID=messageID;
		EncryUserID=encryUserId;
		
	}
	
	public Message(String fromUserId,int toUserId,String message) {
		EncryUserID=fromUserId;
		ToUserID=toUserId;
		Message=message;
		
	}
	

	public String getEncryUserID() {
		return EncryUserID;
	}

	public void setEncryUserID(String encryUserID) {
		EncryUserID = encryUserID;
	}

	public int getMessageID() {
		return MessageID;
	}

	public void setMessageID(int messageID) {
		MessageID = messageID;
	}

	public int getFromUserID() {
		return FromUserID;
	}

	public void setFromUserID(int fromUserId) {
		FromUserID = fromUserId;
	}

	public int getToUserID() {
		return ToUserID;
	}

	public void setToUserID(int toUserId) {
		ToUserID = toUserId;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public boolean isIsRead() {
		return IsRead;
	}

	public void setIsRead(boolean isRead) {
		IsRead = isRead;
	}

	public Timestamp getCreatedDate() {
		return CreatedDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		CreatedDate = createdDate;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}
	

}
