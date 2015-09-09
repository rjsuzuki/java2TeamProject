# java2TeamProject
group project for UCI's JAVA II class

System should provide several features:
The ability to submit a defect - includes status, priority, assignee, summary, and detailed description.
The ability to update an existing bug(defect)
The ability to assign a defect request to a person.
The ability to email status info to a user
The ability to view a list of all open bugs(defects).

Tomcat configuration:
01. Create DefectTracker directory under tomcat\webapps directory
02. Copy jsp folder from GitHub under DefectTracker
03. Copy WEB-INF folder from GitHub under DefectTracker
04. Create lib folder under WEB-INF
05. Download the following jar files and place them under lib folder
   a. jstl-1.2.jar link: http://mvnrepository.com/artifact/javax.servlet/jstl/1.2
   b. mail.jar (JavaMail 1.4.5) link: http://www.oracle.com/technetwork/java/javamail/javamail145-1904579.html
      (Need to unzip file, it is located in javamail-1.4.5 directory)
   c. Copy from mysql-connector-java directory (E.g., mysql-connector-java-5.1.36-bin.jar)
06. Create classes folder under WEB-INF and put all compiled java files (.class) here
07. Copy index.jsp to tomcat\webapps\DefectTracker directory
08. Run database creation script by running mySql (enter password as needed) then copy and paste script to mySql console.
09. Start tomcat with tomcat/bin/startup script
10. Use following link from the web browser - localhost:8080/DefectTracker (Do not use Firefox browser.  Not compatible with FireFox)
