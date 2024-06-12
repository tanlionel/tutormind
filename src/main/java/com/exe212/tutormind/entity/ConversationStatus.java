package com.exe212.tutormind.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Entity
@Table(name = "conversation_status", schema = "mydb")
public class ConversationStatus {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 250)
    @Column(name = "label", length = 250)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String label;

    @Column(name = "is_active")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean isActive;

}