package com.hugs4bugs.core.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Bell {

    @Id
    private Integer id;

    @NonNull
    private String label;

    @NonNull
    private Integer bellOfDay;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bell bell = (Bell) o;

        return id != null ? id.equals(bell.id) : bell.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
