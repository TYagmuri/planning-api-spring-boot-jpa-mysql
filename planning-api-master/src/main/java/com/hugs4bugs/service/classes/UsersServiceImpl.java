package com.hugs4bugs.service.classes;

import com.hugs4bugs.core.entity.User;
import com.hugs4bugs.core.request.AddUserRequest;
import com.hugs4bugs.core.request.ProfileRequest;
import com.hugs4bugs.repository.UserRepository;
import com.hugs4bugs.service.interfaces.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;

    @Autowired
    public UsersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsers(String search, int firstEntity, int limit) {
        return userRepository.searchUsers(search, firstEntity, limit);
    }

    @Override
    public User getUser(int id) {
        return userRepository.getById(id);
    }

    @Override
    public void updateUser(int id, User newUser) {
        User oldUser = getUser(id);
        oldUser.setLastName(newUser.getLastName());
        oldUser.setFirstName(newUser.getFirstName());
        oldUser.setCode(newUser.getCode());
        oldUser.setRole(newUser.getRole());
        userRepository.save(oldUser);
    }

    @Override
    public User getUserByCode(String code) {
        return userRepository.findUsersByCode(code);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User addUser(AddUserRequest addUserRequest) {
        return userRepository.save(createUser(addUserRequest));
    }

    @Override
    public List<User> addUsers(List<AddUserRequest> addUserRequests) {
        return userRepository.saveAll(
                addUserRequests.stream()
                        .map(this::createUser)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public User updateUserProfile(ProfileRequest profileRequest, String code) {
        User newUser = getUserByCode(code);
        newUser.setLastName(profileRequest.getLastName() != null ? profileRequest.getLastName() : newUser.getLastName());
        newUser.setFirstName(profileRequest.getFirstName() != null ? profileRequest.getFirstName() : newUser.getFirstName());
        newUser.setPassword(profileRequest.getPassword() != null ? profileRequest.getPassword() : newUser.getPassword());
        return newUser;
    }

    @Override
    public long countResultUsers(String search) {
        return 0;
    }


    private User createUser(AddUserRequest addUserRequest) {
        User user = new User();
        user.setLastName(addUserRequest.getLastName());
        user.setFirstName(addUserRequest.getFirstName());
        user.setCode(addUserRequest.getCode());
        user.setPassword(addUserRequest.getPassword());
        user.setRole(addUserRequest.getRole());
        return user;
    }

}
