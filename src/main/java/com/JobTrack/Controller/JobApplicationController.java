package com.JobTrack.Controller;

import com.JobTrack.DTO.*;
import com.JobTrack.Service.JobApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/jobtrack/api/jobs")
public class JobApplicationController {
    JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService){
        this.jobApplicationService = jobApplicationService;
    }

    @PostMapping
    public ResponseEntity<JobApplicationResponseDTO> createJobPost(@Valid @RequestBody JobApplicationRequestDTO job){
        JobApplicationResponseDTO response = jobApplicationService.createJobApplication(job);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/company/{company}")
    public ResponseEntity<List<JobApplicationResponseDTO>> getJobsByCompany(@PathVariable String company){
        List<JobApplicationResponseDTO> list = jobApplicationService.findByCompany(company);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping
    public ResponseEntity<List<JobApplicationResponseDTO>> getAllJobs(){
        List<JobApplicationResponseDTO> list = jobApplicationService.findAllJobs();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplicationResponseDTO>getJobById(@PathVariable Long id){
        JobApplicationResponseDTO target = jobApplicationService.findJobById(id);

        return ResponseEntity.status(HttpStatus.OK).body(target);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateJobDetailsResponseDTO> updateJobById
            (@Valid @RequestBody UpdateJobDetailsRequestDTO job, @PathVariable Long id){

        UpdateJobDetailsResponseDTO target =
                jobApplicationService.updateJobDetails(id, job);

        return ResponseEntity.status(HttpStatus.OK).body(target);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        jobApplicationService.deleteJobById(id);
        return ResponseEntity.ok("Job deleted Successfully!");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateJobStatusDetailResponseDTO> updateStatus
            (@Valid @RequestBody UpdateJobStatusDetailRequestDTO job, @PathVariable Long id){

        UpdateJobStatusDetailResponseDTO target = jobApplicationService.updateJobStatus(id,job);

        return ResponseEntity.status(HttpStatus.OK).body(target);
    }

    @GetMapping("/search")
    public ResponseEntity<List<JobApplicationResponseDTO>>searchJobs
            (@RequestParam String search){

        List<JobApplicationResponseDTO> matches = jobApplicationService.searchJobs(search);

        return ResponseEntity.status(HttpStatus.OK).body(matches);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<JobApplicationResponseDTO>>jobFilter
            (@RequestParam(required = false)String company,
             @RequestParam(required = false)String status,
             @RequestParam(required = false)String role,
             @RequestParam(required = false)String location){

        List<JobApplicationResponseDTO> jobs = jobApplicationService.filterJobs(company,status,role,location);
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/pages")
    public ResponseEntity<PaginationResponseDTO>jobPages(Pageable pageable){
        PaginationResponseDTO pages = jobApplicationService.findAllJobsPaginated(pageable);
        return ResponseEntity.ok(pages);
    }
}
