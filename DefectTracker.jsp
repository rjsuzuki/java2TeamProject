
<%@ page import="java.io.IOException"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>

<%!

public void displayDefectTable(ArrayList<String> defectList, javax.servlet.jsp.JspWriter out, HttpServletRequest request){


	
	for (String defect : defectList) {

		String[] defectInfo = defect.split("@@@");

		try {

		    out.println(request.getParameter("FilterByAssignee"));

			out.println("<tr>");
			out.println("<td>" + defectInfo[0] + "</td>");
			out.println("<td>" + defectInfo[1] + "</td>");
			out.println("<td>" + defectInfo[2] + "</td>");
			out.println("<td>" + defectInfo[3] + "</td>");
			out.println("<td>" + defectInfo[4] + "</td>");
			out.println("<td>" + defectInfo[5] + "</td>");
			out.println("<td><form action = \"Edit_Defect_Form.jsp\">");
			out.println("<input type=\"submit\" value=\"Edit\">");
			out.println("</form></td>");
			out.println("</tr>");		
		}
		catch (IOException ioe) {
		}
	}
}

%>

<html>
<head>
    <title>Defect Tracker</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<!--<body bgcolor=#ADD8E6> -->
<body>

    <div>   
         
         <div align="center"> <h1>Defect Tracking System</h1> </div>
         
         <br>

		 <!------------------------------------------------------------------------>
		 <!-- MOCKED input data,  later defects will be populated via a database -->
		 <!------------------------------------------------------------------------>
		 <%

			String assign = "123";
            ArrayList<String> defectList = new ArrayList<String>();
		    ArrayList<String> assignees = new ArrayList<String>();

			/////////////////////////////////////////////////////////////////////////////////
			// THIS IS WHERE WE NEED TO POPULATE THE DEFECT LIST BY QUERYING A DATABASE
			// OR SOMETHING.
			// RIGHT NOW I AM USING CANNED DATA.....
			/////////////////////////////////////////////////////////////////////////////////
			defectList.add("0001@@@Open@@@1@@@Ryan Suzuki@@@Defect 1@@@It does not work");
			defectList.add("0002@@@Open@@@1@@@Michael Morodomi@@@Defect 1@@@It does not work");
			defectList.add("0003@@@Close@@@2@@@Perry On@@@Defect 2@@@It does not work too");
			defectList.add("0004@@@In Progress@@@3@@@Karen Sam@@@Defect 3@@@It sometimes works");
			defectList.add("0005@@@Unknown@@@4@@@Putul Patel@@@Defect 4@@@It works every other time");
			defectList.add("0006@@@Close@@@1@@@Ryan Suzuki@@@Defect 1@@@Incorrect design");
			
			// get a list of assignees from the defect list
			for (String defect : defectList) {

				//
				// Find All Assignees from Defect List
				//
				String def = defect;
				String[] info = def.split("@@@");
				
				if (!assignees.contains(info[3])) {
					assignees.add(info[3]);
				}
				

				out.println(defect);
		 %> <br>
		 <%
			}
		 %>
		 <%
			for (String assignee : assignees) {
				out.println(assignee);
		 %> <br>
		 <%
			}
		 %>


		 <!------------------------------------------------------------------------>
		 <!-- ADD Defect Button                                                  -->
		 <!------------------------------------------------------------------------>
        <!-- <form action = "Add_Defect_Form.jsp">
            <input type="submit" value="Add Defect">
        </form>
-->
					<form action = "Edit_Defect_Form.jsp">
								<input type="submit" value="Edit">
					</form>		
        <br><br><br> 
        
        Email Status to:
        <!--- List of person should come from database  -->
         <select id="EmailStatus" name="EmailStatus">
          <option value="all" selected="selected">Select One</option>
          <option value="assignee1">Mike Morodomi</option>
          <option value="assignee2">Perry On</option>
          <option value="assignee3">Ryan Suzuki</option>
          <option value="assignee4">Putul Patel</option>
         </select>
<!--        <button><a href="mailto:MichaelMorodomi@example.com" onClick="alert('Email Sent!')">Email</a></button> -->

         <br><br><br>         
                 
        <table border="5" bgcolor="#99FFFF" cellpadding="5">
            <tr bgcolor="#66CCFF">
                <th><strong><font size="4">Defect Number</font></strong></th>
                <th><strong><font size="4">Status</font></strong></th>
                <th><strong><font size="4">Priority</font></strong></th>
                <th><strong><font size="4">Assignee</font></strong></th>
                <th><strong><font size="4">Summary</font></strong></th>
                <th><strong><font size="4">Detail Description</font></strong></th>
                <th></th>
            </tr>
            
            <tr>
                <td></td>                
                <td>
                <select name="FilterByStatus">
                  <option value="all" selected="selected">All</option>
                  <option value="open">Open</option>
                  <option value="close">Close</option>
                  <option value="in_progress">In Progress</option>
                  <option value="unknown">Unknown</option>
                 </select> 
             </td>
             <td>
                <select name="FilterByPriority">
                  <option value="all" selected="selected">All</option>
                  <option value="priority1">1</option>
                  <option value="priority2">2</option>
                  <option value="priority3">3</option>
                  <option value="priority4">4</option>
                  <option value="priority5">5</option>
                </select>
             </td>
             <td>
                <form action="DefectTracker.jsp"><select id="FilterByAssignee" name="FilterByAssignee">
                  <option value="all" selected="selected">All</option>

				  <!-- Add All Assignees here -->
				  <% int index = 0; 
				     for (String assignee : assignees) {

						 String strValue = "assignee" + Integer.toString(index++); %>

				         <option value="<%=assignee%>"><%=assignee%></option>

                  <% } %>

                    <form action = "DefectTracker.jsp"></form>		
				 
					<% assign = request.getParameter("FilterByAssignee"); %>
					
					
             <!-- <option value="assignee1">Mike Morodomi</option>
                  <option value="assignee2">Perry On</option>
                  <option value="assignee3">Ryan Suzuki</option>
                  <option value="assignee4">Putul Patel</option> -->
                 </select></form>
				 
				  <% out.println(assign); %>
             </td>
             <td></td>
             <td></td>
            </tr>

			<% displayDefectTable(defectList, out, request); %>

           <!--- Value should be read from database and use JSP to populate the table. (This is hardcoded as an example)-->
		   
		   <% for (String defect : defectList) {

			      String[] defectInfo = defect.split("@@@"); %>
				  
 	              <tr>
                       <td><%=defectInfo[0]%></td>
                       <td><%=defectInfo[1]%></td>
                       <td><%=defectInfo[2]%></td>
                       <td><%=defectInfo[3]%></td>
                       <td><%=defectInfo[4]%></td>
                       <td><%=defectInfo[5]%></td>
					   <td><form action = "Edit_Defect_Form.jsp">
								<input type="submit" value="Edit">
						   </form></td>
<!--                       <td><button type="button" onclick="location.href='Defect_1.html'">Edit</button></td> -->
                  </tr>
		   <% } %>

       <!-- <tr>
                <td>0001</td>
                <td>Open</td>
                <td>1</td>
                <td>Michael Morodomi</td>
                <td>Defect 1</td>
                <td>It does not work.</td>
                <td><button type="button" onclick="location.href='Defect_1.html'">Edit</button></td>
            </tr> -->

        </table>

    </div>

</body>
</html>