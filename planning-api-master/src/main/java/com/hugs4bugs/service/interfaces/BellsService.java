package com.hugs4bugs.service.interfaces;

import com.hugs4bugs.core.entity.Bell;
import com.hugs4bugs.core.request.BellRequest;
import java.util.List;

public interface BellsService {

    List<Bell> getBells(int firstEntity, int limit);
    Bell saveBell(BellRequest bellRequest);
    Bell getBell(int id);
    void updateBell(int id, BellRequest bellRequest);
    void deleteBell(int id);
    long countBells();
}
