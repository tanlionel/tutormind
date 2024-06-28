package com.exe212.tutormind.service.service_interface;

import com.exe212.tutormind.exception.NotFoundException;
import com.exe212.tutormind.model.DTO.InvoiceUserDto;
import com.exe212.tutormind.model.projection.InvoiceUserProjection;
import org.springframework.data.domain.Page;

public interface InvoiceUserService {
    InvoiceUserProjection createInvoiceProjection(InvoiceUserDto invoiceUserDto) throws NotFoundException;

    Page<InvoiceUserProjection> getStudentInvoice(Integer studentId,Integer pageNo,Integer pageSize);

    Page<InvoiceUserProjection> getTutorInvoice(Integer tutorId,Integer pageNo,Integer pageSize);
}
