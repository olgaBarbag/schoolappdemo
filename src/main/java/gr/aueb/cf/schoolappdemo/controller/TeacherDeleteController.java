package gr.aueb.cf.schoolappdemo.controller;

import gr.aueb.cf.schoolappdemo.dao.ITeacherDAO;
import gr.aueb.cf.schoolappdemo.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolappdemo.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolappdemo.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolappdemo.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolappdemo.dto.TeachersReadOnlyDTO;
import gr.aueb.cf.schoolappdemo.model.Teacher;
import gr.aueb.cf.schoolappdemo.service.ITeacherService;
import gr.aueb.cf.schoolappdemo.service.TeacherServiceImpl;
import gr.aueb.cf.schoolappdemo.service.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolappdemo.validator.TeacherValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;
import java.util.Map;

@WebServlet("/teachers/delete")
public class TeacherDeleteController extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1293446937239600110L;

    ITeacherDAO teacherDAO = new TeacherDAOImpl();
    ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer id = Integer.parseInt(request.getParameter("id").trim());


        try {
            teacherService.deleteTeacher(id);
            request.setAttribute("id", id);

            request.getRequestDispatcher("/WEB-INF/jsp/teacher-deleted.jsp").forward(request, response);
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/teachers.jsp").forward(request, response);
        }



    }
}

