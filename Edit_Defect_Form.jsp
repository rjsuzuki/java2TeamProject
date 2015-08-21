<html>

<!--<body bgcolor=#ADD8E6> -->
<body>
    <div>   
         
         <div align="center"> <h1>Defect 0001</h1> </div>
         
         <form>         
             <b>Defect Number: 0001 </b>            
             <br><br>  
             
             <b>Status</:</b>
             <input type="text" name="status" value="Open">             
             <br><br>
             
             <b>Priority</:</b>
             <input type="text" name="priority" value="1">             
             <br><br>
             
             <b>Assignee:</b>
             <input type="text" name="assignee" value="Michael Morodomi">    
             <button><a href="mailto:MichaelMorodomi@example.com">Email</a></button>      
             <br><br>  
              
             <b>Summary:</b>
             <input type="text" name="summary" value="Defect 1">             
             <br><br>
             
             <b>Detail Description:</b>             
             <br>
             <textarea name="description" rows="5" cols="50">It does not work.</textarea>
             <br><br>
              
		<!-- <button type="button" onclick="location.href='index.html'">Update</button>
             <button type="button" onclick="location.href='index.html'">Back</button> -->
         </form>
		 
		 <form action = "DefectTracker.jsp">
             <input type="submit" value="Update">
			 <input type="submit" value="Back">
         </form>
    </div>
    
    
</body>

</html>
