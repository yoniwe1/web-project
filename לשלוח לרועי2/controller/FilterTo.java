package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import crypto.CryptoObj;
import entities.RequestWrapper;
import entities.Review;
import entities.User;
import example.AppConstants;
import existsUserObject.ExistUser;

/**
 * Servlet Filter implementation class FilterTo
 */
@WebFilter("/FilterTo")
public class FilterTo implements Filter {
	
	
	
	public static String convertToJavaFromJsonPost(HttpServletRequest request) throws IOException {
		
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
			
			String jsonStr=sb.toString();
			return jsonStr;
			
	}
	

	public static String myGetUserIdFromRequest(ServletRequest req) throws IOException {
	    HttpServletRequest Request = ((HttpServletRequest) req);
		String uri =Request.getRequestURI();
		Gson gson = new Gson();
		String requestedById="-1";
		if (uri.indexOf("UsersAdmin") != -1){// to convert to java with gson
			if(Request.getMethod().equals("DELETE")) {
				String delUserStr= convertToJavaFromJsonPost(Request);
				User reqUserid = gson.fromJson(delUserStr, User.class);
				requestedById=reqUserid.getEncryUserID();
				
			}
			else {//it's get
				requestedById= Request.getParameter("EncryUserID");
		
			}
		}
		else if(uri.indexOf("reviewsAdmin") != -1) {
			String method= Request.getMethod();
				if(method.equals("GET")) {
					requestedById= Request.getParameter("EncryUserID");
					
				}
				else if(method.equals("DELETE")|| method.equals("PUT")) {
					String delReviewStr= convertToJavaFromJsonPost(Request);
					Review reqUserid = gson.fromJson(delReviewStr, Review.class);
					requestedById=reqUserid.getEncryUserID();
				}
			}
		else if(uri.indexOf("Purchases") != -1) {//it's 'get'
			requestedById= Request.getParameter("EncryUserID");
		}
		else if(uri.indexOf("booksAdmin") != -1) {//it's 'get'
			requestedById= Request.getParameter("EncryUserID");
		}
		else if(uri.indexOf("messagesAdmin") != -1)  {//it's 'get'
			requestedById= Request.getParameter("EncryUserID");
		}
		
		
		return requestedById;
	} 
	
    /**
     * Default constructor. 
     */
    public FilterTo() {
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
		 //who comes here is only who knows admin URL.

        HttpServletRequest properRequest = ((HttpServletRequest) request);
        RequestWrapper wrappedRequest = new RequestWrapper(properRequest);
        request = wrappedRequest;
        
		String uri =properRequest.getRequestURI();


		String encryID=myGetUserIdFromRequest(request);
    	
		String decryIdstr=" ";
		try {
			decryIdstr = CryptoObj.caesarCipherDecrypte(encryID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   
		
		if(decryIdstr.equals("21"))//this is admin
			chain.doFilter(request, response);	// pass the request along the filter chain
		else//this is hacker
		{
			RequestDispatcher rd=request.getRequestDispatcher("admin/Content/html/permissionDenied.html");  
		    rd.include(request, response);
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
