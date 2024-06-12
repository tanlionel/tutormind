package com.exe212.tutormind.controller;


import com.exe212.tutormind.entity.Course;
import com.exe212.tutormind.exception.NotFoundException;
import com.exe212.tutormind.model.DTO.CourseDTO;
import com.exe212.tutormind.model.DTO.CourseDetailDTO;
import com.exe212.tutormind.service.service_interface.CourseService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping()
    public ResponseEntity<?> getCourseList(@RequestParam(defaultValue = "0") Integer pageNo,
                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                           @RequestParam(defaultValue = "") String search){
        return ResponseEntity.ok(courseService.getCourseList(pageNo, pageSize, search));
    };

    @GetMapping("/tutor")
    public ResponseEntity<?> getCourseListTutor(@RequestParam(defaultValue = "0") Integer pageNo,
                                                @RequestParam(defaultValue = "10") Integer pageSize,
                                                @RequestParam(defaultValue = "") String search,
                                                @RequestParam Integer tutorId){
        return ResponseEntity.ok(courseService.getCourseListByTutor(pageNo,pageSize,search,tutorId));
    };

    @GetMapping("{courseId}")
    public CourseDetailDTO getCourseById(@PathVariable Integer courseId) throws NotFoundException {
        return courseService.getCourseByCourseId(courseId);
    }

    @PostMapping()
    public Course createCourse(@RequestBody CourseDTO course){
        return courseService.createCourse(course);
    }

    @PutMapping("{courseId}")
    public Course updateCourse(@PathVariable Integer courseId,@RequestBody CourseDTO course) throws NotFoundException {
        return courseService.updateCourse(courseId,course);
    }
}
