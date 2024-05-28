package com.exe212.tutormind.model.users;

import com.exe212.tutormind.entity.Role;
import com.exe212.tutormind.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TutorDTO {
    private Integer id;

    private String email;

    private String username;

    private String fullName;

    private String phone;

    private String address;

    private Integer gender;

    private Role role;

    private String personalIntroduction;

    private String personalInformation;

    private Double ratingPoint;
}
