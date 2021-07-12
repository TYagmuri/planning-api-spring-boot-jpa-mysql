package com.hugs4bugs.core.request;

import com.hugs4bugs.core.roles.Role;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest {

    private String lastName;

    private String firstName;

    private String code;

    private String password;

    private String role;

}
