package com.hugs4bugs.core.request;

import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class TimeTableBellRequest {

    private int dayId;

    private int bellId;

    private int timeTableId;

}
