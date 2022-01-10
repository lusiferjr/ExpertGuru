package com.application.expertguru.service;


import com.application.expertguru.DTO.UniversalResponseDTO;
import com.application.expertguru.DTO.UserDTO;
import com.application.expertguru.DTO.UserUpdateDTO;

public interface UserService {
    UniversalResponseDTO addUser(UserDTO userDTO);

    UniversalResponseDTO getUsersWithName(String username);

    UniversalResponseDTO getUserByEmailId(String emailId);

    UniversalResponseDTO deleteUser(String userId);

    UniversalResponseDTO updateUser(UserUpdateDTO userUpdate);
    Boolean validateUser(String userId);
}
