package edu.ccrm.domain;

// represent course
public class Course {
    // final fiels can't be change afterwards
    private final String code;       
    private final String title;      
    private final int credits;       
    private final String prof; 
    private final Semester sem; 

    // pvt constructor , only accessible via builder
    private Course(Builder b) {
        this.code = b.code;
        this.title = b.title;
        this.credits = b.credits;
        this.prof = b.prof;
        this.sem = b.sem;
    }

    // getters {kept no setters because fields are final → immutable obj}
    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public String getProf() { return prof; }
    public Semester getSem() { return sem; }

    //toString  for readable course representation
    @Override
    public String toString() {
        return String.format("%s: %s (%d credits) [%s] - %s",
                code, title, credits, sem, prof);
    }

    // builder class kept for flexible course obj creation
    public static class Builder {
        // builder fields
        private String code;
        private String title;
        private int credits;
        private String prof;
        private Semester sem = Semester.FALL; 

        //methods to set individual fields
        public Builder code(String c){ this.code = c; return this; }
        public Builder title(String t){ this.title = t; return this; }
        public Builder credits(int cr){ this.credits = cr; return this; }
        public Builder prof(String p){ this.prof = p; return this; }
        public Builder sem(Semester s){ this.sem = s; return this; }

        // this is final build method which creates an immutable course obj
        public Course build(){ return new Course(this); }
    }
}
