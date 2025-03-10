package com.exe212.tutormind.service.service_interface;


import com.exe212.tutormind.entity.User;
import com.exe212.tutormind.exception.*;
import com.exe212.tutormind.model.DTO.RegisterRequestDTO;
import com.exe212.tutormind.model.DTO.UpdateUserRequestDTO;
import com.exe212.tutormind.model.DTO.UserResponseDTO;
import com.exe212.tutormind.model.users.UserUpdatePasswordDTO;
import org.springframework.data.domain.Page;

public interface UserService {
    User getUserByEmail(String Email) throws UserDoesNotExistException, InvalidateException;
    User getUserByUserName(String Username) throws UserDoesNotExistException, InvalidateException;
    User login(String email, String password) throws UserDoesNotExistException, AccountSuspendedException, InvalidateException;
    User getUserById(Integer id) throws Exception;
    User registerUser(RegisterRequestDTO registerUser) throws Exception;

    User getLoginUser();
    Page<UserResponseDTO> getUserPageable(Integer pageNo, Integer pageSize, String sortField, String sortOrder, String roleName, String search);
    User activateUser(String email) throws UserDoesNotExistException, UserAlreadyActiveException;
    User deactivateUser(String email) throws UserDoesNotExistException, UserAlreadyDeactivateException;
    User updateUser(UpdateUserRequestDTO updateUserRequestDTO, String email) throws UserDoesNotExistException;

    User updatePassword(String email, UserUpdatePasswordDTO tmp) throws Exception;
}
