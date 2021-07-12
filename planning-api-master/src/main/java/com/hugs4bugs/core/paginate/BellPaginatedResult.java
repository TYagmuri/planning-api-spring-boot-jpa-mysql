package com.hugs4bugs.core.paginate;

import com.hugs4bugs.core.entity.Bell;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BellPaginatedResult {

    private List<Bell> list;

    private int pageSize;

    private int page;

    private int totalPages;

}
