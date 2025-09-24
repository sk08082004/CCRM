package edu.ccrm.service;

import edu.ccrm.domain.Student;
import java.util.*;

public class StudentService {
    private final Map<String, Student> students = new HashMap<>();

    public Student addStudent(String regNo, String name, String email){
        if (students.containsKey(regNo))
            throw new IllegalArgumentException("Student exists: " + regNo);
        Student s = new Student(regNo, name, email);
        students.put(regNo, s);
        return s;
    }

    public Optional<Student> getStudent(String regNo){ return Optional.ofNullable(students.get(regNo)); }
    public Collection<Student> listStudents(){ return students.values(); }
}
