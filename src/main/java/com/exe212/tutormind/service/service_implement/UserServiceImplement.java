package com.exe212.tutormind.service.service_implement;

import com.exe212.tutormind.Model.DTO.RegisterRequestDTO;
import com.exe212.tutormind.entity.Role;
import com.exe212.tutormind.entity.User;
import com.exe212.tutormind.exception.*;
import com.exe212.tutormind.repository.RoleRepository;
import com.exe212.tutormind.repository.UserRepository;
import com.exe212.tutormind.service.service_interface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
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
    public User registerUser(RegisterRequestDTO registerUser) throws RoleDoesNotExistException, UserAlreadyExistsException {
        boolean isExistUser = userRepository.findByEmail(registerUser.getEmail()) != null;

        Optional<Role> existRole = roleRepository.findById(registerUser.getRoleId());
        if (existRole.isEmpty()) throw new RoleDoesNotExistException();
        Role role = existRole.get();
        if (isExistUser) throw new UserAlreadyExistsException();


        User user = User.builder()
                .email(registerUser.getEmail())
                .username(registerUser.getUsername())
                .password(passwordEncoder.encode(DEFAULT_PASSWORD))
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
        return user;
    }

    @Override
    public User getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()) {
            String username = authentication.getName();

            return userRepository.findByEmail(username);
        }

        return null;
    }
}
