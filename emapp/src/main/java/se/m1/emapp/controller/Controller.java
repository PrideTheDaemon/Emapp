package se.m1.emapp.controller;
import se.m1.emapp.model.business.Credential;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static se.m1.emapp.utils.Constants.*;

import se.m1.emapp.model.business.AppDbHelper;
import se.m1.emapp.model.business.Employee;
import se.m1.emapp.model.core.exception.dbLink.DBLException;
import se.m1.emapp.model.exception.EmptyResultException;
import se.m1.emapp.model.core.DBLink;
import se.m1.emapp.model.core.DBObject;
import se.m1.emapp.model.core.JPAManager;
import se.m1.emapp.model.core.exception.DatabaseCommunicationException;

public class Controller extends HttpServlet {
    //private Properties properties;
    //private DBLink dbLink;
    private String action;
    private HttpSession session;
    //private AppDbHelper helper;
    private Credential user;
    private JPAManager jpa;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        /*try {Class.forName("com.mysql.jdbc.Driver");} 
        catch (ClassNotFoundException e) {
            throw new ServletException(e);
        }*/
        user = null;
        jpa = new JPAManager();

        if (request.getParameter("action") == null) {
            request.getRequestDispatcher(JSP_HOME_PAGE).forward(request, response);
        } else {
            action = request.getParameter("action");
            session = request.getSession();
            switch (action) {
                case "Login":
                    String login = request.getParameter("loginField");
                    String password = request.getParameter("pwdField");
                    
                    if(login.equals("") || password.equals(""))
                    {
                        request.setAttribute("errKey", ERR_MESSAGE_FIELD_EMPTY);
                        request.getRequestDispatcher(JSP_HOME_PAGE).forward(request, response);
                    }
                    user = new Credential(login, password);
                    
                    if(jpa.checkCredentials(user))
                    {
                        session.setAttribute("empList", jpa.getAll());
                        session.setAttribute("user", user);
                        request.getRequestDispatcher(JSP_WELCOME_PAGE).forward(request, response);
                    }else{
                        request.setAttribute("errKey", ERR_MESSAGE_INVALID_CREDENTIALS);
                        request.getRequestDispatcher(JSP_HOME_PAGE).forward(request, response);
                    }
                    break;
                case "Delete":
                    if(request.getParameter("check")==null){
                        request.setAttribute("errCheck", ERR_CHECK);
                    } else {
                        int id = Integer.parseInt(request.getParameter("check"));          
                        
                            jpa.removeEmployee(new Employee( id));                           
                            session.setAttribute("empList", jpa.getAll());
                    }
                    request.getRequestDispatcher(JSP_WELCOME_PAGE).forward(request, response);
                    break;
                case "Details":
                    if(request.getParameter("check") == null) {
                        request.setAttribute("errCheck", ERR_CHECK);
                        request.getRequestDispatcher(JSP_WELCOME_PAGE).forward(request, response);
                    } else {
                        int idD = Integer.parseInt(request.getParameter("check"));
                            session.setAttribute("employeeChecked", jpa.read(idD));
                            request.getRequestDispatcher(JSP_ADD).forward(request, response);
                    }
                    break;
                case "Add":
                    session.setAttribute("employeeChecked", new Employee(0));
                    request.getRequestDispatcher(JSP_ADD).forward(request, response);
                    break;
                case "Save":
                    if (((Employee)session.getAttribute("employeeChecked")).getId() != 0) {
                        Employee employee = new Employee(((Employee)session.getAttribute("employeeChecked")).getId(), request.getParameter("inputFirstName"),
                                request.getParameter("inputLastName"), request.getParameter("inputHomePhone"),
                                request.getParameter("inputMobilePhone"), request.getParameter("inputWorkPhone"),
                                request.getParameter("inputAddress"), request.getParameter("inputPostalCode"),
                                request.getParameter("inputCity"), request.getParameter("inputEmail"));
                        
                                System.out.println(request.getParameter("inputLastName")+"klqsflkfjmjqfjm<qfjsmQJfm   ");
                                
                            jpa.modifyEmployee(employee);
                            session.setAttribute("empList", jpa.getAll());
                            session.removeAttribute("employeeChecked");
                    } else {
                        Employee employee = new Employee(request.getParameter("inputFirstName"),
                                request.getParameter("inputLastName"), request.getParameter("inputHomePhone"),
                                request.getParameter("inputMobilePhone"), request.getParameter("inputWorkPhone"),
                                request.getParameter("inputAddress"), request.getParameter("inputPostalCode"),
                                request.getParameter("inputCity"), request.getParameter("inputEmail"));
                        System.out.println("klqsflkfjmjqfjm<qfjsmQJfm"+employee.getLastName());
                        jpa.createEmployee(employee);
                            
                    }
                    session.setAttribute("empList", jpa.getAll());
                    request.getRequestDispatcher(JSP_WELCOME_PAGE).forward(request, response);
                    break;
                case "Cancel":
                    if (session.getAttribute("employeeChecked") != null) {
                        session.removeAttribute("employeeChecked");
                    }
                        session.setAttribute("empList", jpa.getAll());


                    request.getRequestDispatcher(JSP_WELCOME_PAGE).forward(request, response);
                case "LogOut":
                    request.getRequestDispatcher(JSP_GOODBYE_PAGE).forward(request, response);
                default:
                    request.getRequestDispatcher(JSP_HOME_PAGE).forward(request, response);
            }
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void sendError(HttpServletRequest request, HttpServletResponse response, Exception errorText) throws ServletException, IOException {
        request.setAttribute("errorMessage", errorText.getMessage());
        request.setAttribute("firstDigit", 5);
        request.setAttribute("secondDigit", 0);
        request.setAttribute("thirdDigit", 0);
        request.getRequestDispatcher(JSP_ERROR_PAGE).forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
