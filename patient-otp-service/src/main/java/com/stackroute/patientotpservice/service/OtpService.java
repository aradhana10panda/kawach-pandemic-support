package com.stackroute.patientotpservice.service;

import com.stackroute.patientotpservice.model.OtpRecord;
import com.stackroute.patientotpservice.repository.OtpRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class OtpService {

    private static final int OTP_VALIDITY_MINUTES = 10;
    private static final SecureRandom secureRandom = new SecureRandom();

    @Autowired
    private OtpRecordRepository otpRecordRepository;

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Generates a 6-digit OTP, persists it, and emails it to the patient.
     */
    public String generateAndSendOtp(String email) {
        // Invalidate any previous OTPs for this email
        otpRecordRepository.deleteByEmail(email);

        String otp = generateOtp();
        LocalDateTime now = LocalDateTime.now();
        OtpRecord record = new OtpRecord(email, otp, now, now.plusMinutes(OTP_VALIDITY_MINUTES));
        otpRecordRepository.save(record);

        sendOtpEmail(email, otp);
        return "OTP sent successfully to " + email;
    }

    /**
     * Verifies the OTP. Returns true if valid and not expired; false otherwise.
     * Marks OTP as used on success so it cannot be reused.
     */
    public boolean verifyOtp(String email, String otp) {
        OtpRecord record = otpRecordRepository
                .findTopByEmailAndUsedFalseOrderByCreatedAtDesc(email)
                .orElse(null);

        if (record == null) {
            return false;
        }
        if (LocalDateTime.now().isAfter(record.getExpiresAt())) {
            return false; // expired
        }
        if (!record.getOtp().equals(otp)) {
            return false; // wrong OTP
        }

        record.setUsed(true);
        otpRecordRepository.save(record);
        return true;
    }

    private String generateOtp() {
        int num = secureRandom.nextInt(1000000);
        return String.format("%06d", num);
    }

    private void sendOtpEmail(String toEmail, String otp) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Kawach - Your OTP for Login");
            message.setText(
                "Dear Patient,\n\n" +
                "Your One-Time Password (OTP) for Kawach is: " + otp + "\n\n" +
                "This OTP is valid for " + OTP_VALIDITY_MINUTES + " minutes.\n" +
                "Please do not share this OTP with anyone.\n\n" +
                "Stay safe,\nKawach Support Team"
            );
            mailSender.send(message);
        } catch (Exception e) {
            // Log but do not fail — OTP is still saved in DB
            System.err.println("Failed to send OTP email to " + toEmail + ": " + e.getMessage());
        }
    }
}
