package com.hugs4bugs.repository;

import com.hugs4bugs.core.entity.Announcement;
import java.util.List;

public interface CustomAnnouncementRepository {

    List<Announcement> getAnnouncements(Integer timeTableId, Integer adminId, int firstEntity, int limit);
    long countAnnouncement(Integer adminId, Integer timeTableId);

}
