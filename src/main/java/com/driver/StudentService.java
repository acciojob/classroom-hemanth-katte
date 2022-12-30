package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public void createStudent(Student student){
        studentRepository.saveStudent(student);
    }

    public void createTeacher(Teacher teacher){
        studentRepository.saveTeacher(teacher);
    }

    public void createStudentTeacherPair(String studentName, String teacherName){
        studentRepository.saveTeacherStudentPair(studentName,teacherName);
    }

    public Student askStudentDetails(String studentName){
        return studentRepository.sendStudentDetails(studentName);
    }

    public Teacher askTeacherDetails(String teacherName){
        return studentRepository.sendTeacherDetails(teacherName);
    }

    public List<String> askStudentListOfTeacher(String teacherName){
        return studentRepository.sendStudentListOfTeacher(teacherName);
    }

    public List<String> askALlStudents(){
        return studentRepository.sendAllStudents();
    }

    public void askDeleteTeacher(String teacherName){
        studentRepository.deleteTeacher(teacherName);
    }

    public void askDeleteAllTeachers(){
        studentRepository.deleteALlTeachersAndAssociatedStudents();
    }

}
