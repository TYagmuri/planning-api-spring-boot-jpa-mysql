package com.hugs4bugs.service.classes;

import com.hugs4bugs.core.entity.Day;
import com.hugs4bugs.core.request.DayRequest;
import com.hugs4bugs.repository.DayRepository;
import com.hugs4bugs.service.interfaces.DaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.SequenceGenerator;
import java.util.List;

@Service
public class DaysServiceImpl implements DaysService {

    private final DayRepository dayRepository;

    @Autowired
    public DaysServiceImpl(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    @Override
    public List<Day> getDays(int fistEntity, int limit) {
        return dayRepository.findAll();
    }

    @Override
    public Day saveDay(DayRequest dayRequest) {
        return dayRepository.save(createDay(dayRequest));
    }

    @Override
    public Day getDay(int id) {
        return dayRepository.getById(id);
    }

    @Override
    public void updateDay(int id, DayRequest dayRequest) {
        Day newDay = getDay(id);
        newDay.setLabel(dayRequest.getLabel());
        newDay.setDayOfWeek(dayRequest.getDayOfWeek());
        dayRepository.save(newDay);
    }

    @Override
    public void deleteDay(int id) {
        dayRepository.deleteById(id);
    }

    @Override
    public Integer count() {
        return (int) dayRepository.count();
    }

    private Day createDay(DayRequest dayRequest) {
        return new Day(dayRequest.getLabel(), dayRequest.getDayOfWeek());
    }
}
