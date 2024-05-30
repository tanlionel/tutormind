package com.exe212.tutormind.model.users;

import com.exe212.tutormind.model.subject.SubjectDTO;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MajorDTO {
    SubjectDTO subject;
    private String majorDescription;
}
