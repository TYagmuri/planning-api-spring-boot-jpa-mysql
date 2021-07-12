package com.hugs4bugs.core.helper;

import lombok.*;

import java.util.LinkedList;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IndexHelper {

    private int courseIndex;

    private int masterIndex;

    private int timeTableBellIndex;

    private LinkedList<Integer> unitTimeTableIndexes;


    public void increaseCourseIndex() {
        courseIndex++;
    }

    public void increaseMasterIndex() {
        masterIndex++;
    }

    public void increaseTimeTableBellIndex() {
        timeTableBellIndex++;
    }

    public void addUnitTimeTableIndex(int index) {
        unitTimeTableIndexes.add(index);
    }

}
