package com.exe212.tutormind.model.users;

import com.exe212.tutormind.entity.Role;
import com.exe212.tutormind.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

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
}
