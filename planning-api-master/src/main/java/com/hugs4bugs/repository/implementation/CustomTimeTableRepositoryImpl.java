package com.hugs4bugs.repository.implementation;

import com.hugs4bugs.core.entity.TimeTable;
import com.hugs4bugs.core.helper.util.QueryGeneratorUtil;
import com.hugs4bugs.repository.CustomTimeTableRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class CustomTimeTableRepositoryImpl implements CustomTimeTableRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TimeTable> getTimeTables(Integer studentId, Integer courseId, Integer masterId, int firstElement, int limit) {
        Query query = entityManager.createNativeQuery(
                QueryGeneratorUtil.timeTableQueryGenerator(
                        studentId != null,
                        courseId != null,
                        masterId != null
                )
        );
        if (studentId != null) {
            query.setParameter("studentId", studentId);
        }
        if (courseId != null) {
            query.setParameter("courseId", courseId);
        }
        if (studentId != null) {
            query.setParameter("masterId", masterId);
        }
        query.setParameter("firstElement", firstElement);
        query.setParameter("limit", limit);
        return query.getResultList();
    }

    @Override
    public long countTimeTable(Integer studentId, Integer courseId, Integer masterId) {
        Query query = entityManager.createNativeQuery(
                QueryGeneratorUtil.countTimeTableQueryGenerator(
                        studentId != null,
                        courseId != null,
                        masterId != null
                )
        );
        return query.getFirstResult();
    }
}
