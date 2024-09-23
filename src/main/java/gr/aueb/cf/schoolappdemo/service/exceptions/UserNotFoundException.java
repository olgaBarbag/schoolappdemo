package gr.aueb.cf.schoolappdemo.service.exceptions;

import gr.aueb.cf.schoolappdemo.model.User;

import java.io.Serial;

public class UserNotFoundException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(User user) {
        super("User with username: " + user.getUsername() + " did not found");
    }
}
