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

import entities.Review;
import example.AppConstants;
import viewModelReviews.UserReviewViewModel;

/**
 * Servlet to manage reviews from admin side
 * Servlet implementation class ReviewAdminServlet
 */
@WebServlet("/reviewsAdmin")
public class ReviewAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * this function will send all unapproved requests in system to admin
	 * @param HTTP request with no parameters
	 * @return int reviewID, int bookID, int userID, string nickname, string review
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// for admin. approved=false in query
		try {
		    		
		        	//obtain reviews DB data source from Tomcat's context
		    		Context context = new InitialContext();
		    		BasicDataSource ds = (BasicDataSource)context.lookup(
		    				getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
		    		Connection conn = ds.getConnection();

		    		Collection<UserReviewViewModel> getReviewsResult = new ArrayList<UserReviewViewModel>(); 
		    			Statement stmt;
		    			try {
		    				stmt = conn.createStatement();
		    				ResultSet rs = stmt.executeQuery(AppConstants.SELECT_JOIN_REVIEWS_ADMIN_STMT);
		    				while (rs.next()){
		    					getReviewsResult.add(new UserReviewViewModel(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5)));
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
		    
		        	writer.print(getreviewJsonResult);
		        	writer.flush();
		        	writer.close();
		    	} catch (SQLException | NamingException e) {
		    		getServletContext().log("Error while closing connection", e);
		    		response.sendError(500);//internal server error
		    	}

	}

	/**
	 * this function will update approved reviews
	 * @param int userID
	 * @param int bookID
	 * @param int reviewID
	 * @return status of action
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	/**
	 * @see HttpServlet#doPut(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// update review with approved==1  . admin approve the review
		// comes with �userId,bookId,
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
				String approvedReview=sb.toString();
				Review approvedRequestAdmin = gson.fromJson(approvedReview, Review.class);
		
    		final int id=approvedRequestAdmin.getReviewID();	


         	//obtain con DB data source from Tomcat's context
        		Context context = new InitialContext();
        		BasicDataSource ds = (BasicDataSource)context.lookup(
        				getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
        		Connection conn = ds.getConnection();
        		PreparedStatement stmt;
        		// here we update  to approved review query
        		PreparedStatement pstmt = conn.prepareStatement(AppConstants.UPDATE_APPROVED_REVIEW_STMT);
    			
    				pstmt.setInt(1,id);
    			
    	
   
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
	
	
	/**
	 * this function will delete selected reviews
	 * @param int reviewID
	 * @return status of action
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
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
		    		String IdToDelete=sb.toString();
		    		
		    	
					Review deleteReviewAdmin = gson.fromJson(IdToDelete, Review.class);
			
	    		final int id=deleteReviewAdmin.getReviewID();	

	    		
	    		
		        	//obtain CustomerDB data source from Tomcat's context
		    		Context context = new InitialContext();
		    		BasicDataSource ds = (BasicDataSource)context.lookup(
		    				getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
		    		Connection conn = ds.getConnection();
		    		PreparedStatement stmt;
		    		PreparedStatement pstmt = conn.prepareStatement(AppConstants.UPDATE_DELETE_REVIEW_STMT);
    				pstmt.setInt(1,id);// here we insert parameter to delete query
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
