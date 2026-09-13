package com.JobTrack.Repository;

import com.JobTrack.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InterviewRepository extends JpaRepository<Interview,Long> {
   Optional<Interview>findTopByJobApplicationIdOrderByRoundNumberDesc(Long jobApplicationId);

   List<Interview>findByJobApplicationId(Long jobId);
}
