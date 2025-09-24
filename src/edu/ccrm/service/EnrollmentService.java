package edu.ccrm.service;

import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Course;
import java.util.*;

// service class to manage enrollments
public class EnrollmentService {
    // map of student reg no. to their enrollments
    private final Map<String, List<Enrollment>> byStudent = new HashMap<>();

    // enrolls a student in a course
    // creates and returns the enrollment record
    public Enrollment enrolStud(String regNo, String courseCod){
        Enrollment e = new Enrollment(regNo, courseCod);
        byStudent.computeIfAbsent(regNo, k -> new ArrayList<Enrollment>()).add(e);
        return e;
    }

    // returns all enrollments of a student by reg no.
    public List<Enrollment> listEnrollments(String regNo){
         return byStudent.getOrDefault(regNo, Collections.emptyList());
    }

    // records marks
    public void markRec(String regNo, String courseCod, int marks){
        List<Enrollment> lst = byStudent.getOrDefault(regNo, Collections.emptyList());
        for (Enrollment e : lst){
            if (e.getCourseCod().equalsIgnoreCase(courseCod)){
                e.setMarks(marks);
                return;
            }
        }
        throw new NoSuchElementException("Enrollment not found for " + regNo + " -> " + courseCod);
    }

    public double computeGPA(String regNo, CourseService courseService){
        List<Enrollment> lst = listEnrollments(regNo);
        int totalCredits = 0;   
        int totalPoints = 0;    

        for (Enrollment e : lst){
            Optional<Course> oc = courseService.getCourse(e.getCourseCod());
            if (oc.isPresent()){
                int credits = oc.get().getCredits();
                totalCredits += credits;
                totalPoints += e.getGrade().getPoints() * credits;
            }
        }
        if (totalCredits == 0) return 0.0;
        return (double) totalPoints / totalCredits;
    }
}
