package com.JobTrack.Mapper;

import com.JobTrack.DTO.JobApplicationRequestDTO;
import com.JobTrack.DTO.JobApplicationResponseDTO;
import com.JobTrack.DTO.UpdateJobDetailsResponseDTO;
import com.JobTrack.DTO.UpdateJobStatusDetailResponseDTO;
import com.JobTrack.JobTrackApplication;
import com.JobTrack.entity.JobApplication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class JobApplicationMapper {

    public JobApplication toEntity(JobApplicationRequestDTO dto){

        JobApplication application = new JobApplication();

        application.setCompany(dto.getCompany());
        application.setRole(dto.getRole());
        application.setLocation(dto.getLocation());
        application.setStatus(dto.getStatus());

        return application;
    }

    public JobApplicationResponseDTO toResponseDTO(JobApplication responseDTO){

        JobApplicationResponseDTO applicationRes = new JobApplicationResponseDTO();

        applicationRes.setCompany(responseDTO.getCompany());
        applicationRes.setStatus(responseDTO.getStatus());
        applicationRes.setLocation(responseDTO.getLocation());
        applicationRes.setRole(responseDTO.getRole());
        applicationRes.setApplicationDate(responseDTO.getApplicationDate());
        applicationRes.setId(responseDTO.getId());

        return applicationRes;
    }

    public UpdateJobDetailsResponseDTO toUpdateResponseDTO(JobApplication responseDTO){

        UpdateJobDetailsResponseDTO updated = new UpdateJobDetailsResponseDTO();

        updated.setCompany(responseDTO.getCompany());
        updated.setRole(responseDTO.getRole());
        updated.setStatus(responseDTO.getStatus());
        updated.setLocation(responseDTO.getLocation());
        updated.setApplicationDate(responseDTO.getApplicationDate());

        return updated;
    }

    public UpdateJobStatusDetailResponseDTO toUpdateStatusResponseDTO(JobApplication responseDTO){

        UpdateJobStatusDetailResponseDTO updated = new UpdateJobStatusDetailResponseDTO();

        updated.setStatus(responseDTO.getStatus());
        return updated;
    }
}
