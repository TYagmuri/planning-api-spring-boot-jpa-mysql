package com.hugs4bugs.core.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class Announcement {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @NonNull
//    @JoinColumn
//    @OneToOne(fetch = FetchType.LAZY)
//    private Integer timeTableId;

    @NonNull
    @JoinColumn
    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private TimeTable timeTable;

    @NonNull
    private String message;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Announcement that = (Announcement) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 390267980;
    }
}
