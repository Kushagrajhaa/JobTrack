package com.JobTrack.Controller;

import com.JobTrack.DTO.InterviewDTO.InterviewRequestDTO;
import com.JobTrack.DTO.InterviewDTO.InterviewResponseDTO;
import com.JobTrack.DTO.InterviewDTO.UpdateInterviewRequestDTO;
import com.JobTrack.DTO.InterviewDTO.UpdateInterviewResponseDTO;
import com.JobTrack.Service.InterviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobtrack/api")
public class InterviewController {

    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @PostMapping("/jobs/{jobId}/interviews")
    public ResponseEntity<InterviewResponseDTO> createInterview(@PathVariable Long jobId,
                                                                @Valid @RequestBody InterviewRequestDTO request){
        InterviewResponseDTO response = interviewService.createInterview(jobId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/jobs/{jobId}/interviews")
    public ResponseEntity<List<InterviewResponseDTO>>getInterviewsByJobId(@PathVariable Long jobId){

        List<InterviewResponseDTO> response = interviewService.findInterviewByJobId(jobId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/interviews/{interviewId}")
    public ResponseEntity<InterviewResponseDTO>getInterviewById(@PathVariable Long interviewId){

        InterviewResponseDTO response = interviewService.findInterviewById(interviewId);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/interviews/{interviewId}")
    public ResponseEntity<UpdateInterviewResponseDTO>updateInterviewById(@Valid @RequestBody UpdateInterviewRequestDTO request,
                                                                         @PathVariable Long interviewId){
        UpdateInterviewResponseDTO updated = interviewService.updateInterviewById(interviewId,request);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/interviews/{interviewId}")
    public ResponseEntity<Void> deleteInterviewById(@PathVariable Long interviewId){
        interviewService.deleteInterviewById(interviewId);

        return ResponseEntity.noContent().build();
    }
}