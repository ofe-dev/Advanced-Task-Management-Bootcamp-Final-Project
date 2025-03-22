package com.omerfarukerol.models;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCommentRequestModel {
    @NotNull(message = "Comment ID cannot be null")
    private Long commentId;
} 