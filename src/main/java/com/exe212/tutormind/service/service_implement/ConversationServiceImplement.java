package com.exe212.tutormind.service.service_implement;

import com.exe212.tutormind.common.Common;
import com.exe212.tutormind.entity.Conversation;
import com.exe212.tutormind.entity.ConversationStatus;
import com.exe212.tutormind.exception.NotFoundException;
import com.exe212.tutormind.model.conversation.ConversationDTO;
import com.exe212.tutormind.repository.ConversationRepository;
import com.exe212.tutormind.service.service_interface.ConversationService;
import com.google.api.client.util.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.swing.text.DateFormatter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class ConversationServiceImplement implements ConversationService {
    @Autowired
    ConversationRepository conversationRepository;
    @Override
    public Page<ConversationDTO> getPaginationConversation(int pageIndex,
                                                           int pageSize,
                                                           String search,
                                                           String SortBy) {

        Page<ConversationDTO> page = conversationRepository.findAllByTeacherFullNameContaining(
                PageRequest.of(pageIndex, pageSize),
                search
        ).map( c ->
                ConversationDTO.builder()
                        .id(c.getId())
                        .title(c.getTitle())
                        .address(c.getAddress())
                        .user(c.getUser())
                        .teacher(c.getTeacher())
                        .contactNumber(c.getContactNumber())
                        .description(c.getDescription())
                        .conversationStatus(c.getConversationStatus())
                        .totalPrice(c.getTotalPrice())
                        .createdDate(c.getCreatedDate())
                        .updatedDate(c.getUpdatedDate())
                        .build()
        );

        return page;
    }

    private List<LocalDate> convertScheduleDateList(String schedule) {
        List<String> list = Arrays.stream(schedule.split("#")).toList();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Common.DATE_FORMATTER);

        return list.stream().map(
                s -> LocalDate.parse(s, formatter)
        ).toList();
    }

    private String generateSchedule(List<LocalDate> schedule) {
        List<String> list = schedule.stream().map(s -> s.toString()).toList();

        String result = String.join("#", list);

        return result;
    }

    @Override
    public ConversationDTO createOrUpdateConversation(ConversationDTO conversation) throws Exception{
        if (conversation.getId() != null) {

            if (!conversationRepository.findById(conversation.getId()).isPresent())
                throw new NotFoundException();
        }

        Conversation record =
                Conversation.builder()
                        .id(conversation.getId())
                        .title(conversation.getTitle())
                        .address(conversation.getAddress())
                        .user(conversation.getUser())
                        .teacher(conversation.getTeacher())
                        .contactNumber(conversation.getContactNumber())
                        .description(conversation.getDescription())
                        .conversationStatus(conversation.getConversationStatus())
                        .totalPrice(conversation.getTotalPrice())
                        .schedule(generateSchedule(conversation.getSchedule()))
                        .createdDate(LocalDateTime.now())
                        .updatedDate(LocalDateTime.now())
                        .build();

        Integer id = conversationRepository.save(record).getId();
        conversationRepository.flush();

        return getConversationById(id);
    }

    @Override
    public ConversationDTO updateConversationStatus(Integer id, Integer status) throws Exception{
        ConversationDTO conversation = getConversationById(id);

        //Assign new status
        conversation.setConversationStatus(
                ConversationStatus
                        .builder()
                        .id(status)
                        .build()
        );

        return createOrUpdateConversation(conversation);
    }

    @Override
    public ConversationDTO getConversationById(Integer id) throws Exception{
        ConversationDTO conversation = null;

        if (conversationRepository.findById(id).isPresent()) {
            Conversation c = conversationRepository.findById(id).get();

            conversation = ConversationDTO.builder()
                    .id(c.getId())
                    .title(c.getTitle())
                    .address(c.getAddress())
                    .user(c.getUser())
                    .teacher(c.getTeacher())
                    .contactNumber(c.getContactNumber())
                    .description(c.getDescription())
                    .conversationStatus(c.getConversationStatus())
                    .totalPrice(c.getTotalPrice())
                    .schedule(convertScheduleDateList(c.getSchedule()))
                    .createdDate(c.getCreatedDate())
                    .updatedDate(c.getUpdatedDate())
                    .build();
        }

        if (conversation == null)
            throw new NotFoundException();

        return conversation;
    }
}
