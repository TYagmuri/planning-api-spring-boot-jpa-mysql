package com.hugs4bugs.service.classes;

import com.hugs4bugs.core.entity.Master;
import com.hugs4bugs.core.entity.TimeTableBell;
import com.hugs4bugs.core.request.TimeTableBellRequest;
import com.hugs4bugs.repository.*;
import com.hugs4bugs.service.interfaces.TimeTableBellsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeTableBellsServiceImpl implements TimeTableBellsService {

    private final TimeTableBellRepository timeTableBellRepository;
    private final DayRepository dayRepository;
    private final BellRepository bellRepository;
    private final UserRepository userRepository;

    @Autowired
    public TimeTableBellsServiceImpl(TimeTableBellRepository timeTableBellRepository, BellRepository bellRepository,
                                     DayRepository dayRepository, UserRepository userRepository) {
        this.timeTableBellRepository = timeTableBellRepository;
        this.dayRepository = dayRepository;
        this.bellRepository = bellRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<TimeTableBell> getTimeTableBells(int fistEntity, int limit) {
        return timeTableBellRepository.findAll();
    }

    @Override
    public TimeTableBell sendTimeTableBell(TimeTableBellRequest timeTableBellRequest, Master master) {
        master.addTimeTableBell(createTimeTableBell(timeTableBellRequest));
        userRepository.save(master);
        return createTimeTableBell(timeTableBellRequest);
    }

    @Override
    public TimeTableBell getTimeTableBell(int id) {
        return timeTableBellRepository.getById(id);
    }

    @Override
    public void deleteTimeTableBell(int id) {
        timeTableBellRepository.deleteById(id);
    }

    @Override
    public long countTimeTableBells() {
        return timeTableBellRepository.count();
    }

    private TimeTableBell createTimeTableBell(TimeTableBellRequest request){
        return new TimeTableBell(dayRepository.getById(request.getDayId()), bellRepository.getById(request.getBellId()));
    }
}
