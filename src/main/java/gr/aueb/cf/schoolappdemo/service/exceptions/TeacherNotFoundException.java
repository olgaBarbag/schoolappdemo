package gr.aueb.cf.schoolappdemo.service.exceptions;

import gr.aueb.cf.schoolappdemo.model.Teacher;

import java.io.Serial;

public class TeacherNotFoundException extends Exception {

    @Serial
    private static final long serialVersionUID = -4194348143463052702L;

    public TeacherNotFoundException(String message) {
        super(message);
    }

    public TeacherNotFoundException(Teacher teacher) {
        super("Teacher with id: " + teacher.getId() + "did not found");
    }
}
