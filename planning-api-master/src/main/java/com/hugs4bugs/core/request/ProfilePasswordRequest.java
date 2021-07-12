package com.hugs4bugs.core.request;

import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ProfilePasswordRequest {

    private String currentPassword;

    private String newPassword;

}
