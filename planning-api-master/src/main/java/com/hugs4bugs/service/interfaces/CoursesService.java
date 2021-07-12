package com.hugs4bugs.service.interfaces;

import com.hugs4bugs.core.entity.Course;
import com.hugs4bugs.core.entity.Master;
import com.hugs4bugs.core.entity.TimeTable;
import com.hugs4bugs.core.request.CourseRequest;

import java.util.List;

public interface CoursesService {

    List<Course> getCourses(String search, Integer unitCount, int firstEntity, int limit);

    Course saveCourse(CourseRequest courseRequest);

    Course getCourse(int id);

    void updateCourse(int id, CourseRequest courseRequest);

    void deleteCourse(int id);

    List<TimeTable> getTimeTableOfCourse(int courseId, int fistEntity, int limit);

    List<Master> getMastersOfCourse(int courseId, int firstElement, int limit);

    void chooseCourse(int courseId, Master master);

    Integer count();
}
