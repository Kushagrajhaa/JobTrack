package com.JobTrack.DTO.InterviewDTO;

import com.JobTrack.Enum.InterviewResult;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateInterviewRequestDTO {
    @NotNull(message = "Result cannot be null")
    private InterviewResult result;

    @Size(max = 50)
    private String notes;

    public InterviewResult getResult() {
        return result;
    }

    public void setResult(InterviewResult result) {
        this.result = result;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
