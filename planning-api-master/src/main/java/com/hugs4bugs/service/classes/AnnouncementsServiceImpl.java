package com.hugs4bugs.service.classes;

import com.hugs4bugs.core.entity.Announcement;
import com.hugs4bugs.core.request.AnnouncementRequest;
import com.hugs4bugs.repository.AnnouncementRepository;
import com.hugs4bugs.repository.TimeTableRepository;
import com.hugs4bugs.service.interfaces.AnnouncementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementsServiceImpl implements AnnouncementsService {

    private final AnnouncementRepository announcementRepository;
    private final TimeTableRepository timeTableRepository;

    @Autowired
    public AnnouncementsServiceImpl(AnnouncementRepository announcementRepository,
                                    TimeTableRepository timeTableRepository) {
        this.announcementRepository = announcementRepository;
        this.timeTableRepository = timeTableRepository;
    }

    @Override
    public List<Announcement> getAnnouncements(Integer timeTableId, Integer adminId, int firstEntity, int limit) {
        return announcementRepository.getAnnouncements(timeTableId, adminId, firstEntity, limit);
    }

    @Override
    public Announcement getAnnouncement(int id) {
        return announcementRepository.getById(id);
    }

    @Override
    public Announcement saveAnnouncement(AnnouncementRequest announcementRequest) {
        return announcementRepository.save(createAnnouncement(announcementRequest));
    }

    @Override
    public void deleteAnnouncement(int id) {
        announcementRepository.deleteById(id);
    }

    @Override
    public long countAnnouncement() {
        return announcementRepository.count();
    }

    private Announcement createAnnouncement(AnnouncementRequest announcementRequest) {
        Announcement announcement = new Announcement();
        announcement.setMessage(announcementRequest.getMessage());
        announcement.setTimeTable(timeTableRepository.getById(announcementRequest.getTimeTableID()));
        return announcement;
    }
}
