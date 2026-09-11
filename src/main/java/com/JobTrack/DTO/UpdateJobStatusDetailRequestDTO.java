package com.JobTrack.DTO;

import com.JobTrack.Enum.JobStatus;

public class UpdateJobStatusDetailRequestDTO {
    private JobStatus status;

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }
}
