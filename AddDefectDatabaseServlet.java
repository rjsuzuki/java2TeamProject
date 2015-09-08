/* AddDefectDatabaseServlet.java			0.02 2015/09/02
 * 
 * This is code written for UCI Extension - I&C SCI_X460.11 SUMMER 2015,UNEX,00172
 *
 * Name: Perry On
 * Date: 09/02/2015
 * Class: UCI Extension - I&C SCI_X460.11 SUMMER 2015,UNEX,00172
 * Assignment: Team Assignment
 * 
 * 09/02/2015 - Perry On - Initial file creation; successfully adds defect to database (but not tied to a button)
 */

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class AddDefectDatabaseServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// call doPost method
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//RequestDispatcher dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/../jsp/Add_Defect_Form.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		System.out.println("IN THE ADD SERVLET........");
		try {

			//STEP 1: Load the Driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 2: Make a Connection to the Database
			Connection connection = (Connection) Database.getConnection();

			//STEP 3: Create a Statement
			Statement statement = (Statement) connection.createStatement();
			
			//STEP 4: Execute SQL Statements
			System.out.println("1");
			String status = request.getParameter("status");
			String assignee = request.getParameter("assignee");
			String summary = request.getParameter("summary");
			String description = request.getParameter("description");
			String priority = request.getParameter("priority");
			System.out.println("Before: " + summary);
			summary = summary.replaceAll("\"", "\\\"");
			System.out.println("After: " + summary);
			description = description.replaceAll("\"", "\\\"");
			statement.execute("INSERT INTO defects(status, assignee, summary, description, priority) VALUES (\"" +
					status + "\", \"" + 
					assignee + "\", \"" +
                    summary + "\", \"" +
                    description + "\", " +
                    priority + ")");
			statement.close();
			connection.close(); 
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		dispatcher.forward(request, response);
	}
} //end of Class
