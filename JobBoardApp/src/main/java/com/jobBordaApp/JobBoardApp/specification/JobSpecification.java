package com.jobBordaApp.JobBoardApp.specification;

import java.util.ArrayList;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

import com.jobBordaApp.JobBoardApp.dto.ExperienceFilterDTO;
import com.jobBordaApp.JobBoardApp.dto.JobSearchDTO;
import com.jobBordaApp.JobBoardApp.entity.Job;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class JobSpecification {

    public static Specification<Job> getJobSpecification(JobSearchDTO jobSearchDTO) {

        return new Specification<Job>() {

            @Override
            public @Nullable Predicate toPredicate(
                    Root<Job> root,
                    CriteriaQuery<?> query,
                    CriteriaBuilder criteriaBuilder) {

                // TODO Auto-generated method stub
                // if(search==null || search.isEmpty()) {
                //     criteriaBuilder.conjunction();
                // }

                List<Predicate> list = new ArrayList<>();

                if (jobSearchDTO.getEmployerName() != null
                        && !jobSearchDTO.getEmployerName().isEmpty()) {

                    list.add(criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("employer").get("employeerName")),
                            "%" + jobSearchDTO.getEmployerName().toLowerCase() + "%"));

                    // OR
                    // list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search + "%"));
                }

                if (jobSearchDTO.getJobTitle() != null
                        && !jobSearchDTO.getJobTitle().isEmpty()) {

                    list.add(criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("jobTitle")),
                            "%" + jobSearchDTO.getJobTitle().toLowerCase() + "%"));

                    // OR
                    // list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search + "%"));
                }

                if (jobSearchDTO.getJobLocation() != null
                        && !jobSearchDTO.getJobLocation().isEmpty()) {

                    list.add(criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("jobLocation")),
                            "%" + jobSearchDTO.getJobLocation().toLowerCase() + "%"));

                    // OR
                    // list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search + "%"));
                }

                // Experience Filter
                if (jobSearchDTO.getExperiences() != null
                        && !jobSearchDTO.getExperiences().isEmpty()) {

                    List<Predicate> experiencePredicates = new ArrayList<>();

                    for (ExperienceFilterDTO exp : jobSearchDTO.getExperiences()) {

                        Predicate predicate = criteriaBuilder.and(

                                criteriaBuilder.lessThanOrEqualTo(
                                        root.get("minExperience"),
                                        exp.getMaxExperience()),

                                criteriaBuilder.greaterThanOrEqualTo(
                                        root.get("maxExperience"),
                                        exp.getMinExperience()));

                        experiencePredicates.add(predicate);
                    }

                    list.add(criteriaBuilder.or(
                            experiencePredicates.toArray(new Predicate[0])));
                }

                // multivalue search
                if (jobSearchDTO.getWorkModes() != null
                        && !jobSearchDTO.getWorkModes().isEmpty()) {

                    list.add(root.get("workMode")
                            .in(jobSearchDTO.getWorkModes()));
                }

                if (jobSearchDTO.getMinSalary() != null) {

                    list.add(criteriaBuilder.greaterThanOrEqualTo(
                            root.get("minSalary"),
                            jobSearchDTO.getMinSalary()));

                    // OR
                    // list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search + "%"));
                }

                if (jobSearchDTO.getMaxSalary() != null) {

                    list.add(criteriaBuilder.lessThanOrEqualTo(
                            root.get("maxSalary"),
                            jobSearchDTO.getMaxSalary()));

                    // OR
                    // list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search + "%"));
                }

                // with this we can accepts multiple values of same type
                if (jobSearchDTO.getEmploymentTypes() != null
                        && !jobSearchDTO.getEmploymentTypes().isEmpty()) {

                    list.add(root.get("employmentType")
                            .in(jobSearchDTO.getEmploymentTypes()));
                }

                // multivalue search
                if (jobSearchDTO.getIndustryTypes() != null
                        && !jobSearchDTO.getIndustryTypes().isEmpty()) {

                    list.add(root.get("industryType")
                            .in(jobSearchDTO.getIndustryTypes()));
                }

                if (jobSearchDTO.getDatePosted() != null) {

                    list.add(criteriaBuilder.greaterThanOrEqualTo(
                            root.get("datePosted"),
                            jobSearchDTO.getDatePosted().atStartOfDay()));

                    // OR
                    // list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search + "%"));
                }

                // list.add(criteriaBuilder.equal(root.get("jobTitle"),search));
                // list.add(criteriaBuilder.equal(root.get("status"),search));

                // list.add(criteriaBuilder.equal(root.get("skills"),search));

                // It is used when result should be get on sub string like jav=java developer
                // list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search + "%"));
                // list.add(criteriaBuilder.like(root.get("status"),"%" + search + "%"));

                return criteriaBuilder.and(list.toArray(new Predicate[0]));

                // complete till 10:00;
            }

        };

    }

}



