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

@WebServlet("/teachers/update")
public class TeacherUpdateController extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 8206647574418031730L;

    ITeacherDAO teacherDAO = new TeacherDAOImpl();
    ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer id = Integer.parseInt(request.getParameter("id").trim());
        String firstname = request.getParameter("firstname").trim();
        String lastname = request.getParameter("lastname").trim();

        TeacherUpdateDTO updateDTO = new TeacherUpdateDTO(id, firstname, lastname);
        request.setAttribute("updateDTO", updateDTO);

        request.getRequestDispatcher("/WEB-INF/jsp/teacher-update.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        TeacherUpdateDTO teacherUpdateDTO = new TeacherUpdateDTO();
//        teacherUpdateDTO.setFirstname(request.getParameter("firstname") == null ? "" : request.getParameter("firstname"));
//        teacherUpdateDTO.setLastname(request.getParameter("lastname") == null ? "" : request.getParameter("lastname"));

        /*Data Binding*/
        Integer id = Integer.parseInt(request.getParameter("id").trim());
        String firstname = request.getParameter("firstname").trim();
        firstname = firstname == null ? "" : firstname;
        String lastname = request.getParameter("lastname").trim();
        lastname = lastname == null ? "" : lastname;

        /*Creates an object for the subsequent conditional check*/
        Map<String, String> errors;
        String errorMessage = "";

        String firstnameMessage = "";
        String lastnameMessage = "";

        /*DTO instance*/
        TeacherUpdateDTO teacherUpdateDTO = new TeacherUpdateDTO(id, firstname, lastname);

        Teacher teacher;
        try {
            /*Connect the object with the validator method that is used in conditional*/
            errors = TeacherValidator.validate(teacherUpdateDTO);

            /*Conditional checks if there are errors*/
            if (!errors.isEmpty()) {
                /*Binding to validator keys*/
                firstnameMessage = errors.getOrDefault("firstname", "");
                lastnameMessage = errors.getOrDefault("lastname", "");

                /*Binding JSP error messages tags (key), value*/
                request.setAttribute("firstnameMessage", firstnameMessage);
                request.setAttribute("lastnameMessage", lastnameMessage);

                /*Keeps the data that user insert even after error(s) and redirect to register page*/
                request.setAttribute("updateDTO", teacherUpdateDTO);

                /*Redirect to the same page (register page)*/
                request.getRequestDispatcher("/WEB-INF/jsp/teacher-update.jsp").forward(request, response);
            }

            teacher = teacherService.updateTeacher(teacherUpdateDTO);
            TeachersReadOnlyDTO teachersReadOnlyDTO = mapToReadOnlyDTO(teacher);
            request.setAttribute("teacherInfo", teachersReadOnlyDTO);
            request.getRequestDispatcher("/WEB-INF/jsp/teacher-updated.jsp").forward(request, response);

        } catch (TeacherDAOException | TeacherNotFoundException e) {
            errorMessage = e.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("WEB-INF/jsp/teacher-update.jsp").forward(request, response);
        }


    }

    private TeachersReadOnlyDTO mapToReadOnlyDTO(Teacher teacher) {
        TeachersReadOnlyDTO dto = new TeachersReadOnlyDTO(teacher.getId(), teacher.getFirstname(), teacher.getLastname());
        return dto;
    }
}
