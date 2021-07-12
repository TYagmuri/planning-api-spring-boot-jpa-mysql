package com.hugs4bugs.repository;

import com.hugs4bugs.core.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer>, CustomAnnouncementRepository {

//    @Query(
//            value = "SELECT * FROM announcement LIMIT :fistEntity, :limit",
//            nativeQuery = true
//    )
//    List<Announcement> getAnnouncements(
//            @Param("fistEntity") Integer fistEntity,
//            @Param("limit") Integer limit);

}
