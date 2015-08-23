
import java.io.*;
import java.sql.*;
import java.util.*;


public class ServletToDatabase {


    public static boolean submit(String status, String priority, String assignee,
                                    String summary, String detailedDescription) {
        boolean classStatus = false;
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

            //the mysql insert statement
            String query = "INSERT INTO defectlist (status, priority, assignee, summary, detailedDescription)"
                            + " values (?, ?, ?, ?, ?)";

            pst = connection.prepareStatement(query);
            pst.setString(1, status);
            pst.setString(2, priority);
            pst.setString(3, assignee);
            pst.setString(4, summary);
            pst.setString(5, detailedDescription);

            pst.execute();


        } catch (Exception exception) {
            System.out.println("Sorry, but there was an error: " + exception);
        } finally {
              if (connection != null) {
                  try {
                      connection.close();
                  } catch (SQLException e) {
                      e.printStackTrace();
                  }
              }
              if (pst != null) {
                  try {
                      pst.close();
                  } catch (SQLException e) {
                      e.printStackTrace();
                  }
              }
        }
        return classStatus;
} //end of submit()
} //end of Class
