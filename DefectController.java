import java.io.*;
import java.text.*;
import java.util.Date;
import java.servlet.*;
import java.servlet.http.*;


public class DefectController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    //these will need to be changed accordingly.
    private static String ADD_OR_EDIT = "/update.jsp";
    private static String VIEW_DEFECTS = "/defects.jsp";
    private static String HOME = "/index.jsp";
    private static String EMAIL_SUCCESS = "/email.jsp";

    private DefectDao dao;

    public DefectController() {
        super();
        dao = new DefectDao();
    }


/* -------------------------------------------------------------------------------------------
All of these actions will take the data from the JSP file and submit it to the MySql Database
The parameters must by in sync with the JSP <form>
So check button "names"
---------------------------------------------------------------------------------------------*/

    //GET operations DO NOT alter data
    public void doGet(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {

              response.setContentType("text/html");
              PrintWriter out = response.getWriter();

              String forward = "";
              String a = request.getParameter("buttonName");
              String b = request.getParameter("buttonName2");

              if (a.equals("viewAllDefects")) {
                    forward = VIEW_DEFECTS;
                    //check method name and parameters
                    request.setAttribute("defectList", dao.getAllDefects());
              } else if (b.equals("sendEmail")) {
                    forward = EMAIL_SUCCESS;
                    String userId = request.getParameter("userId");
                    dao.emailUser(userId); //check method name and parameters
                    request.setAttribute("email", dao.emailUser());
              } else {
                  forward = HOME;
                  out.print("Something went wrong...");

              }

              RequestDispatcher rd = request.getRequestDispatcher(forward);
              rd.forward(request, response);
    }

    //POST operations DO alter data
    public void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            String c = request.getParameter("buttonName3");
            String d = request.getParameter("buttonName4");

            if (c.equals("addDefect")) {
                forward = HOME;
                Defect defect = new Defect();
                defect.setStatus(request.getParameter("status"));
                defect.setAssignee(request.getParameter("assignee"));
                defect.setSummary(request.getParameter("summary"));
                defect.setDescription(request.getParameter("description"));
                request.setAttribute("addDefect", dao.addDefect(defect));
            } else if (d.equals("editDefect")) {
                forward = HOME;
                // must edit this section.

                
            } else {
                  out.print("Something went wrong.");
            }
            RequestDispatcher rd = request.getRequestDispatcher(forward);
            rd.forward(request, response);

      }
} //end of Class
