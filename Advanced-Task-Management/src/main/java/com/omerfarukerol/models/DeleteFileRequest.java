package com.omerfarukerol.models;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteFileRequest {
    
    @NotNull(message = "Attachment ID cannot be null")
    private Long attachmentId;
} 