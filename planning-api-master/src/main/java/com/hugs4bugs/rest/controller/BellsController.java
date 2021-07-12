package com.hugs4bugs.rest.controller;

import com.hugs4bugs.core.entity.Bell;
import com.hugs4bugs.core.paginate.BellPaginatedResult;
import com.hugs4bugs.core.request.BellRequest;
import com.hugs4bugs.service.interfaces.BellsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/Bells")
public class BellsController {

    private final BellsService bellsService;

    @Autowired
    public BellsController(BellsService bellsService) {
        this.bellsService = bellsService;
    }

    @GetMapping("")
    public BellPaginatedResult getBellPaginatedResult(@RequestParam("PageSize") int pageSize, @RequestParam("Page") int page){
        return new BellPaginatedResult(bellsService.getBells((page - 1) * pageSize, pageSize), pageSize,
                page,(int) bellsService.countBells()/ pageSize + 1);
    }

    @PostMapping("")
    @RolesAllowed("ADMIN")
    public Bell sendBell(@RequestBody BellRequest request){
        return bellsService.saveBell(request);
    }

    @GetMapping("/{id}")
    public Bell getBell(@PathVariable int id){
        return bellsService.getBell(id);
    }

    @PutMapping("/{id}")
    @RolesAllowed("ADMIN")
    public void updateBell(@PathVariable int id, @RequestBody BellRequest request){
        bellsService.updateBell(id, request);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("ADMIN")
    public void deleteBell(@PathVariable int id){
        bellsService.deleteBell(id);
    }
}
