package com.stackroute.informationservice.controller;

import com.stackroute.informationservice.model.PandemicStats;
import com.stackroute.informationservice.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/information")
@CrossOrigin
public class InformationController {

    @Autowired
    private InformationService informationService;

    @GetMapping("/stats")
    public ResponseEntity<List<PandemicStats>> getAllStats() {
        return new ResponseEntity<>(informationService.getAllStats(), HttpStatus.OK);
    }

    @GetMapping("/stats/state/{state}")
    public ResponseEntity<List<PandemicStats>> getByState(@PathVariable String state) {
        return new ResponseEntity<>(informationService.getStatsByState(state), HttpStatus.OK);
    }

    @GetMapping("/stats/district/{district}")
    public ResponseEntity<List<PandemicStats>> getByDistrict(@PathVariable String district) {
        return new ResponseEntity<>(informationService.getStatsByDistrict(district), HttpStatus.OK);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return new ResponseEntity<>("information-service is up", HttpStatus.OK);
    }
}
