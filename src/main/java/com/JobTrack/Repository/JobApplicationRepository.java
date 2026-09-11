package com.JobTrack.Repository;

import com.JobTrack.DTO.JobApplicationResponseDTO;
import com.JobTrack.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication,Long>
                                                    ,JpaSpecificationExecutor<JobApplication> {

    List<JobApplication> findByCompany(String company);

    List<JobApplication>findByCompanyContainingIgnoreCaseOrRoleContainingIgnoreCaseOrLocationContainingIgnoreCase
            (String company, String role, String location);


}
