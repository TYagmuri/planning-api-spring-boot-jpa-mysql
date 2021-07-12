package com.hugs4bugs.core.helper.util;


public class QueryGeneratorUtil {

    public static String timeTableQueryGenerator(boolean studentId, boolean courseId, boolean masterId) {
        String query = "select * from time_table " +
                (studentId ? "inner join user_time_tables utt on time_table.id = utt.time_tables_id " +
                        "inner join user on utt.student_id = user.id " : "") +
                (studentId || courseId || masterId ? "where " : "") +
                (masterId ? "master_id = :masterId and " : "") +
                (studentId ? "user.id = :studentId and " : "") +
                (courseId ? "course_id = :courseId and " : "");

        int lastAndIndex = query.lastIndexOf("and ");
        return (lastAndIndex == -1 ?
                query :
                query.substring(0, lastAndIndex)) + "limit :firstElement, :limit";
    }

    public static String countTimeTableQueryGenerator(boolean studentId, boolean courseId, boolean masterId) {
        String query = "select count(time_table.id) from time_table " +
                (studentId ? "inner join user_time_tables utt on time_table.id = utt.time_tables_id " +
                        "inner join user on utt.student_id = user.id " : "") +
                (studentId || courseId || masterId ? "where " : "") +
                (masterId ? "master_id = :masterId and " : "") +
                (studentId ? "user.id = :studentId and " : "") +
                (courseId ? "course_id = :courseId and " : "");
        int lastAndIndex = query.lastIndexOf("and ");
        return lastAndIndex == -1 ?
                query :
                query.substring(0, lastAndIndex);
    }

    public static String announcementQueryGenerator(boolean userId, boolean timeTableId) {
        String query = "select * from announcement " +
                (userId || timeTableId ? "where " : "") +
                (timeTableId ? "time_table_id = :timeTableId and " : "") +
                (userId ? "creator_id = :userId and " : "");
        int lastAndIndex = query.lastIndexOf("and ");
        return (lastAndIndex == -1 ?
                query :
                query.substring(0, lastAndIndex)) + "limit :firstElement, :limit";
    }

    public static String countAnnouncementQueryGenerator(boolean userId, boolean timeTableId) {
        String query = "select count(announcement.id) from announcement " +
                (userId || timeTableId ? "where " : "") +
                (timeTableId ? "time_table_id = :timeTableId and " : "") +
                (userId ? "creator_id = :userId and " : "");
        int lastAndIndex = query.lastIndexOf("and ");
        return lastAndIndex == -1 ?
                query :
                query.substring(0, lastAndIndex);
    }

    public static String courseQueryGenerator(boolean search, boolean unitCount) {
        String query = "select * from course " +
                (search || unitCount ? "where " : "") +
                (search ? "title like concat('%', :search, '%') and " : "") +
                (unitCount ? "units_count = :unitCount and " : "");
        int lastAndIndex = query.lastIndexOf("and ");
        return (lastAndIndex == -1 ?
                query :
                query.substring(0, lastAndIndex)) + "limit :firstElement, :limit";
    }

    public static String countCoursesQueryGenerator(boolean search, boolean unitCount) {
        String query = "select count(course.id) from course " +
                (search || unitCount ? "where " : "") +
                (search ? "title like concat('%', :search, '%') and " : "") +
                (unitCount ? "units_count = :unitCount and " : "");
        int lastAndIndex = query.lastIndexOf("and ");
        return lastAndIndex == -1 ?
                query :
                query.substring(0, lastAndIndex);
    }

}
