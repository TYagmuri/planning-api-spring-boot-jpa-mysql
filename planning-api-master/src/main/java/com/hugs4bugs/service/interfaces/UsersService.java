package com.hugs4bugs.service.interfaces;

import com.hugs4bugs.core.entity.User;
import com.hugs4bugs.core.request.AddUserRequest;
import com.hugs4bugs.core.request.ProfileRequest;

import java.util.List;

public interface UsersService {

    List<User> getUsers(String search, int firstEntity, int limit);
    User getUser(int id);
    void updateUser(int id, User newUser);
    User getUserByCode(String code);
    void deleteUser(int id);
    User addUser(AddUserRequest addUserRequest);
    List<User> addUsers(List<AddUserRequest> addUserRequests);
    User updateUserProfile(ProfileRequest profileRequest, String code);
    long countResultUsers(String search);
}
