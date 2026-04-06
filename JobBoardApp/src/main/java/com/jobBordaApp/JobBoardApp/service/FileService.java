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

import com.jobBordaApp.JobBoardApp.entity.User;
import com.jobBordaApp.JobBoardApp.entity.UserResume;
import com.jobBordaApp.JobBoardApp.repository.UserRepo;
import com.jobBordaApp.JobBoardApp.repository.UserResumeReop;



@Service
public class FileService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserResumeReop userResumeRepo;

    public String uploadOrUpdateResume(Integer userId, MultipartFile file) throws IOException {


        // 1. Get user
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Check if resume already exists
        Optional<UserResume> existingResumeOpt = userResumeRepo.findByUser(user);

        // 3. Create directory if not exists
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String filePath = uploadDir + File.separator + fileName;

        // 4. If exists → delete old file
        if (existingResumeOpt.isPresent()) {
            UserResume existingResume = existingResumeOpt.get();

            File oldFile = new File(existingResume.getPath());
            if (oldFile.exists()) {
                oldFile.delete();  // ✅ delete old file
            }

            // update path
            existingResume.setPath(filePath);
            userResumeRepo.save(existingResume);

        } else {
            // 5. If not exists → create new entry
            UserResume newResume = new UserResume(user, filePath);
            userResumeRepo.save(newResume);
        }

        // 6. Save new file
        file.transferTo(new File(filePath));

        return filePath;  // ✅ return path
    }
    
    
//    
//    @GetMapping("/resume/{userId}")
    public ResponseEntity<Resource> getResume(@PathVariable Integer userId) throws IOException {

        UserResume resume = userResumeRepo.findByUser_UserId(userId)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        File file = new File(resume.getPath());

        Resource resource =  new UrlResource(file.toURI());

        return ResponseEntity.ok()
                .header("Content-Disposition", "inline; filename=" + file.getName())
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
    
}





//My Old code


//@Service
//public class FileService {
//	
//	@Autowired
//	private UserResumeReop URRepo;
//	
//	
//	 @Value("${file.upload-dir}")String uploadDir;
//	 public String uploadFile(MultipartFile file,@PathVariable  Integer userId ) throws IOException {
//
//		 
//		 UserResume ur =URRepo.findResumeByUserId(userId);
//		 
//		 if(ur!=null) {
//			 String oldFilePath=newUserResume.getPath();
//			 File oldFile = new File(oldFilePath);
//		        if (oldFile.exists()) {
//		            oldFile.delete();
//		        }
//		        
//		        // file path
//		        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//		        String filePath = uploadDir + File.separator + fileName;
//
//
//		        // save file
//		        file.transferTo(new File(filePath));
//		        ur.setPath(filePath);
//		        URRepo.save(ur);
//		 }
//		 else {
//			 // file path
//		        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//		        String filePath = uploadDir + File.separator + fileName;
//
//
//		        // save file
//		        file.transferTo(new File(filePath));
//		        UserResume newUserResume=new UserResume(userId,, filePath);
//			 URRepo.save(newUserResume);
//		 }
//    	
//    	
//    	
//    	  
//
////        // create folder if not exists
////        File dir = new File(uploadDir);
////        if (!dir.exists()) {
////            dir.mkdirs();
////        }
////
////        // file path
////        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
////        String filePath = uploadDir + File.separator + fileName;
////
////
////        // save file
////        file.transferTo(new File(filePath));
//        
//
////        return filePath;
//    }
//	
//	
//
//}
