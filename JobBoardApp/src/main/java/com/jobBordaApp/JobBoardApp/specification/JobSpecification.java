package com.jobBordaApp.JobBoardApp.specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

import com.jobBordaApp.JobBoardApp.entity.Job;
import com.jobBordaApp.JobBoardApp.enums.WorkMode;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class JobSpecification {
	
	public static Specification<Job> getJobSpecification (String jobTitle, String jobLocation, String employerName,
			Integer minExperience, Integer maxExperience, WorkMode workMode, Integer minSalary, Integer maxSalary,
			String employmentType, String industryType, LocalDate datePosted) {
		return new Specification<Job>() {
			

				@Override
				public @Nullable Predicate toPredicate(
				        Root<Job> root,
				        CriteriaQuery<?> query,
				        CriteriaBuilder criteriaBuilder) {
				
				    // TODO Auto-generated method stub
				//  if(search==null || search.isEmpty()) {
				//      criteriaBuilder.conjunction();
				//  }
				
				    List<Predicate> list = new ArrayList<>();
				
				    if(employerName != null && !employerName.isEmpty()) {
				        list.add(criteriaBuilder.equal(
				                root.get("employer").get("employeerName"),
				                employerName));
				//                              OR
				//      list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
				    }
				
				    if(jobTitle != null && !jobTitle.isEmpty()) {
				        list.add(criteriaBuilder.equal(root.get("jobTitle"), jobTitle));
				//                              OR
				//      list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
				    }
				
				    if(jobLocation != null && !jobLocation.isEmpty()) {
				        list.add(criteriaBuilder.equal(root.get("jobLocation"), jobLocation));
				//                              OR
				//      list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
				    }
				
				    if(minExperience != null) {
				        list.add(criteriaBuilder.greaterThanOrEqualTo(
				                root.get("minExperience"),
				                minExperience));
				//                              OR
				//      list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
				    }
				
				    if(maxExperience != null) {
				        list.add(criteriaBuilder.lessThanOrEqualTo(
				                root.get("maxExperience"),
				                maxExperience));
				//                              OR
				//      list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
				    }
				
				    if(workMode != null) {
				        list.add(criteriaBuilder.equal(
				                root.get("workMode"),
				                workMode));
				//                              OR
				//      list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
				    }
				
				    if(minSalary != null) {
				        list.add(criteriaBuilder.greaterThanOrEqualTo(
				                root.get("minSalary"),
				                minSalary));
				//                              OR
				//      list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
				    }
				
				    if(maxSalary != null) {
				        list.add(criteriaBuilder.lessThanOrEqualTo(
				                root.get("maxSalary"),
				                maxSalary));
				//                              OR
				//      list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
				    }
				
				    if(employmentType != null && !employmentType.isEmpty()) {
				        list.add(criteriaBuilder.equal(
				                root.get("employmentType"),
				                employmentType));
				//                              OR
				//      list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
				    }
				
				    if(industryType != null && !industryType.isEmpty()) {
				        list.add(criteriaBuilder.equal(
				                root.get("industryType"),
				                industryType));
				//                              OR
				//      list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
				    }
				
				    if(datePosted != null) {
				        list.add(criteriaBuilder.greaterThanOrEqualTo(
				                root.get("datePosted"),
				                datePosted.atStartOfDay()));
				//                              OR
				//      list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
				    }
				
				//  list.add(criteriaBuilder.equal(root.get("jobTitle"),search));
				//  list.add(criteriaBuilder.equal(root.get("status"),search));
				
				//  list.add(criteriaBuilder.equal(root.get("skills"),search));//It is no used right now but use it letter
				
				//  It is used when result should be get on sub string like jav= java developer
				//  list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
				//  list.add(criteriaBuilder.like(root.get("status"),"%"+ search +"%"));
				
				    return criteriaBuilder.and(list.toArray(new Predicate[0]));
				
				//  complete till 10:00;
				}
			
		};
		
	}



}

