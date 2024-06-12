package com.exe212.tutormind.service.service_implement;

import com.exe212.tutormind.common.Common;
import com.exe212.tutormind.entity.Conversation;
import com.exe212.tutormind.enums.EnumConversationStatus;
import com.exe212.tutormind.model.schedule.ConversationResponseDTO;
import com.exe212.tutormind.model.schedule.ConversationSlotDTO;
import com.exe212.tutormind.model.schedule.ScheduleDTO;
import com.exe212.tutormind.repository.ConversationRepository;
import com.exe212.tutormind.service.service_interface.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImplement implements ScheduleService {
    @Autowired
    private ConversationRepository conversationRepository;

    private int APPROVED_STATUS = EnumConversationStatus.Approved.ordinal() + 1;

    @Override
    public List<ScheduleDTO> getScheduleList(LocalDate startDate,
                                             LocalDate endDate,
                                             String email) {
        //Filter by range
        List<Conversation> conversationList = conversationRepository.findAllByTeacherEmailAndConversationStatusId(
                email,
                APPROVED_STATUS).stream().filter(
                        c -> (
                                (c.getStartDate() != null && c.getEndDate() != null)
                                && (!c.getStartDate().isAfter(startDate) || !c.getEndDate().isBefore(endDate)
                                )
                        )
        ).toList();

        List<ScheduleDTO> result = new ArrayList<ScheduleDTO>();

        LocalDate date = startDate;

        while (!date.isAfter(endDate)) {
            //Add new schedule with date
            ScheduleDTO scheduleTmp = ScheduleDTO.builder()
                    .date(date)
                    .dayOfWeek(date.getDayOfWeek().name())
                    .build();

            for (int slot = 1; slot <= Common.numberOfSlot; ++slot) {
                //get day of week of date
                int dayOfWeek = date.getDayOfWeek().getValue();

                //Include dayOfWeek
                int finalSlot = slot;
                LocalDate finalDate = date;
                List<Conversation> conversationFilter = conversationList
                        .stream()
                        .filter(
                                c -> (
                                        c.getDayOfWeek() != null)
                                        &&
                                        c.getDayOfWeek().contains(Integer.toString(dayOfWeek))
                                        &&
                                        c.getSlot() == finalSlot
                                        &&
                                        (!c.getStartDate().isAfter(finalDate) && !c.getEndDate().isBefore(finalDate))
                        ).toList();

                scheduleTmp.setConversationSlots(
                        scheduleTmp.getConversationSlots() == null ? new ArrayList<ConversationSlotDTO>() : scheduleTmp.getConversationSlots()
                );

                scheduleTmp.getConversationSlots().add(
                        ConversationSlotDTO.builder()
                                .slot(slot)
                                .conversationList(
                                        conversationFilter.stream()
                                                .map(
                                                        c -> ConversationResponseDTO
                                                                .builder()
                                                                .id(c.getId())
                                                                .status(c.getConversationStatus())
                                                                .title(c.getTitle())
                                                                .StudentName(c.getUser().getFullName())
                                                                .build()
                                                )
                                                .toList()
                                )
                                .build()
                );
            }


            result.add(scheduleTmp);
            date = date.plusDays(1);
        }

        return result;
    }
}
