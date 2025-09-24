package edu.ccrm.service;

import edu.ccrm.domain.Course;
import java.util.*;
import java.util.stream.Collectors;

public class CourseService {
    private final Map<String, Course> courses = new HashMap<>();

    public Course addCourse(Course c){
        if (courses.containsKey(c.getCode()))
            throw new IllegalArgumentException("Course exists: " + c.getCode());
        courses.put(c.getCode(), c);
        return c;
    }

    public Optional<Course> getCourse(String code){ return Optional.ofNullable(courses.get(code)); }
    public Collection<Course> listCourses(){ return courses.values(); }

    public List<Course> findByInstructor(String instructor){
        return courses.values().stream()
                .filter(c -> c.getInstructor().equalsIgnoreCase(instructor))
                .collect(Collectors.toList());
    }
}
