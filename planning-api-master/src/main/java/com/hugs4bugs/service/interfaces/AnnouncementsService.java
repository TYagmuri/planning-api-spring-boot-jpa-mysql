package com.hugs4bugs.service.interfaces;

import com.hugs4bugs.core.entity.Announcement;
import com.hugs4bugs.core.request.AnnouncementRequest;

import java.util.List;

public interface AnnouncementsService {

    Announcement getAnnouncement(int id);

    Announcement saveAnnouncement(AnnouncementRequest announcementRequest);

    List<Announcement> getAnnouncements(Integer timeTableId, Integer adminId ,int firstEntity, int limit);

    void deleteAnnouncement(int id);

    long countAnnouncement();
}
