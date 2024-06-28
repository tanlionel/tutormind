package com.exe212.tutormind.controller;

import com.exe212.tutormind.service.service_interface.StatisticService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/statistic")
@AllArgsConstructor
@CrossOrigin("*")
public class StatisticController {
    @Autowired
    StatisticService statisticService;

    @GetMapping
    public ResponseEntity<?> getStatistic() {
        int month = LocalDate.now().getMonthValue();

        return ResponseEntity.ok(
                statisticService.getStatistic(month)
        );
    }

}
