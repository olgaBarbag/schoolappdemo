package gr.aueb.cf.schoolappdemo.service;

import gr.aueb.cf.schoolappdemo.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolappdemo.dto.UserInsertDTO;
import gr.aueb.cf.schoolappdemo.model.User;
import gr.aueb.cf.schoolappdemo.service.exceptions.UserNotFoundException;

public interface IUserService {

    User insertUser(UserInsertDTO insertDTO) throws UserDAOException;
    User getUserByUsername(String username) throws UserDAOException, UserNotFoundException;
    boolean isUserValid(String username, String password) throws UserDAOException;
    boolean isEmailExists(String username) throws UserDAOException;
}
