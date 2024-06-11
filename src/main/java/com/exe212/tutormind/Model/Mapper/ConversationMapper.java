package com.exe212.tutormind.model.Mapper;

import com.exe212.tutormind.common.Common;
import com.exe212.tutormind.entity.Conversation;
import com.exe212.tutormind.entity.User;
import com.exe212.tutormind.model.DTO.UserResponseDTO;
import com.exe212.tutormind.model.conversation.ConversationDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConversationMapper {
    public static ConversationDTO mapToConversationDTO(Conversation c){
            return  ConversationDTO.builder()
                    .id(c.getId())
                    .title(c.getTitle())
                    .address(c.getAddress())
                    .user(c.getUser())
                    .teacher(c.getTeacher())
                    .contactNumber(c.getContactNumber())
                    .description(c.getDescription())
                    .conversationStatus(c.getConversationStatus())
                    .totalPrice(c.getTotalPrice())
                    .dateFrom(c.getStartDate())
                    .dateTo(c.getEndDate())
                    .slot(c.getSlot())
                    .dayOfWeek(Common.generateDayOfWeekList(c.getDayOfWeek()))
                    .createdDate(c.getCreatedDate())
                    .updatedDate(c.getUpdatedDate())
                    .build();

        }
    }
