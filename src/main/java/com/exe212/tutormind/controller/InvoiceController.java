package com.exe212.tutormind.controller;

import com.exe212.tutormind.entity.InvoiceUser;
import com.exe212.tutormind.exception.NotFoundException;
import com.exe212.tutormind.model.DTO.InvoiceUserDto;
import com.exe212.tutormind.model.projection.InvoiceUserProjection;
import com.exe212.tutormind.service.service_interface.InvoiceUserService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {
    @Autowired
    InvoiceUserService invoiceUserService;

    @PostMapping()
    public ResponseEntity<?> createInvoice(InvoiceUserDto invoiceUserDto) throws NotFoundException {
        InvoiceUserProjection invoiceUserProjection = invoiceUserService.createInvoiceProjection(invoiceUserDto);
        return ResponseEntity.ok(invoiceUserProjection);
    }
    @GetMapping("/student/{studentId}")
    public Page<InvoiceUserProjection> getPageableStudentInvoice(@PathVariable Integer studentId,
                                                                 @RequestParam(defaultValue = "0") Integer pageNo,
                                                                 @RequestParam(defaultValue = "8") Integer pageSize){
        return invoiceUserService.getStudentInvoice(studentId,pageNo,pageSize);
    }
    @GetMapping("/tutor/{tutorId}")
    public Page<InvoiceUserProjection> getPageableTutorInvoice(@PathVariable Integer tutorId,
                                                               @RequestParam(defaultValue = "0") Integer pageNo,
                                                               @RequestParam(defaultValue = "8") Integer pageSize){
        return invoiceUserService.getTutorInvoice(tutorId,pageNo,pageSize);
    }
}
