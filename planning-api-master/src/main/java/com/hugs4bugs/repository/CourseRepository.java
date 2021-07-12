package com.hugs4bugs.repository;

import com.hugs4bugs.core.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>, CustomCourseRepository {


//    @Query(
//            value = """
//                    SELECT * FROM course WHERE
//                    title = :search OR units_count = :unitCount
//                    LIMIT :firstEntity , :limit ;
//                    """,
//            nativeQuery = true
//    )
//    List<Course> getCourses(
//            @Param("search") String search,
//            @Param("unitCount") Integer unitCount,
//            @Param("firstEntity") Integer fistEntity,
//            @Param("limit") Integer limit);
}
