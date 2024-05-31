package com.exe212.tutormind.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequestDTO {
    private String email;
    private String username;
    private String password;
    private String fullName;
    private String phone;
    private String address;
    private Integer gender;
    private Integer roleId;
    private String avatar;
}
