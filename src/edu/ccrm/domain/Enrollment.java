package edu.ccrm.domain;

// represent enrollmentv
public class Enrollment {
    // student reg no.
    private final String studReg;

    //course code 
    private final String courseCod;

    // scored marks (default -1 until recorded)
    private int marks = -1;

    // assigned grade (default F )
    private Grade grade = Grade.F;

    // Constructor: creates an enrollment with student reg no. and course code
    public Enrollment(String studReg, String courseCod) {
        this.studReg = studReg;
        this.courseCod = courseCod;
    }

    // getter - student reg, course code
    public String getStudReg() {
        return studReg;
    }

    public String getCourseCod() {
        return courseCod;
    }

    // auto set marks and grade
    public void setMarks(int m) {
        this.marks = m;
        this.grade = computeGradeFromMarks(m); 
    }

    // getter - marks, grade
    public int getMarks() {
        return marks;
    }

    public Grade getGrade() {
        return grade;
    }

    // helper method compute grade
    private Grade computeGradeFromMarks(int m) {
        if (m >= 80)
            return Grade.S; 
        if (m >= 70)
            return Grade.A;
        if (m >= 60)
            return Grade.B;
        if (m >= 50)
            return Grade.C;
        if (m >= 40)
            return Grade.D;
        if (m >= 30)
            return Grade.E;
        return Grade.F; 
    }

    @Override
    public String toString() {
        return String.format("%s -> %s : marks=%d grade=%s",
                studReg, courseCod, marks, grade);
    }
}
