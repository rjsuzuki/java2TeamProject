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
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
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
			Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/defecttracker","root", "Password1");

			//STEP 3: Create a Statement
			Statement statement = (Statement) connection.createStatement();
			
			//STEP 4: Execute SQL Statements
			System.out.println("1");
			/*String sqlx = "INSERT INTO defects(status, assignee, summary, description, priority)" + "VALUES(?, ?, ?, ?, ?)";
			System.out.println("2");
			PreparedStatement psx = (PreparedStatement) connection.prepareStatement(sqlx);
			System.out.println("3");
			System.out.println(request.getParameter("status"));
			System.out.println(request.getParameter("assignee"));
			System.out.println(request.getParameter("summary"));
			System.out.println(request.getParameter("description"));
			System.out.println(request.getParameter("priority"));
			psx.setString(1, request.getParameter("status"));
			psx.setString(2, request.getParameter("assignee"));
			psx.setString(3, request.getParameter("summary"));
			psx.setString(4, request.getParameter("description"));
			psx.setString(5, request.getParameter("priority"));
			System.out.println("4");
			psx.executeUpdate(); 
			System.out.println("5");
			statement.execute(sqlx);
			*/
			String status = request.getParameter("status");
			String assignee = request.getParameter("assignee");
			String summary = request.getParameter("summary");
			String description = request.getParameter("description");
			String priority = request.getParameter("priority");
			statement.execute("INSERT INTO defects(status, assignee, summary, description, priority) VALUES ('" +
					status + "', '" + 
					assignee + "', '" +
                    summary + "', '" +
                    description + "', " +
                    priority + ")");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		dispatcher.forward(request, response);
	}
} //end of Class
