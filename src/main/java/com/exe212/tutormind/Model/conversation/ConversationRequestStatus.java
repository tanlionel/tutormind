package com.exe212.tutormind.model.conversation;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConversationRequestStatus {
    private Integer statusId;
    private String remark;
}
