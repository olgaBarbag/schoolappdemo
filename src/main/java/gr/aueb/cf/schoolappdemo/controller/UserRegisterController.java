package gr.aueb.cf.schoolappdemo.controller;

import gr.aueb.cf.schoolappdemo.dao.IUserDAO;
import gr.aueb.cf.schoolappdemo.dao.UserDAOImpl;
import gr.aueb.cf.schoolappdemo.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolappdemo.dto.UserInsertDTO;
import gr.aueb.cf.schoolappdemo.dto.UserReadOnlyDTO;
import gr.aueb.cf.schoolappdemo.model.User;
import gr.aueb.cf.schoolappdemo.service.IUserService;
import gr.aueb.cf.schoolappdemo.service.UserServiceImpl;
import gr.aueb.cf.schoolappdemo.validator.UserValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/users/register")
public class UserRegisterController extends HttpServlet {
    @Serial
    private static final long serialVersionUID = -506323871829403093L;

    private final IUserDAO userDAO = new UserDAOImpl();
    private final IUserService userService = new UserServiceImpl(userDAO);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*Basic role of doGet --> go to the jsp page*/
        request.getRequestDispatcher("/WEB-INF/jsp/user-register.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



        /*Data Binding*/
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String confirmPassword = request.getParameter("confirmPassword").trim();

        /*Creates an object for the subsequent conditional check*/
        Map<String, String> errors;
        String errorMessage = "";

        String usernameMessage;
        String passwordMessage;
        String confirmPasswordMessage;

        /*DTO instance*/
        UserInsertDTO userInsertDTO  = new UserInsertDTO(username, password, confirmPassword);

        User user;
        try {
            /*Connect the object with the method that is used in conditional*/
            errors = UserValidator.validate(userInsertDTO);

            /*Conditional checks if there are errors*/
            if (!errors.isEmpty()) {

                /*Binding to validator keys*/
                usernameMessage = errors.getOrDefault("username", "");
                passwordMessage = errors.getOrDefault("password", "");
                confirmPasswordMessage = errors.getOrDefault("confirmPassword", "");

                /*Binding JSP error messages tags (key), value*/
                request.setAttribute("usernameMessage", usernameMessage);
                request.setAttribute("passwordMessage", passwordMessage);
                request.setAttribute("confirmPasswordMessage", confirmPasswordMessage);

                /*Keeps the data that user insert even after error(s) and redirect to register page*/
                request.setAttribute("userRegisterDTO", userInsertDTO);

                /*Redirect to the same page (register page)*/
                request.getRequestDispatcher("/WEB-INF/jsp/user-register.jsp").forward(request, response);

                return;
            }

            user = userService.insertUser(userInsertDTO);
            UserReadOnlyDTO readOnlyDTO = mapToReadOnlyDTO(user);


            request.setAttribute("userInfo", readOnlyDTO);
            request.getRequestDispatcher("/WEB-INF/jsp/user-registered.jsp").forward(request, response);

        } catch (UserDAOException e) {
            errorMessage = e.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/jsp/user-register.jsp").forward(request, response);

        }

    }

    private UserReadOnlyDTO mapToReadOnlyDTO(User user) {
        return new UserReadOnlyDTO(user.getId(), user.getUsername(), user.getPassword());
    }
}
