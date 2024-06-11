package com.exe212.tutormind.controller;

import com.exe212.tutormind.model.schedule.ScheduleDTO;
import com.exe212.tutormind.service.service_interface.ScheduleService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/schedule")
@AllArgsConstructor
@CrossOrigin("*")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;
    @GetMapping("/{teacher_email}")
    public ResponseEntity<List<ScheduleDTO> > getScheduleByTeacherEmail(
            @PathVariable("teacher_email") String email,
            @PathParam("startDate") LocalDate startDate,
            @PathParam("endDate")LocalDate endDate) {

        return ResponseEntity.ok(
                scheduleService.getScheduleList(
                        startDate, endDate, email
                )
        );

    }
}
