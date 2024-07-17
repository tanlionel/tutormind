package com.exe212.tutormind.model.statistic;

import com.exe212.tutormind.common.Common;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeeklyStatistic {
    private String dayOfWeek;
    @JsonFormat(pattern = Common.DATE_FORMATTER)
    private LocalDate date;
    private Long approvedRequestAmount;
    private Long rejectedRequestAmount;
    private Long otherRequestAmount;
    private Long revenue;
}
