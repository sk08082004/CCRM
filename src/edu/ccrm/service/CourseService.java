package edu.ccrm.service;

import edu.ccrm.domain.Course;
import java.util.*;
import java.util.stream.Collectors;

// service class to manage course obj(CRUD-like operations)
public class CourseService {
    //stores courses in a map with course code as the key for quick lookup
    private final Map <String, Course> courses = new HashMap<>();

    // add new course
    // throws exception  - corse with same code exists
    public Course addCor(Course c){
        if (courses.containsKey(c.getCode()))
            throw new IllegalArgumentException("Course exists: " + c.getCode());
        courses.put(c.getCode(), c);
        return c;
    }

    // retrive course by code)
    public Optional<Course> getCourse(String code){ 
        return Optional.ofNullable(courses.get(code)); 
    }

    public Collection<Course> listCourses(){ 
        return courses.values(); 
    }

    //it finds all courses taught by a teacher
    // case insensitive match
    public List<Course> findByProf(String prof){
        return courses.values().stream()
                .filter(c -> c.getProf().equalsIgnoreCase(prof))
                .collect(Collectors.toList());
    }
}
