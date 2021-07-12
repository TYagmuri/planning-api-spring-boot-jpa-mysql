package com.hugs4bugs.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hugs4bugs.core.exceptions.CourseExistsInMasterException;
import com.hugs4bugs.core.exceptions.TimeTableBellExistsException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@DiscriminatorValue("MASTER")
@NoArgsConstructor
@Getter
@Setter
public class Master extends User {

    @JoinTable(name = "User_courses",
            joinColumns = @JoinColumn(name = "Master_id"),
            inverseJoinColumns = @JoinColumn(name = "courses_id"))
    @ManyToMany
    @JsonIgnore
    private List<Course> existentCourses;

    @OneToMany
    @JsonIgnore
    private List<TimeTableBell> existentTimeTableBells;

    public Master(@NonNull String firstName, @NonNull String lastName, @NonNull String code, @NonNull String role, @NonNull String password) {
        super(firstName, lastName, code, role, password);
    }

    public void addTimeTableBell(TimeTableBell timeTableBell) {
        if (!existentTimeTableBells.add(timeTableBell))
            throw new TimeTableBellExistsException("This timeTableBell exists before");
    }

    public void addCourse(Course course) {
        if (!existentCourses.add(course))
            throw new CourseExistsInMasterException("Master Can not present one course for more than one time");
    }

}
