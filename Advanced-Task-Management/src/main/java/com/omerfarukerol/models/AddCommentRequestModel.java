package com.omerfarukerol.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCommentRequestModel {
    @NotNull(message = "Task ID cannot be null")
    private Long taskId;
    
    @NotBlank(message = "Comment content cannot be empty")
    private String content;
} 