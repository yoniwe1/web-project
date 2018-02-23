package purchasesService;

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
import purchasesViewAdmin.EbookObject;

/**
 * Servlet to handle a user's ebooks collection
 * Servlet implementation class MyEbooksServlet
 */
@WebServlet("/myEbooks/*")
public class MyEbooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyEbooksServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * this function will get all user's ebooks collection
	 * @param string encryuserID
	 * @param int userID
	 * @param int bookID
	 * @return int bookID, string title, string URL, string image, string description, timepurchased timestamp, boolean isLike, int numoflikes
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try {
	//get all books of user
        	
    		Context context = new InitialContext();
    		BasicDataSource ds = (BasicDataSource)context.lookup(
    				getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
    		Connection conn = ds.getConnection();

    		Collection<EbookObject> EbookObjectResult = new ArrayList<EbookObject>(); 
    	
    			  // String id= request.getParameter("UserID");
    			   String   id= request.getParameter("EncryUserID");
    			   id = CryptoObj.caesarCipherDecrypte(id);
    			   
    			PreparedStatement stmt;
    			try {
    				stmt = conn.prepareStatement(AppConstants.SELECT_MYBOOKS_BY_ID_STMT);
    				stmt.setString(1, id);
					stmt.setString(2, id);
    				ResultSet rs = stmt.executeQuery();
    				while (rs.next()){// maybe there are many names like this'name'
    					EbookObjectResult.add(new EbookObject(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
    							rs.getString(5),rs.getTimestamp(6),rs.getInt(7),rs.getBoolean(8)));
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
        	//convert from customers collection to json
        	String EbookJsonResult = gson.toJson(EbookObjectResult, AppConstants.EBOOKOBJECT_COLLECTION);
        	response.addHeader("Content-Type", "application/json");
        	PrintWriter writer = response.getWriter();
        //	userJsonResult=userJsonResult.substring(1, userJsonResult.length()-1); //delete  [ .............]  in the string
        	writer.print(EbookJsonResult);
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
