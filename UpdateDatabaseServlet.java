import java.io.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

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

		System.out.println("IN THE UPDATE DATABASE SERVLET........");
		try {
			
			// get the defect to edit from the index.jsp
			String defectId = request.getParameter("defectId");
			String status = request.getParameter("status");
			String priority = request.getParameter("priority");
			String assignee = request.getParameter("assignee");
			String summary = request.getParameter("summary");
			String description = request.getParameter("description");
			
			// fix apostrohpe escape key issue with mysql
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


			/**
			 *  defect list from database
			 */
			ArrayList<Defect> defectList = new ArrayList<Defect>();
			defectList.clear();
			
			// GET ALL DEFECTS
			statement.execute("SELECT * FROM DEFECTS");
			String tableType = "All Defects List";

			//STEP 5: Process the Results
			ResultSet rs = statement.getResultSet();

			if (rs != null) {
				while (rs.next()) {
					System.out.println("ID: " + rs.getString(1) +
							           " STATUS: " + rs.getString(2) +
					                   " STATUS: " + rs.getString(3) +
					                   " STATUS: " + rs.getString(4) +
					                   " STATUS: " + rs.getString(5) +
					                   " STATUS: " + rs.getString(6));

					Defect def = new Defect();
					def.setDefectId(Integer.parseInt(rs.getString(1)));
					def.setStatus(rs.getString(2));
					def.setAssignee(rs.getString(3));
					def.setSummary(rs.getString(4));
					def.setDescription(rs.getString(5));
					def.setPriority(Integer.parseInt(rs.getString(6)));

					defectList.add(def);
				}
			}

			//STEP 6: Close the Statement and Connection
			statement.close();
			connection.close(); 
			
			for (Defect defect : defectList) {
				System.out.println(defect.getDefectId() + ",  " +
				                   defect.getStatus() + ", " +
				                   defect.getAssignee() + ", " +
				                   defect.getSummary() + ", " +
				                   defect.getDescription() + ", " +
				                   defect.getPriority());
			}

			// setup to send information to index.jsp
	        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");

			// set message to be sent to JSP
	        request.setAttribute("message", tableType);


			// send defects to index jsp
	        request.setAttribute("defectListDB", defectList);
	        view.forward(request, response);

		}
		catch (Exception e) {
			
		}
	}
} //end of Class
