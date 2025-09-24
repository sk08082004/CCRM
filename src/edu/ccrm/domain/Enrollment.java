package edu.ccrm.domain;

public class Enrollment {
    private final String studentReg;
    private final String courseCode;
    private int marks = -1; // -1 = not graded
    private Grade grade = Grade.F;

    public Enrollment(String studentReg, String courseCode){
        this.studentReg = studentReg;
        this.courseCode = courseCode;
    }

    public String getStudentReg() { return studentReg; }
    public String getCourseCode() { return courseCode; }

    public void setMarks(int m){
        this.marks = m;
        this.grade = computeGradeFromMarks(m);
    }

    public int getMarks() { return marks; }
    public Grade getGrade() { return grade; }

    private Grade computeGradeFromMarks(int m){
        if (m >= 80) return Grade.S;
        if (m >= 70) return Grade.A;
        if (m >= 60) return Grade.B;
        if (m >= 50) return Grade.C;
        if (m >= 40) return Grade.D;
        if (m >= 30) return Grade.E;
        return Grade.F;
    }

    @Override
    public String toString(){
        return String.format("%s -> %s : marks=%d grade=%s", studentReg, courseCode, marks, grade);
    }
}
