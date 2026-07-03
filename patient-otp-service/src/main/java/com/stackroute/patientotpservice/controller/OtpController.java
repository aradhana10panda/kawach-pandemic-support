package com.stackroute.patientotpservice.controller;

import com.stackroute.patientotpservice.model.OtpRequest;
import com.stackroute.patientotpservice.model.OtpVerifyRequest;
import com.stackroute.patientotpservice.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/otp")
@CrossOrigin
public class OtpController {

    @Autowired
    private OtpService otpService;

    /**
     * POST /api/v1/otp/generate
     * Body: { "email": "patient@example.com" }
     * Generates a 6-digit OTP and sends it to the email.
     */
    @PostMapping("/generate")
    public ResponseEntity<?> generateOtp(@RequestBody OtpRequest request) {
        if (request.getEmail() == null || request.getEmail().isBlank()) {
            return new ResponseEntity<>("Email is required", HttpStatus.BAD_REQUEST);
        }
        try {
            String result = otpService.generateAndSendOtp(request.getEmail());
            return new ResponseEntity<>(Collections.singletonMap("message", result), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * POST /api/v1/otp/verify
     * Body: { "email": "patient@example.com", "otp": "123456" }
     * Returns 200 + { "valid": true } if correct and not expired, 401 otherwise.
     */
    @PostMapping("/verify")
    public ResponseEntity<Map<String, Boolean>> verifyOtp(@RequestBody OtpVerifyRequest request) {
        boolean valid = otpService.verifyOtp(request.getEmail(), request.getOtp());
        Map<String, Boolean> response = Collections.singletonMap("valid", valid);
        return new ResponseEntity<>(response, valid ? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
    }
}
