package com.exe212.tutormind.model.profile;

import com.exe212.tutormind.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ProfileDTO {
    private Integer id;

    private User user;

    private String personalIntroduction;

    private String personalInformation;

    private Double ratingPoint;
}
