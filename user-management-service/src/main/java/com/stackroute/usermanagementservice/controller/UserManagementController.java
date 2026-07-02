package com.stackroute.usermanagementservice.controller;
import com.stackroute.usermanagementservice.model.*;
import com.stackroute.usermanagementservice.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/v1/user-management") @CrossOrigin
public class UserManagementController {
    @Autowired private UserManagementService service;
    @PostMapping("/doctor/register")
    public ResponseEntity<?> registerDoctor(@RequestBody Doctor d) {
        try { return new ResponseEntity<>(service.registerDoctor(d), HttpStatus.CREATED); }
        catch (Exception e) { return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT); }
    }
    @PostMapping("/volunteer/register")
    public ResponseEntity<?> registerVolunteer(@RequestBody Volunteer v) {
        try { return new ResponseEntity<>(service.registerVolunteer(v), HttpStatus.CREATED); }
        catch (Exception e) { return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT); }
    }
    @GetMapping("/doctor/{id}")
    public ResponseEntity<?> getDoctorById(@PathVariable String id) {
        try { return new ResponseEntity<>(service.getDoctorById(id), HttpStatus.OK); }
        catch (Exception e) { return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND); }
    }
    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors() { return new ResponseEntity<>(service.getAllDoctors(), HttpStatus.OK); }
    @GetMapping("/volunteers")
    public ResponseEntity<List<Volunteer>> getAllVolunteers() { return new ResponseEntity<>(service.getAllVolunteers(), HttpStatus.OK); }
}
