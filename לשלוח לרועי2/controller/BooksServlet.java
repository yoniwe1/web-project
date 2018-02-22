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
import example.AppConstants;

import com.google.gson.Gson;

import crypto.CryptoObj;
import entities.Book;
/**
 * Servlet to manage books
 * Servlet implementation class BooksServlet
 */


@WebServlet(
		description = "Servlet to provide details about books", 
		urlPatterns = { 
				"/books",
				"/BooksServlet", 
				"/books/name/*",
				"/booksAdmin"
		})
public class BooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BooksServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * this function will get books for user filtered by name or unfiltered
	 * @param string title
	 * @param string encryuserID
	 * @param string userID from reciews
	 * @param string userID from purchased
	 * @return int bookID, string title, string URL, string price, string description, string image, int likeCount, boolean isBuy, boolean isLike
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

try {
    		
        	//obtain CustomerDB data source from Tomcat's context
    		Context context = new InitialContext();
    		BasicDataSource ds = (BasicDataSource)context.lookup(
    				getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
    		Connection conn = ds.getConnection();

    		Collection<Book> booksResult = new ArrayList<Book>(); 
    		String uri = request.getRequestURI();
    		if (uri.indexOf(AppConstants.NAME) != -1){//filter book by specific name
    			String name = uri.substring(uri.indexOf(AppConstants.NAME) + AppConstants.NAME.length() + 1);
    			PreparedStatement stmt;
    			try {
    				stmt = conn.prepareStatement(AppConstants.SELECT_BOOK_BY_NAME_STMT);
    				name = name.replaceAll("\\%20", " ");
    				stmt.setString(1, name);
    				ResultSet rs = stmt.executeQuery();
    				while (rs.next()){// maybe there are many names like this'name'
    					booksResult.add(new Book(rs.getInt(1),rs.getString(2),rs.getString(3),
    							rs.getString(4),rs.getString(5),rs.getString(6)));
    				}
    				rs.close();
    				stmt.close();
    			} catch (SQLException e) {
    				getServletContext().log("Error while querying for customers", e);
    	    		response.sendError(500);//internal server error
    			}
    		}else{//get all books custom to user
    			PreparedStatement stmt;
    			try {
    				String userid= request.getParameter("EncryUserID");
    				userid = CryptoObj.caesarCipherDecrypte(userid);
    				stmt = conn.prepareStatement(AppConstants.SELECT_All_BOOKS_CUSTOM_STMT);
    				stmt.setString(1, userid);
    				stmt.setString(2, userid);
    				ResultSet rs = stmt.executeQuery();
    				while (rs.next()){
    					booksResult.add(new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
    							rs.getString(5),rs.getString(6),rs.getInt(7),rs.getBoolean(8),rs.getBoolean(9)));
    				}
    				rs.close();
    				stmt.close();
    			} catch (Exception e) {
    				getServletContext().log("Error while querying for customers", e);
    	    		response.sendError(500);//internal server error
    			}

    		}

    		conn.close();
    		
    		Gson gson = new Gson();
        	//convert from customers collection to json
        	String bookJsonResult = gson.toJson(booksResult, AppConstants.BOOK_COLLECTION);
        	response.addHeader("Content-Type", "application/json");
        	PrintWriter writer = response.getWriter();
        //	userJsonResult=userJsonResult.substring(1, userJsonResult.length()-1); //delete  [ .............]  in the string
        	writer.print(bookJsonResult);
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
