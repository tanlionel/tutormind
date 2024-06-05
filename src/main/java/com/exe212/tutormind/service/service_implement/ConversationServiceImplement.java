package com.exe212.tutormind.service.service_implement;

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

import java.sql.Date;
import java.time.LocalDateTime;

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
                        .createdDate(c.getCreatedDate())
                        .updatedDate(c.getUpdatedDate())
                        .build()
        );

        return page;
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
                    .createdDate(c.getCreatedDate())
                    .updatedDate(c.getUpdatedDate())
                    .build();
        }

        if (conversation == null)
            throw new NotFoundException();

        return conversation;
    }
}
