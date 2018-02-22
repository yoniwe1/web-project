package feedbackServices;

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
import java.util.HashMap;

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
import entities.User;
import example.AppConstants;
import viewModelReviews.LikeServer;
import viewModelReviews.LikesViewModel;
import viewModelReviews.UserReviewViewModel;

/**
 * Servlet to manage like requests
 * Servlet implementation class LikesServlet
 */


@WebServlet(
		description = "Servlet to provide details about customers", 
		urlPatterns = { 
				"/LikesServlet", 
				"/whoLike",
				"/sumLike",
				"/dolike"
		})
public class LikesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * this function will send all likes in system to admin
	 * @param int bookID
	 * @param string book title
	 * @param int userID
	 * @param string nickname
	 * @param boolean isLike
	 * @return sum of likes
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get all nicknames that like the book 
		//or  sum of likes each book has

try {
    		
        	//obtain CustomerDB data source from Tomcat's context
    		Context context = new InitialContext();
    		BasicDataSource ds = (BasicDataSource)context.lookup(
    				getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
    		Connection conn = ds.getConnection();

    		Collection<LikesViewModel> sumLikesResult = new ArrayList<LikesViewModel>(); 

    			PreparedStatement stmt;
    			try {
    		   
    		           
    		            String bookwholiked= request.getParameter("BookID");
    				
    				stmt = conn.prepareStatement(AppConstants.SELECT_NICKNAMES_THAT_LIKE_STMT);
    				
    				stmt.setString(1, bookwholiked);
    				
    				
    				ResultSet rs = stmt.executeQuery();
    				while (rs.next()){
    					sumLikesResult.add(new LikesViewModel(rs.getInt(1),rs.getInt(2),rs.getString(3)));
    				}
    				rs.close();
    				stmt.close();
    			} catch (SQLException e) {
    				getServletContext().log("Error while querying for customers", e);
    	    		response.sendError(500);//internal server error
    			}

    		

    		conn.close();
    		
    		Gson gson = new Gson();
        	//convert from LIKES collection to json
        	String likesJsonResult = gson.toJson(sumLikesResult, AppConstants.LIKESVIEWMODEL_COLLECTION);
        	response.addHeader("Content-Type", "application/json");
        	PrintWriter writer = response.getWriter();
        //	userJsonResult=userJsonResult.substring(1, userJsonResult.length()-1); //delete  [ .............]  in the string
        	writer.print(likesJsonResult);
        	writer.flush();
        	writer.close();
    	} catch (SQLException | NamingException e) {
    		getServletContext().log("Error while closing connection", e);
    		response.sendError(500);//internal server error
    	}
	
	
	
	}

	/**
	 * this function will set likes or unlikes and send to the admin the userid , bookid and like/unlike by the user for the specific book
	 * @param int bookID
	 * @param string EncryUserID
	 * @param boolean isLike
	 * @param int userid
	 * @param string review
	 * @param int approved
	 * @return status of action
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 * @see HttpServlet#doPut(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//set like or unlike. get bookid userid and (true or false)

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
				String islikeStr=sb.toString();
				LikeServer  setlike = gson.fromJson(islikeStr, LikeServer.class);
		
    		final int bookid=setlike.getBookID();
    	
    		 String userid=setlike.getEncryUserID();// encrypted
    		 userid = CryptoObj.caesarCipherDecrypte(userid);//clear
    		
    		final boolean islike=setlike.isIslike();

    		
   
         	//obtain set like data source from Tomcat's context
        		Context context = new InitialContext();
        		BasicDataSource ds = (BasicDataSource)context.lookup(
        				getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
        		Connection conn = ds.getConnection();
        		PreparedStatement stmt;
        		
        		PreparedStatement pstmt = conn.prepareStatement(AppConstants.UPDATE_LIKE_STMT);
    			
    				pstmt.setBoolean(1,islike);// here we insert parameter to insert query
    				pstmt.setString(2,userid);
    				pstmt.setInt(3,bookid);
    		
    				int res=pstmt.executeUpdate();
    				
    				if(res==0) {// the user put likes before review so reviewString will be"" and approved=3
    					 pstmt = conn.prepareStatement(AppConstants.INSERT_LIKES_STMT);	
    						pstmt.setString(1,userid);// here we insert parameter to insert query
    	    				pstmt.setInt(2,bookid);
    	    				pstmt.setString(3," ");
    	    				pstmt.setInt(4,3);
    	    				pstmt.setBoolean(5,islike);
    	    				pstmt.executeUpdate();
    	    		
    				}
    			

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

}
