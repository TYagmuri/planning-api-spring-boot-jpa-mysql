package com.hugs4bugs.repository;

import com.hugs4bugs.core.entity.TimeTableBell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeTableBellRepository extends JpaRepository<TimeTableBell, Integer> {
}