//Old Code
//package com.jobBordaApp.JobBoardApp.specification;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.jspecify.annotations.Nullable;
//import org.springframework.data.jpa.domain.Specification;
//
//import com.jobBordaApp.JobBoardApp.entity.Job;
//import com.jobBordaApp.JobBoardApp.enums.WorkMode;
//
//import jakarta.persistence.criteria.CriteriaBuilder;
//import jakarta.persistence.criteria.CriteriaQuery;
//import jakarta.persistence.criteria.Predicate;
//import jakarta.persistence.criteria.Root;
//
//public class JobSpecification {
//	
//	public static Specification<Job> getJobSpecification (String jobTitle, String jobLocation, String employerName,
//			Integer minExperience, Integer maxExperience, List<WorkMode> workModes, Integer minSalary, Integer maxSalary,
//			List<String> employmentTypes, List<String> industryTypes, LocalDate datePosted) {
//		return new Specification<Job>() {
//			
//
//				@Override
//				public @Nullable Predicate toPredicate(
//				        Root<Job> root,
//				        CriteriaQuery<?> query,
//				        CriteriaBuilder criteriaBuilder) {
//				
//				    // TODO Auto-generated method stub
//				//  if(search==null || search.isEmpty()) {
//				//      criteriaBuilder.conjunction();
//				//  }
//				
//				    List<Predicate> list = new ArrayList<>();
//				
//				    if(employerName != null && !employerName.isEmpty()) {
//				        list.add(criteriaBuilder.like(
//				                criteriaBuilder.lower(root.get("employer").get("employeerName")),
//				                "%" + employerName.toLowerCase() + "%"));
////				                                  OR
////				          list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
//				    }
//
//				    if(jobTitle != null && !jobTitle.isEmpty()) {
//				        list.add(criteriaBuilder.like(
//				                criteriaBuilder.lower(root.get("jobTitle")),
//				                "%" + jobTitle.toLowerCase() + "%"));
////				                                  OR
////				          list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
//				    }
//
//				    if(jobLocation != null && !jobLocation.isEmpty()) {
//				        list.add(criteriaBuilder.like(
//				                criteriaBuilder.lower(root.get("jobLocation")),
//				                "%" + jobLocation.toLowerCase() + "%"));
////				                                  OR
////				          list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
//				    }
//
//				    if(minExperience != null) {
//				        list.add(criteriaBuilder.greaterThanOrEqualTo(
//				                root.get("minExperience"),
//				                minExperience));
////				                                  OR
////				          list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
//				    }
//
//				    if(maxExperience != null) {
//				        list.add(criteriaBuilder.lessThanOrEqualTo(
//				                root.get("maxExperience"),
//				                maxExperience));
////				                                  OR
////				          list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
//				    }
//				    
//				    
//				    //multivalue search
//				    if (workModes != null && !workModes.isEmpty()) {
//				        list.add(root.get("workMode").in(workModes));
//				    }
////				    if(workMode != null) {
////				        list.add(criteriaBuilder.equal(
////				                root.get("workMode"),
////				                workMode));
////				                                  OR
////				          list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
////				    }
//
//				    if(minSalary != null) {
//				        list.add(criteriaBuilder.greaterThanOrEqualTo(
//				                root.get("minSalary"),
//				                minSalary));
////				                                  OR
////				          list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
//				    }
//
//				    if(maxSalary != null) {
//				        list.add(criteriaBuilder.lessThanOrEqualTo(
//				                root.get("maxSalary"),
//				                maxSalary));
////				                                  OR
////				          list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
//				    }
//
//				    
////				    with this we can accepes multipal values of same type
//				    if (employmentTypes != null && !employmentTypes.isEmpty()) {
//				        list.add(root.get("employmentType").in(employmentTypes));
//				    }
////				    for single value
////				    if(employmentType != null && !employmentType.isEmpty()) {
////				        list.add(criteriaBuilder.like(
////				                criteriaBuilder.lower(root.get("employmentType")),
////				                "%" + employmentType.toLowerCase() + "%"));
//////				                                  OR
////				          list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
////				    }
//
////				  multivalue search  
//				    if (industryTypes != null && !industryTypes.isEmpty()) {
//				        list.add(root.get("employmentType").in(industryTypes));
//				    }
////				    if(industryType != null && !industryType.isEmpty()) {
////				        list.add(criteriaBuilder.like(
////				                criteriaBuilder.lower(root.get("industryType")),
////				                "%" + industryType.toLowerCase() + "%"));
////				                                  OR
////				          list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
////				    }
//
//				    if(datePosted != null) {
//				        list.add(criteriaBuilder.greaterThanOrEqualTo(
//				                root.get("datePosted"),
//				                datePosted.atStartOfDay()));
////				                                  OR
////				          list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
//				    }
//				
//				//  list.add(criteriaBuilder.equal(root.get("jobTitle"),search));
//				//  list.add(criteriaBuilder.equal(root.get("status"),search));
//				
//				//  list.add(criteriaBuilder.equal(root.get("skills"),search));//It is no used right now but use it letter
//				
//				//  It is used when result should be get on sub string like jav= java developer
//				//  list.add(criteriaBuilder.like(root.get("jobTitle"),"%" + search+ "%"));
//				//  list.add(criteriaBuilder.like(root.get("status"),"%"+ search +"%"));
//				
//				    return criteriaBuilder.and(list.toArray(new Predicate[0]));
//				
//				//  complete till 10:00;
//				}
//			
//		};
//		
//	}
//
//
//
//}
