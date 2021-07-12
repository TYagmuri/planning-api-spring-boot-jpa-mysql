package com.hugs4bugs.repository;

import com.hugs4bugs.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(
            value = """
                    SELECT * FROM user
                    WHERE CONCAT(first_name, last_name) LIKE CONCAT('%' , :search , '%')
                    LIMIT :firstElement , :limit ;
                    """,
            nativeQuery = true
    )
    List<User> searchUsers(
            @Param("search") String search,
            @Param("firstElement") Integer firstElement,
            @Param("limit") Integer limit
    );

    User findUsersByCode(String code);
}
