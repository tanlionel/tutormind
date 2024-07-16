package com.exe212.tutormind.service.service_implement;

import com.exe212.tutormind.entity.Role;
import com.exe212.tutormind.entity.User;
import com.exe212.tutormind.exception.*;
import com.exe212.tutormind.model.DTO.RegisterRequestDTO;
import com.exe212.tutormind.model.DTO.UpdateUserRequestDTO;
import com.exe212.tutormind.model.DTO.UserResponseDTO;
import com.exe212.tutormind.model.Mapper.UserMapper;
import com.exe212.tutormind.repository.RoleRepository;
import com.exe212.tutormind.repository.UserRepository;
import com.exe212.tutormind.service.service_interface.UserService;
import com.exe212.tutormind.service.service_interface.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@RequiredArgsConstructor
@Service

public class UserServiceImplement implements UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final WalletService walletService;
    private static final String ACTIVE = "Active";
    private static final String DEACTIVATE = "Deactivate";
    private static final String SORT_ASC = "asc";
    private static final String DEFAULT_PASSWORD = "123456";
    private static final int DEFAULT_PASSWORD_LENGTH = 7;


    @Override
    public User getUserByEmail(String Email) throws UserDoesNotExistException, InvalidateException {
        User user = userRepository.findByEmail(Email);
        if (user == null) {
            throw new UserDoesNotExistException();
        }
        return user;
    }
    @Override
    public User getUserByUserName(String Username) throws UserDoesNotExistException, InvalidateException {
        User user = userRepository.findUserByUsername(Username);
        if (user == null) {
            throw new UserDoesNotExistException();
        }
        return user;
    }

    @Override
    public User login(String email, String password) throws UserDoesNotExistException, AccountSuspendedException, InvalidateException {
        User loginUser = userRepository.findByEmail(email);
        if (loginUser == null) throw new UserDoesNotExistException();
        if (!loginUser.getIsActive()) throw new AccountSuspendedException();
        if (!passwordEncoder.matches(password, loginUser.getPassword())) {
            throw new InvalidateException();
        }

        return loginUser;
    }

    @Override
    public User getUserById(Integer id) throws Exception {
        User user = null;

        try {
            user = userRepository.findById(id).get();
        }
        catch (Exception e) {
            throw new UserDoesNotExistException();
        }

        return user;
    }

    @Override
    public User registerUser(RegisterRequestDTO registerUser) throws Exception {
        boolean isExistUser = userRepository.findByEmail(registerUser.getEmail()) != null;

        Optional<Role> existRole = roleRepository.findById(registerUser.getRoleId());
        if (existRole.isEmpty()) throw new RoleDoesNotExistException();
        Role role = existRole.get();
        if (isExistUser) throw new UserAlreadyExistsException();


        User user = User.builder()
                .email(registerUser.getEmail())
                .username(registerUser.getUsername())
                .password(passwordEncoder.encode(registerUser.getPassword()).trim())
                .fullName(registerUser.getFullName())
                .phone(registerUser.getPhone())
                .address(registerUser.getAddress())
                .gender(registerUser.getGender())
                .isActive(true)
                .createdDate(Instant.now())
                .updatedDate(Instant.now())
                .role(role)
                .build();

        User result = userRepository.save(user);
        walletService.createOrUpdateWalletBallance(result.getId(),0);

        return user;
    }

    @Override
    public User getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()) {
            String username = authentication.getName();

            return userRepository.findUserByUsername(username);
        }

        return null;
    }

    @Override
    public Page<UserResponseDTO> getUserPageable(Integer pageNo, Integer pageSize, String sortField, String sortOrder, String roleName, String search) {
        Pageable pageable;
        if (sortOrder.equals(SORT_ASC)) {
            pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortField).ascending());
        } else {
            pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortField).descending());
        }
        Page<User> userPageable;
        if (roleName == null || roleName.isEmpty()) roleName = "";
            userPageable = userRepository.findByRoleNameContainingAndFullNameContaining(roleName,search, pageable);
        return userPageable.map(UserMapper::mapToUserResponseDTO);
    }

    @Override
    public User activateUser(String email) throws UserDoesNotExistException, UserAlreadyActiveException {
        return null;
    }

    @Override
    public User deactivateUser(String email) throws UserDoesNotExistException, UserAlreadyDeactivateException {
        return null;
    }

    @Override
    public User updateUser(UpdateUserRequestDTO updateUserRequestDTO, String email) throws UserDoesNotExistException {
        User user=userRepository.findByEmail(email);
        if (user==null) throw new UserDoesNotExistException();
        if (updateUserRequestDTO.getGender()!=null) user.setGender(updateUserRequestDTO.getGender());
        if (updateUserRequestDTO.getFullName()!=null ) user.setFullName(updateUserRequestDTO.getFullName());
        if (updateUserRequestDTO.getAddress()!=null) user.setAddress(updateUserRequestDTO.getAddress());
        if (updateUserRequestDTO.getAvatar()!=null) user.setAvatar(updateUserRequestDTO.getAvatar());
        return userRepository.save(user);
    }
}
