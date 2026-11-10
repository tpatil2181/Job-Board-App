package com.jobBordaApp.JobBoardApp.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

//import com.jobBordaApp.JobBoardApp.dto.AppliedJobSearchDTO;
import com.jobBordaApp.JobBoardApp.entity.ApplyJob;
import com.jobBordaApp.JobBoardApp.enums.JobApplicationStatus;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class AppliedJobSpecification {

	public static Specification<ApplyJob> getAppliedJobSpecification(
	        Integer candidateId,
	        JobApplicationStatus status) {

	    return (root, query, criteriaBuilder) -> {

	        List<Predicate> predicates = new ArrayList<>();

	        // Filter by logged-in candidate
	        predicates.add(
	                criteriaBuilder.equal(
	                        root.get("candidate").get("candidateId"),
	                        candidateId
	                )
	        );

	        // Filter by application status, if provided
	        if (status != null) {
	            predicates.add(
	                    criteriaBuilder.equal(root.get("status"), status)
	            );
	        }

	        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	    };
	}
}