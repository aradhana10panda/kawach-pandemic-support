package com.stackroute.volunteerrevertservice.controller;

import com.stackroute.volunteerrevertservice.model.VolunteerRevert;
import com.stackroute.volunteerrevertservice.service.VolunteerRevertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/volunteer-revert")
@CrossOrigin
public class VolunteerRevertController {

    @Autowired
    private VolunteerRevertService volunteerRevertService;

    @PostMapping
    public ResponseEntity<?> submitRevert(@RequestBody VolunteerRevert revert) {
        try {
            return new ResponseEntity<>(volunteerRevertService.submitRevert(revert), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{revertId}/verify")
    public ResponseEntity<?> verifyRevert(@PathVariable String revertId,
                                          @RequestParam boolean approved) {
        try {
            return new ResponseEntity<>(volunteerRevertService.verifyRevert(revertId, approved), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/volunteer/{volunteerEmail}")
    public ResponseEntity<List<VolunteerRevert>> getByVolunteer(@PathVariable String volunteerEmail) {
        return new ResponseEntity<>(volunteerRevertService.getRevertsByVolunteer(volunteerEmail), HttpStatus.OK);
    }

    @GetMapping("/request/{resourceRequestId}")
    public ResponseEntity<List<VolunteerRevert>> getByRequest(@PathVariable String resourceRequestId) {
        return new ResponseEntity<>(volunteerRevertService.getRevertsByRequest(resourceRequestId), HttpStatus.OK);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<VolunteerRevert>> getPending() {
        return new ResponseEntity<>(volunteerRevertService.getPendingReverts(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<VolunteerRevert>> getAll() {
        return new ResponseEntity<>(volunteerRevertService.getAllReverts(), HttpStatus.OK);
    }
}
