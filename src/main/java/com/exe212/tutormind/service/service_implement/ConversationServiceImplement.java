package com.exe212.tutormind.service.service_implement;

import com.exe212.tutormind.common.Common;
import com.exe212.tutormind.entity.Conversation;
import com.exe212.tutormind.entity.ConversationStatus;
import com.exe212.tutormind.entity.User;
import com.exe212.tutormind.exception.NotFoundException;
import com.exe212.tutormind.model.Mapper.ConversationMapper;
import com.exe212.tutormind.model.conversation.ConversationDTO;
import com.exe212.tutormind.repository.ConversationRepository;
import com.exe212.tutormind.service.service_interface.ConversationService;
import com.exe212.tutormind.service.service_interface.UserService;
import com.google.api.client.util.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.DateFormatter;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ConversationServiceImplement implements ConversationService {
    @Autowired
    ConversationRepository conversationRepository;
    @Autowired UserService userService;
    @Override
    public Page<ConversationDTO> getPaginationConversation(int pageIndex,
                                                           int pageSize,
                                                           String search,
                                                           String SortBy) {
        User loginUser = userService.getLoginUser();

        return conversationRepository.findAllByTeacherFullNameContainingAndTeacherIdOrUserId(
                PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Order.by("updatedDate")).descending()),
                search,
                loginUser.getId(),
                loginUser.getId()
        ).map(ConversationMapper::mapToConversationDTO);
    }

    @Override
    public ConversationDTO createOrUpdateConversation(ConversationDTO conversation) throws Exception{
        String remark = null;

        remark = conversation.getRemark();

        if (conversation.getId() != null) {
            Optional<Conversation> c = conversationRepository.findById(conversation.getId());

            if (!c.isPresent())
                throw new NotFoundException();

            remark = (remark == null) ? c.get().getRemark() : remark;
        }

        System.out.println("Check remark: " + remark);

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
                        .startDate(conversation.getDateFrom())
                        .endDate(conversation.getDateTo())
                        .slot(conversation.getSlot())
                        .remark(remark)
                        .dayOfWeek(Common.convertDayOfWeek(conversation.getDayOfWeek()))
                        .createdDate(LocalDateTime.now())
                        .updatedDate(LocalDateTime.now())
                        .build();

        Integer id = conversationRepository.save(record).getId();
        conversationRepository.flush();

        return getConversationById(id);
    }

    @Override
    public ConversationDTO updateConversationStatus(Integer id,
                                                    Integer status,
                                                    String remark) throws Exception {
        ConversationDTO conversation = getConversationById(id);

        //Assign new status
        conversation.setConversationStatus(
                ConversationStatus
                        .builder()
                        .id(status)
                        .build()
        );

        conversation.setRemark(remark);

        return createOrUpdateConversation(conversation);
    }

    @Override
    public ConversationDTO getConversationById(Integer id) throws Exception{
        ConversationDTO conversation = null;

        if (conversationRepository.findById(id).isPresent()) {
            Conversation c = conversationRepository.findById(id).get();

            conversation = ConversationMapper.mapToConversationDTO(c);
        }

        if (conversation == null)
            throw new NotFoundException();

        return conversation;
    }
}
