package com.JobTrack.DTO;


import com.JobTrack.Enum.JobStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class JobApplicationRequestDTO {
    @NotBlank(message = "Company name cannot be empty or null")
    @Size(min = 2, max = 50, message = "Company name must be between 2 and 50 characters long")
    private String company;

    @NotBlank(message = "role cannot be empty or null")
    @Size(min = 2, max = 20, message = "Role must be between 2 and 20 characters long")
    private String role;

    @NotNull(message = "Status cannot be null")
    private JobStatus status;

    @NotBlank(message = "location cannot be empty or null")
    @Size(min = 2, max = 30, message = "Location must be between 2 and 30 characters long")
    private String location;

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
}
