package com.exe212.tutormind.service.service_interface;

import com.exe212.tutormind.Model.statistic.StatisticDTO;

import java.time.LocalDate;

public interface StatisticService {

    public StatisticDTO getStatistic(int month);

}
