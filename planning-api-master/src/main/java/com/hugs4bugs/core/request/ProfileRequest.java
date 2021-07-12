package com.hugs4bugs.core.request;

import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequest {

    private String lastName;

    private String firstName;

    private String password;

}
