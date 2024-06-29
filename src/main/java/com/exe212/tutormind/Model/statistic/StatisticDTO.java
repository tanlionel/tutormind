package com.exe212.tutormind.model.statistic;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticDTO {
    private Integer studentAmount;
    private Integer tutorAmount;
    private Long approvedRequestAmount;
    private Long rejectedRequestAmount;
    private Long otherRequestAmount;
    private Long revenue;
}
