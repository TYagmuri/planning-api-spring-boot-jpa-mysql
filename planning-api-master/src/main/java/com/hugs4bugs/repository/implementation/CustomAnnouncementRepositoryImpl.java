package com.hugs4bugs.repository.implementation;

import com.hugs4bugs.core.entity.Announcement;
import com.hugs4bugs.core.helper.util.QueryGeneratorUtil;
import com.hugs4bugs.repository.CustomAnnouncementRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class CustomAnnouncementRepositoryImpl implements CustomAnnouncementRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Announcement> getAnnouncements(Integer timeTableId, Integer adminId, int firstEntity, int limit) {
        Query query = entityManager.createNativeQuery(
                QueryGeneratorUtil.announcementQueryGenerator(
                        adminId != null,
                        timeTableId != null
                ),
                Announcement.class
        );
        if (timeTableId != null)
            query.setParameter("timeTableId", timeTableId);
        if (adminId != null)
            query.setParameter("userId", adminId);
        query.setParameter("firstElement", firstEntity);
        query.setParameter("limit", limit);
        return query.getResultList();
    }

    @Override
    public long countAnnouncement(Integer adminId, Integer timeTableId) {
        Query query = entityManager.createNativeQuery(
                QueryGeneratorUtil.countAnnouncementQueryGenerator(
                        adminId != null,
                        timeTableId != null
                )
        );
        if (adminId != null)
            query.setParameter("userId", adminId);
        if (timeTableId != null)
            query.setParameter("timeTableId", timeTableId);
        return query.getFirstResult();
    }

}
