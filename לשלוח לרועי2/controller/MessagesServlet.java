package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import com.google.gson.Gson;

import crypto.CryptoObj;
import entities.Message;
import example.AppConstants;
import viewModelReviews.UserReviewViewModel;

/**
 * Servlet to provide details about messages
 * Servlet implementation class MessagesServlet
 */

@WebServlet(
		description = "Servlet to provide details about messages", 
		urlPatterns = { 
				"/messages",
				"/messagesAdmin"
			
		})
public class MessagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessagesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * this function will messages from admin and from user
	 * @param string fromuserID
	 * @param string touserID
	 * @return int messageID, int clearfromuser, int cleartouser, string message, boolean isRead, Createddate timestamp
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		
try {
	String UserId= request.getParameter("EncryUserID");//encrypted
	UserId = CryptoObj.caesarCipherDecrypte(UserId);//clear
        	//obtain reviews DB data source from Tomcat's context
    		Context context = new InitialContext();
    		BasicDataSource ds = (BasicDataSource)context.lookup(
    				getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
    		Connection conn = ds.getConnection();

    		Collection<Message> MessageResult = new ArrayList<Message>(); 
    		PreparedStatement stmt;
    			try {
    				stmt = conn.prepareStatement(AppConstants.SELECT_MESSAGES_STMT);
    				stmt.setString(1, UserId);
    				stmt.setString(2, UserId);
    				ResultSet rs = stmt.executeQuery();
    				int clearFromUser,clearToUser;
    			
    				while (rs.next()){
    					clearFromUser=rs.getInt(2);
    					clearToUser=rs.getInt(3);
    					if(clearFromUser==21)// if the msg from admin so change it from 21 to "-1". security reasons
    						clearFromUser=-1;
    					if(clearToUser==21)// if the msg from admin so change it from 21 to "-1". security reasons
    						clearToUser=-1;//now the client will not know the ID of the admin
    					
   					MessageResult.add(new Message(rs.getInt(1),clearFromUser,clearToUser,rs.getString(4),rs.getBoolean(5),rs.getTimestamp(6)));
    				}
    				rs.close();
    				stmt.close();
    			} catch (SQLException e) {
    				getServletContext().log("Error while querying for customers", e);
    	    		response.sendError(500);//internal server error
    			}

    		

    		conn.close();
    		
    		Gson gson = new Gson();
        	//convert from customers collection to json
        	String getMessagesJsonResult = gson.toJson(MessageResult, AppConstants.MESSAGES_COLLECTION);
        	response.addHeader("Content-Type", "application/json");
        	PrintWriter writer = response.getWriter();
        	writer.print(getMessagesJsonResult);
        	writer.flush();
        	writer.close();
    	} catch (SQLException | NamingException e) {
    		getServletContext().log("Error while closing connection", e);
    		response.sendError(500);//internal server error
    	} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


	}

	/**
	 * this function will insert or update messages from and to users
	 * @param string encryuserID
	 * @param int touserID
	 * @param string message
	 * @param string fromuserID
	 * @param boolean isRead
	 * @return status of action
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// insert message
		try {

            StringBuilder sb = new StringBuilder();
			    BufferedReader reader = request.getReader();
			    try {
			        String line;
			        while ((line = reader.readLine()) != null) {
			            sb.append(line).append('\n');
			        }
			    } finally {
			        reader.close();
			    }
				Gson gson = new Gson();
				String insertMessage=sb.toString();
				Message MessageRequest = gson.fromJson(insertMessage, Message.class);
		
    
    		 String fromuserId=MessageRequest.getEncryUserID();// encrypted
    		 fromuserId = CryptoObj.caesarCipherDecrypte(fromuserId);//clear
    		
    		
    		 int toUserId=MessageRequest.getToUserID();
    		
    		 
    		 if(toUserId==-1)
    			 toUserId=21;
    		
    		final String msg=MessageRequest.getMessage();

    		
    	
         	//obtain con DB data source from Tomcat's context
        		Context context = new InitialContext();
        		BasicDataSource ds = (BasicDataSource)context.lookup(
        				getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
        		Connection conn = ds.getConnection();
        		PreparedStatement stmt;
        		
        		PreparedStatement pstmt = conn.prepareStatement(AppConstants.INSERT_MESSAGE_STMT);
    			
    				pstmt.setString(1,fromuserId);// here we insert parameter to insert query
    				pstmt.setInt(2,toUserId);
    				pstmt.setString(3,msg);
    				pstmt.setBoolean(4,false);
    			
   
    				pstmt.executeUpdate();
    				
    			

    			//commit update
    			conn.commit();
    			//close statements
    			pstmt.close();
    		

    		//close connection
    		conn.close();
    		

		}
		
		
		
	 catch (SQLException | NamingException e) {
		getServletContext().log("Error while closing connection", e);
		response.sendError(500);//internal server error
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	/**
	 * this function will update messages from and to users
	 * @param int messageID
	 * @return status of action
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 * @see HttpServlet#doPut(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//mark as read
		try {

            StringBuilder sb = new StringBuilder();
			    BufferedReader reader = request.getReader();
			    try {
			        String line;
			        while ((line = reader.readLine()) != null) {
			            sb.append(line).append('\n');
			        }
			    } finally {
			        reader.close();
			    }
				Gson gson = new Gson();
				String isReadMessage=sb.toString();
				Message isReadMessageRequest = gson.fromJson(isReadMessage, Message.class);
		
    		final int messageID=isReadMessageRequest.getMessageID();
    		
    

    		
    	
         	//obtain con DB data source from Tomcat's context
        		Context context = new InitialContext();
        		BasicDataSource ds = (BasicDataSource)context.lookup(
        				getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
        		Connection conn = ds.getConnection();
        		PreparedStatement stmt;
        		
        		PreparedStatement pstmt = conn.prepareStatement(AppConstants.UPDATE_READ_MESSAGE_STMT);
    			
    				pstmt.setInt(1,messageID);// here we insert parameter to insert query
    			
    				
    			
   
    				pstmt.executeUpdate();
    				
    			

    			//commit update
    			conn.commit();
    			//close statements
    			pstmt.close();
    		

    		//close connection
    		conn.close();
    		

		}
		
		
		
	 catch (SQLException | NamingException e) {
		getServletContext().log("Error while closing connection", e);
		response.sendError(500);//internal server error
	}
		
	}


}
