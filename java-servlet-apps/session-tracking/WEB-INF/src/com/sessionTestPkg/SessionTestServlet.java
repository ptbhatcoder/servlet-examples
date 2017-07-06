/**
* This program is a servlet in java
* Created by Pranav Bhat
* It directly opens up as the default page in the web browser since it is a welcome file
* It shows the number of times the particular page was accesses from any browser, along with the last access time, creation time a
* and max inactive time interval
* Source : https://www.ntu.edu.sg/home/ehchua/programming/java/JavaServlets.html
* Uses Java servlets
**/

package com.sessionTestPkg;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionTestServlet extends HttpServlet {

	private static final String ACCESS_COUNT_KEY = "accessCount";
	private static final String INVALIDATE_KEY = "invalidateSession";
	private static final String POST_STRING = "POST";

	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		boolean invalidated = (POST_STRING.equals(request.getMethod()))&&(request.getParameter(INVALIDATE_KEY) != null) ? true : false;
		// This is because in a get request if we refresh the page, then the query parameters also get transferred, so allow this only when the method is post
		
		HttpSession currentSession = null;// to avoid the un-initialised error at compile
		Integer accessCount=0;// to avoid the un-initialised error at compile
		boolean new_session = false;

		if (!invalidated) {
			// Get the session information or create one if the session does not exist, uses the static singleton pattern
			currentSession = request.getSession(false);
			if (currentSession==null){
				new_session = true;
				currentSession = request.getSession(true);
			} 
			synchronized(currentSession){
				accessCount = (Integer)currentSession.getAttribute(ACCESS_COUNT_KEY);
				if (accessCount==null) {
					accessCount = 0;
				} else {
					accessCount = new Integer(accessCount+1);
				}
				currentSession.setAttribute(ACCESS_COUNT_KEY,accessCount);
			}
			
		}
		

		try{
			// Information to be shown to the end user
			// Number of accesses, id,time of the last access, session creation time, session max inactive time
			// Include a button to invalidate the current session.
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Session Tester</title>");
			out.println("</head>");

			out.println("<body>");

			if (invalidated) {
				currentSession = request.getSession(false);
				if (currentSession!=null) {
					currentSession.invalidate();
				}
				out.println("<h2>Session successfully invalidated.</h2>");

			} else {
				if (new_session) {
					out.println("<h1>Welcome to a new Session</h1>");
				}
				// number of accesses
				out.println("<h2>You have accessed the session "+accessCount+" times before this.</h2>");

				// session details
				out.println("<h4>Session Details</h4>");
				out.println("<table border=\"1\">");
				out.println("<tr><td>Session ID</td><td><b>"+currentSession.getId()+"</b></td></tr>");
				out.println("<tr><td>Session Creation Time</td><td><b>"+(new Date(currentSession.getCreationTime()))+"</b></td></tr>");
				out.println("<tr><td>Last Access Time</td><td><b>"+(new Date(currentSession.getLastAccessedTime()))+"</b></td></tr>");
				out.println("<tr><td>Max Inactivity time</td><td><b>"+currentSession.getMaxInactiveInterval()+"</b> seconds </td></tr>");
				out.println("</table>");

				//session invalidator
				out.println("<form method='"+POST_STRING+"' action='sessionTest'>");
				out.println("<input type='hidden' name='"+INVALIDATE_KEY+"' value='y'/>");
				out.println("<input type='submit' value='Invalidate Session'/>");
				out.println("</form>");

				// refresh buttons
				String urlWithoutRewriting = request.getRequestURI();
				String urlWithRewriting = response.encodeURL(urlWithoutRewriting);// this adds the session id if the cookies have been disabled in the client browser
				boolean cookiesNotDisabled = urlWithRewriting.equals(urlWithoutRewriting);
				out.println("<p><a href='"+urlWithoutRewriting+"'>Refresh</a></p>");
				out.println("<p><a href='"+urlWithRewriting+"'>Refresh With Url Encode</a></p>");
				if (!cookiesNotDisabled) {
					out.println("<p>Seems like cookies have been disabled</p>");
				}
			}

			

			out.println("</body>");
			out.println("</html>");

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(out!=null) out.close();
		}
	}

	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		doGet(request,response);
	}
}