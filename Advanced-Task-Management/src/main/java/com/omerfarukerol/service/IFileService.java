package com.omerfarukerol.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    String saveFile(MultipartFile file, String directory);
    void deleteFile(String filePath);
} 