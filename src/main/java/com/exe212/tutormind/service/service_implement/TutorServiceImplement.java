package com.exe212.tutormind.service.service_implement;

import com.exe212.tutormind.entity.User;
import com.exe212.tutormind.enums.UserRole;
import com.exe212.tutormind.model.users.UserDTO;
import com.exe212.tutormind.repository.ProfileRepository;
import com.exe212.tutormind.repository.UserRepository;
import com.exe212.tutormind.service.service_interface.TutorService;
import com.exe212.tutormind.service.service_interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorServiceImplement implements TutorService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProfileRepository profileRepository;



    @Override
    public Page<UserDTO> getPageableTutor(int pageIndex, int pageSize, String search) {

                //DEFAULT TUTOR ROLE IS 1
        Integer tutorRoleId = UserRole.TUTOR.ordinal() + 1;

        Page<UserDTO> tutorPage = profileRepository
                .findAll(
                        PageRequest.of(pageIndex, pageSize)
                ).map(
                                p -> UserDTO.builder()
                                        .id(p.getUser().getId())
                                        .email(p.getUser().getEmail())
                                        .phone(p.getUser().getPhone())
                                        .username(p.getUser().getUsername())
                                        .fullName(p.getUser().getFullName())
                                        .address(p.getUser().getAddress())
                                        .gender(p.getUser().getGender())
                                        .role(p.getUser().getRole())
                                        .build()
                        );

        return tutorPage;
    }

//    @Override
//    public Page<UserDTO> getPageableTutor(int pageIndex,
//                                          int pageSize,
//                                          String search) {
//        //DEFAULT TUTOR ROLE IS 1
//        Integer tutorRoleId = UserRole.TUTOR.ordinal() + 1;
//
//        Page<User> tutorPage = userRepository.findUserByRoleId(
//                PageRequest.of(pageIndex, pageSize),
//                search,
//                tutorRoleId
//        );
//
//        Page<UserDTO> result = tutorPage.map(
//                u -> UserDTO.builder()
//                        .id(u.getId())
//                        .email(u.getEmail())
//                        .username(u.getUsername())
//                        .address(u.getAddress())
//                        .role(u.getRole())
//                        .gender(u.getGender())
//                        .fullName(u.getFullName())
//                        .phone(u.getPhone())
//                        .build()
//        );
//
//        return result;
//    }
}
