package com.exe212.tutormind.controller;


import com.exe212.tutormind.common.Common;
import com.exe212.tutormind.model.users.ProfileDTO;
import com.exe212.tutormind.service.service_interface.TutorService;
import io.swagger.models.auth.In;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tutors")
@AllArgsConstructor
@CrossOrigin("*")
public class TutorController {
    @Autowired
    TutorService tutorService;

    @PostMapping
    public ResponseEntity<?> getTutorList(@RequestParam(name = "pageIndex", defaultValue = "0") Integer pageIndex,
                                          @RequestParam(name = "pageSize", defaultValue = Common.DEFAULT_PAGE_SIZE) Integer pageSize,
                                          @RequestParam(name = "search", defaultValue = "") String search,
                                          @RequestBody(required = false) List<Integer> subjectIdList) {

        return ResponseEntity.ok(tutorService.getPageableTutor(pageIndex, pageSize, search, subjectIdList));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getTutorById(@PathVariable Integer id) throws Exception{
        return ResponseEntity.ok(
                tutorService.getTutorById(id)
        );
    }

    @PostMapping("{email}")
    public ResponseEntity<?> createOrUpdateTutorProfile(@PathVariable String email, ProfileDTO profileDto) throws Exception {
        return ResponseEntity.ok(
                tutorService.createOrUpdateTutorProfile(email, profileDto)
        );
    }

}
