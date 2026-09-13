package com.JobTrack.entity;

import com.JobTrack.Enum.JobStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String company;
   private String role;
   @Enumerated(EnumType.STRING)
   private JobStatus status;
   private String location;
   private LocalDateTime applicationDate;

   @OneToMany(mappedBy = "jobApplication",
              cascade = CascadeType.ALL,
              orphanRemoval = true)
   private List<Interview> interviews = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }

    public List<Interview> getInterviews() {
        return interviews;
    }

    public void setInterviews(List<Interview> interviews) {
        this.interviews = interviews;
    }

    public void addInterview(Interview interview){
        interviews.add(interview);
        interview.setJobApplication(this);
    }

    public void removeInterview(Interview interview){
        interviews.remove(interview);
        interview.setJobApplication(null);
    }
}
