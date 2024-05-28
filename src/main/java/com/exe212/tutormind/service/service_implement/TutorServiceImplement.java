package com.exe212.tutormind.service.service_implement;

import com.exe212.tutormind.entity.Profile;
import com.exe212.tutormind.entity.User;
import com.exe212.tutormind.enums.UserRole;
import com.exe212.tutormind.exception.UserDoesNotExistException;
import com.exe212.tutormind.exception.UserDoesNotHavePermission;
import com.exe212.tutormind.model.users.ProfileDTO;
import com.exe212.tutormind.model.users.TutorDTO;
import com.exe212.tutormind.repository.ProfileRepository;
import com.exe212.tutormind.repository.UserRepository;
import com.exe212.tutormind.service.service_interface.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorServiceImplement implements TutorService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProfileRepository profileRepository;

    @Override
    public Page<TutorDTO> getPageableTutor(int pageIndex, int pageSize, String search) {

                //DEFAULT TUTOR ROLE IS 1
        Integer tutorRoleId = UserRole.TUTOR.ordinal() + 1;

        Page<TutorDTO> tutorPage = profileRepository
                .findAll(
                        PageRequest.of(pageIndex, pageSize)
                ).map(
                                p -> TutorDTO.builder()
                                        .id(p.getUser().getId())
                                        .email(p.getUser().getEmail())
                                        .phone(p.getUser().getPhone())
                                        .username(p.getUser().getUsername())
                                        .fullName(p.getUser().getFullName())
                                        .address(p.getUser().getAddress())
                                        .gender(p.getUser().getGender())
                                        .role(p.getUser().getRole())
                                        .personalIntroduction(p.getPersonalIntroduction())
                                        .personalInformation(p.getPersonalInformation())
                                        .ratingPoint((p.getRatingPoint() == null ? null : p.getRatingPoint().doubleValue()))
                                        .build()
                        );

        return tutorPage;
    }

    @Override
    public Page<TutorDTO> getPageableTutor(int pageIndex, int pageSize, String search, List<Integer> subjectIdList) {
        Integer tutorRoleId = UserRole.TUTOR.ordinal() + 1;

        if (subjectIdList == null || subjectIdList.isEmpty())
            subjectIdList = null;

        Page<TutorDTO> tutorPage = profileRepository
                .getProfilePagination(
                        PageRequest.of(pageIndex, pageSize), search, subjectIdList
                ).map(
                        p -> TutorDTO.builder()
                                .id(p.getUser().getId())
                                .email(p.getUser().getEmail())
                                .phone(p.getUser().getPhone())
                                .username(p.getUser().getUsername())
                                .fullName(p.getUser().getFullName())
                                .address(p.getUser().getAddress())
                                .gender(p.getUser().getGender())
                                .role(p.getUser().getRole())
                                .personalIntroduction(p.getPersonalIntroduction())
                                .personalInformation(p.getPersonalInformation())
                                .ratingPoint((p.getRatingPoint() == null ? null : p.getRatingPoint().doubleValue()))
                                .build()
                );

        return tutorPage;
    }

    @Override
    public TutorDTO getTutorById(Integer id) throws Exception {
        if (id == null)
            throw new UserDoesNotExistException();

        Profile profile = profileRepository.findById(id).get();

        TutorDTO tutor = TutorDTO.builder()
                .id(profile.getUser().getId())
                .email(profile.getUser().getEmail())
                .phone(profile.getUser().getPhone())
                .username(profile.getUser().getUsername())
                .fullName(profile.getUser().getFullName())
                .address(profile.getUser().getAddress())
                .gender(profile.getUser().getGender())
                .role(profile.getUser().getRole())
                .personalIntroduction(profile.getPersonalIntroduction())
                .personalInformation(profile.getPersonalInformation())
                .ratingPoint((profile.getRatingPoint() == null ? null : profile.getRatingPoint().doubleValue()))
                .build();

        return tutor;
    }

    @Override
    public TutorDTO createOrUpdateTutorProfile(String email, ProfileDTO profileDto) throws Exception {
        User user = userRepository.findByEmail(email);

        if (user == null || !user.getIsActive())
            throw new UserDoesNotExistException();

        System.out.println(user.getRole().getName());

        if (user.getRole() == null || user.getRole().getId() != UserRole.TUTOR.ordinal() + 1)
            throw new UserDoesNotHavePermission();

        Profile profile = profileRepository.findByUserId(user.getId());

        profile = (profile == null) ? new Profile() : profile;

        profile = Profile.builder()
                .id(profile.getId())
                .personalInformation(profileDto.getPersonalInformation())
                .personalIntroduction(profileDto.getPersonalIntroduction())
                .ratingPoint(profile.getRatingPoint())
                .user(profile.getUser())
                .build();

        profileRepository.saveAndFlush(profile);

        return getTutorById(user.getId());
    }

}
