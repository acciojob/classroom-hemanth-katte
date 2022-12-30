package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {

    private HashMap<String,Student> studentDB = new HashMap<>();

    private HashMap<String,Teacher> teacherDb = new HashMap<>();

    private HashMap<String,List<String>> teacherStudentMapping = new HashMap<>();

    public void saveStudent(Student student){
        studentDB.put(student.getName(),student);
    }

    public void saveTeacher(Teacher teacher){
        teacherDb.put(teacher.getName(),teacher);
    }

    public void saveTeacherStudentPair(String studentName, String teacherName){

        if(studentDB.containsKey(studentName) && teacherDb.containsKey(teacherName)){

            if(teacherStudentMapping.containsKey(teacherName))
                teacherStudentMapping.get(teacherName).add(studentName);

            else{
                List<String> listOfStudentsOfTeacher = new ArrayList<>();
                listOfStudentsOfTeacher.add(studentName);
                teacherStudentMapping.put(teacherName,listOfStudentsOfTeacher);
            }
        }

    }

    public Student sendStudentDetails(String studentName){
        return studentDB.get(studentName);
    }

    public Teacher sendTeacherDetails(String teacherName){
        return teacherDb.get(teacherName);
    }

    public List<String> sendStudentListOfTeacher(String teacherName){
        return teacherStudentMapping.get(teacherName);
    }

    public List<String> sendAllStudents(){

        List<String> listOfStudents = new ArrayList<>();

        for(String s : studentDB.keySet())
            listOfStudents.add(s);

        return listOfStudents;

    }

    public void deleteTeacher(String teacherName){

        if(teacherStudentMapping.containsKey(teacherName)) {
            //collect all students of a teacher
            List<String> students = new ArrayList<>();
            if (teacherStudentMapping.containsKey(teacherName))
                students = teacherStudentMapping.get(teacherName);

            //delete all students from the students list in studentDB
            for (String name : students) {
                if (studentDB.containsKey(name))
                    studentDB.remove(name);
            }

            teacherStudentMapping.remove(teacherName);

        }
        if (teacherDb.containsKey(teacherName))
            teacherDb.remove(teacherName);
    }

    public void deleteALlTeachersAndAssociatedStudents(){

        for(String teacherName : teacherStudentMapping.keySet()){

            List<String> students = new ArrayList<>();
            students = teacherStudentMapping.get(teacherName);

            for(String studentName : students){
                if(studentDB.containsKey(studentName))
                    studentDB.remove(studentName);
            }
        }

        teacherDb.clear();
        teacherStudentMapping.clear();

    }
}
