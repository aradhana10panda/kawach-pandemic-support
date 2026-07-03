package com.stackroute.resourcerequestservice.repository;

import com.stackroute.resourcerequestservice.model.ResourceRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ResourceRequestRepository extends MongoRepository<ResourceRequest, String> {
    List<ResourceRequest> findByPatientEmail(String patientEmail);
    List<ResourceRequest> findByStatus(String status);
    List<ResourceRequest> findByCityAndResourceType(String city, String resourceType);
    List<ResourceRequest> findByUrgencyAndStatus(String urgency, String status);
    List<ResourceRequest> findByCityAndStatus(String city, String status);
}
