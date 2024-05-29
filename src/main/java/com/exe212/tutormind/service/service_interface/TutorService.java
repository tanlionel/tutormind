package com.exe212.tutormind.service.service_interface;

import com.exe212.tutormind.model.users.ProfileDTO;
import com.exe212.tutormind.model.users.UserDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TutorService {
    Page<UserDTO> getPageableTutor(int pageIndex, int pageSize, String search);

    Page<UserDTO> getPageableTutor(int pageIndex,
                                   int pageSize,
                                   String search,
                                   List<Integer> subjectIdList);

    UserDTO getTutorById(Integer id) throws Exception;

    UserDTO createOrUpdateTutorProfile(String email, ProfileDTO profile) throws Exception;
}
