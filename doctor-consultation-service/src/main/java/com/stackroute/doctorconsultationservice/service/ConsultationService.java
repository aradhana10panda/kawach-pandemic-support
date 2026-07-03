package com.stackroute.doctorconsultationservice.service;

import com.stackroute.doctorconsultationservice.model.Consultation;
import com.stackroute.doctorconsultationservice.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    public Consultation createConsultation(Consultation consultation) {
        consultation.setStatus("PENDING");
        consultation.setConsultationDate(LocalDateTime.now());
        return consultationRepository.save(consultation);
    }

    public List<Consultation> getConsultationsByPatient(String patientEmail) {
        return consultationRepository.findByPatientEmail(patientEmail);
    }

    public List<Consultation> getConsultationsByDoctor(String doctorId) {
        return consultationRepository.findByDoctorId(doctorId);
    }

    public Consultation getConsultationById(String id) {
        return consultationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consultation not found: " + id));
    }

    public Consultation updateConsultation(String id, Consultation updated) {
        Consultation existing = getConsultationById(id);
        existing.setPrescription(updated.getPrescription());
        existing.setStatus(updated.getStatus());
        if ("COMPLETED".equals(updated.getStatus())) {
            existing.setCompletedAt(LocalDateTime.now());
        }
        return consultationRepository.save(existing);
    }

    public List<Consultation> getAllConsultations() {
        return consultationRepository.findAll();
    }
}
