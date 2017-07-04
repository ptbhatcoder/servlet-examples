/**
* This program is a servlet in java
* Created by Pranav Bhat
* It takes the name of the end user sent as a "name" query parameter and returns a Hello to that user
* It also sends other information about the request like the 
* Request URI
* Request URL
* PathInfo
* Remote Address
* A random seed
* Source : https://www.ntu.edu.sg/home/ehchua/programming/java/JavaServlets.html
* Uses Java servlets
**/

package com.helloWorld;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {
	// We will support get request and post request only for this simple programme
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException {
		// first we set the response type parameters
		response.setContentType("text/html");//the end response to the requester

		PrintWriter out = response.getWriter(); // this points to the output stream which keeps sending html data to the end user.

		try {

			// first fetch the name of the person
			String name = request.getParameter("name").trim();
			if (name.length()==0) {
				name = "John Doe";
			}

			// now keep sending the customised html response through the output stream

			out.println("<html>");

			// head section
			out.println("<head>");
			out.println("<title>Hello World Servlet");
			out.println("</title>");
			out.println("</head>");

			// body section
			out.println("<body>");
			out.println("<h2>Hello "+name+"</h2>");

			out.println("<hr/>");

			out.println("<h3>Some of the request Details</h3>");

			out.println("<p>Request Url : "+request.getRequestURL()+"</p>");
			out.println("<p>Request URI : "+request.getRequestURI()+"</p>");
			out.println("<p>Protocol : "+request.getProtocol()+"</p>");
			out.println("<p>Method : "+request.getMethod()+"</p>");
			out.println("<p>Path Info : "+request.getPathInfo()+"</p>");
			out.println("<p>Remote Address(IPv6) : "+request.getRemoteAddr()+"</p>");

			out.println("<p>Random Seed : <b>"+Math.random()+"</b></p>");
			out.println("<hr/>");
			
			out.println("</body>");

			out.println("</html");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();// the open pipe should be closed, or the client will be of the assumption that the response was not received
		}

	}

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException {
		// the functionality is the same essentially as get for this simple programme
		doGet(request,response);
	}
}