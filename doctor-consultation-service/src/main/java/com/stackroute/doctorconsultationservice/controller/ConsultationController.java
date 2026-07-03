package com.stackroute.doctorconsultationservice.controller;

import com.stackroute.doctorconsultationservice.model.Consultation;
import com.stackroute.doctorconsultationservice.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/consultation")
@CrossOrigin
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    @PostMapping
    public ResponseEntity<?> createConsultation(@RequestBody Consultation consultation) {
        try {
            return new ResponseEntity<>(consultationService.createConsultation(consultation), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getConsultationById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(consultationService.getConsultationById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/patient/{patientEmail}")
    public ResponseEntity<List<Consultation>> getByPatient(@PathVariable String patientEmail) {
        return new ResponseEntity<>(consultationService.getConsultationsByPatient(patientEmail), HttpStatus.OK);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Consultation>> getByDoctor(@PathVariable String doctorId) {
        return new ResponseEntity<>(consultationService.getConsultationsByDoctor(doctorId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Consultation>> getAllConsultations() {
        return new ResponseEntity<>(consultationService.getAllConsultations(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateConsultation(@PathVariable String id, @RequestBody Consultation consultation) {
        try {
            return new ResponseEntity<>(consultationService.updateConsultation(id, consultation), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
