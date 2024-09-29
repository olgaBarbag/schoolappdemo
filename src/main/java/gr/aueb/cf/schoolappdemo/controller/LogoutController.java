package gr.aueb.cf.schoolappdemo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Serial;

public class LogoutController extends HttpServlet {
    @Serial
    private static final long serialVersionUID = -3427227805581059481L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);  //1)
        if (session != null) {                               //2)
            session.invalidate();
        }

        response.sendRedirect("login.jsp");               //3)
    }
}
