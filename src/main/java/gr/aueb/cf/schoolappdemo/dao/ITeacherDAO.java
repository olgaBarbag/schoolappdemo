package gr.aueb.cf.schoolappdemo.dao;

import gr.aueb.cf.schoolappdemo.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolappdemo.model.Teacher;

import java.util.List;

public interface ITeacherDAO {

    Teacher insert(Teacher teacher) throws TeacherDAOException;
    Teacher update(Teacher teacher) throws TeacherDAOException;
    void delete(Integer id) throws TeacherDAOException;
    Teacher findById(Integer id) throws TeacherDAOException;
    List<Teacher> findFilteredTeachers(String firstname, String lastname)
            throws TeacherDAOException;


}
