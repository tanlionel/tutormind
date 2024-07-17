package com.exe212.tutormind.model.users;

import com.exe212.tutormind.model.subject.SubjectDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.antlr.v4.runtime.misc.Pair;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    private String personalIntroduction;
    private String personalInformation;
    private Double ratingPoint;
    private List<MajorDTO> majorList;
}
