package gr.aueb.cf.schoolappdemo.authentication;

import gr.aueb.cf.schoolappdemo.dao.IUserDAO;
import gr.aueb.cf.schoolappdemo.dao.UserDAOImpl;
import gr.aueb.cf.schoolappdemo.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolappdemo.dto.UserLoginDTO;
import gr.aueb.cf.schoolappdemo.service.IUserService;
import gr.aueb.cf.schoolappdemo.service.UserServiceImpl;

public class AuthenticationProvider {
    private final static IUserDAO userDAO = new UserDAOImpl();
    private final static IUserService userService = new UserServiceImpl(userDAO);

    private AuthenticationProvider() {}

    public static boolean authenticate(UserLoginDTO userLoginDTO) throws UserDAOException {
        return userService.isUserValid(userLoginDTO.getUsername(), userLoginDTO.getPassword());
    }
}
