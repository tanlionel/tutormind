package com.exe212.tutormind.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.api.client.util.DateTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "conversation", schema = "mydb")
public class Conversation {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 250)
    @Column(name = "title", length = 250)
    private String title;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "teacher_id", nullable = false)
    private User teacher;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "address", length = 1000)
    private String address;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "schedule", length = 2000)
    private String schedule;

    @Column(name = "contact_number", length = 50)
    private String contactNumber;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "conversation_status_id", nullable = false)
    private ConversationStatus conversationStatus;

}