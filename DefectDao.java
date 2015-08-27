import java.sql.*;
import java.util.*;

public class DefectDao {
    private Connection connection;

    public DefectDao() {
      connection = Database.getConnection();
    }

    public void addDefect(Defect defect) {
          try {
              //Need to edit MYSQL statement
              PreparedStatement ps = connection.prepareStatement("INSERT INTO DEFECTLIST(status, priority, assignee, summary, description)VALUES (?, ?, ?, ?, ?)");
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

    //getAllDefects();
    public List<Defect> getAllDefects() {
        List<Defect> defectList = new ArrayList<Defect>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM DEFECTLIST");
            while (rs.next()) {
                  Defect defect = new Defect();
                  defect.setStatus(rs.getString("status"));
                  defect.setPriority(rs.getString("priority"));
                  defect.setAssignee(rs.getString("assignee"));
                  defect.setSummary(rs.getString("summary"));
                  defect.setDescription(rs.getString("description"));
                  defectList.add(defect);
            }
        } catch (SQLException e) {
              e.printStackTrace();
        }
        return defectList;
    }

    //Need to add email method? Does that go in here?



} //end of Class
