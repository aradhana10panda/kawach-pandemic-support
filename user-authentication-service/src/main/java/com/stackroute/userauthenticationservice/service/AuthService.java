package com.stackroute.userauthenticationservice.service;

import com.stackroute.userauthenticationservice.model.AuthRequest;
import com.stackroute.userauthenticationservice.model.AuthResponse;
import com.stackroute.userauthenticationservice.model.UserCredential;
import com.stackroute.userauthenticationservice.repository.UserCredentialRepository;
import com.stackroute.userauthenticationservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository credentialRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse authenticate(AuthRequest request) {
        UserCredential credential = credentialRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), credential.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        String token = jwtUtil.generateToken(credential.getUsername(), credential.getRole());
        return new AuthResponse(token, credential.getUsername(), credential.getRole());
    }

    public UserCredential registerCredential(UserCredential credential) {
        if (credentialRepository.existsByUsername(credential.getUsername())) {
            throw new RuntimeException("Username already exists: " + credential.getUsername());
        }
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        return credentialRepository.save(credential);
    }

    public boolean validateToken(String token) {
        return jwtUtil.validateToken(token);
    }
}
