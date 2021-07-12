package com.hugs4bugs.service.interfaces;

import com.hugs4bugs.core.entity.Master;
import com.hugs4bugs.core.entity.TimeTableBell;
import com.hugs4bugs.core.request.TimeTableBellRequest;

import java.util.List;

public interface TimeTableBellsService {

    List<TimeTableBell> getTimeTableBells(int firstEntity, int limit);
    TimeTableBell sendTimeTableBell(TimeTableBellRequest timeTableBellRequest, Master master);
    TimeTableBell getTimeTableBell(int id);
    void deleteTimeTableBell(int id);
    long countTimeTableBells();
}
