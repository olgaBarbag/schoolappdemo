package gr.aueb.cf.schoolappdemo.service;

import gr.aueb.cf.schoolappdemo.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolappdemo.dto.FiltersDTO;
import gr.aueb.cf.schoolappdemo.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolappdemo.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolappdemo.model.Teacher;
import gr.aueb.cf.schoolappdemo.service.exceptions.TeacherNotFoundException;

import java.util.List;

public interface ITeacherService {

    Teacher insertTeacher(TeacherInsertDTO insertDTO) throws TeacherDAOException;
    Teacher updateTeacher(TeacherUpdateDTO updateDTO) throws TeacherDAOException, TeacherNotFoundException;
    void deleteTeacher(Integer id) throws TeacherDAOException, TeacherNotFoundException;
    Teacher getTeacherById(Integer id) throws TeacherDAOException, TeacherNotFoundException;
    List<Teacher> getFilteredTeachers(FiltersDTO filtersDTO) throws TeacherDAOException;
}
