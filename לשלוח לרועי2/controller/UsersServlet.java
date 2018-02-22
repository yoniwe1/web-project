package controller;


import java.io.File;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.SQLException;
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
import com.google.gson.reflect.TypeToken;

import entities.Book;
import entities.User;
import example.AppConstants;
import example.model.Customer;





/**
 * This servlet is responisble to manage  users details for regular users
 * Servlet implementation class UsersServlet
 */
@WebServlet(
		description = "Servlet to provide details about users", 
		urlPatterns = { 
				"/users", 
				"/users/name/*"
		})
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public boolean validation_register(String username,String password,String email,String address,
    		String telephone,String nickname,String description,String photourl) {

    	Pattern generalPattern = Pattern.compile("^([a-zA-Z]|\\s)*$");
		Pattern mailPattern = Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$");
		Pattern zipPattern = Pattern.compile("[0-9]{7}");
		Pattern phonePattern = Pattern.compile("[0-9]{10}");
		Matcher mail = mailPattern.matcher(email);	//check Email with Regex
		boolean isMail = mail.matches();
		
		Matcher Telephone = phonePattern.matcher(telephone);	//check phone with Regex
		boolean isCellphone = Telephone.matches();
		
		if (username == null || username.isEmpty())
		{
			
			return false;
		}
		else if (email == null || email.isEmpty())
		{
		
			return false;
		}
		else if (!isMail) {	//check regex of mail
		
			return false;
		}
		else if (!isCellphone) {	//check regex of phone
			
			return false;
		}
		else if (address == null || address.isEmpty())
		{
		
			return false;
		}
		

		else if (telephone == null || telephone.isEmpty())
		{
			
			return false;
		}
		else if (nickname == null || nickname.isEmpty())
		{

			return false;
		}
		else if (password == null || password.isEmpty())
		{

			return false;
		}


		return true;
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	
    }

	/**
	 * this function will insert or update details about users
	 * @param string Username
	 * @param string password
	 * @param string email
	 * @param string address
	 * @param string telephone
	 * @param string nickname
	 * @param string description
	 * @param string photoURL
	 * @return status of action
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {

            StringBuilder sb = new StringBuilder();
			    BufferedReader reader1 = request.getReader();
			    try {
			        String line;
			        while ((line = reader1.readLine()) != null) {
			            sb.append(line).append('\n');
			        }
			    } finally {
			        reader1.close();
			    }
				Gson gson = new Gson();
				String RegisterUserStr=sb.toString();
				User RegisterUser = gson.fromJson(RegisterUserStr, User.class);
		
    		final String username=RegisterUser.getUsername();
    		final String password=RegisterUser.getPassword();
    		final String email=RegisterUser.getEmail();
    		final String address=RegisterUser.getAddress();
    		final String telephone=RegisterUser.getTelephone();
    		final String nickname=RegisterUser.getNickname();
    		final String description=RegisterUser.getDescription();
    		final String photourl=RegisterUser.getPhotoUrl();
    		
    		if(!validation_register(username, password, email, address,
    	    		 telephone, nickname, description, photourl)) { //validate parameters
    			throw new  SQLException();
    		}
         	//obtain CustomerDB data source from Tomcat's context
        		Context context = new InitialContext();
        		BasicDataSource ds = (BasicDataSource)context.lookup(
        				getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
        		Connection conn = ds.getConnection();
        		PreparedStatement stmt;
        		
        		PreparedStatement pstmt = conn.prepareStatement(AppConstants.INSERT_USERS_STMT);
    			
    				pstmt.setString(1,username);// here we insert parameter to insert query
    				pstmt.setString(2,password);
    				pstmt.setString(3,email);
    				pstmt.setString(4,address);
    				pstmt.setString(5,telephone);
    				pstmt.setString(6,nickname);
    				pstmt.setString(7,description);
    				pstmt.setString(8,photourl);
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
