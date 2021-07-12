package com.hugs4bugs.core.paginate;

import com.hugs4bugs.core.entity.Course;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CoursePaginatedResult {

    private List<Course> list;

    private int pageSize;

    private int page;

    private int totalPages;

}
