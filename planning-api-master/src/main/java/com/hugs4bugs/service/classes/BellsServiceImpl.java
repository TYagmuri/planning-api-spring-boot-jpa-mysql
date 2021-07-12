package com.hugs4bugs.service.classes;

import com.hugs4bugs.core.entity.Bell;
import com.hugs4bugs.core.request.BellRequest;
import com.hugs4bugs.repository.BellRepository;
import com.hugs4bugs.service.interfaces.BellsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BellsServiceImpl implements BellsService {

    private final BellRepository bellRepository;

    @Autowired
    public BellsServiceImpl(BellRepository bellRepository) {
        this.bellRepository = bellRepository;
    }

    @Override
    public List<Bell> getBells(int fistEntity, int limit) {
        return bellRepository.getBells(fistEntity, limit);
    }

    @Override
    public Bell saveBell(BellRequest bellRequest) {
        return bellRepository.save(createBell(bellRequest));
    }

    @Override
    public Bell getBell(int id) {
        return bellRepository.getById(id);
    }

    @Override
    public void updateBell(int id, BellRequest bellRequest) {
        Bell newBell = getBell(id);
        newBell.setLabel(bellRequest.getLabel());
        newBell.setBellOfDay(bellRequest.getBellOfDay());
        bellRepository.save(newBell);

    }

    @Override
    public void deleteBell(int id) {
        bellRepository.deleteById(id);
    }

    @Override
    public long countBells() {
        return bellRepository.count();
    }

    private Bell createBell(BellRequest bellRequest){
        return new Bell(bellRequest.getLabel(), bellRequest.getBellOfDay());
    }
}
