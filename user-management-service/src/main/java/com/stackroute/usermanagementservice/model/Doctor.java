package com.stackroute.usermanagementservice.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "doctors")
public class Doctor {
    @Id private String doctorId;
    private String doctorName;
    private String doctorEmail;
    private String doctorPassword;
    private String doctorSpecialization;
    private String doctorCity;
    private String doctorContactNo;
    private String doctorExperience;
    private String doctorQualification;
    private byte[] doctorImage;
    private boolean isOnline;
    public Doctor() {}
    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }
    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }
    public String getDoctorEmail() { return doctorEmail; }
    public void setDoctorEmail(String doctorEmail) { this.doctorEmail = doctorEmail; }
    public String getDoctorPassword() { return doctorPassword; }
    public void setDoctorPassword(String doctorPassword) { this.doctorPassword = doctorPassword; }
    public String getDoctorSpecialization() { return doctorSpecialization; }
    public void setDoctorSpecialization(String s) { this.doctorSpecialization = s; }
    public String getDoctorCity() { return doctorCity; }
    public void setDoctorCity(String doctorCity) { this.doctorCity = doctorCity; }
    public String getDoctorContactNo() { return doctorContactNo; }
    public void setDoctorContactNo(String s) { this.doctorContactNo = s; }
    public String getDoctorExperience() { return doctorExperience; }
    public void setDoctorExperience(String s) { this.doctorExperience = s; }
    public String getDoctorQualification() { return doctorQualification; }
    public void setDoctorQualification(String s) { this.doctorQualification = s; }
    public byte[] getDoctorImage() { return doctorImage; }
    public void setDoctorImage(byte[] doctorImage) { this.doctorImage = doctorImage; }
    public boolean isOnline() { return isOnline; }
    public void setOnline(boolean online) { isOnline = online; }
}
