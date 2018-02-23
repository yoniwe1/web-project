package purchasesService;

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

import entities.Book;
import example.AppConstants;
import purchasesViewAdmin.PurchaseObject;

/**
 * Servlet to handle all purchase requests
 * Servlet implementation class PurchasesServlet
 */
@WebServlet("/Purchases")
public class PurchasesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PurchasesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * this function will send all transactions to admin
	 * @param HTTP request without parameters
	 * @return string username, string telephone, string title, int bookID, string price, timepurchased timestamp
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

try {// get all transactions to admin
    		
        	//obtain DB connection
    		Context context = new InitialContext();
    		BasicDataSource ds = (BasicDataSource)context.lookup(
    				getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
    		Connection conn = ds.getConnection();

    		Collection<PurchaseObject> purchasesResult = new ArrayList<PurchaseObject>(); 
    	
    			Statement stmt;
    			try {
    				stmt = conn.createStatement();
    				ResultSet rs = stmt.executeQuery(AppConstants.SELECT_ALL_PURCHASES_STMT);
    				while (rs.next()){
    					purchasesResult.add(new PurchaseObject(rs.getString(1),rs.getString(2),rs.getString(3),
    							rs.getInt(4),rs.getString(5),rs.getTimestamp(6)));
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
        	String purchasesJsonResult = gson.toJson(purchasesResult, AppConstants.PURCHASED_COLLECTION);
        	response.addHeader("Content-Type", "application/json");
        	PrintWriter writer = response.getWriter();
        //	userJsonResult=userJsonResult.substring(1, userJsonResult.length()-1); //delete  [ .............]  in the string
        	writer.print(purchasesJsonResult);
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
	
	}

}
