package com.exe212.tutormind.Model.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String email;
    private String username;
    private String password;
    private String fullName;
    private String phone;
    private String address;
    private Integer gender;
    private Integer roleId;
}
