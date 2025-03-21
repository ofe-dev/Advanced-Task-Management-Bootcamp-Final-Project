package com.omerfarukerol.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCommentResponseModel {
    private Long id;
    private String content;
    private UserDTO user;
    private Long taskId;
} 