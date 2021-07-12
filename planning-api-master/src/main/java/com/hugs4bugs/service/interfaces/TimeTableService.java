package com.hugs4bugs.service.interfaces;

import com.hugs4bugs.core.entity.Student;
import com.hugs4bugs.core.entity.TimeTable;

import java.util.List;

public interface TimeTableService {

    List<TimeTable> getTimeTables(Integer studentId, Integer courseId, Integer masterId, int firstEntity, int limit);
    TimeTable getTimeTable(int id);
    void chooseTimeTable(int id, Student student);
    void startProcess(int maxClassPerBell);
    long countTimeTablesOfCourse(int courseId);
    long countTimeTables(Integer studentId, Integer courseId, Integer masterId);

}
