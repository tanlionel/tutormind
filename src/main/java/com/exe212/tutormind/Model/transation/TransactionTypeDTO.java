package com.exe212.tutormind.model.transation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionTypeDTO {
    private Integer id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String label;
}
