# java2TeamProject
group project for UCI's JAVA II class

System should provide several features:
The ability to submit a defect - includes status, priority, assignee, summary, and detailed description.
The ability to update an existing bug(defect)
The ability to assign a defect request to a person.
The ability to email status info to a user
The ability to view a list of all open bugs(defects).

Tomcat configuration:
1. Create DefectTracker directory under tomcat\webapps directory
2. Copy jsp folder from GitHub under DefectTracker
3. Copy WEB-INF folder from GitHub under DefectTracker
4. Create lib folder under WEB-INF
5. Download following jar files and place them under lib folder
    - jstl-1.2.jar link: http://mvnrepository.com/artifact/javax.servlet/jstl/1.2
    - mail.jar link: http://www.oracle.com/technetwork/java/javamail/javamail145-1904579.html
    - mysql-connector-java-5.1.36-bin.jar
6. Create classes folder under WEB-INF and put all compiled java files (.class) here
7. Start tomcat with tomcat/bin/startup script
8. Use following link from the web browser
    - localhost:8080\DefectTracker
