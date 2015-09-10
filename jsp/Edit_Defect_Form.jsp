<!-- *********************************************************** -->
<!-- Edit_Defect_Form.jsp: provides the JSP form that allows     -->
<!--                       the user to edit a defect.            -->
<!--                                                             -->
<!-- *********************************************************** -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.util.*"%>
<%@ page import = "javax.servlet.RequestDispatcher" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>Defect Tracker - Edit Defect</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<!--<body bgcolor=#ADD8E6> -->
<body>

	<!-- Get Defect Information from Edit Defect Servlet -->
	<c:set var="defectId" value="${defect.defectId}" />
	<c:set var="status" value="${defect.status}" />
	<c:set var="priority" value="${defect.priority}" />
	<c:set var="assignee" value="${defect.assignee}" />
	<c:set var="summary" value="${defect.summary}" />
	<c:set var="description" value="${defect.description}" />
	<br><br>
	
	<!-- Javascript to verify Text field are not empty -->
	<script>
		function verify(){
			if (!document.getElementById('id_summary').value.trim()) {
				alert('Please enter the summary.');
				return false;
			}
			if (!document.getElementById('id_description').value.trim()) {
				alert('Please enter the detailed description.');
				return false;
			}
			else {
				return true;
			}
		}
	</script>

    <div>   
         
        <div style="padding-left: 200px;"> <h1>Update Defect ID-${defectId}</h1> </div><br>


        <form action="/DefectTracker/UpdateDatabaseServlet" method="Post" onsubmit="return verify()">
		
			<!-- hide defect ID -->
			<input type="hidden" name="defectId" value="<c:out value="${defectId}" />" />

			<!-- Status options -->         
			<b> * Status: </b>
			<select name="status">
				<option value="Open" ${status == 'Open' ? 'Selected' : ''}>Open</option>
				<option value="Complete" ${status == 'Complete' ? 'Selected' : ''}>Complete</option>
				<option value="Hold" ${status == 'Hold' ? 'Selected' : ''}>Hold</option>
				<option value="Unknown" ${status == 'Unknown' ? 'Selected' : ''}>Unknown</option>
			</select>          
			<br><br><br>

			<!-- Priority options --> 
			<b> * Priority: </b>
			<select name="priority">
				<option value="1" ${priority == '1' ? 'Selected' : ''}>1</option>
				<option value="2" ${priority == '2' ? 'Selected' : ''}>2</option>
				<option value="3" ${priority == '3' ? 'Selected' : ''}>3</option>
				<option value="4" ${priority == '4' ? 'Selected' : ''}>4</option>
				<option value="5" ${priority == '5' ? 'Selected' : ''}>5</option>
			</select>            
			<br><br><br>

			<!-- Update assignee -->
			<b> * Assignee: </b>
			<select name="assignee">
				<c:forEach items="${assigneeListDB}" var="AssigneeDB">
					<option value="<c:out value="${AssigneeDB}" />" ${assignee == AssigneeDB ? 'Selected' : ''} />${AssigneeDB}</option>
				</c:forEach>
			</select>
			<br><br><br>

			<!-- Update summary -->
            <b> * Summary: </b>
            <input type="text" id="id_summary" name="summary" maxlength="100" value="<c:out value="${summary}" />" />
			<label style="padding-left: 8px;">(100 character max)</label>
            <br><br><br>

			<!-- Update description -->
            <b> * Detail Description: </b>
            <br>
			<div style="padding-left: 10px;">
            <textarea name="description" rows="5" cols="50" id="id_description" maxlength="200" name="description">${description}</textarea>
			<label style="padding-left: 8px;">(200 character max)</label>
			</div>
			<p style="padding-left: 10px;"> * required fields</p>
            <br>

			<label style="padding-left: 260px;"></label>
			<input type="submit" name="submit_name" value="Update" />&nbsp;

			<!-- Cancel Defect Update -->
            <button type="button" onclick="location.href='/DefectTracker/index.jsp'">Cancel</button>
        </form>

    </div>

</body>
</html>
