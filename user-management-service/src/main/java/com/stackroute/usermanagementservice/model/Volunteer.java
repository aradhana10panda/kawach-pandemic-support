package com.stackroute.usermanagementservice.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "volunteers")
public class Volunteer {
    @Id private String volunteerId;
    private String volunteerName;
    private String volunteerEmail;
    private String volunteerPassword;
    private String volunteerCity;
    private String volunteerContactNo;
    private int score;
    public Volunteer() {}
    public String getVolunteerId() { return volunteerId; }
    public void setVolunteerId(String v) { this.volunteerId = v; }
    public String getVolunteerName() { return volunteerName; }
    public void setVolunteerName(String v) { this.volunteerName = v; }
    public String getVolunteerEmail() { return volunteerEmail; }
    public void setVolunteerEmail(String v) { this.volunteerEmail = v; }
    public String getVolunteerPassword() { return volunteerPassword; }
    public void setVolunteerPassword(String v) { this.volunteerPassword = v; }
    public String getVolunteerCity() { return volunteerCity; }
    public void setVolunteerCity(String v) { this.volunteerCity = v; }
    public String getVolunteerContactNo() { return volunteerContactNo; }
    public void setVolunteerContactNo(String v) { this.volunteerContactNo = v; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
}
