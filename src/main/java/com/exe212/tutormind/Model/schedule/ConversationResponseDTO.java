package com.exe212.tutormind.model.schedule;

import com.exe212.tutormind.entity.ConversationStatus;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConversationResponseDTO {
    Integer id;

    String title;

    ConversationStatus status;

    String StudentName;
}
