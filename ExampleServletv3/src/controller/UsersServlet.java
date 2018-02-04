package controller;

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
import example.model.Customer;

/**
 * Servlet implementation class UsersServlet
 */
@WebServlet(
		description = "Servlet to provide details about customers", 
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

try {
    		
        	//obtain CustomerDB data source from Tomcat's context
    		Context context = new InitialContext();
    		BasicDataSource ds = (BasicDataSource)context.lookup(
    				getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
    		Connection conn = ds.getConnection();

    		Collection<User> usersResult = new ArrayList<User>(); 
    		String uri = request.getRequestURI();
    		if (uri.indexOf(AppConstants.NAME) != -1){//filter customer by specific name
    			String name = uri.substring(uri.indexOf(AppConstants.NAME) + AppConstants.NAME.length() + 1);
    			PreparedStatement stmt;
    			try {
    				stmt = conn.prepareStatement(AppConstants.SELECT_USER_BY_NAME_STMT);
    				name = name.replaceAll("\\%20", " ");
    				stmt.setString(1, name);
    				ResultSet rs = stmt.executeQuery();
    				while (rs.next()){// maybe there are many names like this'name'
    					usersResult.add(new User(rs.getString(1),rs.getString(2),rs.getString(3),
    							rs.getString(4),rs.getString(5),rs.getString(6),
    							rs.getString(7),rs.getString(8)));
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
    					usersResult.add(new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
    							rs.getString(5),rs.getString(6),rs.getString(7),
    							rs.getString(8)));
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
