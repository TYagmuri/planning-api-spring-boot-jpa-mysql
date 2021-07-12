package com.hugs4bugs.repository.implementation;

import com.hugs4bugs.core.entity.Course;
import com.hugs4bugs.core.helper.util.QueryGeneratorUtil;
import com.hugs4bugs.repository.CustomCourseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class CustomCourseRepositoryImpl implements CustomCourseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Course> getCourses(String search, Integer unitCounts, int firstElement, int limit) {
        Query query = entityManager.createNativeQuery(
                QueryGeneratorUtil.courseQueryGenerator(
                        search != null,
                        unitCounts != null
                )
        );
        if (search != null)
            query.setParameter("search", search);
        if (unitCounts != null)
            query.setParameter("unitCount", unitCounts);
        query.setParameter("firstElement", firstElement);
        query.setParameter("limit", limit);
        return query.getResultList();
    }

    @Override
    public long countCourses(String search, Integer unitCounts) {
        Query query = entityManager.createNativeQuery(
                QueryGeneratorUtil.countCoursesQueryGenerator(
                        search != null,
                        unitCounts != null
                )
        );
        if (search != null)
            query.setParameter("search", search);
        if (unitCounts != null)
            query.setParameter("unitCount", unitCounts);
        return query.getFirstResult();
    }

}
