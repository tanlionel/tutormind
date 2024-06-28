package com.exe212.tutormind.model.projection;

import com.exe212.tutormind.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public interface InvoiceUserProjection {
    Integer getId();
    String getType();
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDateTime getCreatedDate();
    Integer getPrice();
    User getStudent();
    User getTutor();
}
