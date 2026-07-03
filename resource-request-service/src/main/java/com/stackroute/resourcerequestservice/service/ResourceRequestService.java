package com.stackroute.resourcerequestservice.service;

import com.stackroute.resourcerequestservice.model.ResourceRequest;
import com.stackroute.resourcerequestservice.repository.ResourceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResourceRequestService {

    @Autowired
    private ResourceRequestRepository resourceRequestRepository;

    public ResourceRequest createRequest(ResourceRequest request) {
        request.setStatus("OPEN");
        request.setRequestedAt(LocalDateTime.now());
        return resourceRequestRepository.save(request);
    }

    public ResourceRequest getRequestById(String id) {
        return resourceRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource request not found: " + id));
    }

    public List<ResourceRequest> getRequestsByPatient(String patientEmail) {
        return resourceRequestRepository.findByPatientEmail(patientEmail);
    }

    public List<ResourceRequest> getOpenRequests() {
        return resourceRequestRepository.findByStatus("OPEN");
    }

    public List<ResourceRequest> getRequestsByCity(String city) {
        return resourceRequestRepository.findByCityAndStatus(city, "OPEN");
    }

    public List<ResourceRequest> getRequestsByCityAndType(String city, String resourceType) {
        return resourceRequestRepository.findByCityAndResourceType(city, resourceType);
    }

    public List<ResourceRequest> getCriticalOpenRequests() {
        return resourceRequestRepository.findByUrgencyAndStatus("CRITICAL", "OPEN");
    }

    public ResourceRequest updateStatus(String id, String status) {
        ResourceRequest request = getRequestById(id);
        request.setStatus(status);
        if ("FULFILLED".equals(status)) {
            request.setFulfilledAt(LocalDateTime.now());
        }
        return resourceRequestRepository.save(request);
    }

    public List<ResourceRequest> getAllRequests() {
        return resourceRequestRepository.findAll();
    }
}
