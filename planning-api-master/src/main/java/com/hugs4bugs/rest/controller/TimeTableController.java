package com.hugs4bugs.rest.controller;

import com.hugs4bugs.core.entity.Student;
import com.hugs4bugs.core.entity.TimeTable;
import com.hugs4bugs.core.paginate.TimeTablePaginatedResult;
import com.hugs4bugs.service.interfaces.TimeTableService;
import com.hugs4bugs.service.interfaces.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/TimeTable")
public class TimeTableController {

    private final TimeTableService timeTableService;
    private final UsersService usersService;

    @Autowired
    public TimeTableController(TimeTableService timeTableService, UsersService usersService) {
        this.timeTableService = timeTableService;
        this.usersService = usersService;
    }

    @GetMapping("")
    public TimeTablePaginatedResult getTimeTablePaginatedResult(@RequestParam(value = "StudentId", required = false) Integer studentId,
                                                                @RequestParam(value = "CourseId", required = false) Integer courseId,
                                                                @RequestParam(value = "MasterId", required = false) Integer masterId,
                                                                @RequestParam("PageSize") int pageSize, @RequestParam("Page") int page){
        return new TimeTablePaginatedResult(timeTableService.getTimeTables(studentId, courseId,masterId,
                (page - 1) * pageSize, pageSize), pageSize, page,
                (int) (timeTableService.countTimeTables(studentId, courseId, masterId) / pageSize + 1));
    }

    @GetMapping("/{id}")
    public TimeTable getTimeTable(@PathVariable int id){
        return timeTableService.getTimeTable(id);
    }

    @PostMapping("/{id}/Choose")
    @RolesAllowed("STUDENT")
    public void chooseTimeTable(@PathVariable int id){
        Student student = (Student) usersService.getUserByCode(
                SecurityContextHolder.getContext().getAuthentication().getName());
        timeTableService.chooseTimeTable(id, student);
    }

    @PostMapping("/StartProcess")
    @RolesAllowed("ADMIN")
    public void startProcess(@RequestParam("maxClassPerBell") int maxClassPerBell){
        timeTableService.startProcess(maxClassPerBell);
    }

}
