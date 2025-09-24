package edu.ccrm.service;

import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Course;
import java.util.*;

public class EnrollmentService {
    private final Map<String, List<Enrollment>> byStudent = new HashMap<>();

    public Enrollment enroll(String regNo, String courseCode){
        Enrollment e = new Enrollment(regNo, courseCode);
        byStudent.computeIfAbsent(regNo, k -> new ArrayList<>()).add(e);
        return e;
    }

    public List<Enrollment> listEnrollments(String regNo){
        return byStudent.getOrDefault(regNo, Collections.emptyList());
    }

    public void recordMarks(String regNo, String courseCode, int marks){
        List<Enrollment> lst = byStudent.getOrDefault(regNo, Collections.emptyList());
        for (Enrollment e : lst){
            if (e.getCourseCode().equalsIgnoreCase(courseCode)){
                e.setMarks(marks);
                return;
            }
        }
        throw new NoSuchElementException("Enrollment not found for " + regNo + " -> " + courseCode);
    }

    public double computeGPA(String regNo, CourseService courseService){
        List<Enrollment> lst = listEnrollments(regNo);
        int totalCredits = 0;
        int totalPoints = 0;
        for (Enrollment e : lst){
            Optional<Course> oc = courseService.getCourse(e.getCourseCode());
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
