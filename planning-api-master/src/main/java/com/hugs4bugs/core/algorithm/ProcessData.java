package com.hugs4bugs.core.algorithm;

import com.hugs4bugs.core.entity.*;
import com.hugs4bugs.core.helper.IndexHelper;

import java.util.*;
import java.util.stream.Collectors;

public class ProcessData {

    private final List<Day> days;
    private final List<Bell> bells;
    private final List<Course> courses;
    private final Map<Course, List<Master>> courseListMap;
    private final int[][] limits;
    private final Stack<IndexHelper> history;
//    private final int finalMaxClassCount;

    public ProcessData(List<Day> days,
                       List<Bell> bells,
                       List<Course> courses,
                       int limit,
                       List<Master> masterList) {
        this.days = days;
        this.bells = bells;
        this.courses = courses;
//        this.finalMaxClassCount = limit;
        this.limits = new int[days.size()][bells.size()];
        this.history = new Stack<>();
        this.courseListMap = new HashMap<>();
        initializeLimits(limit);
        processMastersForCourse(masterList, courseListMap);
    }

    private void initializeLimits(int limitation) {
        for (int i = 0; i < days.size(); i++) {
            for (int j = 0; j < bells.size(); j++) {
                limits[i][j] = limitation;
            }
        }
    }

    public List<TimeTable> startProcess() {
        List<TimeTable> timeTableList = new ArrayList<>();
        if (totalCountValidate(limits[0][0]) && totalMasterValidate()) {
            IndexHelper indexHelper = new IndexHelper();
            while (indexHelper.getCourseIndex() < courses.size()) {
                internalCourseProcess(indexHelper);
            }
            for (IndexHelper helper : history) {
                Course course = null;
                Master master = null;
                timeTableList.add(
                        new TimeTable(
                                course = courses.get(helper.getCourseIndex()),
                                master = courseListMap.get(course).get(helper.getMasterIndex()),
                                helper.getUnitTimeTableIndexes().stream()
                                        .map(master.getExistentTimeTableBells()::get)
                                        .collect(Collectors.toList())
                        )
                );
            }
            return timeTableList;
        }
        return null;
    }

    private void internalCourseProcess(IndexHelper indexHelper) {

        if (courseIndexCheck(indexHelper)) {
            internalMasterProcess(indexHelper, courses.get(indexHelper.getCourseIndex()));
        }
    }

    private void internalMasterProcess(IndexHelper indexHelper, Course course) {
        if (masterIndexCheck(indexHelper, courseListMap.get(course))) {
            internalTimeTableProcess(indexHelper, courseListMap.get(course).get(indexHelper.getMasterIndex()), course);

        } else {
            indexHelper = history.pop();
            clearCourseInTimeTable(courses.get(indexHelper.getCourseIndex()), courseListMap);
            indexHelper.setTimeTableBellIndex(indexHelper.getUnitTimeTableIndexes().removeLast() + 1);
        }
    }

    private void internalTimeTableProcess(IndexHelper indexHelper, Master master, Course course) {
        if (timeTableBellIndexCheck(indexHelper, master.getExistentTimeTableBells())) {
            TimeTableBell timeTableBell =
                    master.getExistentTimeTableBells().get(indexHelper.getTimeTableBellIndex());
            if (indexHelper.getUnitTimeTableIndexes().size() < course.getUnitsCount()) {
                if (checkTimeTableBellForUnit(master,
                        timeTableBell,
                        indexHelper.getUnitTimeTableIndexes())) {
                    indexHelper.addUnitTimeTableIndex(indexHelper.getTimeTableBellIndex());
                    indexHelper.setTimeTableBellIndex(0);
                } else {
                    indexHelper.increaseTimeTableBellIndex();
                }
            } else {
                chooseTimeTableForCourse(course, master, indexHelper.getUnitTimeTableIndexes());
                history.push(new IndexHelper(indexHelper.getCourseIndex(),
                        indexHelper.getMasterIndex(),
                        indexHelper.getTimeTableBellIndex(),
                        new LinkedList<>(indexHelper.getUnitTimeTableIndexes())));
                indexHelper.increaseCourseIndex();
                indexHelper.setMasterIndex(0);
                indexHelper.setTimeTableBellIndex(0);
            }
        } else {
            if (indexHelper.getUnitTimeTableIndexes().size() == 1) {
                indexHelper.increaseMasterIndex();
            } else {
                indexHelper.setTimeTableBellIndex(indexHelper.getUnitTimeTableIndexes().removeLast());
                indexHelper.increaseTimeTableBellIndex();
            }
        }
    }

