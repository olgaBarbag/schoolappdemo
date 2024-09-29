package gr.aueb.cf.schoolappdemo.dao;

import gr.aueb.cf.schoolappdemo.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolappdemo.model.Teacher;
import gr.aueb.cf.schoolappdemo.service.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements ITeacherDAO {
    @Override
    public Teacher insert(Teacher teacher) throws TeacherDAOException {

        String insertSql = "INSERT INTO teachers (firstname, lastname) VALUES (?,?)";

        try (Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(insertSql)) {
            ps.setString(1, teacher.getFirstname());
            ps.setString(2, teacher.getLastname());
            ps.executeUpdate();

            //logging
            return teacher;

        } catch (SQLException e) {
            e.printStackTrace();
            //logging
            throw new TeacherDAOException("Insert teacher failed: SQL error.");
        }
    }

    @Override
    public Teacher update(Teacher teacher) throws TeacherDAOException {
        String updateSql = "UPDATE teachers SET firstname = ?, lastname = ? WHERE id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(updateSql)) {
            ps.setString(1, teacher.getFirstname());
            ps.setString(2, teacher.getLastname());
            ps.setInt(3, teacher.getId());
            ps.executeUpdate();

            //logging
            return teacher;

        } catch (SQLException e) {
            e.printStackTrace();
            //logging
            throw new TeacherDAOException("Update teacher failed: SQL error.");
        }
    }

    @Override
    public void delete(Integer id) throws TeacherDAOException {

        String deleteSql = "DELETE FROM teachers WHERE id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(deleteSql)) {
            ps.setInt(1, id);
            ps.executeUpdate();

            //logging


        } catch (SQLException e) {
            e.printStackTrace();
            //logging
            throw new TeacherDAOException("Delete teacher failed: SQL error.");
        }

    }

    @Override
    public Teacher findById(Integer id) throws TeacherDAOException {
        String findByIdSql = "SELECT * FROM teachers WHERE id = ?";
        Teacher teacher = null;
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(findByIdSql)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                teacher = new Teacher(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"));
            }

            //logging

            return teacher;
        } catch (SQLException e) {
            e.printStackTrace();
            //logging
            throw new TeacherDAOException("Find teacher with id: \" + id + \" failed: SQL error.");
        }
    }

    @Override
    public List<Teacher> findFilteredTeachers(String firstname, String lastname) throws TeacherDAOException {
        String findFilteredTeachersSql = "SELECT * FROM teachers WHERE firstname = ? AND lastname = ?";
        List<Teacher> teachers = new ArrayList<Teacher>(); //isEmpty == true
        ResultSet rs;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(findFilteredTeachersSql);
        ){
            ps.setString(1, "%" + firstname + "%");
            ps.setString(2, "%" + lastname + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                teachers.add(new Teacher(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname")));
            }
            //logging
            teachers.forEach(System.out::println);
            return teachers;

        } catch (SQLException e) {
            e.printStackTrace();
            //logging
            throw new TeacherDAOException("Find any teacher with firstname: " + firstname + "and/or lastname: " + lastname + " failed: SQL error.");
        }
    }
}
