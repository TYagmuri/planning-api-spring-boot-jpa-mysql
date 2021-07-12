package com.hugs4bugs.core.paginate;

import com.hugs4bugs.core.entity.User;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserPaginatedResult {

    private List<User> list;

    private int count;

    private int page;

    private int totalPages;

}
