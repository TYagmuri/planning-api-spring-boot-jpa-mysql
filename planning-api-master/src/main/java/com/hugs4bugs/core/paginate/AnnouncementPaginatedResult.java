package com.hugs4bugs.core.paginate;

import com.hugs4bugs.core.entity.Announcement;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementPaginatedResult {

    private List<Announcement> list;

    private Integer count;

    private Integer page;

    private Integer totalPage;

}
