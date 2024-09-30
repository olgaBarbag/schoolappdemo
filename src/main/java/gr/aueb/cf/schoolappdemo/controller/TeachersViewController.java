package gr.aueb.cf.schoolappdemo.controller;

import gr.aueb.cf.schoolappdemo.dao.ITeacherDAO;
import gr.aueb.cf.schoolappdemo.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolappdemo.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolappdemo.dto.FiltersDTO;
import gr.aueb.cf.schoolappdemo.model.Teacher;
import gr.aueb.cf.schoolappdemo.service.ITeacherService;
import gr.aueb.cf.schoolappdemo.service.TeacherServiceImpl;
import gr.aueb.cf.schoolappdemo.service.exceptions.TeacherNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;
import java.util.List;

@WebServlet("/teachers")
public class TeachersViewController extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 5625722001732942107L;

    ITeacherDAO teacherDAO = new TeacherDAOImpl();
    ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Teacher> teachers;
        /*Firstname search field*/
        String filterFirstname = request.getParameter("filterFirstname");
        //if input in filterFirstname == null (user does not insert input in this field) then:
        filterFirstname = filterFirstname == null ? "" : filterFirstname;

        /*Firstname search field*/
        String filterLastname = request.getParameter("filterLastname");
        //if input in filterLastname == null (user does not insert input in this field) then:
        filterLastname = filterLastname == null ? "" : filterLastname;

        FiltersDTO filtersDTO = new FiltersDTO(filterFirstname, filterLastname);

        String message = "";

        try {
            teachers = teacherService.getFilteredTeachers(filtersDTO);
            if (teachers.isEmpty()) {
                request.setAttribute("message", "Teachers not found");
                request.getRequestDispatcher("/WEB-INF/jsp/teachers.jsp").forward(request, response);
                return;
            }

            request.setAttribute("teachers", teachers);

            request.getRequestDispatcher("/WEB-INF/jsp/teachers.jsp").forward(request, response);

        } catch (TeacherDAOException e) {
            message = e.getMessage();
            request.setAttribute("message", message);
        }

    }
}
