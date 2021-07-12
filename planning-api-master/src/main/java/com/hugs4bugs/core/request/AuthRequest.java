package com.hugs4bugs.core.request;

import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    private String code;

    private String password;

}
