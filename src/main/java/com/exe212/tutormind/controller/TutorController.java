package com.exe212.tutormind.controller;


import com.exe212.tutormind.common.Common;
import com.exe212.tutormind.service.service_interface.TutorService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tutors")
@AllArgsConstructor
@CrossOrigin("*")
public class TutorController {
    @Autowired
    TutorService tutorService;

    @GetMapping
    public ResponseEntity<?> getTutorList(@RequestParam(name = "pageIndex", defaultValue = "0") Integer pageIndex,
                                          @RequestParam(name = "pageSize", defaultValue = Common.DEFAULT_PAGE_SIZE) Integer pageSize,
                                          @RequestParam(name = "search", defaultValue = "") String search) {

        return ResponseEntity.ok(tutorService.getPageableTutor(pageIndex, pageSize, search));
    }

}
