package com.exe212.tutormind.service.service_interface;

import com.exe212.tutormind.model.schedule.ScheduleDTO;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    List<ScheduleDTO> getScheduleList(LocalDate startDate, LocalDate endDate, String email);

}
