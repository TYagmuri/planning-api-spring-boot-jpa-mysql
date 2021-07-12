package com.hugs4bugs.repository;

import com.hugs4bugs.core.entity.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterRepository extends JpaRepository<Master, Integer> {

    @Query(
            value = """
                    SELECT * FROM user
                    INNER JOIN time_table
                    ON user.id = time_table.id
                    WHERE course_id = :courseID
                    LIMIT :firstElement , :limit ;
                    """,
            nativeQuery = true
    )
    List<Master> getMastersOfCourse(
            @Param("courseID") int courseId,
            @Param("firstElement") int firstElement,
            @Param("limit") int limit);

    @Query(
            value = """
                            SELECT COUNT(user.id) FROM user
                            INNER JOIN time_table
                            ON user.id = time_table.user_id
                            WHERE course_id = :courseId;
                    """,
            nativeQuery = true
    )
    long countMastersOfCourse(@Param("courseId") int courseId);
}
