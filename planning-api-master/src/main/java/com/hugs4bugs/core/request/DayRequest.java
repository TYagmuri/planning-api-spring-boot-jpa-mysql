package com.hugs4bugs.core.request;

import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DayRequest {

    private String label;

    private int dayOfWeek;

}
