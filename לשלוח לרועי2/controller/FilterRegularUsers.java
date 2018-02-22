package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import com.google.gson.Gson;

import checkExistsUser.ExistUserServlet;
import crypto.CryptoObj;
import entities.Message;
import entities.RequestWrapper;
import entities.Review;
import entities.User;
import example.AppConstants;
import existsUserObject.ExistUser;
import purchasesViewAdmin.BuyForm;
import scrollingService.ScrollingClass;
import viewModelReviews.LikeServer;
import viewModelReviews.UserReviewViewModel;


 
/**
 * Servlet to filter
 * Servlet Filter implementation class FilterRegularUsers
 */
@WebFilter("/FilterRegularUsers")
public  class  FilterRegularUsers implements Filter {

	public static String RegularGetUserIdFromRequest(ServletRequest req) throws IOException {
	    HttpServletRequest Request = ((HttpServletRequest) req);
		String uri =Request.getRequestURI();
		Gson gson = new Gson();
		String requestedById="-1";
		String method=Request.getMethod();
	
		if (uri.indexOf("reviews") != -1){// to convert to java with gson
			if(method.equals("POST")) {
				String reviewsStr= FilterTo.convertToJavaFromJsonPost(Request);
				UserReviewViewModel reqUserid = gson.fromJson(reviewsStr, UserReviewViewModel.class);
				requestedById=reqUserid.getEncryUserID();
				
			}
			else {//it's get
				requestedById= Request.getParameter("EncryUserID");
				
			}
		}
		if (uri.indexOf("scrollingServlet") != -1){// to convert to java with gson
			if(method.equals("POST")) {
				String scrollingStr= FilterTo.convertToJavaFromJsonPost(Request);
				ScrollingClass reqUserid = gson.fromJson(scrollingStr, ScrollingClass.class);
				requestedById=reqUserid.getEncryUserID();
				
			}
			else {//it's get
				requestedById= Request.getParameter("EncryUserID");
				
			}
		}
		else if(uri.indexOf("whoLike") != -1) {//it's 'get'
			requestedById= Request.getParameter("EncryUserID");
					
			}
		else if(uri.indexOf("messages") != -1) {//it's 'get'
			if(method.equals("GET")) {
				requestedById= Request.getParameter("EncryUserID");
			
			}
			else if(method.equals("PUT")){
				
				String messageStr= FilterTo.convertToJavaFromJsonPost(Request);
				Message reqUserid = gson.fromJson(messageStr, Message.class);
				requestedById=reqUserid.getEncryUserID();
			}
			else {//post
				String messageStr= FilterTo.convertToJavaFromJsonPost(Request);
				Message reqUserid = gson.fromJson(messageStr, Message.class);
				requestedById=reqUserid.getEncryUserID();
			}
		}
		else if((uri.indexOf("myEbooks") != -1)||(uri.indexOf("books") != -1)) {//it's 'get'
			 requestedById= Request.getParameter("EncryUserID");
			
			
		}
		else if(uri.indexOf("buy") != -1)  {//it's 'post'
			String buyStr= FilterTo.convertToJavaFromJsonPost(Request);
			BuyForm reqUserid = gson.fromJson(buyStr, BuyForm.class);
			requestedById=reqUserid.getEncryUserID();
		}
		else if(uri.indexOf("dolike") != -1)  {//it's 'put'
			String LikeServerStr= FilterTo.convertToJavaFromJsonPost(Request);
			LikeServer reqUserid = gson.fromJson(LikeServerStr, LikeServer.class);
			requestedById=reqUserid.getEncryUserID();
		}

		return requestedById;
	} 
	
	
    /**
     * Default constructor. 
     */
    public FilterRegularUsers() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       
	
		HttpServletRequest properRequest = ((HttpServletRequest) request);
        RequestWrapper wrappedRequest = new RequestWrapper(properRequest);
        request = wrappedRequest;

		String uri =properRequest.getRequestURI();


    	String encryID=RegularGetUserIdFromRequest(request);// encrypted!
    	
    	//let's decry
    String decryIdstr = null;

	try {
		decryIdstr = CryptoObj.caesarCipherDecrypte(encryID);

	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
  
  
try {
		

		Context context = new InitialContext();
		BasicDataSource ds = (BasicDataSource)context.lookup(
				request.getServletContext().getInitParameter(AppConstants.DB_DATASOURCE) + AppConstants.OPEN);
		Connection conn = ds.getConnection();
		PreparedStatement stmt;
		stmt = conn.prepareStatement("UPDATE USERS SET Nickname=Nickname WHERE ID=?");
	
		stmt.setString(1, decryIdstr);
	
		int res=stmt.executeUpdate();

			
		
    	if(res!=0) {
			conn.commit();
    		stmt.close();
    		conn.close();
    		chain.doFilter(request, response);
    	}else
    	{
			conn.commit();
    		stmt.close();
    		conn.close();
			RequestDispatcher rd=request.getRequestDispatcher("admin/Content/html/permissionDenied.html");  
		    rd.include(request, response);
		    return;
    	}
}
    	catch (SQLException | NamingException e) {
    		request.getServletContext().log("Error while closing connection", e);
    		
    		 return;
    	}
}
	

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
