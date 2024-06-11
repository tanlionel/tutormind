package com.exe212.tutormind.model.conversation;

import com.exe212.tutormind.entity.ConversationStatus;
import com.exe212.tutormind.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.api.client.util.DateTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.cglib.core.Local;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConversationDTO {
    private Integer id;

    private String title;

    private User teacher;

    private User user;

    private String description;

    private String address;

    private String contactNumber;

    @JsonFormat(pattern="yyyy-MM-dd")
    private List<LocalDate> schedule;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateFrom;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateTo;

    private List<Integer> dayOfWeek;

    private Integer slot;

    private Integer totalPrice;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedDate;

    private ConversationStatus conversationStatus;
}
