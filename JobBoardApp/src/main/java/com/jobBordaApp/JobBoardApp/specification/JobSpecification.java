package com.jobBordaApp.JobBoardApp.specification;

import java.util.ArrayList;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

import com.jobBordaApp.JobBoardApp.entity.Job;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class JobSpecification {
	
	public static Specification<Job> getJobSpecification(Integer jobId,String employer,String jobTitle,String status, String createDate) {
		return new Specification<Job>() {
			
			
			@Override
			public @Nullable Predicate toPredicate(Root<Job> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
//				if(search==null || search.isEmpty()) {
//					criteriaBuilder.conjunction();
//				}
				
				List<Predicate> list = new ArrayList<>();
				
				if(employer==null || employer.isEmpty()) {
					list.add(criteriaBuilder.equal(root.get("employer"),employer));
//									OR
//					list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
				}
				if(jobId==null ) {
					list.add(criteriaBuilder.equal(root.get("jobId"),jobId));
//									OR
//					list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
				}
				if(jobTitle==null || jobTitle.isEmpty()) {
					list.add(criteriaBuilder.equal(root.get("jobTitle"),jobTitle));
//									OR
//					list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
				}
				if(status==null || status.isEmpty()) {
					list.add(criteriaBuilder.equal(root.get("status"),status));
//									OR
//					list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
				}
				if(createDate==null || createDate.isEmpty()) {
					list.add(criteriaBuilder.equal(root.get("createDate"),createDate));
//									OR
//					list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
				}
				
				
//				list.add(criteriaBuilder.equal(root.get("jobTitle"),search));
//				list.add(criteriaBuilder.equal(root.get("status"),search));
				
				
//				list.add(criteriaBuilder.equal(root.get("skills"),search));//It is no used right now but use it letter
				
				
//				It is used when result should be get on sub string like jav= java developer
//				list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
//				list.add(criteriaBuilder.like(root.get("status"),"%"+ search +"%"));
//				
				
				return criteriaBuilder.or(list.toArray(new Predicate[0]));
				
//				complete till 10:00;
			}
			
		};
		
	}

}
