/** EchoFormServlet.java
* Author : Pranav Bhat
* Programme takes a form which asks the details about a user like his name, password, gender and then his preferred coding language and also asks for a snippet of code which is taken in fieldsets
* The output of the programme later contains this information which has actually been refltected by the server response
* This requires tomcat server and also needs to be compiled with the classpath to include the servlet-api.jar
* source : https://www.ntu.edu.sg/home/ehchua/programming/java/JavaServlets.html
**/

package com.echoServletPkg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

public class EchoFormServlet extends HttpServlet {

	private static final String INFO_NOT_AVAILABLE = "&lt; INFORMATION NOT AVAILABLE &gt;";

	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		// Input is the list of parameters which contains the necessary code
		// Parameters are username, password, gender, age, language, instruction
		// Also note that every text input from the user needs to be encoded for special characters to avoid sql injections
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		try{

			out.println("<html>");
			out.println("<head>");
			out.println("<meta http-equiv='Content-Type' charset='UTF-8' content='text/html'");
			out.println("<title>User Input Form</title>");
			out.println("</head>");

			out.println("<body>");

			out.println("<h2>The details you entered are</h2>");

			// username 
			String username = htmlFilter(request.getParameter("username"));
			if (username==null) username = INFO_NOT_AVAILABLE;

			String password = htmlFilter(request.getParameter("password"));
			if (password==null) password = INFO_NOT_AVAILABLE;

			String gender = request.getParameter("gender").equals("m") ? "male" : "female";
			String age;
			if (request.getParameter("age").equals("1")) age = " Less than one year old.";
			else if(request.getParameter("age").equals("99")) age = " Between 1 and 99 year old.";
			else age = "Greater than 99 years.";

			String languages = "";
			for (String language : request.getParameterValues("lang")) {
				if(language.equals("java")) languages = languages + "\nJava";
				else if(language.equals("java")) languages = languages + "\nC/C++";
				else languages = languages + "\nC#";
			}

			String instruction = htmlFilter(request.getParameter("instruction"));
			if (instruction==null || instruction.equals("Enter your instruction here....")) {
				instruction = INFO_NOT_AVAILABLE;
			}

			String secret = htmlFilter(request.getParameter("secret"));

			out.println("<table border='1'>");
			out.println("<tr><td>Name</td><td><b>"+username+"</b></td></tr>");
			out.println("<tr><td>Password</td><td><b>"+password+"</b></td></tr>");
			out.println("<tr><td>Gender</td><td><b>"+gender+"</b></td></tr>");
			out.println("<tr><td>Age</td><td><b>"+age+"</b></td></tr>");
			out.println("<tr><td>Languages</td><td><b>"+languages+"</b></td></tr>");
			out.println("<tr><td>Instruction Given</td><td><b>"+instruction+"</b></td></tr>");
			out.println("<tr><td>Secret</td><td><b>"+secret+"</b></td></tr>");
			out.println("</table>");

			Enumeration names = request.getParameterNames();
			out.println("<p>Request Parameters were: ");
			if (names.hasMoreElements()) {
				out.println(htmlFilter(names.nextElement().toString())+"\n");
			}
			do{
				out.println(htmlFilter(names.nextElement().toString())+"\n");
			} while(names.hasMoreElements());
			out.println(".</p>");

			out.println("<a href='index.html'>BACK</a>");

			out.println("</body>");
			out.println("</html>");


		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			out.close();
		}
	}

	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		doGet(request,response);
	}

	private static String htmlFilter(String inputString){
		if (inputString==null) {
			return null;
		}
		inputString = inputString.trim();
		int nString = inputString.length();
		if (nString==0) {
			return null;
		}
		StringBuilder resultSB = new StringBuilder();

		char curChar;
		for (int i=0; i<nString; i++) {
		 	curChar = inputString.charAt(i);
		 	switch(curChar){
		 		case '<': resultSB.append("&lt;"); break;
		 		case '>': resultSB.append("&gt;"); break;
		 		case '&': resultSB.append("&amp;"); break;
		 		case '"': resultSB.append("&quot;"); break;
		 		default : resultSB.append(curChar); break;
		 	} 
		}
		return resultSB.toString();
	}

}
