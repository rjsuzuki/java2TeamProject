
import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmailServlet extends HttpServlet {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private EmailDAO emailDao;
    
    public EmailServlet() {
        super();
        emailDao = new EmailDAO();
    }
    
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		doPost(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String assignee = "";
        String defectId = "";

        assignee = request.getParameter("EmailAssignee");
        defectId = request.getParameter("EmailDefectId");
        System.out.println("doGet: Assignee = "+assignee);
        System.out.println("doGet: DefectId = "+defectId);
        try {
			if( emailDao.sendEmail(assignee, defectId) == true ) {
				request.setAttribute("email_status", "SUCCESS");
				request.setAttribute("recipient_email", emailDao.getEmail());
				request.setAttribute("defect", emailDao.getDefect());
			} else {
				request.setAttribute("email_status", "FAILURE");
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/Email_Status.jsp");
        rd.forward(request, response);
    }
}
