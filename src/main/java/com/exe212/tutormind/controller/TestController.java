package com.exe212.tutormind.controller;

import com.exe212.tutormind.common.Common;
import com.exe212.tutormind.model.users.ProfileDTO;
import com.exe212.tutormind.service.service_interface.TutorService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
@CrossOrigin
public class TestController {
    @Autowired
    TutorService tutorService;
    @PostMapping("/tutors")
    public ResponseEntity<?> getTutorList(@RequestParam(name = "pageIndex", defaultValue = "0") Integer pageIndex,
                                          @RequestParam(name = "pageSize", defaultValue = Common.DEFAULT_PAGE_SIZE) Integer pageSize,
                                          @RequestParam(name = "search", defaultValue = "") String search,
                                          @RequestBody(required = false) List<Integer> subjectIdList) {

        System.out.println(subjectIdList.size());
        return ResponseEntity.ok(tutorService.getPageableTutor(pageIndex, pageSize, search, subjectIdList));
    }

    @GetMapping("/tutors/{id}")
    public ResponseEntity<?> getTutorById(@PathVariable Integer id) throws Exception{
        return ResponseEntity.ok(
                tutorService.getTutorById(id)
        );
    }

    @PostMapping("/tutors/{email}")
    public ResponseEntity<?> createOrUpdateTutorProfile(@PathVariable String email, ProfileDTO profileDto) throws Exception {
        return ResponseEntity.ok(
                tutorService.createOrUpdateTutorProfile(email, profileDto)
        );
    }

}
