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

import entities.User;
import example.AppConstants;

/**
 * Servlet implementation class UsersAdmin
 * This servlet is responisble to manage  users details for admin 
 *  there is GET request to get all details of users
 * 	and DELETE request to delete any user in system
 *  for more information see documentaion in the function see GET and DELETE docs
 *  @see UsersAdmin#doGet(HttpServletRequest request, HttpServletResponse response)
 *   @see UsersAdmin#doDelete(HttpServletRequest request, HttpServletResponse response)
 */

@WebServlet(
		description = "Servlet to provide details about users", 
		urlPatterns = { 
				"/UsersAdmin", 
				"/UsersAdmin/name"
		})
public class UsersAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * this function send all details of users in system to admin
	 * @param userID
	 * @return int id, String username, String password, String email, String address, String telephone, String nickname, String description, String photoUrl)
	 * @throws IOException if parameter not as expected
	 * @throws SQLException An exception that provides information on a database access error or other errors. 
	 * @throws  NamingException This is the superclass of all exceptions thrown by operations in the Context and DirContext interfaces
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

try {
    		
        	//obtain users data source from Tomcat's context
    		Context context = new InitialContext();
    		BasicDataSource ds = (BasicDataSource)context.lookup(
    				getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
    		Connection conn = ds.getConnection();

    		Collection<User> usersResult = new ArrayList<User>(); 
    		String uri = request.getRequestURI();
    		if (uri.indexOf(AppConstants.NAME) != -1){//filter customer by specific name
    			PreparedStatement stmt;
    			try {
    				String name= request.getParameter("showUserID");
    				
    				stmt = conn.prepareStatement(AppConstants.SELECT_USER_BY_NAME_STMT);
    				name = name.replaceAll("\\%20", " ");
    				stmt.setString(1, name);
    				ResultSet rs = stmt.executeQuery();
    				while (rs.next()){// maybe there are many names like this'name'
    					usersResult.add(new User(rs.getInt(1),rs.getString(2),rs.getString(3),
    							rs.getString(4),rs.getString(5),rs.getString(6),
    							rs.getString(7),rs.getString(8),rs.getString(9)));
    				}
    				rs.close();
    				stmt.close();
    			} catch (SQLException e) {
    				getServletContext().log("Error while querying for customers", e);
    	    		response.sendError(500);//internal server error
    			}
    		}else{//get all users
    			Statement stmt;
    			try {
    				stmt = conn.createStatement();
    				ResultSet rs = stmt.executeQuery(AppConstants.SELECT_ALL_USERS_STMT);
    				while (rs.next()){
    					usersResult.add(new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
    							rs.getString(5),rs.getString(6),rs.getString(7),
    							rs.getString(8),rs.getString(9)));
    				}
    				rs.close();
    				stmt.close();
    			} catch (SQLException e) {
    				getServletContext().log("Error while querying for customers", e);
    	    		response.sendError(500);//internal server error
    			}

    		}

    		conn.close();
    		
    		Gson gson = new Gson();
        	//convert from customers collection to json
        	String userJsonResult = gson.toJson(usersResult, AppConstants.USER_COLLECTION);
        	response.addHeader("Content-Type", "application/json");
        	PrintWriter writer = response.getWriter();
        //	userJsonResult=userJsonResult.substring(1, userJsonResult.length()-1); //delete  [ .............]  in the string
        	writer.print(userJsonResult);
        	writer.flush();
        	writer.close();
    	} catch (SQLException | NamingException e) {
    		getServletContext().log("Error while closing connection", e);
    		response.sendError(500);//internal server error
    	}

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
		    		String IdToDeletestr=sb.toString();
		    		User DeleteUser = gson.fromJson(IdToDeletestr, User.class);
		    		final int IdToDelete=DeleteUser.getId();
		    				
		    		
		    		
		    		
		        	//obtain CustomerDB data source from Tomcat's context
		    		Context context = new InitialContext();
		    		BasicDataSource ds = (BasicDataSource)context.lookup(
		    				getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
		    		Connection conn = ds.getConnection();
		    		PreparedStatement stmt;
		    		PreparedStatement pstmt = conn.prepareStatement(AppConstants.DELETE_USER_STMT);
    				pstmt.setInt(1,IdToDelete);// here we insert parameter to delete query
    				int CountRowsAffected=pstmt.executeUpdate();
    				if(CountRowsAffected>0)
    					response.setStatus(HttpServletResponse.SC_OK);
    				else
    					response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
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
