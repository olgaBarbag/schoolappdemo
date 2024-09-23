package gr.aueb.cf.schoolappdemo.dao.exceptions;

import gr.aueb.cf.schoolappdemo.model.Teacher;

import java.io.Serial;

public class TeacherDAOException extends Exception {

    @Serial
    private static final long serialVersionUID = -708970017870822590L;

    public TeacherDAOException(String message) {
        super(message);
    }

}
