package com.exe212.tutormind.Model.DTO;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseObjectDTO {
    private String accessToken;
    private String refreshToken;
}
