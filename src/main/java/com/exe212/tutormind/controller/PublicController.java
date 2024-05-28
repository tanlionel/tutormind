package com.exe212.tutormind.controller;

import com.exe212.tutormind.service.service_interface.SubjectService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
@AllArgsConstructor
@CrossOrigin("*")
public class PublicController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping("/subjects")
    public ResponseEntity<?> getAllSubject(@RequestParam(name = "search", defaultValue = "") String search) {
        return ResponseEntity.ok(subjectService.getSubjectList(search));
    }

}
