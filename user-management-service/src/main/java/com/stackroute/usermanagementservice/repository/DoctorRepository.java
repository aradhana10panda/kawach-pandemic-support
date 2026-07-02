package com.stackroute.usermanagementservice.repository;
import com.stackroute.usermanagementservice.model.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DoctorRepository extends MongoRepository<Doctor, String> {
    Doctor findByDoctorEmail(String email);
}
