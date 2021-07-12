package com.hugs4bugs.core.request;

import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementRequest {

    @NonNull
    private Integer timeTableID;

    @NonNull
    private String message;

}
