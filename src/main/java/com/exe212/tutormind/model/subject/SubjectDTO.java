package com.exe212.tutormind.model.subject;

import com.exe212.tutormind.entity.SubjectCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {
    private Integer id;

    private SubjectCategory subjectCategory;

    private String name;

    private String description;
}
