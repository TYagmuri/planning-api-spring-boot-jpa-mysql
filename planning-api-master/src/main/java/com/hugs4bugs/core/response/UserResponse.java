package com.hugs4bugs.core.response;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Integer id;

    private String lastName;

    private String firstName;

    private String code;

    private String role;



}
