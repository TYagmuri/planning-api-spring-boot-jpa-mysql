package com.hugs4bugs.rest.controller;

import com.hugs4bugs.core.entity.User;
import com.hugs4bugs.core.paginate.UserPaginatedResult;
import com.hugs4bugs.core.request.AddUserRequest;
import com.hugs4bugs.core.request.ProfilePasswordRequest;
import com.hugs4bugs.core.request.ProfileRequest;
import com.hugs4bugs.core.response.UserResponse;
import com.hugs4bugs.service.interfaces.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/Users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("")
    @RolesAllowed("ADMIN")
    public UserPaginatedResult getUserPaginatedResult(@RequestParam(value = "search", required = false) String search,
                                                      @RequestParam("PageSize") int pageSize,
                                                      @RequestParam("Page") int page) {
        return new UserPaginatedResult(usersService.getUsers(search, (page - 1) * pageSize, pageSize),
                pageSize, page, (int) (usersService.countResultUsers(search) / pageSize + 1));
    }

    @GetMapping("/{id}")
    @RolesAllowed("ADMIN")
    public User getUser(@PathVariable int id) {
        return usersService.getUser(id);
    }

    @PutMapping("/{id}")
    @RolesAllowed("ADMIN")
    public void updateUser(@PathVariable int id, @RequestBody User user) {
        usersService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("ADMIN")
    public void deleteUser(@PathVariable int id) {
        usersService.deleteUser(id);
    }

    @GetMapping("/Profile")
    public User getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return usersService.getUserByCode(authentication.getName());
    }

    @PostMapping("/profile")
    public User updateProfile(@RequestBody ProfileRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return usersService.updateUserProfile(request, authentication.getName());
    }

    @PostMapping("/Profile/ChangePassword")
    public void changePassword(@RequestBody ProfilePasswordRequest request) {
        updateProfile(new ProfileRequest(null, null, request.getNewPassword()));
    }

    @PostMapping("/Add")
    @RolesAllowed("ADMIN")
    public User addUser(@RequestBody AddUserRequest request) {
        return usersService.addUser(request);
    }

    @PostMapping("/AddList")
    @RolesAllowed("ADMIN")
    public List<User> addUsers(@RequestBody List<AddUserRequest> requestList) {
        return usersService.addUsers(requestList);
    }

    @GetMapping("/test")
    public String test() {
        return "ADIOS ((:";
    }

    private UserResponse userResponseCreator(User user) {
        return new UserResponse(user.getId(),
                user.getLastName(),
                user.getFirstName(),
                user.getRole(),
                user.getCode()
        );
    }
}
