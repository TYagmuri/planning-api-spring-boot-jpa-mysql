package com.hugs4bugs.core.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class TimeTableBell {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @ToString.Exclude
    private Day day;


    @NonNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @ToString.Exclude
    private Bell bell;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TimeTableBell that = (TimeTableBell) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1344748178;
    }
}
