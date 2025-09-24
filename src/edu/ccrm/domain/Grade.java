package edu.ccrm.domain;

//   enumer for grade levels
public enum Grade {
    // gpa points for each grade
    S(10), 
    A(9), 
    B(8), 
    C(7), 
    D(6), 
    E(5), 
    F(0); 

    // gpa points for each grad;

    // field to hold points
    private final int points;

    Grade(int p) {
        this.points = p;
    }

    // getter for points
    public int getPoints() {
        return points;
    }
}
