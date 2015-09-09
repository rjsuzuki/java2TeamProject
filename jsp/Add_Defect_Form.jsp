<html>

<head>
<!--
<script type="text/javascript" src="scrip.js">
 
 var p = (document.getElementById("priority");
 if (p.options[p.selectedIndex].value != 0)
    document.getElementById("submit").setAttribute("enabled");
</script>
-->
<!--<body bgcolor=#ADD8E6> -->
<body>
	<!-- Javascript to verify Text field are not empty -->
	<script>
		function verify(){
			if (document.getElementById('priority').value.trim() == "Select One") {
				alert('Please enter the priority.');
				return false;
			}
			else if (document.getElementById('status').value.trim() == "Select One") {
				alert('Please enter the status.');
				return false;
			}
			else if (document.getElementById('assignee').value.trim() == "Select One") {
				alert('Please enter the assignee.');
				return false;
			}
			else if (!document.getElementById('summary').value.trim()) {
				alert('Please enter the summary.');
				return false;
			}
			else if (document.getElementById('summary').length > 100) {
				alert('Please enter the summary less than 100 characters.');
				return false;
			}
			else if (!document.getElementById('description').value.trim()) {
				alert('Please enter the detailed description.');
				return false;
			}
			else {
				return true;
			}
		}
	</script>
    <div>   
         
         <div align="center"> <h1>Add Defect</h1> </div>
         
		<form action = "/DefectTracker/AddDefectDatabaseServlet" method="POST" onsubmit="return verify()">
         
         <!-- 
         Defect Number should be hardcoded and come from defect number in database + 1 
         This is where JSP is useful: getAttribute()
         -->
         
         <b> * Priority</:</b>
         <select name="priority" id="priority">
          <option selected disabled class="hideoption">Select One</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
         </select>            
         <br><br>
         
         <!-- Status options should be stored in database. (This is hardcoded as an example) -->         
         <b> * Status</:</b>
         <select name="status" id="status">
          <option selected disabled class="hideoption">Select One</option>
			<option value="Open">Open</option>
			<option value="Unknown">Unknown</option>
         </select>          
         <br><br>
         
         <!-- Assignee list should come from Database. (This is hardcoded as an example)-->
         <b> * Assignee:</b>
         <select name="assignee" id="assignee">>
          <option selected disabled class="hideoption">Select One</option>
          <option value="Mike Morodomi">Mike Morodomi</option>
          <option value="Perry On">Perry On</option>
          <option value="Ryan Suzuki">Ryan Suzuki</option>
          <option value="Putul Patel">Putul Patel</option>
		  <option value="Karen Sam">Karen Sam</option>
         </select>         
         <br><br>
         
         <b> * Summary:</b>
         <input type="text" id="summary" name="summary"> 
		 <label style="padding-left: 8px;">(100 character max)</label>
         <br><br>         
         
         <b> * Detail Description (200 char max):</b>
         <br>
         <textarea name="description" id="description" rows="5" cols="50"></textarea>  
		 <label style="padding-left: 8px;">(200 character max)</label>		 
         <br><br>          
         <p style="padding-left: 10px;"> * required fields</p>
         <!-- After clicking submit button, should update the table in the index.html page, use JSP -->
<!--         <button type="button" id="submit" disabled onclick="location.href='index.html'">Submit</button>
         <button type="button" id="cancel" onclick="location.href='index.html'">Cancel</button> -->

             <input type="submit" value="Submit">
			 			<!-- Cancel Adding Defect -->
            <button type="submit" onclick="location.href='/DefectTracker/index.jsp'">Cancel</button>
         </form>
    </div>   
</body>
</html>
