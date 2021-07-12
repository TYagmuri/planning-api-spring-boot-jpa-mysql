package com.hugs4bugs.service.classes;

import com.hugs4bugs.core.entity.Course;
import com.hugs4bugs.core.entity.Master;
import com.hugs4bugs.core.entity.TimeTable;
import com.hugs4bugs.core.request.CourseRequest;
import com.hugs4bugs.repository.CourseRepository;
import com.hugs4bugs.repository.MasterRepository;
import com.hugs4bugs.repository.TimeTableRepository;
import com.hugs4bugs.service.interfaces.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursesServiceImpl implements CoursesService {

    private final CourseRepository courseRepository;
    private final MasterRepository masterRepository;
    private final TimeTableRepository timeTableRepository;

    @Autowired
    public CoursesServiceImpl(CourseRepository courseRepository, MasterRepository masterRepository,
                              TimeTableRepository timeTableRepository) {
        this.courseRepository = courseRepository;
        this.masterRepository = masterRepository;
        this.timeTableRepository = timeTableRepository;
    }

    @Override
    public List<Course> getCourses(String search, Integer unitCount, int fistEntity, int limit) {
        return courseRepository.getCourses(search, unitCount, fistEntity, limit);
    }

    @Override
    public Course saveCourse(CourseRequest courseRequest) {
        return courseRepository.save(createCourse(courseRequest));
    }

    @Override
    public Course getCourse(int id) {
        return courseRepository.getById(id);
    }

    @Override
    public void updateCourse(int id, CourseRequest courseRequest) {
        Course newCourse = getCourse(id);
        newCourse.setTitle(courseRequest.getTitle());
        newCourse.setUnitsCount(courseRequest.getUnitCount());
        courseRepository.save(newCourse);
    }

    @Override
    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<TimeTable> getTimeTableOfCourse(int courseId, int fistEntity, int limit) {
        return timeTableRepository.getTimeTableOfCourse(courseId, fistEntity, limit);
    }

    @Override
    public List<Master> getMastersOfCourse(int courseId, int firstElement, int limit) {
        return masterRepository.getMastersOfCourse(courseId, firstElement, limit);
    }

    @Override
    public void chooseCourse(int courseId, Master master) {
        master.addCourse(getCourse(courseId));
        masterRepository.save(master);
    }

    @Override
    public Integer count() {
        return (int) courseRepository.count();
    }

    private Course createCourse(CourseRequest courseRequest) {
        return new Course(courseRequest.getTitle(), courseRequest.getUnitCount());
    }
}
