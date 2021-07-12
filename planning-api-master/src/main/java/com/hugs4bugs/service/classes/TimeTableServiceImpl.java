package com.hugs4bugs.service.classes;

import com.hugs4bugs.core.algorithm.ProcessData;
import com.hugs4bugs.core.entity.Student;
import com.hugs4bugs.core.entity.TimeTable;
import com.hugs4bugs.repository.TimeTableRepository;
import com.hugs4bugs.repository.UserRepository;
import com.hugs4bugs.service.interfaces.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeTableServiceImpl implements TimeTableService {

    private final TimeTableRepository timeTableRepository;
    private final UserRepository userRepository;

    @Autowired
    public TimeTableServiceImpl(TimeTableRepository timeTableRepository, UserRepository userRepository) {
        this.timeTableRepository = timeTableRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<TimeTable> getTimeTables(Integer studentId, Integer courseId, Integer masterId, int fistEntity, int limit) {
        //TODO Process data
//        ProcessData processData = new ProcessData();
        return timeTableRepository.getTimeTables(studentId, courseId, masterId, fistEntity, limit);
    }

    @Override
    public TimeTable getTimeTable(int id) {
        return timeTableRepository.getById(id);

    }

    @Override
    public void chooseTimeTable(int id, Student student) {
        student.addTimeTable(getTimeTable(id));
        userRepository.save(student);
    }

    @Override
    public void startProcess(int maxClassPerBell) {

    }

    @Override
    public long countTimeTablesOfCourse(int courseId) {
        return timeTableRepository.countTimeTablesOfCourse(courseId);
    }

    @Override
    public long countTimeTables(Integer studentId, Integer courseId, Integer masterId) {
        return timeTableRepository.countTimeTable(studentId, courseId, masterId);
    }
}
