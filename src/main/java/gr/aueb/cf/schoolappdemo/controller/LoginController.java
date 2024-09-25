package gr.aueb.cf.schoolappdemo.controller;

import gr.aueb.cf.schoolappdemo.authentication.AuthenticationProvider;
import gr.aueb.cf.schoolappdemo.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolappdemo.dto.UserLoginDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/*Flow: User goes to register page, does successful registration, so record to the DB
 * After registration goes to login page, and
 * Login Controller raise a session, writing in Session Object a username
 * and redirect it to the teachers page
 * Then client will receive a cookie(setCookie) and send another request  to go to another page
 * So after login, every new request should be checked if there is a session active with this login
Every new request should be checked if there is state for that,  login has been done by filters (AuthFilter)/
        */

@WebServlet("/login")
public class LoginController extends HttpServlet {

    /*doGET*/
    //1. Receives a request to login and send a response, go to the page - dispatch
    //2. error management. If user insert wrong credentials return to login page so we have another ONE GET
    //   after login error
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*Query String*/
        // "/login?isError=true"
        //So after unsuccessful POST, providing wrong credentials that did NOT VALIDATE
        //It will turn back in login page with this Query String: isError=true
        //And then GET will take with GET Parameter the value of isError, which is true
        //then pass this in jsp

        //Takes some data of isError condition
        //Data Binding
        String isError = request.getParameter("isError");

        //if "/login" then isError == null
        //if "/login?isError=true" then isError == isError

//        if (isError != null) {
//            request.setAttribute("isError", isError);
//        }
        request.setAttribute("isError", isError == null ? "false" : "true" );

        /*Basic role of doGet --> go to the page*/
        request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
    }

    /* doPost */
    // the action that activated after user's credentials submission.
    // Takes some data: .getParameter("username") == name="username" from the input of the form in login.jsp file
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*Data Binding*/
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();


        /*Creates an object for the subsequent conditional check*/
        //Principle is the login user
        boolean principleIsAuthenticated = false;

        /*DTO instance*/
        UserLoginDTO userLoginDTO = new UserLoginDTO(username, password);

        try {
            /*Connect the object with the method that is used in conditional*/
            principleIsAuthenticated = AuthenticationProvider.authenticate(userLoginDTO);

            /*If authentication passed*/
            if (principleIsAuthenticated) {

                //1) creation of new session set false
                HttpSession session = request.getSession(false);
                //2) saving username for this session in Session Object
                session.setAttribute("username", username);
                //3) send the page "teachers" back to client
                response.sendRedirect(request.getContextPath() + "/teachers");
            } else {
                /*If authentication fail send user back to page login with the parameter isError=true: "/login?isError=true"*/
                response.sendRedirect(request.getContextPath() + "/login?isError=true");
            }
        } catch (UserDAOException e) {
            response.sendRedirect(request.getContextPath() + "/login?isError=true");
        }
    }
}
