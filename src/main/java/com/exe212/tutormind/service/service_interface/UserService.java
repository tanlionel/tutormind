package com.exe212.tutormind.service.service_interface;

import com.exe212.tutormind.Model.DTO.RegisterRequestDTO;
import com.exe212.tutormind.entity.User;
import com.exe212.tutormind.exception.*;

public interface UserService {
    User getUserByEmail(String Email) throws UserDoesNotExistException, InvalidateException;
    User getUserByUserName(String Username) throws UserDoesNotExistException, InvalidateException;
    User login(String email, String password) throws UserDoesNotExistException, AccountSuspendedException, InvalidateException;

    User registerUser(RegisterRequestDTO registerUser) throws RoleDoesNotExistException, UserAlreadyExistsException;

    User getLoginUser();
}
