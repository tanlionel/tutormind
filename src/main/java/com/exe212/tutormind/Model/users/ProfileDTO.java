package com.exe212.tutormind.model.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    private String personalIntroduction;

    private String personalInformation;

    @JsonIgnore
    private Double ratingPoint;
}
