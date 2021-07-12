package com.hugs4bugs.core.paginate;

import com.hugs4bugs.core.entity.Master;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MasterPaginatedResult {

    private List<Master> list;

    private int pageSize;

    private int page;

    private int totalPages;

}
