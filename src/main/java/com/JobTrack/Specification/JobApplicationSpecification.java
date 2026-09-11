package com.JobTrack.Specification;

import com.JobTrack.entity.JobApplication;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class JobApplicationSpecification implements Specification<JobApplication> {

    private String company;
    private String status;
    private String role;
    private String location;

    public JobApplicationSpecification(String company, String status, String role, String location){
        this.company = company;
        this.status = status;
        this.role = role;
        this.location = location;
    }

    @Override
    public @Nullable Predicate toPredicate
            (Root<JobApplication> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate>predicateList = new ArrayList<>();

        if(company != null && !company.isBlank()){
            Predicate predicate = criteriaBuilder.equal
                    (criteriaBuilder.lower(root.get("company")),company.toLowerCase());
            predicateList.add(predicate);
        }

        if(status != null && !status.isBlank()){
            Predicate predicate = criteriaBuilder.equal
                    (criteriaBuilder.lower(root.get("status")),status.toLowerCase());
            predicateList.add(predicate);
        }

        if (role != null && !role.isBlank()){
            Predicate predicate = criteriaBuilder.equal
                    (criteriaBuilder.lower(root.get("role")),role.toLowerCase());
            predicateList.add(predicate);
        }

        if(location != null && !location.isBlank()){
            Predicate predicate = criteriaBuilder.equal
                    (criteriaBuilder.lower(root.get("location")),location.toLowerCase());
            predicateList.add(predicate);
        }

        return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
    }
}
