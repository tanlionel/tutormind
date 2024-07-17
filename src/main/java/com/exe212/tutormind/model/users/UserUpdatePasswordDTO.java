package com.exe212.tutormind.model.users;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdatePasswordDTO {
    private String oldPassword;
    private String newPassword;
}
