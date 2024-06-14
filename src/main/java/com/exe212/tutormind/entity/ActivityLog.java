package com.exe212.tutormind.entity;

import com.exe212.tutormind.common.Common;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "activity_log", schema = "mydb")
public class ActivityLog {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "create_date")
    @JsonFormat(pattern = Common.DATETIME_FORMATTER)
    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "create_by")
    private User createBy;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "activity_type")
    private Integer activityType;
}
