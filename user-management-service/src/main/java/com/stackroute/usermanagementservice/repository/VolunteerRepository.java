package com.stackroute.usermanagementservice.repository;
import com.stackroute.usermanagementservice.model.Volunteer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface VolunteerRepository extends MongoRepository<Volunteer, String> {
    Volunteer findByVolunteerEmail(String email);
}
