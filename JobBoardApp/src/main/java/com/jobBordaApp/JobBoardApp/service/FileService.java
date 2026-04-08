package com.jobBordaApp.JobBoardApp.service;


import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.jobBordaApp.JobBoardApp.entity.Candidate;
import com.jobBordaApp.JobBoardApp.entity.CandidateResume;
import com.jobBordaApp.JobBoardApp.repository.CandidateRepo;
import com.jobBordaApp.JobBoardApp.repository.CandidateResumeReop;



@Service
public class FileService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private CandidateRepo userRepo;

    @Autowired
    private CandidateResumeReop userResumeRepo;
    
    
//=======================================Upload User Resume=======================================\
    
    public int uploadOrUpdateResume(Integer userId, MultipartFile file) throws IOException {
    	
    	
    	int resumeid=-1;


        // 1. Get user
    	Candidate candidate = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Check if resume already exists
        Optional<CandidateResume> existingResumeOpt = userResumeRepo.findByCandidate(candidate);

        // 3. Create directory if not exists
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String filePath = uploadDir + File.separator + fileName;

        // 4. If exists → delete old file
        if (existingResumeOpt.isPresent()) {
            CandidateResume existingResume = existingResumeOpt.get();

            File oldFile = new File(existingResume.getPath());
            if (oldFile.exists()) {
                oldFile.delete();  // ✅ delete old file
            }

            // update path
            existingResume.setPath(filePath);
            userResumeRepo.save(existingResume);
            resumeid= existingResume.getResumeId(); 

        } else {
            // 5. If not exists → create new entry
            CandidateResume newResume = new CandidateResume(candidate, filePath);
            userResumeRepo.save(newResume);
            resumeid= newResume.getResumeId(); 
        }

        // 6. Save new file
        file.transferTo(new File(filePath));
        
        return resumeid;

//        return filePath;  // ✅ return path
    }
    
    
    
    
//=======================================Get User Resume=======================================
    
    public ResponseEntity<Resource> getResume(@PathVariable Integer Candidate_Id) throws IOException {

        CandidateResume resume = userResumeRepo.findByCandidate_CandidateId(Candidate_Id)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        File file = new File(resume.getPath());

        Resource resource =  new UrlResource(file.toURI());

        return ResponseEntity.ok()
                .header("Content-Disposition", "inline; filename=" + file.getName())
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
    
}




