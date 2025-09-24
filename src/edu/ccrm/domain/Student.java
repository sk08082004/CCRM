package edu.ccrm.domain;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private final String regNo;
    private final List<Enrollment> enrollments = new ArrayList<>();

    public Student(String regNo, String fullName, String email) {
        super(regNo, fullName, email);
        this.regNo = regNo;
    }

    public String getRegNo() { return regNo; }

    public void addEnrollment(Enrollment e) { enrollments.add(e); }
    public List<Enrollment> getEnrollments() { return enrollments; }

    @Override
    public String toString() {
        return String.format("Student %s - %s", regNo, super.toString());
    }
}
