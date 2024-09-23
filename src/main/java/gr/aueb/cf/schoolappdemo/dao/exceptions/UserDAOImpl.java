package gr.aueb.cf.schoolappdemo.dao.exceptions;

import gr.aueb.cf.schoolappdemo.dao.IUserDAO;
import gr.aueb.cf.schoolappdemo.model.User;
import gr.aueb.cf.schoolappdemo.security.SecUtil;
import gr.aueb.cf.schoolappdemo.service.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IUserDAO {
    @Override
    public User insert(User user) throws UserDAOException {
        String insertSql = "INSERT INTO users (username, password) values(?,?)";

        try (Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(insertSql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, SecUtil.encryptPassword(user.getPassword()));
            ps.executeUpdate();

            //logging

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            //logging
            throw new UserDAOException("Insert user failed. SQL error");
        }
    }

    @Override
    public User reset(User user) throws UserDAOException {
        String updateSql = "UPDATE users SET password=? WHERE id=?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(updateSql)) {

            ps.setString(1, SecUtil.encryptPassword(user.getPassword()));
            ps.setInt(2, user.getId());
            ps.executeUpdate();

            //logging

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            //logging
            throw new UserDAOException("Update user failed. SQL error");
        }
    }

    @Override
    public void delete(Integer id) throws UserDAOException {
        String deleteSql = "DELETE FROM users WHERE id=?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(deleteSql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

            //logging

        } catch (SQLException e) {
            e.printStackTrace();
            //logging
            throw new UserDAOException("Update user failed. SQL error");
        }

    }

    @Override
    public List<User> findAll() throws UserDAOException {
        String findAllSql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        ResultSet rs;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(findAllSql);) {

            rs = ps.executeQuery();

            while (rs.next()) {
                users.add(new User(rs.getInt("id"), rs.getString("username"), SecUtil.encryptPassword(rs.getString("password"))));
            }

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException("Find any user failed. SQL error");
        }
    }

    @Override
    public User findByUsername(String username) throws UserDAOException {
        String findByUsernameSql = "SELECT * FROM users WHERE username=?";
        User user = null;
        ResultSet rs;

        try (Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(findByUsernameSql)){

            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            //logging
            throw new UserDAOException("Find user with username: " + username + " failed. SQL error");
        }
    }

    @Override
    public boolean isEmailExists(String username) throws UserDAOException {
        String sql = "SELECT COUNT(*) FROM users WHERE username=?";
        ResultSet rs;
        int count = 0;

        try (Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            //logging
            throw new UserDAOException("Email existence check failed. SQL error");
        }
    }

    @Override
    public boolean isUserValid(String username, String password) throws UserDAOException {
        String sql = "SELECT * FROM users WHERE username=?";
        ResultSet rs;
        User user = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            } else {
                return false;
            }

            return SecUtil.checkPassword(password, user.getPassword());

        } catch (SQLException e) {
            e.printStackTrace();
            //logging
            throw new UserDAOException("Email existence check failed. SQL error");
        }
    }
}
