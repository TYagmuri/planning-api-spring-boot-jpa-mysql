package com.hugs4bugs.core.entity;

import com.hugs4bugs.core.exceptions.TimeTableExistsInStudentException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
@DiscriminatorValue("STUDENT")
@NoArgsConstructor
@Getter
@Setter
public class Student extends User {

    @JoinTable(name = "user_time_tables",
            joinColumns = @JoinColumn(name = "Student_id"),
            inverseJoinColumns = @JoinColumn(name = "time_tables_id"))

    @ManyToMany
    private Set<TimeTable> timeTables;

    public Student(@NonNull String firstName, @NonNull String lastName, @NonNull String code, @NonNull String role, @NonNull String password) {
        super(firstName, lastName, code, role, password);
    }

    public void addTimeTable(TimeTable timeTable) {
        if (!timeTables.add(timeTable))
            throw new TimeTableExistsInStudentException("This timeTable exists before");
    }
}
