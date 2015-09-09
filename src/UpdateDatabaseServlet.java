import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class UpdateDatabaseServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// call doPost method
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// setup to send information to index.jsp
        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
		System.out.println("IN THE UPDATE DATABASE SERVLET........");
		try {
			
			// get the defect to edit from the index.jsp
			String defectId = request.getParameter("defectId");
			String status = request.getParameter("status");
			String priority = request.getParameter("priority");
			String assignee = request.getParameter("assignee");
			String summary = request.getParameter("summary");
			String description = request.getParameter("description");
			
			// fix apostrophe escape key issue with mysql
			description = description.replaceAll("'", "''");
			summary = summary.replaceAll("'", "''");

			// debug code
			System.out.println("UPDATE DEFECT ID:  " + defectId);
			System.out.println("UPDATE STATUS:  " + status);
			System.out.println("UPDATE PRIORITY:  " + priority);
			System.out.println("UPDATE ASSIGNEE:  " + assignee);
			System.out.println("UPDATE SUMMARY:  " + summary);
			System.out.println("UPDATE DESC:  " + description);
			

			/**
			 * Connect to database....
			 */
			//STEP 1: Load the Driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 2: Make a Connection to the Database
			Connection connection = (Connection) Database.getConnection();

			//STEP 3: Create a Statement
			Statement statement = (Statement) connection.createStatement();
			
			//STEP 4: Execute SQL Statements
			// UPDATE DEFECT
			statement.execute("UPDATE defects SET status = '" + status + "', " +
					                             "assignee = '" + assignee + "', " +
				                                 "summary = '" + summary + "', " +
				                                 "description = '" + description + "', " +
				                                 "priority = " + priority + " " +
				                                             "WHERE id = '" + defectId + "'");

			//STEP 5: Close the Statement and Connection
			statement.close();
			connection.close(); 
		}
		catch (SQLException e) {
            System.out.println("Cannot connect to database: " + e);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
        view.forward(request, response);
	}
} //end of Class
