
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
   <head>
      <title>Java Programming II Example</title>
	  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
   </head>
   <body bgcolor="#FFFFF">

		<!-- Get Defect Information from Edit Defect Servlet -->
		<c:set var="defectId" value="${defect.defectId}" />
		<c:set var="status" value="${defect.status}" />
		<c:set var="priority" value="${defect.priority}" />
		<c:set var="assignee" value="${defect.assignee}" />
		<c:set var="summary" value="${defect.summary}" />
		<c:set var="description" value="${defect.description}" />

      <%
	    // Get email status and recipient information
		String emailstatus = request.getAttribute("email_status").toString();
		String email = request.getAttribute("recipient_email").toString();
	  %><br>
		<div style="padding-left: 140px;"> <h1>Email Information</h1> </div>
		<br>
		<b style="padding-right: 8px;">Email Status :</b>  <%=emailstatus%><br>
		<br>
		<b style="padding-right: 8px;">To :</b>  <%=email%><br>
		<br>
		<b style="padding-right: 8px;">From :</b>  Defect Tracker<br>
		<br>
		<b style="padding-right: 8px;">Subject :</b>  Defect ID-${defectId} status information<br>
		<br>
		<b style="padding-right: 8px;">Date :</b>  Today<br>
		<br>
		<b>Content:</b>
		<br>
		<b>---------------------------------------------------------</b>
		<br>
		<label style="padding-left: 30px;padding-right: 9px;">Defect Id    :</label>${defectId}<br>
		<label style="padding-left: 30px;padding-right: 9px;">Status       :</label>${status}<br>
		<label style="padding-left: 30px;padding-right: 9px;">Assignee     :</label>${assignee}<br>
		<label style="padding-left: 30px;padding-right: 9px;">Summary      :</label>${summary}<br>
		<label style="padding-left: 30px;padding-right: 9px;">Description  :</label>${description}<br>
		<label style="padding-left: 30px;padding-right: 9px;">Priority     :</label>${priority}<br>
		<br>
		<br>
		<label style="padding-left: 160px;"></label>
		<button type="button" onclick="location.href='/DefectTracker/index.jsp'">Back to Main Page</button>
	  
   </body>
</html>
