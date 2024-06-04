package com.exe212.tutormind.model.conversation;

import com.exe212.tutormind.entity.ConversationStatus;
import com.exe212.tutormind.entity.User;
import com.google.api.client.util.DateTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Date;

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

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private ConversationStatus conversationStatus;
}