    private void processMastersForCourse(List<Master> masters,
                                         Map<Course, List<Master>> courseListMap) {
        for (Master master : masters) {
            for (Course course : master.getExistentCourses()) {
                if (courseListMap.containsKey(course)) {
                    courseListMap.get(course).add(master);
                } else {
                    List<Master> courseMastersList = new ArrayList<>();
                    courseMastersList.add(master);
                    courseListMap.put(course, courseMastersList);
                }
            }
        }
    }

    private boolean totalCountValidate(int limitation) {
        int counter = 0;
        for (Course course : courses) {
            counter += course.getUnitsCount();
        }
        return counter <= days.size() * bells.size() * limitation;
    }

    private boolean totalMasterValidate() {
        return courseListMap.keySet().size() == courses.size();
    }

    private boolean courseIndexCheck(IndexHelper indexHelper) {
        return -1 < indexHelper.getCourseIndex() && indexHelper.getCourseIndex() < courses.size();
    }

    private boolean masterIndexCheck(IndexHelper indexHelper, List<Master> masters) {
        return -1 < indexHelper.getMasterIndex() && indexHelper.getMasterIndex() < masters.size();
    }

    private boolean timeTableBellIndexCheck(IndexHelper indexHelper, List<TimeTableBell> timeTableBells) {
        return -1 < indexHelper.getTimeTableBellIndex() &&
                indexHelper.getTimeTableBellIndex() < timeTableBells.size();
    }

    private void clearCourseInTimeTable(Course course, Map<Course, List<Master>> map) {
        List<TimeTableBell> timeTableBells = history.stream()
                .filter(indexHelper -> course.equals(courses.get(indexHelper.getCourseIndex())))
                .findFirst()
                .map(indexHelper -> {
                    List<TimeTableBell> result = new ArrayList<>();
                    Master master = map.get(course).get(indexHelper.getMasterIndex());
                    for (int index : indexHelper.getUnitTimeTableIndexes()) {
                        result.add(master.getExistentTimeTableBells().get(index));
                    }
                    return result;
                }).get();
        for (TimeTableBell timeTableBell : timeTableBells) {
            limits[days.indexOf(timeTableBell.getDay())][bells.indexOf(timeTableBell.getBell())]++;
        }
    }

    private boolean checkTimeTableBellForUnit(Master master, TimeTableBell timeTableBell, List<Integer> timeTableBellIndexes) {
        List<TimeTableBell> timeTablesBells = generateTimeTableBells(timeTableBellIndexes, master);
        for (TimeTableBell t : timeTablesBells) {
            if (t.getDay().equals(timeTableBell.getDay()))
                return false;
        }

        if (limits[days.indexOf(timeTableBell.getDay())][bells.indexOf(timeTableBell.getBell())] == 0)
            return false;
        for (IndexHelper indexHelper : history) {
            Master master1 = courseListMap
                    .get(courses.get(indexHelper.getCourseIndex()))
                    .get(indexHelper.getMasterIndex());
            TimeTableBell timeTableBell1 = master1
                    .getExistentTimeTableBells()
                    .get(indexHelper.getTimeTableBellIndex());
            if ((timeTableBell1.getDay().equals(timeTableBell.getDay()))
                    && (timeTableBell1.getBell().equals(timeTableBell.getBell()))
                    && (master1.equals(master))) {
                return false;
            }
        }
        return true;
    }

    private void chooseTimeTableForCourse(Course course, Master master, List<Integer> timeTableBellIndexes) {
        List<TimeTableBell> timeTableBells = generateTimeTableBells(timeTableBellIndexes, master);
        for (TimeTableBell timeTableBell : timeTableBells) {
            limits[days.indexOf(timeTableBell.getDay())][bells.indexOf(timeTableBell.getBell())]--;
        }
    }

    private List<TimeTableBell> generateTimeTableBells(List<Integer> timeTableBellIndexes, Master master) {
        return timeTableBellIndexes.stream()
                .map(master.getExistentTimeTableBells()::get)
                .collect(Collectors.toList());
    }
}
