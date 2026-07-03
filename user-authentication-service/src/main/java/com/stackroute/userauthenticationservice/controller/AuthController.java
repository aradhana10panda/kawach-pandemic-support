package com.stackroute.userauthenticationservice.controller;

import com.stackroute.userauthenticationservice.model.AuthRequest;
import com.stackroute.userauthenticationservice.model.AuthResponse;
import com.stackroute.userauthenticationservice.model.UserCredential;
import com.stackroute.userauthenticationservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            AuthResponse response = authService.authenticate(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserCredential credential) {
        try {
            UserCredential saved = authService.registerCredential(credential);
            saved.setPassword(null); // never return password
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validate(@RequestParam String token) {
        boolean valid = authService.validateToken(token);
        return new ResponseEntity<>(valid, valid ? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
    }
}
