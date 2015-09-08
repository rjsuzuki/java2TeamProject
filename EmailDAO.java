
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

public class EmailDAO {
    private Connection connection;
    private Defect defectObj;
    private String email = "";
    
    public EmailDAO() {
    	connection = Database.getConnection();
    	defectObj = new Defect();
    }
    
    public boolean sendEmail(String assignee, String defectId) throws NamingException, SQLException {
    	Statement statement;
    	// name[0] = first name
    	// name[1] = last name
    	String name[] = assignee.split(" ");
    	
    	System.out.println("sendEmail: name[0] = "+name[0]);
    	System.out.println("sendEmail: name[1] = "+name[1]);
		statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM ASSIGNEE");
		while (rs.next()) {
			// Compare last name with assignee column 3 (last name)
			if ( name[1].contains(rs.getString(3)) ) {
				System.out.println("sendEmail: Last Name = "+rs.getString(3)+" Email Address = "+rs.getString(4));
				System.out.println("sendEmail: Found last name");
				email = rs.getString(4);
			}
		}
		
		statement = connection.createStatement();
		ResultSet rs2 = statement.executeQuery("SELECT * FROM DEFECTS");
		//Defect defect = new Defect();
		while (rs2.next()) {
			// Compare defectId
			if ( defectId.contains(rs2.getString(1)) ) {
				System.out.println("sendEmail: Found defectId");
				defectObj.setDefectId(rs2.getInt(1));
				defectObj.setStatus(rs2.getString(2));
				defectObj.setAssignee(rs2.getString(3));
				defectObj.setSummary(rs2.getString(4));
				defectObj.setDescription(rs2.getString(5));
				defectObj.setPriority(rs2.getInt(6));
			}
		}
		
		//Close the Statement and Connection
		statement.close();
		connection.close();
		
		System.out.println("sendEmail: email = "+email);
		
		EmailSMTP mailObj = new EmailSMTP();
		
		return mailObj.sendMail(email, defectObj);
    }
    
    public Defect getDefect() {
    	return defectObj;
    }
    
    public String getEmail() {
    	return email;
    }

}
