package checkExistsUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import com.google.gson.Gson;

import controller.FilterRegularUsers;
import crypto.CryptoObj;
import crypto.Sample;
import entities.User;
import example.AppConstants;
import existsUserObject.ExistUser;
import viewModelReviews.UserReviewViewModel;
import org.apache.catalina.core.ApplicationFilterChain;
/**
 * Servlet to check no double username and log in and out
 * Servlet implementation class ExistUserServlet
 */



@WebServlet(
		description = "Servlet to log in and out from system", 
		urlPatterns = { 
				"/ExistUser"
			
		})
public class ExistUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExistUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * this function will insert or update admin enter details and check no double username 
	 * @param string username
	 * @param string password
	 * @return status of action
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		
		ResultSet rs ;
		Collection<ExistUser> getCheckUSer = new ArrayList<ExistUser>(); 

		try {

		/*	int aa=111;
			String enc=  CryptoObj.encrypt(aa);   
			String decodedString = URLDecoder.decode(enc, "UTF-8");
			String dec = CryptoObj.decrypt(enc);*/
			
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
				String userdetails=sb.toString();
				ExistUser existUser = gson.fromJson(userdetails, ExistUser.class);
		
    		final String username=existUser.getUsername();
    		final String password=existUser.getPassword();

    	
         	//obtain CustomerDB data source from Tomcat's context
        		Context context = new InitialContext();
        		BasicDataSource ds = (BasicDataSource)context.lookup(
        				getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
        		Connection conn = ds.getConnection();
        		PreparedStatement stmt;
        		
        		PreparedStatement pstmt = conn.prepareStatement(AppConstants.SELECT_EXIST_USER_STMT);
    			
    				pstmt.setString(1,username);// here we insert parameter to insert query
    				pstmt.setString(2,password);// here we insert parameter to insert query

    				 rs = pstmt.executeQuery();
    				
    			

    			//commit update
    			conn.commit();
    	
    			
    			while (rs.next()){
    				
        			 
        			String UserId=rs.getString(2);
        			String encryUserId=CryptoObj.caesarCipherEncrypt(UserId);
    				getCheckUSer.add(new ExistUser(rs.getString(1),encryUserId,""));
    			
				}
				rs.close();
				
    			//close statements
    			pstmt.close();
    		

    		//close connection
    		conn.close();
    		
    		Gson gsonResult = new Gson();
    		//convert from customers collection to json
    		String getCHeckedUserJsonResult = gsonResult.toJson(getCheckUSer, AppConstants.EXIUSER_COLLECTION);
    		response.addHeader("Content-Type", "application/json");
    		PrintWriter writer = response.getWriter();
    		writer.print(getCHeckedUserJsonResult);
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

}
