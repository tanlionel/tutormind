package com.exe212.tutormind.service.service_interface;

import com.exe212.tutormind.model.subject.SubjectDTO;

import java.util.List;

public interface SubjectService {
    public List<SubjectDTO> getSubjectList(String search);
}
