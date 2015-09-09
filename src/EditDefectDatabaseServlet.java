import java.io.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class EditDefectDatabaseServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// call doPost method
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// setup to send defect to Edit_Defect_Form.jsp
        RequestDispatcher view = request.getRequestDispatcher("/jsp/Edit_Defect_Form.jsp");
		System.out.println("IN THE EDIT DEFECT SERVLET........");
		try {
			
//			String defectIDLabel = request.getParameter("EmailDefectId");
//			System.out.println("Selected EMAIL ID:  " + defectIDLabel);
//			
//			String assignee = request.getParameter("EmailAssignee");
//			System.out.println("Selected EMAIL ASSIGNEE:  " + assignee);

			// get the defect to edit from the index.jsp
			String defectLabel = request.getParameter("defectToEdit");
			System.out.println("EDIT LABEL:  " + defectLabel);

			// parse out the ID
			int idx = defectLabel.lastIndexOf("-");
			String defectID = defectLabel.substring(idx+1);
			System.out.println("DEFECT ID:  " + defectID);
			

			/**
			 *  defect to edit from from database
			 */
			Defect def = null;
			
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
			// GET ALL DEFECTS
			statement.execute("SELECT * FROM DEFECTS WHERE id=" + defectID);

			//STEP 5: Process the Results
			ResultSet rs = statement.getResultSet();

			if (rs != null) {
				while (rs.next()) {
					System.out.println("ID: " + rs.getString(1) +
							           " STATUS: " + rs.getString(2) +
					                   " ASSINEE: " + rs.getString(3) +
					                   " SUMMARY: " + rs.getString(4) +
					                   " DESC: " + rs.getString(5) +
					                   " PRIORITY: " + rs.getString(6));

					def = new Defect();
					def.setDefectId(Integer.parseInt(rs.getString(1)));
					def.setStatus(rs.getString(2));
					def.setAssignee(rs.getString(3));
					def.setSummary(rs.getString(4));
					def.setDescription(rs.getString(5));
					def.setPriority(Integer.parseInt(rs.getString(6)));
				}
			}


			/////////////////////////////////////////////////////////
			// GET ASSIGNEES LIST
			/////////////////////////////////////////////////////////
			
			/**
			 *  assignee list from database
			 */
			ArrayList<String> assigneeList = new ArrayList<String>();
			assigneeList.clear();
			
			// Get assignee's from DB
			statement.execute("SELECT * FROM ASSIGNEE");
			
			// Setup assignee array
			rs = statement.getResultSet();

			if (rs != null) {
				while (rs.next()) {
					
					String assignee = rs.getString(2) + " " + rs.getString(3);
					System.out.println("ASSIGNEE: " + assignee);

					assigneeList.add(assignee);
				}
			}

			//STEP 6: Close the Statement and Connection
			statement.close();
			connection.close(); 

			System.out.println(def.getDefectId() + ",  " +
			                   def.getStatus() + ", " +
			                   def.getAssignee() + ", " +
			                   def.getSummary() + ", " +
			                   def.getDescription() + ", " +
			                   def.getPriority());

			// send defect to Edit_Defect_Form.jsp
	        request.setAttribute("defect", def);

			// send assignees to Edit_Defect_Form.jsp
	        request.setAttribute("assigneeListDB", assigneeList);
		}
		catch (Exception e) {
			
		}
        view.forward(request, response);
	}
} //end of Class
