package com.hugs4bugs.rest.controller;

import com.hugs4bugs.core.entity.Announcement;
import com.hugs4bugs.core.paginate.AnnouncementPaginatedResult;
import com.hugs4bugs.core.request.AnnouncementRequest;
import com.hugs4bugs.service.interfaces.AnnouncementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/Announcements")
public class AnnouncementsController {

    private final AnnouncementsService announcementsService;

    @Autowired
    public AnnouncementsController(AnnouncementsService announcementsService) {
        this.announcementsService = announcementsService;
    }

    @GetMapping("")
    public AnnouncementPaginatedResult getAnnouncementPaginatedResult(@RequestParam(value = "MasterId", required = false) Integer masterId,
                                                                      @RequestParam(value = "TimeTableId", required = false) Integer timeTableId,
                                                                      @RequestParam("PageSize") int pageSize,
                                                                      @RequestParam("Page") int page) {
        System.out.println("'getAnnouncementPaginatedResult' in 'AnnouncementsController'");
        return new AnnouncementPaginatedResult(
                announcementsService.getAnnouncements(timeTableId, masterId,(page - 1) * pageSize, pageSize),
                pageSize, page, (int) announcementsService.countAnnouncement()/ pageSize + 1);
    }

    @PostMapping("")
    @RolesAllowed({"ADMIN", "MASTER"})
    public Announcement sendAnnouncement(@RequestBody AnnouncementRequest request) {
        return announcementsService.saveAnnouncement(request);
    }

    @GetMapping("/{id}")
    public Announcement getAnnouncementWithId(@PathVariable int id) {
        return announcementsService.getAnnouncement(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAnnouncement(@PathVariable int id) {
        announcementsService.deleteAnnouncement(id);
    }
}
