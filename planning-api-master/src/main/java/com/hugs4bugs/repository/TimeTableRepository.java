package com.hugs4bugs.repository;

import com.hugs4bugs.core.entity.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable, Integer>, CustomTimeTableRepository {

    @Query(
            value = """
                    SELECT * FROM time_table
                    INNER JOIN course
                    ON time_table.course_id = course.id
                    WHERE course_id = :courseId
                    LIMIT :firstElement, :limit;
                    """,
            nativeQuery = true
    )
    List<TimeTable> getTimeTableOfCourse(
            @Param("courseId") int courseId,
            @Param("firstElement") int firstElement,
            @Param("limit") int limit
    );

    @Query(
            value = """
                    SELECT COUNT (time_table.id) FROM time_table 
                    WHERE course_id = :courseId;
                    """,
            nativeQuery = true
    )
    long countTimeTablesOfCourse(
            @Param("courseId") int courseId
    );
}
