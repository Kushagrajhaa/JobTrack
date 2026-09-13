package com.JobTrack.Mapper;

import com.JobTrack.DTO.InterviewDTO.InterviewRequestDTO;
import com.JobTrack.DTO.InterviewDTO.InterviewResponseDTO;
import com.JobTrack.DTO.InterviewDTO.UpdateInterviewResponseDTO;
import com.JobTrack.entity.Interview;
import org.springframework.stereotype.Component;


@Component
public class InterviewMapper {

    public Interview toEntity(InterviewRequestDTO request){
        Interview interview = new Interview();

        interview.setRoundName(request.getRoundName());
        interview.setScheduledAt(request.getScheduledAt());
        interview.setCompletedAt(request.getCompletedAt());
        interview.setResult(request.getResult());
        interview.setNotes(request.getNotes());

        return interview;
    }

    public InterviewResponseDTO toResponseDTO(Interview response){
        InterviewResponseDTO interview = new InterviewResponseDTO();

        interview.setId(response.getId());
        interview.setRoundName(response.getRoundName());
        interview.setRoundNumber(response.getRoundNumber());
        interview.setScheduledAt(response.getScheduledAt());
        interview.setCompletedAt(response.getCompletedAt());
        interview.setResult(response.getResult());
        interview.setNotes(response.getNotes());

        return interview;
    }

    public UpdateInterviewResponseDTO toUpdateResultResponseDTO(Interview response){
        UpdateInterviewResponseDTO update = new UpdateInterviewResponseDTO();

        update.setResult(response.getResult());
        update.setNotes(response.getNotes());

        return update;
    }
}
