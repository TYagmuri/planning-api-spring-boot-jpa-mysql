package com.hugs4bugs.repository;

import com.hugs4bugs.core.entity.TimeTable;

import java.util.List;

public interface CustomTimeTableRepository {

    List<TimeTable> getTimeTables(Integer studentId, Integer courseId, Integer masterId,
                                  int firstElement, int limit);

    long countTimeTable(Integer studentId, Integer courseId, Integer masterId);

}
