package com.hugs4bugs.service.classes;

import com.hugs4bugs.repository.MasterRepository;
import com.hugs4bugs.service.interfaces.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterServiceImpl implements MasterService {

    private final MasterRepository masterRepository;

    @Autowired
    public MasterServiceImpl(MasterRepository repository) {
        this.masterRepository = repository;
    }

    @Override
    public long countMastersOfCourse(int courseId) {
        return masterRepository.countMastersOfCourse(courseId);
    }
}
