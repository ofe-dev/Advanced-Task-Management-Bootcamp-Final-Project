package com.omerfarukerol.service.impl;

import com.omerfarukerol.enums.MessageType;
import com.omerfarukerol.exception.BaseException;
import com.omerfarukerol.exception.ErrorMessage;
import com.omerfarukerol.service.IFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements IFileService {

    @Value("${file.upload.base-dir}")
    private String baseUploadDir;

    @Override
    public String saveFile(MultipartFile file, String directory) {
        try {
            Path uploadPath = Paths.get(baseUploadDir, directory);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFilename = UUID.randomUUID().toString() + fileExtension;

            Path filePath = uploadPath.resolve(uniqueFilename);
            Files.copy(file.getInputStream(), filePath);

            return directory + "/" + uniqueFilename;
        } catch (IOException e) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, 
                "Failed to save file: " + e.getMessage()));
        }
    }

    @Override
    public void deleteFile(String filePath) {
        try {
            Path fullPath = Paths.get(baseUploadDir, filePath);
            Files.deleteIfExists(fullPath);
        } catch (IOException e) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, 
                "Failed to delete file: " + e.getMessage()));
        }
    }
} 