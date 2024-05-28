package com.exe212.tutormind.controller;


import com.exe212.tutormind.entity.User;
import com.exe212.tutormind.exception.UserDoesNotExistException;
import com.exe212.tutormind.model.DTO.UpdateUserRequestDTO;
import com.exe212.tutormind.model.DTO.UserResponseDTO;
import com.exe212.tutormind.service.service_interface.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@CrossOrigin("*")
public class UserController {
    @Autowired
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<?> getUsersPageable(@RequestParam(required = false,defaultValue = "0") Integer pageNo,
                                              @RequestParam(required = false,defaultValue = "10") Integer pageSize,
                                              @RequestParam(required = false,defaultValue = "id") String sortField,
                                              @RequestParam(required = false,defaultValue = "asc") String sortOrder,
                                              @RequestParam(required = false,defaultValue = "") String search,
                                              @RequestParam(required = false) String roleName){
        return ResponseEntity.ok(userService.getUserPageable(pageNo,pageSize,sortField,sortOrder,roleName,search));
    }
    @PutMapping("/users")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequestDTO updateUserRequestDTO, @RequestParam String email) throws UserDoesNotExistException {
        User user= userService.updateUser(updateUserRequestDTO,email);
        return ResponseEntity.ok(UserResponseDTO.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .gender(user.getGender())
                        .roleName(user.getRole().getName())
                        .username(user.getUsername())
                        .fullName(user.getFullName())
                        .phone(user.getPhone())
                        .address(user.getAddress())
                        .build());
    }
}
