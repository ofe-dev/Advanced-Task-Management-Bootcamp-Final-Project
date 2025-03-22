package com.omerfarukerol.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequestModel {
    
    @NotBlank(message = "Old password cannot be empty")
    private String oldPassword;
    
    @NotBlank(message = "New password cannot be empty")
    private String newPassword;
    
    @NotBlank(message = "New password confirmation cannot be empty")
    private String newPasswordConfirmation;
} 