
import java.sql.*;
import java.util.*;

public class DefectDao {
    private Connection connection;

    public DefectDao() {
      connection = Database.getConnection();
    }

    public void checkDefect(Defect defect) {
        try {
            //Need to edit MYSQL statement
            PreparedStatement ps = connection.prepareStatement("SELECT * from DFECTLIST where * =?");
            ps.setString(1, defect.get);

        }
    }

    public void addDefect(Defect defect) {
          try {
              //Need to edit MYSQL statement
              PreparedStatement ps = connection.prepareStatement("INSERT INTO ");
              ps.setString(1, defect.getStatus());
              ps.setString(2, defect.getPriority());
              ps.setString(3, defect.getAssignee());
              ps.setString(4, defect.getSummary());
              ps.setString(5, defect.getDescription());
              ps.executeUpdate();
          } catch (SQLException e) {
              e.printStackTrace();
          }
    }

    public void deleteDefect(int defectId) {
        try {
            //Need to edit MYSQL statement
            PrepararedStatement ps = connection.prepareStatement("");
            ps.setString(1, defectId);
            ps.executeUpdate();
          } catch (SQLException e) {
              e.printStackTrace();
          }
    }

    //Need to add view method to see all Defects
    //getAllDefects();

    //Need to add email method? Does that go in here?

    //Need to add assign a defect request to a person



} //end of Class
