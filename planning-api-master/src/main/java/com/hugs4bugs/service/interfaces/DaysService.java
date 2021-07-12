package com.hugs4bugs.service.interfaces;

import com.hugs4bugs.core.entity.Day;
import com.hugs4bugs.core.request.DayRequest;

import java.util.List;

public interface DaysService {

    List<Day> getDays(int firstEntity, int limit);

    Day saveDay(DayRequest dayRequest);

    Day getDay(int id);

    void updateDay(int id, DayRequest dayRequest);

    void deleteDay(int id);

    Integer count();
}
