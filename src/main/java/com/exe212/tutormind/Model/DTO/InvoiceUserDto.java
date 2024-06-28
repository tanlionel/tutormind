package com.exe212.tutormind.model.DTO;

import com.exe212.tutormind.entity.User;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.exe212.tutormind.entity.InvoiceUser}
 */
@Value
public class InvoiceUserDto implements Serializable {
    String type;
    Integer price;
    Integer studentId;
    Integer tutorId;
}