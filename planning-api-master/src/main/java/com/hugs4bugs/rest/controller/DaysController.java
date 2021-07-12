package com.hugs4bugs.rest.controller;

import com.hugs4bugs.core.entity.Day;
import com.hugs4bugs.core.paginate.DayPaginatedResult;
import com.hugs4bugs.core.request.DayRequest;
import com.hugs4bugs.service.interfaces.DaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/Days")
public class DaysController {

    private final DaysService daysService;

    @Autowired
    public DaysController(DaysService daysService) {
        this.daysService = daysService;
    }

    @GetMapping("")
    public DayPaginatedResult getDayPaginatedResult(@RequestParam("PageSize") int pageSize,
                                                    @RequestParam("Page") int page) {
        return new DayPaginatedResult(daysService.getDays((page - 1) * pageSize, pageSize), pageSize, page,
                (daysService.count()));
    }

    @PostMapping("")
    @RolesAllowed("ADMIN")
    public Day sendDay(@RequestBody DayRequest request) {
        return daysService.saveDay(request);
    }

    @GetMapping("/{id}")
    public Day getDay(@PathVariable int id) {
        return daysService.getDay(id);
    }

    @PutMapping("/{id}")
    @RolesAllowed("ADMIN")
    public void updateDay(@PathVariable int id, @RequestBody DayRequest request) {
        daysService.updateDay(id, request);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("ADMIN")
    public void deleteDay(@PathVariable int id) {
        daysService.deleteDay(id);
    }

}
