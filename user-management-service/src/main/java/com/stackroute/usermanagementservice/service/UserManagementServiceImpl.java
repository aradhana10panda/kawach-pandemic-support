package com.stackroute.usermanagementservice.service;
import com.stackroute.usermanagementservice.exception.*;
import com.stackroute.usermanagementservice.model.*;
import com.stackroute.usermanagementservice.repository.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class UserManagementServiceImpl implements UserManagementService {
    @Autowired private DoctorRepository doctorRepository;
    @Autowired private VolunteerRepository volunteerRepository;
    @Autowired private RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.exchange}") private String exchange;
    @Value("${rabbitmq.routing.key.doctor}") private String doctorRoutingKey;
    @Value("${rabbitmq.routing.key.volunteer}") private String volunteerRoutingKey;
    @Override
    public Doctor registerDoctor(Doctor doctor) {
        if (doctorRepository.findByDoctorEmail(doctor.getDoctorEmail()) != null)
            throw new DoctorAlreadyExistsException("Doctor already exists: " + doctor.getDoctorEmail());
        Doctor saved = doctorRepository.save(doctor);
        rabbitTemplate.convertAndSend(exchange, doctorRoutingKey, saved);
        return saved;
    }
    @Override
    public Volunteer registerVolunteer(Volunteer volunteer) {
        if (volunteerRepository.findByVolunteerEmail(volunteer.getVolunteerEmail()) != null)
            throw new VolunteerAlreadyExistsException("Volunteer already exists: " + volunteer.getVolunteerEmail());
        Volunteer saved = volunteerRepository.save(volunteer);
        rabbitTemplate.convertAndSend(exchange, volunteerRoutingKey, saved);
        return saved;
    }
    @Override
    public Doctor getDoctorById(String doctorId) {
        Optional<Doctor> d = doctorRepository.findById(doctorId);
        if (!d.isPresent()) throw new DoctorNotFoundException("Doctor not found: " + doctorId);
        return d.get();
    }
    @Override public List<Doctor> getAllDoctors() { return doctorRepository.findAll(); }
    @Override public List<Volunteer> getAllVolunteers() { return volunteerRepository.findAll(); }
}
