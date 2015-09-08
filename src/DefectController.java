import java.io.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class DefectController extends HttpServlet {

    private static final long serialVersionUID = 1L;


/* -------------------------------------------------------------------------------------------
All of these actions will take the data from the JSP file and submit it to the MySql Database
The parameters must by in sync with the JSP <form>
So check button "names"
---------------------------------------------------------------------------------------------*/

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// call doPost method
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("IN THE SERVLET........");
		try {

			String tableType = "All Defects List";
			
			// get the DB list type form the index.jsp
			String listType = request.getParameter("defectListType");
			System.out.println("LIST TYPE:  " + listType);


			/**
			 *  defect list from database
			 */
			ArrayList<Defect> defectList = new ArrayList<Defect>();
			defectList.clear();
			
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
			if(listType == null) {

				// GET ALL DEFECTS
				statement.execute("SELECT * FROM DEFECTS");
				tableType = "All Defects List";
			}
			else if (listType.equals("SHOW ALL OPEN DEFECTS")) {

				// GET ALL DEFECTS
				statement.execute("SELECT * FROM DEFECTS WHERE status = 'Open'");
				tableType = "All Open Defects List";
			}
			else {

				// GET ALL DEFECTS
				statement.execute("SELECT * FROM DEFECTS");
				tableType = "All Defects List";
			}


			//STEP 5: Process the Results
			ResultSet rs = statement.getResultSet();

			if (rs != null) {
				while (rs.next()) {
					System.out.println("ID: " + rs.getString(1) +
							           " STATUS: " + rs.getString(2) +
					                   " ASSIGNEE: " + rs.getString(3) +
					                   " SUMMARY: " + rs.getString(4) +
					                   " DESCRIPTION: " + rs.getString(5) +
					                   " PRIORITY: " + rs.getString(6));

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
			
			/////////////////////////////////////////////////////////
			// GET DEFECT ID LIST
			/////////////////////////////////////////////////////////
			
			/**
			 *  defect ID list from database
			 */
			ArrayList<String> defectIdList = new ArrayList<String>();
			defectIdList.clear();
			
			// GET ALL DEFECTS
			statement.execute("SELECT * FROM DEFECTS");
			
			// Setup defect ID array
			rs = statement.getResultSet();

			if (rs != null) {
				while (rs.next()) {
					
					String defId = rs.getString(1);
					System.out.println("DEF ID: " + defId);

					defectIdList.add(defId);
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
	        
			// send assignees to index jsp
	        request.setAttribute("assigneeListDB", assigneeList);

	        // send defect all defect IDs to index jsp
	        request.setAttribute("defectIdListDB", defectIdList);
	        
	        view.forward(request, response);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
} //end of Class
