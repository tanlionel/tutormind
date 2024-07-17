package com.exe212.tutormind.service.service_interface;

import com.exe212.tutormind.model.statistic.StatisticDTO;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

public interface StatisticService {
    public StatisticDTO getStatistic();

    public Long getRevenueByRange(LocalDate startDate,
                             LocalDate endDate);
}
