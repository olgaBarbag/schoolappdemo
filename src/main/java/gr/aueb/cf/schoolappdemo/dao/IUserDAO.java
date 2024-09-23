package gr.aueb.cf.schoolappdemo.dao;

import gr.aueb.cf.schoolappdemo.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolappdemo.model.User;

import java.util.List;

public interface IUserDAO {

    User insert(User user) throws UserDAOException;
    User reset(User user) throws UserDAOException;
    void delete(Integer id) throws UserDAOException;
    List<User> findAll() throws UserDAOException;
    User findByUsername(String username) throws UserDAOException;
    boolean isEmailExists(String username) throws UserDAOException;
    boolean isUserValid(String username, String password) throws UserDAOException;
}