//
//@Override
//public @Nullable Predicate toPredicate(
//        Root<Job> root,
//        CriteriaQuery<?> query,
//        CriteriaBuilder criteriaBuilder) {
//
//    // TODO Auto-generated method stub
////  if(search==null || search.isEmpty()) {
////      criteriaBuilder.conjunction();
////  }
//
//    List<Predicate> list = new ArrayList<>();
//
//    if(employerName != null && !employerName.isEmpty()) {
//        list.add(criteriaBuilder.equal(
//                root.get("employer").get("employeerName"),
//                employerName));
////                              OR
////      list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
//    }
//
//    if(jobTitle != null && !jobTitle.isEmpty()) {
//        list.add(criteriaBuilder.equal(root.get("jobTitle"), jobTitle));
////                              OR
////      list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
//    }
//
//    if(jobLocation != null && !jobLocation.isEmpty()) {
//        list.add(criteriaBuilder.equal(root.get("jobLocation"), jobLocation));
////                              OR
////      list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
//    }
//
//    if(minExperience != null) {
//        list.add(criteriaBuilder.greaterThanOrEqualTo(
//                root.get("minExperience"),
//                minExperience));
////                              OR
////      list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
//    }
//
//    if(maxExperience != null) {
//        list.add(criteriaBuilder.lessThanOrEqualTo(
//                root.get("maxExperience"),
//                maxExperience));
////                              OR
////      list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
//    }
//
//    if(workMode != null) {
//        list.add(criteriaBuilder.equal(
//                root.get("workMode"),
//                workMode));
////                              OR
////      list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
//    }
//
//    if(minSalary != null) {
//        list.add(criteriaBuilder.greaterThanOrEqualTo(
//                root.get("minSalary"),
//                minSalary));
////                              OR
////      list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
//    }
//
//    if(maxSalary != null) {
//        list.add(criteriaBuilder.lessThanOrEqualTo(
//                root.get("maxSalary"),
//                maxSalary));
////                              OR
////      list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
//    }
//
//    if(employmentType != null && !employmentType.isEmpty()) {
//        list.add(criteriaBuilder.equal(
//                root.get("employmentType"),
//                employmentType));
////                              OR
////      list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
//    }
//
//    if(industryType != null && !industryType.isEmpty()) {
//        list.add(criteriaBuilder.equal(
//                root.get("industryType"),
//                industryType));
////                              OR
////      list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
//    }
//
//    if(datePosted != null) {
//        list.add(criteriaBuilder.greaterThanOrEqualTo(
//                root.get("datePosted"),
//                datePosted.atStartOfDay()));
////                              OR
////      list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
//    }
//
////  list.add(criteriaBuilder.equal(root.get("jobTitle"),search));
////  list.add(criteriaBuilder.equal(root.get("status"),search));
//
////  list.add(criteriaBuilder.equal(root.get("skills"),search));//It is no used right now but use it letter
//
////  It is used when result should be get on sub string like jav= java developer
////  list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
////  list.add(criteriaBuilder.like(root.get("status"),"%"+ search +"%"));
//
//    return criteriaBuilder.and(list.toArray(new Predicate[0]));
//
////  complete till 10:00;
//}


















//
//
//public class JobSpecification {
//	
//	public static Specification<Job> getJobSpecificationString (String jobTitle,
//															    String jobLocation,
//															    String employerName,
//															    Integer minExperience,
//															    Integer maxExperience,
//															    WorkMode workMode,
//															    Integer minSalary,
//															    Integer maxSalary,
//															    String employmentType,
//															    String industryType,
//															    LocalDate datePosted) {
//		return new Specification<Job>() {
//			
//			
//			@Override
//			public @Nullable Predicate toPredicate(Root<Job> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//				// TODO Auto-generated method stub
////				if(search==null || search.isEmpty()) {
////					criteriaBuilder.conjunction();
////				}
//				
//				List<Predicate> list = new ArrayList<>();
//				
//				if(employer !=null || employer.isEmpty()) {
//					list.add(criteriaBuilder.equal(root.get("employer"),employer));
////									OR
////					list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
//				}
////				if(jobId==null ) {
////					list.add(criteriaBuilder.equal(root.get("jobId"),jobId));
//////									OR
//////					list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
////				}
//				if(jobTitle!=null || !jobTitle.isEmpty()) {
//					list.add(criteriaBuilder.equal(root.get("jobTitle"),jobTitle));
////									OR
////					list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
//				}
//				if(status==null || status.isEmpty()) {
//					list.add(criteriaBuilder.equal(root.get("status"),status));
////									OR
////					list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
//				}
//				if(createDate==null || createDate.isEmpty()) {
//					list.add(criteriaBuilder.equal(root.get("createDate"),createDate));
////									OR
////					list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
//				}
//				
//				
////				list.add(criteriaBuilder.equal(root.get("jobTitle"),search));
////				list.add(criteriaBuilder.equal(root.get("status"),search));
//				
//				
////				list.add(criteriaBuilder.equal(root.get("skills"),search));//It is no used right now but use it letter
//				
//				
////				It is used when result should be get on sub string like jav= java developer
////				list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
////				list.add(criteriaBuilder.like(root.get("status"),"%"+ search +"%"));
////				
//				
//				return criteriaBuilder.or(list.toArray(new Predicate[0]));
//				
////				complete till 10:00;
//			}
//			
//		};
//		
//	}
//
//}
//
