package com.hugs4bugs.core.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    @NonNull
    private Course course;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    @NonNull
    private Master master;


    @NonNull
    @ManyToMany
    private List<TimeTableBell> timeTableBellList;


}
