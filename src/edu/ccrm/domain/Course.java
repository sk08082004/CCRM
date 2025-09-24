package edu.ccrm.domain;

public class Course {
    private final String code;
    private final String title;
    private final int credits;
    private final String instructor;
    private final Semester semester;

    private Course(Builder b) {
        this.code = b.code;
        this.title = b.title;
        this.credits = b.credits;
        this.instructor = b.instructor;
        this.semester = b.semester;
    }

    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public String getInstructor() { return instructor; }
    public Semester getSemester() { return semester; }

    @Override
    public String toString() {
        return String.format("%s: %s (%d credits) [%s] - %s",
                code, title, credits, semester, instructor);
    }

    // Builder Pattern
    public static class Builder {
        private String code;
        private String title;
        private int credits;
        private String instructor;
        private Semester semester = Semester.FALL;

        public Builder code(String c){ this.code = c; return this; }
        public Builder title(String t){ this.title = t; return this; }
        public Builder credits(int cr){ this.credits = cr; return this; }
        public Builder instructor(String i){ this.instructor = i; return this; }
        public Builder semester(Semester s){ this.semester = s; return this; }
        public Course build(){ return new Course(this); }
    }
}
