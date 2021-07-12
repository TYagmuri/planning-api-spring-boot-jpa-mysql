package com.hugs4bugs.rest.controller;

import com.hugs4bugs.core.entity.Master;
import com.hugs4bugs.core.entity.TimeTable;
import com.hugs4bugs.core.entity.TimeTableBell;
import com.hugs4bugs.core.paginate.TimeTableBellPaginatedResult;
import com.hugs4bugs.core.paginate.TimeTablePaginatedResult;
import com.hugs4bugs.core.request.TimeTableBellRequest;
import com.hugs4bugs.service.interfaces.TimeTableBellsService;
import com.hugs4bugs.service.interfaces.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/TimeTableBells")
public class TimeTableBellsController {

    private final TimeTableBellsService timeTableBellsService;
    private final UsersService usersService;

    @Autowired
    public TimeTableBellsController(TimeTableBellsService timeTableBellsService, UsersService usersService) {
        this.timeTableBellsService = timeTableBellsService;
        this.usersService = usersService;
    }

    @GetMapping("")
    @RolesAllowed({"ADMIN", "MASTER"})
    public TimeTableBellPaginatedResult getTimeTableBellPaginatedResult(@RequestParam("PageSize") int pageSize, @RequestParam("Page") int page){
        return new TimeTableBellPaginatedResult(timeTableBellsService.getTimeTableBells((page - 1) * pageSize, pageSize), pageSize, page,
                (int) timeTableBellsService.countTimeTableBells()/ pageSize + 1);
    }

    @PostMapping("")
    @RolesAllowed("Master")
    public TimeTableBell saveTimeTableBell(@RequestBody TimeTableBellRequest request) {
        Master master = (Master) usersService.getUserByCode(
                SecurityContextHolder.getContext().getAuthentication().getName());
        return timeTableBellsService.sendTimeTableBell(request, master);
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ADMIN", "MASTER"})
    public TimeTableBell getTimeTableBell(@PathVariable int id){
        return timeTableBellsService.getTimeTableBell(id);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"ADMIN", "MASTER"})
    public void deleteTimeTableBell(@PathVariable int id) {
        timeTableBellsService.deleteTimeTableBell(id);
    }
}
