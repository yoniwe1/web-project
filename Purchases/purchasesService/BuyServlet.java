package purchasesService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.ResultSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import com.google.gson.Gson;

import crypto.CryptoObj;
import entities.User;
import example.AppConstants;
import existsUserObject.ExistUser;
import purchasesViewAdmin.BuyForm;

/**
 * this servlet provides details about book purchases
 * Servlet implementation class BuyServlet
 */


@WebServlet(
		description = "Servlet to provide details about books", 
		urlPatterns = { 
				"/isbought",
				"/buy"
		})

public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    	public static boolean validation_buy(String firstname, String lastname, String email, String address, String city, String zip,
    			String ccName, String ccNumber, String month, String year, String cvv) {
    		
    		LocalDateTime now = LocalDateTime.now();
    		String currMonth = DateTimeFormatter.ofPattern("MM").format(now);
    		String currYear = DateTimeFormatter.ofPattern("yy").format(now);
    		
    		Pattern generalPattern = Pattern.compile("^([a-zA-Z]|\\s)*$");
    		Pattern mailPattern = Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$");
    		Pattern zipPattern = Pattern.compile("[0-9]{7}");
    		Pattern visaPattern = Pattern.compile("^(?:4[0-9]{12}(?:[0-9]{3})?)$");
    		Pattern masterPattern = Pattern.compile("^(?:5[1-5][0-9]{14})$");
    		Pattern amexPattern = Pattern.compile("^(?:3[47][0-9]{13})$");
    		Pattern monthPattern = Pattern.compile("^(0[1-9]|1[0-2])$");
    		Pattern yearPattern = Pattern.compile("\\d{2}$");
    		Pattern cvvPattern = Pattern.compile("\\d{3}$");

    		
    		Matcher first = generalPattern.matcher(firstname);
    		boolean isFirst = first.matches();
    		
    		Matcher last = generalPattern.matcher(lastname);
    		boolean isLast = last.matches();
    		
    		Matcher mail = mailPattern.matcher(email);
    		boolean isMail = mail.matches();
    		
    		Matcher zipcode = zipPattern.matcher(zip);
    		boolean isZip = zipcode.matches();
    		
    		Matcher City = generalPattern.matcher(city);
    		boolean isCity = City.matches();
    		
    		Matcher CCName = generalPattern.matcher(ccName);
    		boolean isCCName = CCName.matches();
    		
    		Matcher Visa = visaPattern.matcher(ccNumber);
    		boolean isVisa = Visa.matches();
    		
    		Matcher Master = masterPattern.matcher(ccNumber);
    		boolean isMaster = Master.matches();
    		
    		Matcher Amex = amexPattern.matcher(ccNumber);
    		boolean isAmex = Amex.matches();
    		
    		Matcher Month = monthPattern.matcher(month);
    		boolean isMonth = Month.matches();
    		
    		Matcher Year = yearPattern.matcher(year);
    		boolean isYear = Year.matches();
    		
    		Matcher Cvv = cvvPattern.matcher(cvv);
    		boolean isCvv = Cvv.matches();
    		
    		if (firstname == null || firstname.isEmpty())
    		{
    			JOptionPane.showMessageDialog(null, "First name is a required field");
    			return false;
    		}
    		else if (!isFirst) {
    			JOptionPane.showMessageDialog(null, "First name format is incorrect");
    			return false;
    		}
    		if (lastname == null || lastname.isEmpty())
    		{
    			JOptionPane.showMessageDialog(null, "Last name is a required field");
    			return false;
    		}
    		else if (!isLast) {
    			JOptionPane.showMessageDialog(null, "Last name format is incorrect");
    			return false;
    		}
    		else if (email == null || email.isEmpty())
    		{
    			JOptionPane.showMessageDialog(null, "Email is a required field");
    			return false;
    		}
    		else if (!isMail) {
    			JOptionPane.showMessageDialog(null, "Email format is incorrect");
    			return false;
    		}
    		else if (address == null || address.isEmpty())
    		{
    			JOptionPane.showMessageDialog(null, "Address is a required field");
    			return false;
    		}
    		else if (city == null || city.isEmpty())
    		{
    			JOptionPane.showMessageDialog(null, "City is a required field");
    			return false;
    		}
    		else if (!isCity) {
    			JOptionPane.showMessageDialog(null, "City format is incorrect");
    			return false;
    		}
    		else if (city.length() < 3) {
    			JOptionPane.showMessageDialog(null, "City must contain 3 or more characters");
    			return false;
    		}
    		else if (zip == null || zip.isEmpty())
    		{
    			JOptionPane.showMessageDialog(null, "Zip code is a required field");
    			return false;
    		}
    		else if (!isZip) {
    			JOptionPane.showMessageDialog(null, "Zip format is incorrect");
    			return false;
    		}
    		else if (ccName == null || ccName.isEmpty())
    		{
    			JOptionPane.showMessageDialog(null, "Name on card is a required field");
    			return false;
    		}
    		else if (!isCCName) {
    			JOptionPane.showMessageDialog(null, "Credit card name format is incorrect (Only English letters are required)");
    			return false;
    		}
    		else if (ccNumber == null || ccNumber. isEmpty())
    		{
    			JOptionPane.showMessageDialog(null, "Credit card number is a required field");
    			return false;
    		}
    		else if( isVisa==false&&  isMaster==false&& isAmex ==false ) {
    			JOptionPane.showMessageDialog(null, "Expiration month is a required field");
    			return false;
    		}
    		else if (month == null || month.isEmpty()) {
    			JOptionPane.showMessageDialog(null, "Expiration month is a required field");
    			return false;
    		}
    		else if (year == null || year.isEmpty()) {
    			JOptionPane.showMessageDialog(null, "Expiration year is a required field");
    			return false;
    		}
    		else if (!isMonth || !isYear || (Integer.valueOf(year) < Integer.valueOf(currYear)) || ((Integer.valueOf(month) < Integer.valueOf(currMonth)) && (Integer.valueOf(year) == Integer.valueOf(currYear)))) {
    			JOptionPane.showMessageDialog(null, "Expiration date is not correct");
    			return false;
    		}
    		else if (cvv == null || cvv.isEmpty()) {
    			JOptionPane.showMessageDialog(null, "CVV is a required field");
    			return false;
    		}
    		else if (!isCvv) {
    			JOptionPane.showMessageDialog(null, "CVV format is incorrect");
    			return false;
    		}
    		else {
    			return true;
    		}
    	}


	/**
	 * this function will send buy request if book is not baught already
	 * @param string encryuserID
	 * @param int bookID
	 * @return int bookID
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// check if was bought already?
		try { 
		String UserId= request.getParameter("EncryUserID");
		UserId = CryptoObj.caesarCipherDecrypte(UserId);
        String bookId= request.getParameter("BookID");
		Collection<BuyForm> getBuyId = new ArrayList<BuyForm>(); 


     	//obtain CustomerDB data source from Tomcat's context
    		Context context = new InitialContext();
    		BasicDataSource ds = (BasicDataSource)context.lookup(
    				getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
    		Connection conn = ds.getConnection();
    		PreparedStatement stmt;
    	
    		PreparedStatement pstmt = conn.prepareStatement(AppConstants.SELECT_DID_BOUGHT_STMT);
			
				pstmt.setString(1,UserId);// here we insert parameter to insert query
				pstmt.setString(2,bookId);// here we insert parameter to insert query

				ResultSet rs	= pstmt.executeQuery();
				
				
			
			

			//commit update
			conn.commit();
			
			while (rs.next()){
				getBuyId.add(new BuyForm(rs.getInt(1)));
			}
			rs.close();
			
			//close statements
			pstmt.close();
		

		//close connection
		conn.close();
		
		Gson gsonResult = new Gson();
		//convert from customers collection to json
		String getBuyIdJsonResult = gsonResult.toJson(getBuyId, AppConstants.BUYFORM_COLLECTION);
		response.addHeader("Content-Type", "application/json");
		PrintWriter writer = response.getWriter();
		writer.print(getBuyIdJsonResult);
		writer.flush();
		writer.close();
	}
	
	
	
	 catch (SQLException | NamingException e) {
		getServletContext().log("Error while closing connection", e);
		response.sendError(500);//internal server error
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		
	}

	/**
	 * this function will insert or update purchased books for user
	 * @param string firstname
	 * @param string encryuserID
	 * @param int bookID
	 * @param string lastname
	 * @param string email
	 * @param string address
	 * @param string city
	 * @param string zipcode
	 * @param string cardname
	 * @param string cardnum
	 * @param string month
	 * @param string year
	 * @param string cvv
	 * @param string userID
	 * @return status of action
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// send buy info and update in DB
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
				String buyformStr=sb.toString();
				BuyForm buyformobj = gson.fromJson(buyformStr, BuyForm.class);
		
				String userId=buyformobj.getEncryUserID();//encrypted
				
	    	
				userId = CryptoObj.caesarCipherDecrypte(userId);//clear
				
				final int  bookId=buyformobj.getBookID();
    		final String firstName=buyformobj.getFirstname();
    		final String lastName=buyformobj.getLastname();
    		final String email=buyformobj.getEmail();
    		final String address=buyformobj.getAddress();
    	    final String city=buyformobj.getCity();
    	    final String zip=buyformobj.getZip();
			final String ccName =buyformobj.getCardName();
    		final String ccNumber=buyformobj.getCcNum();
			final String month=buyformobj.getMonth();
    		final String year=buyformobj.getYear();
			final String cvv =buyformobj.getCvv();
    	    	    	    	    	

    		
    		if(!validation_buy(firstName,lastName,email,address,city,zip,ccName,ccNumber,month,year,cvv)) //validate parameters
    		{
    		
    			throw new SQLException();
    			
    		}
         	//obtain CustomerDB data source from Tomcat's context
        		Context context = new InitialContext();
        		BasicDataSource ds = (BasicDataSource)context.lookup(
        				getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
        		Connection conn = ds.getConnection();
        		PreparedStatement stmt;
        		
        		PreparedStatement pstmt = conn.prepareStatement(AppConstants.INSERT_PURCHASED_STMT);
    			
    				pstmt.setString(1,userId);// here we insert parameter to insert query
    				pstmt.setInt(2,bookId);
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
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

}
