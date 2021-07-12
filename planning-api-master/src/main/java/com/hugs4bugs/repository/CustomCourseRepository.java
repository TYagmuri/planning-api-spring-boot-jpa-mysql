package com.hugs4bugs.repository;

import com.hugs4bugs.core.entity.Course;

import java.util.List;

public interface CustomCourseRepository {

    List<Course> getCourses(String search, Integer unitCounts, int firstElement, int limit);
    long countCourses(String search, Integer unitCounts);

}
