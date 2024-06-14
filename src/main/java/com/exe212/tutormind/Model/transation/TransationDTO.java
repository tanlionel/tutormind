package com.exe212.tutormind.model.transation;

import com.exe212.tutormind.common.Common;
import com.exe212.tutormind.entity.User;
import com.exe212.tutormind.model.DTO.UserResponseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransationDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = Common.DATETIME_FORMATTER)
    private LocalDateTime createDate;

    private UserResponseDTO createBy;

    private UserResponseDTO owner;

    private UserResponseDTO receiver;

    private Integer entityId;

    private Integer entityType;

    private String description;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private TransactionTypeDTO transactionType;
}
