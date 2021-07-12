package com.hugs4bugs.core.paginate;

import com.hugs4bugs.core.entity.TimeTableBell;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TimeTableBellPaginatedResult {

    private List<TimeTableBell> list;

    private int pageSize;

    private int page;

    private int totalPages;

}
