package com.exe212.tutormind.model.DTO;

import com.exe212.tutormind.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private Integer id;
    private String title;
    private String description;
    private String simpleDescription;
    private Integer tutorId;
    private BigDecimal price;
    private String image;
}
