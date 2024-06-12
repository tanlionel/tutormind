package com.exe212.tutormind.service.service_interface;

import com.exe212.tutormind.entity.Conversation;
import com.exe212.tutormind.model.conversation.ConversationDTO;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;

public interface ConversationService {

    public Page<ConversationDTO> getPaginationConversation(
            int pageIndex,
            int pageSize,
            String search,
            String sortBy
    );

    public ConversationDTO createOrUpdateConversation(ConversationDTO conversation) throws Exception;

    public ConversationDTO updateConversationStatus(Integer id,
                                                    Integer status,
                                                    String remark) throws Exception;

    public ConversationDTO getConversationById(Integer id) throws Exception;
}
