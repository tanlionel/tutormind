package com.exe212.tutormind.model.schedule;

import com.exe212.tutormind.model.conversation.ConversationDTO;
import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConversationSlotDTO {
    private Integer slot;
    private List<ConversationResponseDTO> conversationList;
}
