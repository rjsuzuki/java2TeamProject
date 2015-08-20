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
    <div>   
         
         <div align="center"> <h1>Add Defect</h1> </div>
         
         <form>
         
         <!-- 
         Defect Number should be hardcoded and come from defect number in database + 1 
         This is where JSP is useful: getAttribute()
         -->      
         <b>Defect Number: 0002 </b>          
         <br><br>
         
         <b>Priority</:</b>
         <select name="priority" id="priority">
          <option selected disabled class="hideoption">Select One</option>
          <option value="priority1">1</option>
          <option value="priority2">2</option>
          <option value="priority3">3</option>
          <option value="priority4">4</option>
          <option value="priority5">5</option>
         </select>            
         <br><br>
         
         <!-- Status options should be stored in database. (This is hardcoded as an example) -->         
         <b>Status</:</b>
         <select name="status" id="status">
          <option selected disabled class="hideoption">Select One</option>
          <option value="open">Open</option>
          <option value="close">Close</option>
          <option value="in_progress">In Progress</option>
          <option value="unknown">Unknown</option>
         </select>          
         <br><br>
         
         <!-- Assignee list should come from Database. (This is hardcoded as an example)-->
         <b>Assignee:</b>
         <select name="assignee" id="assignee">>
          <option selected disabled class="hideoption">Select One</option>
          <option value="assignee1">Mike Morodomi</option>
          <option value="assignee2">Perry On</option>
          <option value="assignee3">Ryan Suzuki</option>
          <option value="assignee4">Putul Patel</option>
         </select>         
         <br><br>
         
         <b>Summary:</b>
         <input type="text" name="summary" id="assignee">>         
         <br><br>         
         
         <b>Detail Description:</b>
         <br>
         <textarea name="description" id="description"rows="5" cols="50"></textarea>         
         <br><br>          
         
         <!-- After clicking submit button, should update the table in the index.html page, use JSP -->
<!--         <button type="button" id="submit" disabled onclick="location.href='index.html'">Submit</button>
         <button type="button" id="cancel" onclick="location.href='index.html'">Cancel</button> -->
 
         </form>

		 <form action = "DefectTracker.jsp">
             <input type="submit" value="Submit">
			 <input type="submit" value="Cancel">
         </form>
    </div> 
   
    
</body>

</html>