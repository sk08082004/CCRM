package edu.ccrm.service;

import edu.ccrm.domain.Student;
import java.util.*;

//service class to manage student obj
public class StudentService {
    // map of reg no. to student obj
    private final Map<String, Student> studReg = new HashMap<>();

    public Student addStud(String regNo, String name, String email) {
        if (studReg.containsKey(regNo))
            throw new IllegalArgumentException("Student exists: " + regNo);
        Student s = new Student(regNo, name, email);
        studReg.put(regNo, s);
        return s;
    }

    public Optional<Student> getStudent(String regNo) {
        return Optional.ofNullable(studReg.get(regNo));
    }
    public Collection<Student> listStudents() {
        return studReg.values();
    }
}
