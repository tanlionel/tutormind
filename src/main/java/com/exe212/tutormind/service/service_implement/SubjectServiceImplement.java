package com.exe212.tutormind.service.service_implement;

import com.exe212.tutormind.entity.Subject;
import com.exe212.tutormind.model.subject.SubjectDTO;
import com.exe212.tutormind.repository.SubjectRepository;
import com.exe212.tutormind.service.service_interface.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImplement implements SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public List<SubjectDTO> getSubjectList(String search) {
        List<Subject> list = subjectRepository.findAllByNameContaining(search);

        List<SubjectDTO> result = list.stream()
                .map(
                        s -> SubjectDTO.builder()
                        .id(s.getId())
                        .subjectCategory(s.getSubjectCategory())
                        .name(s.getName())
                        .description(s.getDescription())
                        .build()
                ).toList();

        return result;
    }
}
