package edu.ccrm.domain;

import java.util.ArrayList;
import java.util.List;

// represents student in the system (extends Person)
public class Student extends Person {
    // Unique regnumber of the student
    private final String regNo;

    // list of enrollments 
    private final List<Enrollment> enrollments = new ArrayList<>();

    // constructor - set reg no, name, email
    public Student(String regNo, String fullName, String email) {
        super(regNo, fullName, email); // call person const
        this.regNo = regNo;
    }

    // Getter for reg number
    public String getRegNo() { return regNo; }

    // add enrollment record 
    public void addEnrollment(Enrollment e) { enrollments.add(e); }
    public List<Enrollment> getEnrollments() { return enrollments; }
    @Override
    public String toString() {
        return String.format("Student %s - %s", regNo, super.toString());
    }
}
