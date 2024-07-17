package com.exe212.tutormind.model.statistic;

import com.exe212.tutormind.common.Common;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticDTO {
    //Monthly statistic
    private Long approvedRequestAmount;
    private Long rejectedRequestAmount;
    private Long otherRequestAmount;
    private Long numberOfCourse;
    private Long revenue;

    List<WeeklyStatistic> weeklyStatisticList;
    private Integer studentAmount;
    private Integer tutorAmount;
}
