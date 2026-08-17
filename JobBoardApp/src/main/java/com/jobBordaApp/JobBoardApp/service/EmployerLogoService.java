package com.jobBordaApp.JobBoardApp.service;


import java.io.File;
import java.io.IOException;
import java.util.Optional;

import java.nio.file.Files;

import org.springframework.core.io.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jobBordaApp.JobBoardApp.entity.Candidate;
import com.jobBordaApp.JobBoardApp.entity.CandidateImage;
import com.jobBordaApp.JobBoardApp.entity.Employeer;
import com.jobBordaApp.JobBoardApp.entity.EmployerLogo;
import com.jobBordaApp.JobBoardApp.repository.CandidateImageRepo;
import com.jobBordaApp.JobBoardApp.repository.CandidateRepo;
import com.jobBordaApp.JobBoardApp.repository.EmployeerRepo;
import com.jobBordaApp.JobBoardApp.repository.EmployerLogoRepo;

//public class EmployerLogoService {
//
//}


@Service
public class EmployerLogoService {

    @Value("${file.employer-logo-dir}")
    private String uploadDir;

    @Autowired
    private EmployeerRepo employerRepo;

    @Autowired
    private EmployerLogoRepo employerLogoRepo;


    // =======================================
    // Upload / Update Employer Logo
    // =======================================

    public int uploadOrUpdateLogo(
            Integer employerId,
            MultipartFile file) throws IOException {

        int logoId = -1;


        // 1. Get employer
        Employeer employer =
                employerRepo.findById(employerId)
                .orElseThrow(() ->
                        new RuntimeException("Employer not found"));


        // 2. Check existing logo
        Optional<EmployerLogo> existingLogoOpt =
                employerLogoRepo.findByEmployer(employer);


        // 3. Create directory
        File dir = new File(uploadDir);

        if (!dir.exists()) {
            dir.mkdirs();
        }


        // 4. New filename
        String fileName =
                System.currentTimeMillis()
                + "_" + file.getOriginalFilename();

        String filePath =
                uploadDir + File.separator + fileName;


        // 5. Update existing logo
        if (existingLogoOpt.isPresent()) {

            EmployerLogo existingLogo =
                    existingLogoOpt.get();

            File oldFile =
                    new File(existingLogo.getPath());

            if (oldFile.exists()) {
                oldFile.delete();
            }

            existingLogo.setPath(filePath);

            employerLogoRepo.save(existingLogo);

            logoId = existingLogo.getLogoId();

        } else {

            // 6. Create new logo
            EmployerLogo newLogo =
                    new EmployerLogo(
                            employer,
                            filePath
                    );

            employerLogoRepo.save(newLogo);

            logoId = newLogo.getLogoId();
        }


        // 7. Save file
        file.transferTo(new File(filePath));

        return logoId;
    }


    // =======================================
    // Get Employer Logo
    // =======================================
    
    public ResponseEntity<Resource> getEmployerLogo(Integer logoId) throws IOException {

        EmployerLogo logo = employerLogoRepo.findById(logoId)
                .orElseThrow(() -> new RuntimeException("Employer logo not found"));

        File file = new File(logo.getPath());

        if (!file.exists()) {
            throw new RuntimeException("Employer logo file not found");
        }

        Resource resource = new UrlResource(file.toURI());

        String contentType = Files.probeContentType(file.toPath());

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .header(
                    "Content-Disposition",
                    "inline; filename=\"" + file.getName() + "\""
                )
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
}