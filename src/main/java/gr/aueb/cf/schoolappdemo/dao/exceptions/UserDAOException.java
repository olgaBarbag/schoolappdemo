package gr.aueb.cf.schoolappdemo.dao.exceptions;

import java.io.Serial;

public class UserDAOException extends Exception {

    @Serial
    private static final long serialVersionUID = -7790122111233047931L;

    public UserDAOException(String message) {
        super(message);
    }
}
