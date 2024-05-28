package com.exe212.tutormind.Model.Mapper;

import com.exe212.tutormind.Model.DTO.UserResponseDTO;
import com.exe212.tutormind.entity.User;

public class UserMapper {
    public static final UserResponseDTO mapToUserResponseDTO(User user){
        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .gender(user.getGender())
                .roleName(user.getRole().getName())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .address(user.getAddress())
                .phone(user.getPhone())
                .build();
    }
}
