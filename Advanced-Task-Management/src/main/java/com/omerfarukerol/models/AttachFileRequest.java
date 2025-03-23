package com.omerfarukerol.models;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttachFileRequest {
    
    @NotNull(message = "Task ID cannot be null")
    private Long taskId;
    
    @NotNull(message = "File cannot be null")
    private MultipartFile file;
} 