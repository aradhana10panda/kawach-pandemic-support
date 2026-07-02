package com.stackroute.usermanagementservice.service;
import com.stackroute.usermanagementservice.model.Doctor;
import com.stackroute.usermanagementservice.model.Volunteer;
import java.util.List;
public interface UserManagementService {
    Doctor registerDoctor(Doctor doctor);
    Volunteer registerVolunteer(Volunteer volunteer);
    Doctor getDoctorById(String doctorId);
    List<Doctor> getAllDoctors();
    List<Volunteer> getAllVolunteers();
}
