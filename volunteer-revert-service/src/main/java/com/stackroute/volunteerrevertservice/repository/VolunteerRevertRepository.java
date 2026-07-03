package com.stackroute.volunteerrevertservice.repository;

import com.stackroute.volunteerrevertservice.model.VolunteerRevert;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VolunteerRevertRepository extends MongoRepository<VolunteerRevert, String> {
    List<VolunteerRevert> findByVolunteerEmail(String volunteerEmail);
    List<VolunteerRevert> findByResourceRequestId(String resourceRequestId);
    List<VolunteerRevert> findByStatus(String status);
    List<VolunteerRevert> findByVolunteerIdOrderByScoreDesc(String volunteerId);
}
