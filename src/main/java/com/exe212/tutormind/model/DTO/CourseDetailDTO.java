package com.exe212.tutormind.model.DTO;

import com.exe212.tutormind.entity.Lessons;
import com.exe212.tutormind.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDetailDTO {
    private Integer id;
    private String title;
    private String description;
    private String simpleDescription;
    private User tutor;
    private BigDecimal price;
    private List<Lessons> lessonsList;
    private boolean isEnroll;
}
