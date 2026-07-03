package com.stackroute.resourcerequestservice.controller;

import com.stackroute.resourcerequestservice.model.ResourceRequest;
import com.stackroute.resourcerequestservice.service.ResourceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resource-request")
@CrossOrigin
public class ResourceRequestController {

    @Autowired
    private ResourceRequestService resourceRequestService;

    @PostMapping
    public ResponseEntity<?> createRequest(@RequestBody ResourceRequest request) {
        try {
            return new ResponseEntity<>(resourceRequestService.createRequest(request), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(resourceRequestService.getRequestById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ResourceRequest>> getAllRequests() {
        return new ResponseEntity<>(resourceRequestService.getAllRequests(), HttpStatus.OK);
    }

    @GetMapping("/open")
    public ResponseEntity<List<ResourceRequest>> getOpenRequests() {
        return new ResponseEntity<>(resourceRequestService.getOpenRequests(), HttpStatus.OK);
    }

    @GetMapping("/critical")
    public ResponseEntity<List<ResourceRequest>> getCriticalOpenRequests() {
        return new ResponseEntity<>(resourceRequestService.getCriticalOpenRequests(), HttpStatus.OK);
    }

    @GetMapping("/patient/{patientEmail}")
    public ResponseEntity<List<ResourceRequest>> getByPatient(@PathVariable String patientEmail) {
        return new ResponseEntity<>(resourceRequestService.getRequestsByPatient(patientEmail), HttpStatus.OK);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<ResourceRequest>> getByCity(@PathVariable String city) {
        return new ResponseEntity<>(resourceRequestService.getRequestsByCity(city), HttpStatus.OK);
    }

    @GetMapping("/city/{city}/type/{resourceType}")
    public ResponseEntity<List<ResourceRequest>> getByCityAndType(@PathVariable String city,
                                                                   @PathVariable String resourceType) {
        return new ResponseEntity<>(resourceRequestService.getRequestsByCityAndType(city, resourceType), HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable String id, @RequestParam String status) {
        try {
            return new ResponseEntity<>(resourceRequestService.updateStatus(id, status), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
