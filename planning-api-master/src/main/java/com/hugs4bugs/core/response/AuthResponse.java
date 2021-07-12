package com.hugs4bugs.core.response;

import com.hugs4bugs.core.entity.User;
import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String token;

    private String expireAt;

    private User user;

}
