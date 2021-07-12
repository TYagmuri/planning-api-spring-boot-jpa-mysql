package com.hugs4bugs.repository;

import com.hugs4bugs.core.entity.Bell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BellRepository extends JpaRepository<Bell, Integer> {

    @Query(
            value = """
                    SELECT * FROM bell LIMIT :fistEntity , :limit;
                    """,
            nativeQuery = true
    )
    List<Bell> getBells(@Param("fistEntity") Integer fistEntity,
                        @Param("limit") Integer limit);
}
