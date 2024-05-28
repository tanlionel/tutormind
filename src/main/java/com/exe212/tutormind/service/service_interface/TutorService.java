package com.exe212.tutormind.service.service_interface;

import com.exe212.tutormind.model.users.ProfileDTO;
import com.exe212.tutormind.model.users.TutorDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TutorService {
    Page<TutorDTO> getPageableTutor(int pageIndex, int pageSize, String search);

    Page<TutorDTO> getPageableTutor(int pageIndex,
                                    int pageSize,
                                    String search,
                                    List<Integer> subjectIdList);

    TutorDTO getTutorById(Integer id) throws Exception;

    TutorDTO createOrUpdateTutorProfile(String email, ProfileDTO profile) throws Exception;
}
