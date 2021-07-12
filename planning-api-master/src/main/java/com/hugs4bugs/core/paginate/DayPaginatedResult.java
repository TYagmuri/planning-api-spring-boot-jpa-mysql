package com.hugs4bugs.core.paginate;

import com.hugs4bugs.core.entity.Day;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DayPaginatedResult {

    private List<Day> list;

    private int pageSize;

    private int page;

    private int totalPages;

}
