package gr.aueb.cf.schoolappdemo.service;

import gr.aueb.cf.schoolappdemo.dao.ITeacherDAO;
import gr.aueb.cf.schoolappdemo.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolappdemo.dto.FiltersDTO;
import gr.aueb.cf.schoolappdemo.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolappdemo.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolappdemo.model.Teacher;
import gr.aueb.cf.schoolappdemo.service.exceptions.TeacherNotFoundException;

import java.util.List;

public class TeacherServiceImpl implements ITeacherService {

    private final ITeacherDAO teacherDAO;

    public TeacherServiceImpl(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public Teacher insertTeacher(TeacherInsertDTO insertDTO) throws TeacherDAOException {

        Teacher teacher;
        try {
            teacher = mapToTeacher(insertDTO);
            return teacherDAO.insert(teacher);

        } catch (TeacherDAOException e) {
            e.printStackTrace();
            //logging
            //rollback
            throw e;
        }
    }

    @Override
    public Teacher updateTeacher(TeacherUpdateDTO updateDTO) throws TeacherDAOException, TeacherNotFoundException {
        Teacher teacher;
        try {
            teacher = mapToTeacher(updateDTO);

            if (teacherDAO.findById(teacher.getId()) == null) {
                throw new TeacherNotFoundException(teacher);
            }

            return teacherDAO.update(teacher);

        } catch (TeacherDAOException | TeacherNotFoundException e) {
            e.printStackTrace();
            //logging
            //rollback
            throw e;
        }
    }

    @Override
    public void deleteTeacher(Integer id) throws TeacherDAOException, TeacherNotFoundException {

        try {
            if (teacherDAO.findById(id) == null) {
                throw new TeacherNotFoundException("Teacher with id " + id + " not found");
            }

            teacherDAO.delete(id);

        } catch (TeacherDAOException | TeacherNotFoundException e) {
            e.printStackTrace();
            //logging
            //rollback
            throw e;
        }
    }

    @Override
    public Teacher getTeacherById(Integer id) throws TeacherDAOException, TeacherNotFoundException {

        try {

            if (teacherDAO.findById(id) == null) {
                throw new TeacherNotFoundException("Teacher with id " + id + " not found");
            }

            return teacherDAO.findById(id);

        } catch (TeacherDAOException | TeacherNotFoundException e) {
            e.printStackTrace();
            //logging
            //rollback
            throw e;
        }

    }

    @Override
    public List<Teacher> getFilteredTeachers(FiltersDTO filtersDTO) throws TeacherDAOException {
        List<Teacher> teachers;

        try {
           teachers = teacherDAO.findFilteredTeachers(filtersDTO.getFirstname(), filtersDTO.getLastname());

           return teachers;
        } catch (TeacherDAOException e) {
            e.printStackTrace();
            //logging
            //rollback
            throw e;
        }
    }

    private Teacher mapToTeacher(TeacherInsertDTO insertDTO) {
        return new Teacher(null, insertDTO.getFirstname(), insertDTO.getLastname());
    }

    private Teacher mapToTeacher(TeacherUpdateDTO updateDTO) {
        return new Teacher(updateDTO.getId(), updateDTO.getFirstname(), updateDTO.getLastname());
    }
}
