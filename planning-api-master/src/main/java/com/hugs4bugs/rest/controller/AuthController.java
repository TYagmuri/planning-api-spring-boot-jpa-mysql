package com.hugs4bugs.rest.controller;

import com.hugs4bugs.core.entity.User;
import com.hugs4bugs.core.request.AuthRequest;
import com.hugs4bugs.core.response.AuthResponse;
import com.hugs4bugs.service.classes.UserDetailedService;
import com.hugs4bugs.service.classes.UsersServiceImpl;
import com.hugs4bugs.service.util.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtUtil;
    private final UserDetailedService detailedService;
    private final UsersServiceImpl service;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenUtil jwtUtil,
                          UserDetailedService detailedService,
                          UsersServiceImpl service) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.detailedService = detailedService;
        this.service = service;
    }

    @PostMapping("/Login")
    public AuthResponse sendAuthRequest(@RequestBody AuthRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getCode(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("-Bad Credentials Exception-", e);
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("-Username Not Found Exception-", e);
        } catch (Exception e) {
            throw new Exception("-An error has occurred-", e);
        }
        final UserDetails userDetails = detailedService.loadUserByUsername(request.getCode());
        final String token = jwtUtil.generateToken(userDetails);
        final String expireAt = jwtUtil.extractExpiration(token).toString();
        final User user = service.getUserByCode(userDetails.getUsername());
        return new AuthResponse("hugs4bugs " + token, expireAt, user);
    }
}
