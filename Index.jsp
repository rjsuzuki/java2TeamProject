<!-- *********************************************************** -->
<!-- index.jsp: Defect Tracker main page.                        -->
<!--                                                             -->
<!-- *********************************************************** -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.*"%>
<%@ page import = "javax.servlet.RequestDispatcher" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
    <title>Defect Tracker</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<!--<body bgcolor=#ADD8E6> -->
<body onload="document.createElement('form').submit.call(document.getElementById('showAllDefects'))">

	<!-- Javascript to verify Email field are valid -->
	<script>
		function verify(){
			if (document.getElementById('EmailAssignee_ID').value.trim() == "all") {
				alert('Please enter the assignee in order to send email.');
				return false;
			}
			else if (document.getElementById('EmailDefectId_ID').value.trim() == "all") {
				alert('Please enter the defect ID in order to send email.');
				return false;
			}
			else {
				return true;
			}
		}
	</script>


    <div>

		<br>
        <div style="padding-left: 300px;"> <h1>Defect Tracking System</h1></div>
        <br><br>	
		
		<!------------------------------------------------------------------------>
		<!-- ADD Defect Button                                                  -->
		<!------------------------------------------------------------------------>
		<div>
			<b>Submit a Defect:</b>
			<form action = "/DefectTracker/jsp/Add_Defect_Form.jsp">
				<input type="submit" value="Submit Defect">
			</form>
		</div>
        <br><br>


        <b>Email Status To:</b>
        <!--- List of person should come from database  -->
		<form  class="row" action = "../DefectTracker/EmailServlet" method="POST" onsubmit="return verify()">

			<div>
				<b style="padding-right: 5px;">Users:</b>
				<select id="EmailAssignee_ID" name="EmailAssignee">
					<option value="all" selected="selected">Select One</option>
					<c:forEach items="${assigneeListDB}" var="AssigneeDB">
						<option value="<c:out value="${AssigneeDB}" />" />${AssigneeDB}</option>
					</c:forEach>
				</select>

				<b style="padding-left: 15px;padding-right: 5px;">Defect ID:</b>
				<select id="EmailDefectId_ID" name="EmailDefectId">
					<option value="all" selected="selected">Select One</option>
					<c:forEach items="${defectIdListDB}" var="DefectIdDB">
						<option value=${DefectIdDB}>${DefectIdDB}</option>
					</c:forEach>
				</select>

				<label style="padding-left: 20px;"></label>
				<input type="submit" name="Email_name" value="Email">
			</div>
		</form>
        <br><br>

		<div>

			<b>Display Defects:</b>
			<br>
			<form action="../DefectTracker/DefectController" method="POST">
				<input type="submit" name="defectListType" value="SHOW ALL DEFECTS">
				<label style="padding-left: 10px;"></label>
				<input type="submit" name="defectListType" value="SHOW ALL OPEN DEFECTS">
			</form>
			<br>

			<% if(request.getAttribute("message") != null) { %>
				<h3 style="padding-left: 380px;"><%= request.getAttribute("message") %></h3>

				<table border="5" bgcolor="#99FFFF" cellpadding="5">
					<tr bgcolor="#66CCFF">
						<th><strong><font size="4">Defect Number</font></strong></th>
						<th><strong><font size="4">Status</font></strong></th>
						<th><strong><font size="4">Priority</font></strong></th>
						<th><strong><font size="4">Assignee</font></strong></th>
						<th><strong><font size="4">Summary</font></strong></th>
						<th><strong><font size="4">Detail Description</font></strong></th>
						<th><strong><font size="4">Edit Defect</font></strong></th>
					</tr>
					<!--- Show defects from MySQL database -->	
					<c:choose>
						<c:when test="${not empty defectListDB}">
							<c:forEach items="${defectListDB}" var="DefectDB">
								<tr>
									<td align="center">${DefectDB.defectId}</td>
									<td align="center">${DefectDB.status}</td>
									<td align="center">${DefectDB.priority}</td>
									<td style="word-wrap: break-word;" width="140">${DefectDB.assignee}</td>
									<td style="word-wrap: break-word;" width="140">${DefectDB.summary}</td>
									<td style="word-wrap: break-word;" width="210">${DefectDB.description}</td>
									<c:set var="edit" value="Edit-" />
									<c:set var="editLabel" value="${edit}${DefectDB.defectId}" />
									<td align="center">
										<form action="../DefectTracker/EditDefectDatabaseServlet" method="POST">
											<input type="hidden" name="defectToEdit" value=${editLabel}>
											<input type="submit" value="Edit">
										</form>
									</td>
								</tr>
							</c:forEach>
							</table>

						</c:when>
						<c:otherwise>
							</table>
							<br>
							<b style="padding-left: 20px;">No Defect Found...</b>
						</c:otherwise>
					</c:choose> 

			<% }
			   else { %>
				<form id="showAllDefects" name="showAllDefects"  action="../DefectTracker/DefectController" method="POST">
					<input type="hidden" name="defectListType" value="SHOW ALL DEFECTS">
				</form>
			<% }
			%>
		</div>
    </div>
</body>

</html>
