package com.exe212.tutormind.controller;

import com.exe212.tutormind.service.service_interface.StatisticService;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        return ResponseEntity.ok(
                statisticService.getStatistic()
        );
    }

    @GetMapping("/revenue-by-range")
    public ResponseEntity<Long> getRevenueByRange(@RequestParam @JsonFormat(pattern="yyyy-MM-dd") LocalDate startDate,
                                                  @RequestParam @JsonFormat(pattern="yyyy-MM-dd") LocalDate endDate) {
        return ResponseEntity.ok(
                statisticService.getRevenueByRange(startDate, endDate)
        );
    }

}
