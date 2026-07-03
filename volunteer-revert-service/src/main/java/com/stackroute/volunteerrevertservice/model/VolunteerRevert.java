package com.stackroute.volunteerrevertservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "volunteer_reverts")
public class VolunteerRevert {

    @Id
    private String revertId;
    private String volunteerId;
    private String volunteerEmail;
    private String resourceRequestId;   // which request this volunteer responded to
    private String resourceType;        // BED, OXYGEN, MEDICINE, AMBULANCE, etc.
    private String resourceDetails;     // actual resource info provided
    private String city;
    private String contactNumber;
    private boolean verified;
    private int score;                  // score awarded for this contribution
    private String status;             // PENDING_VERIFICATION, VERIFIED, REJECTED
    private LocalDateTime revertedAt;
    private LocalDateTime verifiedAt;

    public VolunteerRevert() {}

    public String getRevertId() { return revertId; }
    public void setRevertId(String revertId) { this.revertId = revertId; }
    public String getVolunteerId() { return volunteerId; }
    public void setVolunteerId(String volunteerId) { this.volunteerId = volunteerId; }
    public String getVolunteerEmail() { return volunteerEmail; }
    public void setVolunteerEmail(String volunteerEmail) { this.volunteerEmail = volunteerEmail; }
    public String getResourceRequestId() { return resourceRequestId; }
    public void setResourceRequestId(String resourceRequestId) { this.resourceRequestId = resourceRequestId; }
    public String getResourceType() { return resourceType; }
    public void setResourceType(String resourceType) { this.resourceType = resourceType; }
    public String getResourceDetails() { return resourceDetails; }
    public void setResourceDetails(String resourceDetails) { this.resourceDetails = resourceDetails; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public boolean isVerified() { return verified; }
    public void setVerified(boolean verified) { this.verified = verified; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getRevertedAt() { return revertedAt; }
    public void setRevertedAt(LocalDateTime revertedAt) { this.revertedAt = revertedAt; }
    public LocalDateTime getVerifiedAt() { return verifiedAt; }
    public void setVerifiedAt(LocalDateTime verifiedAt) { this.verifiedAt = verifiedAt; }
}
