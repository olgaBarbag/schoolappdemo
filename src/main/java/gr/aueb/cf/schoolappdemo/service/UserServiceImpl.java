package gr.aueb.cf.schoolappdemo.service;

import gr.aueb.cf.schoolappdemo.core.RoleType;
import gr.aueb.cf.schoolappdemo.dao.IUserDAO;
import gr.aueb.cf.schoolappdemo.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolappdemo.dto.UserInsertDTO;
import gr.aueb.cf.schoolappdemo.model.User;
import gr.aueb.cf.schoolappdemo.service.exceptions.UserNotFoundException;

public class UserServiceImpl implements IUserService {

    private final IUserDAO userDAO;

    public UserServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User insertUser(UserInsertDTO insertDTO) throws UserDAOException {

        User user;
        try {
            user = mapToUser(insertDTO);
            return userDAO.insert(user);

        } catch (UserDAOException e) {
            e.printStackTrace();
            //logging
            //rollback
            throw e;
        }
    }

    @Override
    public User getUserByUsername(String username) throws UserDAOException, UserNotFoundException {
        User user;

        try {
            user = userDAO.findByUsername(username);

            if (user == null) {
                throw new UserNotFoundException(username);
            }

            return user;
        } catch (UserDAOException | UserNotFoundException e) {
            e.printStackTrace();
            //logging
            //rollback
            throw e;
        }
    }

    @Override
    public boolean isUserValid(String username, String password) throws UserDAOException {

        try {
            //logging
            return userDAO.isUserValid(username, password);
        } catch (UserDAOException e) {
            e.printStackTrace();
            //logging
            throw e;
        }
    }

    @Override
    public boolean isEmailExists(String username) throws UserDAOException {
        try {
            //logging
            return userDAO.isEmailExists(username);
        } catch (UserDAOException e) {
            e.printStackTrace();
            //logging
            throw e;
        }
    }

    /*User: RoleType
    * dto: String
    * Convert String to RoleType using a static factory method (provided by ENUM class) ".valueOf()"*/
    private User mapToUser(UserInsertDTO insertDTO) {
        return new User(null, insertDTO.getUsername(), insertDTO.getPassword(), RoleType.valueOf(insertDTO.getRole()));
    }
}
