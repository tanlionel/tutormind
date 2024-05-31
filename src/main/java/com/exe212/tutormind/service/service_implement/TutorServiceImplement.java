package com.exe212.tutormind.service.service_implement;

import com.exe212.tutormind.entity.Major;
import com.exe212.tutormind.entity.Profile;
import com.exe212.tutormind.entity.Subject;
import com.exe212.tutormind.entity.User;
import com.exe212.tutormind.enums.UserRole;
import com.exe212.tutormind.exception.UserDoesNotExistException;
import com.exe212.tutormind.exception.UserDoesNotHavePermission;
import com.exe212.tutormind.model.subject.SubjectDTO;
import com.exe212.tutormind.model.users.MajorDTO;
import com.exe212.tutormind.model.users.ProfileDTO;
import com.exe212.tutormind.model.users.UserDTO;
import com.exe212.tutormind.repository.MajorRepository;
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
    @Autowired
    MajorRepository majorRepository;
    @Override
    public Page<UserDTO> getPageableTutor(int pageIndex,
                                          int pageSize,
                                          String search,
                                          List<Integer> subjectIdList) {
        Integer tutorRoleId = UserRole.TUTOR.ordinal() + 1;

        if (subjectIdList == null || subjectIdList.isEmpty())
            subjectIdList = null;

        Page<UserDTO> tutorPage = profileRepository
                .getProfilePagination(
                        PageRequest.of(pageIndex, pageSize), search, subjectIdList
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
                                .profile(
                                        ProfileDTO.builder()
                                                .personalIntroduction(p.getPersonalIntroduction())
                                                .personalInformation(p.getPersonalInformation())
                                                .ratingPoint((p.getRatingPoint() == null ? null : p.getRatingPoint().doubleValue()))
                                                .majorList(
                                                        majorRepository.findAllByUserId(p.getUser().getId())
                                                                .stream().map(
                                                                        m -> MajorDTO.builder()
                                                                                .majorDescription(m.getDescription())
                                                                                .subject(
                                                                                        SubjectDTO.builder()
                                                                                                .id(m.getSubject().getId())
                                                                                                .name(m.getSubject().getName())
                                                                                                .subjectCategory(m.getSubject().getSubjectCategory())
                                                                                                .description(m.getSubject().getDescription())
                                                                                                .build()
                                                                                ).build()
                                                                ).toList()
                                                ).build()
                                ).build()
                );

        return tutorPage;
    }

    @Override
    public UserDTO getTutorById(Integer id) throws Exception {
        if (id == null)
            throw new UserDoesNotExistException();

        Profile profile = profileRepository.findByUserId(id);

        if (profile == null)
            throw new UserDoesNotExistException();

        UserDTO tutor = UserDTO.builder()
                .id(profile.getUser().getId())
                .email(profile.getUser().getEmail())
                .phone(profile.getUser().getPhone())
                .username(profile.getUser().getUsername())
                .fullName(profile.getUser().getFullName())
                .address(profile.getUser().getAddress())
                .gender(profile.getUser().getGender())
                .role(profile.getUser().getRole())
                .profile(
                        ProfileDTO.builder()
                                .personalIntroduction(profile.getPersonalIntroduction())
                                .personalInformation(profile.getPersonalInformation())
                                .ratingPoint((profile.getRatingPoint() == null ? null : profile.getRatingPoint().doubleValue()))
                                .majorList(
                                        majorRepository.findAllByUserId(profile.getUser().getId())
                                                .stream().map(
                                                        m -> MajorDTO.builder()
                                                                .majorDescription(m.getDescription())
                                                                .subject(
                                                                        SubjectDTO.builder()
                                                                                .id(m.getSubject().getId())
                                                                                .name(m.getSubject().getName())
                                                                                .subjectCategory(m.getSubject().getSubjectCategory())
                                                                                .description(m.getSubject().getDescription())
                                                                                .build()
                                                                ).build()
                                                ).toList()
                                )
                                .build()
                )
                .build();

        return tutor;
    }

    @Override
    public UserDTO createOrUpdateTutorProfile(String email, ProfileDTO profileDto) throws Exception {
        User tutor = userRepository.findByEmail(email);

        if (tutor == null || !tutor.getIsActive())
            throw new UserDoesNotExistException();

        System.out.println(tutor.getRole().getName());

        if (tutor.getRole() == null || tutor.getRole().getId() != UserRole.TUTOR.ordinal() + 1)
            throw new UserDoesNotHavePermission();

        Profile profile = profileRepository.findByUserId(tutor.getId());

        profile = (profile == null) ? new Profile() : profile;

        profile = Profile.builder()
                .id(profile.getId())
                .personalInformation(profileDto.getPersonalInformation())
                .personalIntroduction(profileDto.getPersonalIntroduction())
                .ratingPoint(profileDto.getRatingPoint())
                .user(profile.getUser())
                .build();

        profileRepository.saveAndFlush(profile);

        majorRepository.deleteAllByUserId(tutor.getId());

        if (profileDto.getMajorList() != null && !profileDto.getMajorList().isEmpty()) {
            List<Major> majorList = profileDto.getMajorList().stream().map(
                    m -> Major.builder()
                            .description(m.getMajorDescription())
                            .user(
                                    tutor
                            )
                            .subject(
                                    Subject.builder()
                                            .id(m.getSubject().getId())
                                            .build()
                            )
                            .build()
            ).toList();

            majorRepository.saveAllAndFlush(
                majorList
            );
        }

        return getTutorById(tutor.getId());
    }

}
