package com.stackroute.volunteerrevertservice.service;

import com.stackroute.volunteerrevertservice.model.VolunteerRevert;
import com.stackroute.volunteerrevertservice.repository.VolunteerRevertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VolunteerRevertService {

    @Autowired
    private VolunteerRevertRepository volunteerRevertRepository;

    public VolunteerRevert submitRevert(VolunteerRevert revert) {
        revert.setStatus("PENDING_VERIFICATION");
        revert.setVerified(false);
        revert.setScore(0);
        revert.setRevertedAt(LocalDateTime.now());
        return volunteerRevertRepository.save(revert);
    }

    public VolunteerRevert verifyRevert(String revertId, boolean approved) {
        VolunteerRevert revert = volunteerRevertRepository.findById(revertId)
                .orElseThrow(() -> new RuntimeException("Revert not found: " + revertId));
        if (approved) {
            revert.setVerified(true);
            revert.setStatus("VERIFIED");
            revert.setScore(10); // base score for verified contribution
        } else {
            revert.setStatus("REJECTED");
            revert.setScore(0);
        }
        revert.setVerifiedAt(LocalDateTime.now());
        return volunteerRevertRepository.save(revert);
    }

    public List<VolunteerRevert> getRevertsByVolunteer(String volunteerEmail) {
        return volunteerRevertRepository.findByVolunteerEmail(volunteerEmail);
    }

    public List<VolunteerRevert> getRevertsByRequest(String resourceRequestId) {
        return volunteerRevertRepository.findByResourceRequestId(resourceRequestId);
    }

    public List<VolunteerRevert> getPendingReverts() {
        return volunteerRevertRepository.findByStatus("PENDING_VERIFICATION");
    }

    public List<VolunteerRevert> getAllReverts() {
        return volunteerRevertRepository.findAll();
    }
}
