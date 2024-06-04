package com.exe212.tutormind.controller;

import com.exe212.tutormind.common.Common;
import com.exe212.tutormind.model.conversation.ConversationDTO;
import com.exe212.tutormind.service.service_interface.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.server.ExportException;

@RestController
@RequestMapping("/api/conversation")
@RequiredArgsConstructor
@CrossOrigin
public class ConversationController {
    @Autowired
    ConversationService conversationService;

    @GetMapping
    public ResponseEntity<Page<ConversationDTO> > getPaginationConversation(
            @RequestParam(defaultValue = "0") Integer pageIndex,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = Common.SORT_ASC) String sortBy
    ) throws Exception {

        return ResponseEntity.ok(

               conversationService.getPaginationConversation(
                       pageIndex,
                       pageSize,
                       search,
                       sortBy
                       )

        );

    }

    @GetMapping("/{id}")
    public ResponseEntity<ConversationDTO> getConversationById(@PathVariable Integer id) throws Exception {

        return ResponseEntity.ok(
                conversationService.getConversationById(id)
        );

    }

    @PostMapping
    public ResponseEntity<ConversationDTO> createConversation(@RequestBody ConversationDTO conversation) throws Exception {

        return ResponseEntity.ok(
                conversationService.createOrUpdateConversation(conversation)
        );

    }

    @PutMapping("/{id}")
    public ResponseEntity<ConversationDTO> updateConversation(
            @PathVariable Integer id,
            @RequestBody ConversationDTO conversation
    ) throws Exception {
        conversation.setId(id);

        return ResponseEntity.ok(
                conversationService.createOrUpdateConversation(conversation)
        );
    }
}
