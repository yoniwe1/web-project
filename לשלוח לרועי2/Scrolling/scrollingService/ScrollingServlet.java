package scrollingService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import example.AppConstants;
import viewModelReviews.LikeServer;
import viewModelReviews.LikesViewModel;

/**
 * Servlet to manage scrolling and retrieve current location on book
 * Servlet implementation class ScrollingServlet
 */
@WebServlet("/scrollingServlet")
public class ScrollingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScrollingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * this function gets current location in book
	 * @param string bookID
	 * @param string encryuserID
	 * @param string userID
	 * @return string location
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

try {
    		
        	//obtain scrolling
    		Context context = new InitialContext();
    		BasicDataSource ds = (BasicDataSource)context.lookup(
    				getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
    		Connection conn = ds.getConnection();

    		Collection<ScrollingClass> scrollingClassResult = new ArrayList<ScrollingClass>(); 

    			PreparedStatement stmt;
    			try {
    		   
    				String bookId= request.getParameter("BookID");
    		            String userId= request.getParameter("EncryUserID");
        			
    		            userId = CryptoObj.caesarCipherDecrypte(userId);
        				
        				
    				stmt = conn.prepareStatement(AppConstants.SELECT_LOCATION_STMT);
    				
    				stmt.setString(1, bookId);
    				stmt.setString(2, userId);
    				
    				
    				ResultSet rs = stmt.executeQuery();
    				while (rs.next()){
    					scrollingClassResult.add(new ScrollingClass(rs.getString(1)));
    				}
    				rs.close();
    				stmt.close();
    			} catch (SQLException e) {
    				getServletContext().log("Error while querying for customers", e);
    	    		response.sendError(500);//internal server error
    			} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

    		

    		conn.close();
    		
    		Gson gson = new Gson();
        	//convert from LIKES collection to json
        	String scrollingClassResultJsonResult = gson.toJson(scrollingClassResult, AppConstants.SCROLLING_COLLECTION);
        	response.addHeader("Content-Type", "application/json");
        	PrintWriter writer = response.getWriter();
        //	userJsonResult=userJsonResult.substring(1, userJsonResult.length()-1); //delete  [ .............]  in the string
        	writer.print(scrollingClassResultJsonResult);
        	writer.flush();
        	writer.close();
    	} catch (SQLException | NamingException e) {
    		getServletContext().log("Error while closing connection", e);
    		response.sendError(500);//internal server error
    	}
	
	
	
	
	}
	
	
	/**
	 * this function will insert or update location of user in book
	 * @param int bookID
	 * @param string encryuserID
	 * @param string location
	 * @param int userID
	 * @return string userID, int bookID, string location
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

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
				String scrollStr=sb.toString();
				ScrollingClass  setLocation = gson.fromJson(scrollStr, ScrollingClass.class);
		
    		final int bookid=setLocation.getBookID();
    		String userid=setLocation.getEncryUserID();
    		final String location=setLocation.getLocation();

    	
    		userid = CryptoObj.caesarCipherDecrypte(userid);
   
         	//obtain set like data source from Tomcat's context
        		Context context = new InitialContext();
        		BasicDataSource ds = (BasicDataSource)context.lookup(
        				getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
        		Connection conn = ds.getConnection();
        		PreparedStatement stmt;
        		
        		PreparedStatement pstmt = conn.prepareStatement(AppConstants.UPDATE_LOCATION_STMT);
    			
    				pstmt.setString(1,location);// here we insert parameter to insert query
    				pstmt.setString(2,userid);
    				pstmt.setInt(3,bookid);
    		
    				int res=pstmt.executeUpdate();
    				
    				if(res==0) {// the user put likes before review so reviewString will be"" and approved=3
    					 pstmt = conn.prepareStatement(AppConstants.INSERT_LOCATION__STMT);	
    						pstmt.setString(1,userid);// here we insert parameter to insert query
    	    				pstmt.setInt(2,bookid);
    	    				pstmt.setString(3,location);
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
