package com.exe212.tutormind.service.service_interface;

import com.exe212.tutormind.model.users.UserDTO;
import org.springframework.data.domain.Page;

public interface TutorService {
    Page<UserDTO> getPageableTutor(int pageIndex, int pageSize, String search);

}
