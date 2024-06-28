package com.exe212.tutormind.service.service_implement;

import com.exe212.tutormind.entity.InvoiceUser;
import com.exe212.tutormind.entity.User;
import com.exe212.tutormind.exception.NotFoundException;
import com.exe212.tutormind.model.DTO.InvoiceUserDto;
import com.exe212.tutormind.model.projection.InvoiceUserProjection;
import com.exe212.tutormind.repository.InvoiceUserRepository;
import com.exe212.tutormind.repository.UserRepository;
import com.exe212.tutormind.service.service_interface.InvoiceUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class InvoiceUserServiceImpl implements InvoiceUserService {
    @Autowired
    InvoiceUserRepository invoiceUserRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public InvoiceUserProjection createInvoiceProjection(InvoiceUserDto invoiceUserDto) throws NotFoundException {
        User student = userRepository.findById(invoiceUserDto.getStudentId()).orElseThrow(NotFoundException::new);
        User tutor = userRepository.findById(invoiceUserDto.getTutorId()).orElseThrow(NotFoundException::new);
        InvoiceUser invoiceUser = InvoiceUser.builder()
                .price(invoiceUserDto.getPrice())
                .createdDate(LocalDateTime.now())
                .student(student)
                .tutor(tutor)
                .type(invoiceUserDto.getType())
                .build();
        InvoiceUser invoiceUserDB = invoiceUserRepository.saveAndFlush(invoiceUser);
        InvoiceUserProjection invoiceUserProjection = invoiceUserRepository.findProjectedById(invoiceUserDB.getId());
        return invoiceUserProjection;
    }

    @Override
    public Page<InvoiceUserProjection> getStudentInvoice(Integer studentId,Integer pageNo,Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize).withSort(Sort.by("createdDate").descending());
        Page<InvoiceUserProjection> invoiceUserProjections = invoiceUserRepository.findAllProjectedByStudent_Id(studentId,pageable);
        return invoiceUserProjections;
    }

    @Override
    public Page<InvoiceUserProjection> getTutorInvoice(Integer tutorId,Integer pageNo,Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize).withSort(Sort.by("createdDate").descending());
        Page<InvoiceUserProjection> invoiceUserProjections = invoiceUserRepository.findAllProjectedByTutor_Id(tutorId,pageable);
        return invoiceUserProjections;
    }
}
