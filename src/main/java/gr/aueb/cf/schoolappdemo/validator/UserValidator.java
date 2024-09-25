package gr.aueb.cf.schoolappdemo.validator;

import gr.aueb.cf.schoolappdemo.dao.IUserDAO;
import gr.aueb.cf.schoolappdemo.dao.UserDAOImpl;
import gr.aueb.cf.schoolappdemo.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolappdemo.dto.BaseUserDTO;
import gr.aueb.cf.schoolappdemo.service.IUserService;
import gr.aueb.cf.schoolappdemo.service.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility Class
 * Generics both INSERT and UPDATE validation
 * @param <T>
 */
public class UserValidator<T> {

    /*Like Authentication Provider*/
    private final static IUserDAO userDAO = new UserDAOImpl();
    private final static IUserService userService = new UserServiceImpl(userDAO);

    private UserValidator() {}



    /*Validation method, takes dto credentials and returns a HashMap with errors*/
    public static <T extends BaseUserDTO> Map<String, String> validate(T dto) throws UserDAOException {


        Map<String, String> errors = new HashMap<>();

        /*1) ConfirmPassword Error*/
        if(!dto.getPassword().equals(dto.getConfirmPassword())) {

            /*.put is like .add in List or ArrayList*/
            errors.put("confirmPassword", "Passwords do not match");
        }

        /*2) Password Error*/
        if (dto.getPassword().length() < 5 || dto.getPassword().length() > 16) {
            errors.put("password", "Password should be between 5 and 16 characters");
        }

        /*3) Password Error*/
        if (dto.getPassword().matches("^.*\\s+.*$")) {
            errors.put("password", "Whitespaces are not allowed");
        }

        /*4) Username Error*/
        if (dto.getUsername().matches("^.*\\s+.*$")) {
            errors.put("username", "Username should not be blank or include whitespaces");
        }

        /*5) Username Error*/
        if (dto.getUsername().length() <3 || dto.getUsername().length() > 45) {
            errors.put("username", "Username should be between 3 and 45 characters");
        }

        /*7) Username Error*/
        if (userService.isEmailExists(dto.getUsername())) {
            errors.put("username", "Username is already in use");
        }

        return errors;
    }

}
