package com.carpediem.skeleton.model.dto;

import com.carpediem.skeleton.model.enumaration.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String userName;

    @Email
    private String email;

    @NotNull
    private LocalDate birthDate;

    private StatusEnum status;
}
