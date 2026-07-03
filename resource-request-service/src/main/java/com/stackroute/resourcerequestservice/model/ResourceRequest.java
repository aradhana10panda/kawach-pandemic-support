package com.stackroute.resourcerequestservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "resource_requests")
public class ResourceRequest {

    @Id
    private String requestId;
    private String patientEmail;
    private String patientName;
    private String resourceType;      // BED, ICU_BED, VENTILATOR, OXYGEN, MEDICINE, AMBULANCE, EQUIPMENT
    private String resourceName;      // specific medicine name, equipment name, etc.
    private String city;
    private String state;
    private String description;
    private String urgency;           // CRITICAL, HIGH, MEDIUM, LOW
    private String status;            // OPEN, IN_PROGRESS, FULFILLED, CANCELLED
    private int quantity;
    private LocalDateTime requestedAt;
    private LocalDateTime fulfilledAt;

    public ResourceRequest() {}

    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }
    public String getPatientEmail() { return patientEmail; }
    public void setPatientEmail(String patientEmail) { this.patientEmail = patientEmail; }
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    public String getResourceType() { return resourceType; }
    public void setResourceType(String resourceType) { this.resourceType = resourceType; }
    public String getResourceName() { return resourceName; }
    public void setResourceName(String resourceName) { this.resourceName = resourceName; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getUrgency() { return urgency; }
    public void setUrgency(String urgency) { this.urgency = urgency; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public LocalDateTime getRequestedAt() { return requestedAt; }
    public void setRequestedAt(LocalDateTime requestedAt) { this.requestedAt = requestedAt; }
    public LocalDateTime getFulfilledAt() { return fulfilledAt; }
    public void setFulfilledAt(LocalDateTime fulfilledAt) { this.fulfilledAt = fulfilledAt; }
}
