package com.exe212.tutormind.service.service_implement;

import com.exe212.tutormind.Utils.DataBucketUtil;
import com.exe212.tutormind.service.service_interface.FileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Transactional
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {


    private final DataBucketUtil dataBucketUtil;


    @Override
    public String uploadFiles(MultipartFile files) throws BadRequestException {
        String originalFileName = files.getOriginalFilename();
        String url;
        if (originalFileName == null) {
            throw new BadRequestException("Original file name is null");
        }
        Path path = new File(originalFileName).toPath();

        try {
            String contentType = Files.probeContentType(path);
            url = dataBucketUtil.uploadFile(files, originalFileName, contentType);

        } catch (Exception e) {
            throw new RuntimeException();
        }

        return url;
    }
}