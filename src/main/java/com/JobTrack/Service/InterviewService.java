package com.JobTrack.Service;

import com.JobTrack.DTO.InterviewDTO.InterviewRequestDTO;
import com.JobTrack.DTO.InterviewDTO.InterviewResponseDTO;
import com.JobTrack.DTO.InterviewDTO.UpdateInterviewRequestDTO;
import com.JobTrack.DTO.InterviewDTO.UpdateInterviewResponseDTO;
import com.JobTrack.Mapper.InterviewMapper;
import com.JobTrack.Repository.InterviewRepository;
import com.JobTrack.Repository.JobApplicationRepository;
import com.JobTrack.entity.Interview;
import com.JobTrack.entity.JobApplication;
import com.JobTrack.exception.InterviewNotFoundException;
import com.JobTrack.exception.JobNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InterviewService {
    private final JobApplicationRepository jobApplicationRepository;
    private final InterviewRepository interviewRepository;
    private final InterviewMapper interviewMapper;

    public InterviewService(JobApplicationRepository jobApplicationRepository,
                            InterviewRepository interviewRepository,
                            InterviewMapper interviewMapper) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.interviewRepository = interviewRepository;
        this.interviewMapper = interviewMapper;
    }

    public InterviewResponseDTO createInterview(Long jobId, InterviewRequestDTO request){
        JobApplication job = jobApplicationRepository.findById(jobId)
                .orElseThrow(() -> new JobNotFoundException("Job not found!"));

        Interview interview = interviewMapper.toEntity(request);
        interview.setJobApplication(job);

        int highestRound = interviewRepository
                .findTopByJobApplicationIdOrderByRoundNumberDesc(jobId)
                .map(Interview::getRoundNumber)
                .orElse(0);

        int nextRound = highestRound + 1;

        interview.setRoundNumber(nextRound);

        Interview savedInterview = interviewRepository.save(interview);

        return interviewMapper.toResponseDTO(savedInterview);
    }

    public List<InterviewResponseDTO> findInterviewByJobId(Long jobId){
        JobApplication job = jobApplicationRepository.findById(jobId)
                .orElseThrow(() -> new JobNotFoundException("Job not found!"));

        List<Interview> interviews= interviewRepository.findByJobApplicationId(jobId);

        List<InterviewResponseDTO> interviewList = new ArrayList<>();

        for(int i = 0; i < interviews.size(); i++){
            interviewList.add(interviewMapper.toResponseDTO(interviews.get(i)));
        }
        return interviewList;
    }

    public InterviewResponseDTO findInterviewById(Long interviewId){
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new InterviewNotFoundException("Interview not found!"));

        InterviewResponseDTO response = interviewMapper.toResponseDTO(interview);
        return response;
    }

    public UpdateInterviewResponseDTO updateInterviewById(Long interviewId, UpdateInterviewRequestDTO request){
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new InterviewNotFoundException("Interview not found!"));

        interview.setResult(request.getResult());
        interview.setNotes(request.getNotes());

        Interview updated = interviewRepository.save(interview);
        return interviewMapper.toUpdateResultResponseDTO(updated);
    }

    public void deleteInterviewById(Long interviewId){
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new InterviewNotFoundException("Interview not found!"));

        interviewRepository.delete(interview);
    }
}
