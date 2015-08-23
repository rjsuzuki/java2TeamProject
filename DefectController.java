import java.io.*;
import java.text.*;
import java.util.Date;
import java.servlet.*;
import java.servlet.http.*;


public class DefectController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    //need to edit here
    private static String ADD_OR_EDIT = "/example.jsp";
    private static String LIST_DEFECT = "/defect.jsp";
    private DefectDao dao;

    public DefectController() {
        super();
        dao = new DefectDao();
    }


/* -------------------------------------------------------------------------------------------
All of these actions will take the data from the JSP file and submit it to the MySql Database
The parameters must by in sync with the JSP <form>
---------------------------------------------------------------------------------------------*/

    public void doGet(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {

              String jspPage = "";
              String action = request.getParameter("action");
                
                //equalsIgnoreCase() compares Strings without case sensitivity.
              if (action.equalsIgnoreCase("delete")) {
                    String defectId = request.getParameter("defectId");
                    dao.deleteDefect(defectId);
                    jspPage = LIST_DEFECT;
                    request.setAttribute("defects", dao.getAllDefects());
              } else if (action.equalsIgnoreCase("edit")) {
                  jspPage = ADD_OR_EDIT;
                  String userId = request.getParameter("defectId");
                  Defect defect = dao.getDefectById(defectId);
                  request.setAttribute("defect", defect);
              } else if(action.equalsIgnoreCase("listDefect")){
                  jspPage = LIST_DEFECT;
                  request.setAttribute("defects", dao.getAllDefects());
              } else {
                  jspPage = ADD_OR_EDIT;
              }

              RequestDispatcher rd = request.getRequestDispatcher(forward);
              rd.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

            Defect defect = new Defect();
            //add more here

            try {
                Date creation = new SimpleDateFormat("yyyy/MM/dd").parse(request.getParameter(""));
                System.out.println("Created on: " + creation);
                defect.setDateOfCreation(creation);
              } catch (ParseException e) {
                  e.printStackTrace();
              }
              defect.setExample(request.getParameter("example"));
              String example = request.getParameter("example");

              RequestDispatcher rd = request.getRequestDispatcher(LIST_DEFECT);
              request.setAttribute("defects", dao.getAllDefects());
              rd.forward(request, response);
            }
} //end of Class
