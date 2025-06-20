/**
 * Package: moe.zzy040330.chat138.controller
 * File: AuthenticationController.java
 * Author: Ziyu ZHOU
 * Date: 20/06/2025
 * Time: 15:18
 * Description: RESTful API for user login & logout
 */
package moe.zzy040330.chat138.controller;

import moe.zzy040330.chat138.dto.LoginRequest;
import moe.zzy040330.chat138.dto.LoginResponse;
import moe.zzy040330.chat138.entity.SecurityUser;
import moe.zzy040330.chat138.entity.User;
import moe.zzy040330.chat138.service.AuthenticationService;
import moe.zzy040330.chat138.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest loginUserDto) {
        SecurityUser authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setUserCode(authenticatedUser.user().getCode());
        loginResponse.setUserName(authenticatedUser.user().getName());
        loginResponse.setUserId(authenticatedUser.user().getId());
        loginResponse.setUserRoleCode(authenticatedUser.user().getRole().equals(0) ? "ROLE_ADMIN" : "ROLE_USER");
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        authenticationService.logout(token);
        return ResponseEntity.ok().build();
    }



    @GetMapping("/encoded-all-pwd")
    public ResponseEntity<List<User>> alwet() {
        authenticationService.encryptAllPasswords();
        return null;
    }

}