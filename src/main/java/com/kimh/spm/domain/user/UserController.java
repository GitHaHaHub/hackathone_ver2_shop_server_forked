package com.kimh.spm.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.kimh.spm.model.AuthRequest;
import com.kimh.spm.security.jwt.JwtService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class UserController {

    @Autowired
    private UserHandler userHandler;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    //SignIn controller
    // @PostMapping("/SignIn")
    // public ResponseEntity<String> signIn(@RequestBody SignInRequest request) {
    //     try {
    //         userHandler.searchUser(user.getId(), user.getPassword());
    //         return ResponseEntity.ok("Success");
    //     } catch (Exception e) {
    //         return ResponseEntity.badRequest().body("Fail");
    //     } 
            
    // }

    @PostMapping("/SignUp")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        try {
            userHandler.createUser(user);
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Fail");
        }
    }

 
    @PostMapping("/ChangePassword")
    public String changePassword() {
        return "ChangePassword";
    }


    @GetMapping("/SearchInfo")
    public String searchInfo(@RequestParam String id) {
        return "SearchInfo";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody User user) {
        return userHandler.addUser(user);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('USER')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/generateToken")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            Cookie cookie = new Cookie("RefreshToken", jwtService.generateRefreshToken());
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            
            return ResponseEntity.ok()
            .body("{\"Authorization\": \"" + jwtService.generateToken(authRequest.getUsername()) + "\"}");
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }   
    
    @PostMapping("/renewToken")
    public ResponseEntity<String> authenticateAndRenewToken(@RequestHeader("Authorization") String token, @CookieValue("RefreshToken") String refreshToken) {
        if (jwtService.validateRefreshToken(token, refreshToken)) {
            return ResponseEntity.ok()
            .body("{\"Authorization\": \"" + jwtService.generateToken(jwtService.extractUsername(token, "access")) + "\"}");
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }   
}