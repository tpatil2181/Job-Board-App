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
//import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jobBordaApp.JobBoardApp.entity.Candidate;
import com.jobBordaApp.JobBoardApp.entity.CandidateImage;
import com.jobBordaApp.JobBoardApp.repository.CandidateImageRepo;
import com.jobBordaApp.JobBoardApp.repository.CandidateRepo;

//import jakarta.annotation.Resource;





//public class CandidateImageService {
//
//}

@Service
public class CandidateImageService {

    @Value("${file.candidate-image-dir}")
    private String uploadDir;

    @Autowired
    private CandidateRepo candidateRepo;

    @Autowired
    private CandidateImageRepo candidateImageRepo;


    // =======================================
    // Upload / Update Candidate Image
    // =======================================

    public int uploadOrUpdateImage(
            Integer candidateId,
            MultipartFile file) throws IOException {

        int imageId = -1;

        // 1. Get candidate
        Candidate candidate = candidateRepo.findById(candidateId)
                .orElseThrow(() ->
                        new RuntimeException("Candidate not found"));


        // 2. Check existing image
        Optional<CandidateImage> existingImageOpt =
                candidateImageRepo.findByCandidate(candidate);


        // 3. Create directory
        File dir = new File(uploadDir);

        if (!dir.exists()) {
            dir.mkdirs();
        }


        // 4. Create new filename
        String fileName =
                System.currentTimeMillis()
                + "_" + file.getOriginalFilename();

        String filePath =
                uploadDir + File.separator + fileName;


        // 5. If image already exists
        if (existingImageOpt.isPresent()) {

            CandidateImage existingImage =
                    existingImageOpt.get();

            // Delete old image
            File oldFile =
                    new File(existingImage.getPath());

            if (oldFile.exists()) {
                oldFile.delete();
            }

            // Update path
            existingImage.setPath(filePath);

            candidateImageRepo.save(existingImage);

            imageId = existingImage.getImageId();

        } else {

            // 6. Create new database entry
            CandidateImage newImage =  new CandidateImage(candidate, filePath);

            candidateImageRepo.save(newImage);

            imageId = newImage.getImageId();
        }


        // 7. Save physical file
        file.transferTo(new File(filePath));

        return imageId;
    }


    // =======================================
    // Get Candidate Image
    // =======================================
    
    public ResponseEntity<Resource> getCandidateImage(Integer imageId) throws IOException {

        CandidateImage image = candidateImageRepo.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Candidate image not found"));

        File file = new File(image.getPath());

        if (!file.exists()) {
            throw new RuntimeException("Image file not found");
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

//    public ResponseEntity<Resource> getCandidateImage(
//            Integer imageId) throws IOException {
//
//        CandidateImage image =
//                candidateImageRepo.findById(imageId)
//                .orElseThrow(() ->
//                        new RuntimeException("Image not found"));
//
//
//        File file = new File(image.getPath());
//
//        if (!file.exists()) {
//            throw new RuntimeException("Image file not found");
//        }
//
//
//        Resource resource = (Resource) new UrlResource(file.toURI());
//
//
//        return ResponseEntity.ok()
//                .header(
//                    "Content-Disposition",
//                    "inline; filename=" + file.getName()
//                )
//                .contentType(
//                    MediaTypeFactory
//                        .getMediaType(file.getName())
//                        .orElse(MediaType.APPLICATION_OCTET_STREAM)
//                )
//                .body(resource);
//    }
}