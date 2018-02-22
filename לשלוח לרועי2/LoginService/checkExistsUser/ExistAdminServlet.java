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

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
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

import controller.FilterRegularUsers;
import crypto.CryptoObj;
import crypto.Sample;
import entities.User;
import example.AppConstants;
import existsUserObject.ExistUser;
import viewModelReviews.UserReviewViewModel;

/**
 * Servlet to check no double admin username
 * Servlet implementation class ExistUserServlet
 */
@WebServlet("/ExistAdmin")
public class ExistAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExistAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * this function will insert or update admin enter details 
	 * @param string username
	 * @param string password
	 * @return status of action
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
    		
    		if(username.equals("admin") && password.equals("Passw0rd")) {
    			
    			String encryUserId=CryptoObj.caesarCipherEncrypt("21");
    			
    				getCheckUSer.add(new ExistUser("userNick21admin",encryUserId,""));
    				
    		}
    		
		
    		Gson gsonResult = new Gson();
    		//convert from customers collection to json
    		String getCHeckedUserJsonResult = gsonResult.toJson(getCheckUSer, AppConstants.EXIUSER_COLLECTION);
    		response.addHeader("Content-Type", "application/json");
    		PrintWriter writer = response.getWriter();
    		writer.print(getCHeckedUserJsonResult);
    		writer.flush();
    		writer.close();
    		

		}
		
		
		catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		

		
		
	}

}
