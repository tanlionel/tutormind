package com.exe212.tutormind.entity;

import com.exe212.tutormind.common.Common;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.checker.units.qual.C;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction", schema = "mydb")
public class Transaction {
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

    @Column(name = "entity_id")
    private Integer entityId;

    @Column(name = "entity_type")
    private Integer entityType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "transation_type")
    private Integer transation_type;
}
