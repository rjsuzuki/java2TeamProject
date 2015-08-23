
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class ServletToDatabase extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse resonse)
                throws ServletException, IOException {

                Connection connection = null;
                PreparedStatement pst = null;

                //the server is currently set locally on Ryan's computer, MUST change this for external access.
                String url = "jdbc:mysql://localhost:3306/";
                String databaseName = "mysql";
                String driver = "com.mysql.jdbc.Driver";
                String userName = "root";
                String password = "yolo7156rty7";

                try {
                    Class.forName(driver);
                    connection = DriverManager.getConnection(url + databaseName, userName, password);

                    //retrieves information from HTML Form submission.
                    String status = request.getParameter("status");
                    String priority = request.getParameter("priority");
                    String assignee = request.getParameter("assignee");
                    String summary = request.getParameter("summary");
                    String description = request.getParameter("description");

                    //the mysql insert statement
                    String query = "INSERT INTO defectlist (status, priority, assignee, summary, description)"
                                    + " values (?, ?, ?, ?, ?)";

                    pst = connection.prepareStatement(query);
                    pst.setString(1, status);
                    pst.setString(2, priority);
                    pst.setString(3, assignee);
                    pst.setString(4, summary);
                    pst.setString(5, description);

                    pst.execute();

        } catch (Exception exception) {
            System.out.println("Sorry, but there was an error: " + exception);
        }
} //end of submit()
} //end of Class
