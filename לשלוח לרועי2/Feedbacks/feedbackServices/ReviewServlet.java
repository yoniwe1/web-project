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
import entities.Book;
import entities.Review;
import entities.User;
import example.AppConstants;
import viewModelReviews.UserReviewViewModel;

/**
 * Servlet to manage reviews from users side
 * Servlet implementation class ReviewServlet
 */
@WebServlet(
		description = "Servlet to provide details about reviews", 
		urlPatterns = { 
				"/reviews"
				
				
		})
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * this function will send all approved requests in system to users view
	 * @param int bookID
	 * @return string nickname, string review
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// for all users. approved=true in query
try {
    		
        	//obtain reviews DB data source from Tomcat's context
    		Context context = new InitialContext();
    		BasicDataSource ds = (BasicDataSource)context.lookup(
    				getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
    		Connection conn = ds.getConnection();

    		Collection<UserReviewViewModel> getReviewsResult = new ArrayList<UserReviewViewModel>(); 
    		PreparedStatement stmt;
    			try {
    				
    				
    				
    		          String bookIdReview= request.getParameter("BookID");
      				
      				stmt = conn.prepareStatement(AppConstants.SELECT_JOIN_REVIEWS_STMT);
      				
      				stmt.setString(1, bookIdReview);

      				ResultSet rs = stmt.executeQuery();

    				while (rs.next()){
    					getReviewsResult.add(new UserReviewViewModel(rs.getString(1),rs.getString(2)));
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
        	String getreviewJsonResult = gson.toJson(getReviewsResult, AppConstants.USERREVIEWVIEWMODEL_COLLECTION);
        	response.addHeader("Content-Type", "application/json");
        	PrintWriter writer = response.getWriter();
        //	userJsonResult=userJsonResult.substring(1, userJsonResult.length()-1); //delete  [ .............]  in the string
        	writer.print(getreviewJsonResult);
        	writer.flush();
        	writer.close();
    	} catch (SQLException | NamingException e) {
    		getServletContext().log("Error while closing connection", e);
    		response.sendError(500);//internal server error
    	}

	}

	/**
	 * this function will insert or update reviews from users
	 * @param int userID
	 * @param string encryuserID
	 * @param int bookID
	 * @param int reviewID
	 * @param string review
	 * @param int approved
	 * @param boolean isLike
	 * @return status of action
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// insert review with approved==0 but or insert or update
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
				String insertReview=sb.toString();
				UserReviewViewModel userReviewRequest = gson.fromJson(insertReview, UserReviewViewModel.class);
		
    		 String userId=userReviewRequest.getEncryUserID();// encrypted
    		userId = CryptoObj.caesarCipherDecrypte(userId);//clear
    		final int bookId=userReviewRequest.getBookID();
    		
    		final String review=userReviewRequest.getReview();

    		
    	
         	//obtain con DB data source from Tomcat's context
        		Context context = new InitialContext();
        		BasicDataSource ds = (BasicDataSource)context.lookup(
        				getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
        		Connection conn = ds.getConnection();
        		PreparedStatement stmt;
        		
        		
        		// because maybe he did in the past only LIKE , and now he want to insert review
        		PreparedStatement pstmt = conn.prepareStatement(AppConstants.UPDATE_REVIEW_APPROVED_ZERO_STMT);
        
        		pstmt.setString(1,review);
    				pstmt.setString(2,userId);// here we insert parameter to insert query
    				pstmt.setInt(3,bookId);
    			int res=	pstmt.executeUpdate();
    				
    				if(res==0) {
    			   		 pstmt = conn.prepareStatement(AppConstants.INSERT_REVIEWS_STMT);
    		    			
    	    				pstmt.setString(1,userId);// here we insert parameter to insert query
    	    				pstmt.setInt(2,bookId);
    	    				pstmt.setString(3,review);
    	    				pstmt.setInt(4,0);
    	    				pstmt.setString(5,"false");
    	   
    	    				pstmt.executeUpdate();
    	    				
    				}
    			

    			//commit update
    			conn.commit();
    			//close statements
    			pstmt.close();
    		

    		//close connection
    		conn.close();
    		

		}
		
		
		
	 catch (SQLException | NamingException  e) {
		getServletContext().log("Error while closing connection", e);
		response.sendError(500);//internal server error
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	
}
