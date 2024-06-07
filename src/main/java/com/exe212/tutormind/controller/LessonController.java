package com.exe212.tutormind.controller;

import com.exe212.tutormind.entity.Lessons;
import com.exe212.tutormind.exception.NotFoundException;
import com.exe212.tutormind.model.DTO.CourseDetailDTO;
import com.exe212.tutormind.service.service_interface.LessonService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/lesson")
public class LessonController {
    @Autowired
    LessonService lessonService;

    @GetMapping("{id}")
    public ResponseEntity<Lessons> getLessonById(@PathVariable Integer id) throws BadRequestException {
        return ResponseEntity.ok(lessonService.getLessonById(id));
    }
    @PostMapping ("{courseId}")
    public CourseDetailDTO createLesson(@PathVariable Integer courseId,@RequestBody List<Lessons> lessonsList) throws NotFoundException {
        CourseDetailDTO courseDetailDTO = lessonService.createLesson(courseId,lessonsList);
        return courseDetailDTO;
    }
    @PutMapping("{courseId}")
    public CourseDetailDTO updateLesson(@PathVariable Integer courseId,@RequestBody List<Lessons> lessonsList) throws NotFoundException, BadRequestException {
        CourseDetailDTO courseDetailDTOUpdate = lessonService.updateLesson(courseId,lessonsList);
        return courseDetailDTOUpdate;
    }
}
