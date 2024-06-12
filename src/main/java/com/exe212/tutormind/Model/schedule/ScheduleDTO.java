package com.exe212.tutormind.model.schedule;

import com.exe212.tutormind.model.conversation.ConversationDTO;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String dayOfWeek;

    List<ConversationSlotDTO> conversationSlots;
}
