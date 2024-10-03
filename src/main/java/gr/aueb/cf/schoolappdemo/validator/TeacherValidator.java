package gr.aueb.cf.schoolappdemo.validator;

import gr.aueb.cf.schoolappdemo.dao.ITeacherDAO;
import gr.aueb.cf.schoolappdemo.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolappdemo.dto.BaseTeacherDTO;
import gr.aueb.cf.schoolappdemo.model.Teacher;
import gr.aueb.cf.schoolappdemo.service.ITeacherService;
import gr.aueb.cf.schoolappdemo.service.TeacherServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class TeacherValidator<T> {

    ITeacherDAO teacherDAO;
    ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

    private TeacherValidator() {
    }



    public static <T extends BaseTeacherDTO> Map<String, String> validate(T dto)
            throws TeacherDAOException {

        Map<String, String> errors = new HashMap<>();

        if (dto.getFirstname().length() < 3 || dto.getFirstname().length() > 45) {
            errors.put("firstname", "firstname must be between 3 and 45 characters");
        }

        if (dto.getFirstname().matches("^.*\\s+.*$")) {
            errors.put("firstname", "firstname should not be blank or include whitespaces");
        }

        if (dto.getLastname().length() < 3 || dto.getLastname().length() > 45) {
            errors.put("lastname", "lastname must be between 3 and 45 characters");
        }

        if (dto.getLastname().matches("^.*\\s+.*$")) {
            errors.put("lastname", "lastname should not be blank or include whitespaces");
        }

        return errors;

    }

}
