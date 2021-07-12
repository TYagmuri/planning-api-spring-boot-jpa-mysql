package com.hugs4bugs.rest.controller;

import com.hugs4bugs.core.entity.Course;
import com.hugs4bugs.core.entity.Master;
import com.hugs4bugs.core.paginate.CoursePaginatedResult;
import com.hugs4bugs.core.paginate.MasterPaginatedResult;
import com.hugs4bugs.core.paginate.TimeTableBellPaginatedResult;
import com.hugs4bugs.core.paginate.TimeTablePaginatedResult;
import com.hugs4bugs.core.request.CourseRequest;
import com.hugs4bugs.service.classes.UsersServiceImpl;
import com.hugs4bugs.service.interfaces.CoursesService;
//import com.hugs4bugs.service.interfaces.MasterService;
import com.hugs4bugs.service.interfaces.MasterService;
import com.hugs4bugs.service.interfaces.TimeTableService;
import com.hugs4bugs.service.interfaces.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/Course")
public class CoursesController {

    private final CoursesService coursesService;
    private final TimeTableService timeTableService;
    private final MasterService masterService;
    private final UsersService usersService;

    @Autowired
    public CoursesController(CoursesService coursesService, TimeTableService timeTableService,
                             UsersService usersService, MasterService masterService) {
        this.coursesService = coursesService;
        this.timeTableService = timeTableService;
        this.masterService = masterService;
        this.usersService = usersService;
    }

    @GetMapping("")
    @RolesAllowed("ADMIN")
    public CoursePaginatedResult getCoursePaginatedResult(@RequestParam(value = "search", required = false) String search,
                                                          @RequestParam(value = "unitCount", required = false) Integer unitCount,
                                                          @RequestParam("PageSize") int pageSize, @RequestParam("Page") int page) {
        return new CoursePaginatedResult(coursesService.getCourses(search, unitCount,
                (page - 1) * pageSize, pageSize), pageSize, page,
                coursesService.count()/ pageSize + 1);
    }

    @PostMapping("")
    public Course sendCourse(@RequestBody CourseRequest request) {
        return coursesService.saveCourse(request);
    }

    @GetMapping("{id}")
    @RolesAllowed("ADMIN")
    public Course getCourse(@PathVariable int id) {
        return coursesService.getCourse(id);
    }

    @PutMapping("/{id}")
    @RolesAllowed("ADMIN")
    public void updateCourse(@PathVariable int id, @RequestBody CourseRequest request) {
        coursesService.updateCourse(id, request);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("ADMIN")
    public void deleteCourse(@PathVariable int id) {
        coursesService.deleteCourse(id);
    }

    @GetMapping("/{id}/TimeTables")
    public TimeTablePaginatedResult getTimeTableBellPaginatedResult(@PathVariable int id, @RequestParam("PageSize") int pageSize,
                                                                    @RequestParam("Page") int page) {

        return new TimeTablePaginatedResult(coursesService.getTimeTableOfCourse(id, (page - 1) * pageSize, pageSize),
                pageSize,
                page,
                (int) timeTableService.countTimeTablesOfCourse(id) / pageSize + 1);
    }

    @GetMapping("/{id}/Masters")
    public MasterPaginatedResult getMasterPaginatedResult(@PathVariable int id, @RequestParam("PageSize") int pageSize,
                                                          @RequestParam("page") int page) {
        return new MasterPaginatedResult(coursesService.getMastersOfCourse(id, (page - 1) * pageSize, pageSize),
                pageSize,
                page,
                (int) (masterService.countMastersOfCourse(id) / pageSize +1));
    }

    @PostMapping("/{id}/Choose")
    @RolesAllowed("MASTER")
    public void chooseCourse(@PathVariable int id) {
        Master master = (Master) usersService.getUserByCode(
               SecurityContextHolder.getContext().getAuthentication().getName());
        coursesService.chooseCourse(id, master);
    }
}
