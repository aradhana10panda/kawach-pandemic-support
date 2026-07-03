package com.stackroute.doctorconsultationservice.repository;

import com.stackroute.doctorconsultationservice.model.Consultation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConsultationRepository extends MongoRepository<Consultation, String> {
    List<Consultation> findByPatientEmail(String patientEmail);
    List<Consultation> findByDoctorId(String doctorId);
    List<Consultation> findByStatus(String status);
}
