package com.exe212.tutormind.service.service_interface;

import org.apache.coyote.BadRequestException;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String uploadFiles(MultipartFile files) throws BadRequestException;
}