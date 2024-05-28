package com.exe212.tutormind.model.users;

import com.exe212.tutormind.entity.Role;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer id;

    private String email;

    private String username;

    private String fullName;

    private String phone;

    private String address;

    private Integer gender;

    private Role role;

    private ProfileDTO profile;
//    private String personalIntroduction;
//
//    private String personalInformation;
//
//    private Double ratingPoint;
}
