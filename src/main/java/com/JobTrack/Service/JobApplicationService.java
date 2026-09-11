package com.JobTrack.Service;

import com.JobTrack.DTO.*;
import com.JobTrack.Mapper.JobApplicationMapper;
import com.JobTrack.Repository.JobApplicationRepository;
import com.JobTrack.Specification.JobApplicationSpecification;
import com.JobTrack.entity.JobApplication;
import com.JobTrack.exception.JobNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final JobApplicationMapper jobApplicationMapper;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository,
                                 JobApplicationMapper jobApplicationMapper){
        this.jobApplicationRepository = jobApplicationRepository;
        this.jobApplicationMapper = jobApplicationMapper;
    }

    public JobApplicationResponseDTO createJobApplication(JobApplicationRequestDTO job){
         JobApplication newjob = jobApplicationMapper.toEntity(job);

         newjob.setApplicationDate(LocalDateTime.now());

         JobApplication savedJob = jobApplicationRepository.save(newjob);

         return jobApplicationMapper.toResponseDTO(savedJob);
    }

    public List<JobApplicationResponseDTO> findByCompany(String company){
       List<JobApplication> targetJobs = jobApplicationRepository.findByCompany(company);

       List<JobApplicationResponseDTO> responseJobs = new ArrayList<>();

       for(int i = 0; i < targetJobs.size(); i++ ){
            responseJobs.add(jobApplicationMapper.toResponseDTO(targetJobs.get(i)));
       }
       return responseJobs;
    }

    public List<JobApplicationResponseDTO> findAllJobs(){
        List<JobApplication> jobList = jobApplicationRepository.findAll();

        List<JobApplicationResponseDTO> responseList = new ArrayList<>();

        for(int i = 0; i < jobList.size(); i++){
            responseList.add(jobApplicationMapper.toResponseDTO(jobList.get(i)));
        }
        return responseList;
    }

    public JobApplicationResponseDTO findJobById(Long id){
        JobApplication job = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException("Job application with id " + id + " not found!"));

        return jobApplicationMapper.toResponseDTO(job);
    }

    public UpdateJobDetailsResponseDTO updateJobDetails(Long id, UpdateJobDetailsRequestDTO request){
        JobApplication existingJob = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException("Job application with id " + id + " not found!"));

            existingJob.setCompany(request.getCompany());
            existingJob.setRole(request.getRole());
            existingJob.setStatus(request.getStatus());
            existingJob.setLocation(request.getLocation());

            JobApplication updated = jobApplicationRepository.save(existingJob);
            return  jobApplicationMapper.toUpdateResponseDTO(updated);
    }

    public void deleteJobById(Long id){
        JobApplication target = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException("Job application with id " + id + " not found!"));

        jobApplicationRepository.delete(target);
    }

    public UpdateJobStatusDetailResponseDTO updateJobStatus(Long id, UpdateJobStatusDetailRequestDTO request){
        JobApplication job = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException("Job application with id " + id + " not found!"));

            job.setStatus(request.getStatus());
            JobApplication updatedStatus = jobApplicationRepository.save(job);

            return jobApplicationMapper.toUpdateStatusResponseDTO(updatedStatus);
    }

    public List<JobApplicationResponseDTO> searchJobs(String search){
        List<JobApplication> jobList = jobApplicationRepository
                .findByCompanyContainingIgnoreCaseOrRoleContainingIgnoreCaseOrLocationContainingIgnoreCase
                        (search,search,search);

        List<JobApplicationResponseDTO> matches = new ArrayList<>();

        for(int i = 0; i < jobList.size(); i++){
            matches.add(jobApplicationMapper.toResponseDTO(jobList.get(i)));
        }
        return matches;
    }

    public List<JobApplicationResponseDTO>filterJobs
            (String company, String status, String role, String location){

        JobApplicationSpecification specification = new JobApplicationSpecification(company,status,role,location);

        List<JobApplication> jobList = jobApplicationRepository.findAll(specification);

        List<JobApplicationResponseDTO> resList = new ArrayList<>();

        for(int i = 0; i < jobList.size(); i++){
            resList.add(jobApplicationMapper.toResponseDTO(jobList.get(i)));
        }
        return resList;
    }

    public PaginationResponseDTO findAllJobsPaginated(Pageable pageable){
        Page<JobApplication> jobPage = jobApplicationRepository.findAll(pageable);

        Page<JobApplicationResponseDTO>responsePage = jobPage.map(job ->
                                                jobApplicationMapper.toResponseDTO(job));

        PaginationResponseDTO response = new PaginationResponseDTO();

        response.setContent(responsePage.getContent());
        response.setPage(responsePage.getNumber());
        response.setSize(responsePage.getSize());
        response.setTotalElements(responsePage.getTotalElements());
        response.setTotalPages(responsePage.getTotalPages());

        return response;
    }
}
